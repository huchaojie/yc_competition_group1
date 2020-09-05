package com.yc.ibike.analysis.util

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.common.serialization.StringDeserializer

object ConfUtil {
  //解析配置文件
  private lazy val config: Config = ConfigFactory.load()
  val topic = config.getString("kafka.topic")
  val groupId: String = config.getString("kafka.group.id")
  val redisHost: String = config.getString("redis.host")
  val selectDBIndex = config.getInt("redis.db.index")
  val broker: String = config.getString("kafka.broker.list")

  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> broker,
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> groupId,
    "auto.offset.reset" -> "earliest",
    "enable.auto.commit" -> "true"
  )
}
