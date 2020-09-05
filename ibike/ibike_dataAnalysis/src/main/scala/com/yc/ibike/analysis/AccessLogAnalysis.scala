package com.yc.ibike.analysis

import com.yc.ibike.analysis.util.ConfUtil
import com.yc.ibike.analysis.util.RedisPoolUtil.getJedis
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf}
import redis.clients.jedis.JedisCluster

object AccessLogAnalysis {
  /**
   * String: 聚合的key
   * Seq[Int]:当前批次阁下生批次该单词在每一个分区出现的次数
   * Option:初始值或累加的中间结果
   */
  val updateFunc = (iter: Iterator[(String, Seq[Int], Option[Int])]) => { //   ("a",[1,1,1,1,1],  5   )
    //方案一:  当成元组元素来操作
    //iter.map(  t=>(t._1,t._2.sum+t._3.getOrElse(0)))
    iter.map { case (x, y, z) => (x, y.sum + z.getOrElse(0)) }
  }

  val updateFunc2 = (iter: Iterator[(String, Seq[Int], Option[Int])]) => { //   ("a",[1,1,1,1,1],  5   )
    //方案一:  当成元组元素来操作
    //iter.map(  t=>(t._1,t._2.sum+t._3.getOrElse(0)))
    iter.map { case (x, y, z) => (x, 1) }
  }


  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR) //配置日志
    val conf = new SparkConf().setAppName("AccessLogAnalysis").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(5))

    //  ssc.cache()
    //状态要更新的话，要将中间结果保存下来，
    ssc.checkpoint("./chpoint") //   也可以是hdfs

    //    val kafkaParams = Map[String, Object](
    //      "bootstrap.servers" -> "node1:9092,node2:9092,node3:9092",
    //      "key.deserializer" -> classOf[StringDeserializer],
    //      "value.deserializer" -> classOf[StringDeserializer],
    //      "group.id" -> "accesslogAnalysis",
    //      "auto.offset.reset" -> "latest",
    //      "enable.auto.commit" -> (true: java.lang.Boolean)
    //    )
    val topics = Array(ConfUtil.topic)
    val stream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, ConfUtil.kafkaParams) //订阅一组主题，以获取消息
    )
    //需求1： 计算总  PV
    //   在流中的每一个元素都是一个 ConsumerRecord
    //stream.map(record => (record.key, record.value))
    val lines: DStream[String] = stream.map(record => ("a")) //我们这里只需要值的部分
    //切分压平
    //val words:DStream[String]=lines.flatMap( _.split("\t"))
    val wordAndOne: DStream[(String, Int)] = lines.map((_, 1)) //    ("a",1)
    //聚合
    val reduced = wordAndOne.updateStateByKey(updateFunc, new HashPartitioner(ssc.sparkContext.defaultMinPartitions), true)
    //    (a,10)   (a,5)  ->       (a,15)
    //  reduced：DStream   ->  RDD  ->  record
    reduced.foreachRDD { rdd =>
      rdd.foreachPartition { partitionOfRecords =>
        val jedisCluster: JedisCluster = getJedis()
        partitionOfRecords.foreach(tp => {
          jedisCluster.set("accesslog_analysis_total_pv", tp._2 + "");
        })
      }
    }

    //需求2： 计算总的UV
    val remoteIp = stream.map(x => (x.value().split("\t")(0), 1))
    val ipAndOne = remoteIp.reduceByKey((x, y) => 1)
    val reducedIp = ipAndOne.updateStateByKey(updateFunc2, new HashPartitioner(ssc.sparkContext.defaultMinPartitions), true)
    reducedIp.count().foreachRDD { rdd =>
      rdd.foreachPartition { partitionOfRecords =>
        val jedisCluster: JedisCluster = getJedis()
        partitionOfRecords.foreach(tp => {
          jedisCluster.set("accesslog_analysis_total_uv", tp + "");
        })

      }
    }


    //启动sparkstreaming程序
    ssc.start()
    //优雅退出
    ssc.awaitTermination()
  }
}