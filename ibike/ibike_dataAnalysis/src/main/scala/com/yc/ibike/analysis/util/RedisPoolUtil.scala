package com.yc.ibike.analysis.util


import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import redis.clients.jedis.{HostAndPort, JedisCluster, JedisPool}

object RedisPoolUtil {

  private val poolConfig = new GenericObjectPoolConfig()
  poolConfig.setMaxIdle(5) //最大的空闲连接数，连接池中最大的空闲连接数，默认是8
  poolConfig.setMaxTotal(2000) //只支持最大的连接数，连接池中最大的连接数，默认是8

  //连接池是私有的不能对外公开访问
  private lazy val jedisPool = new JedisPool(poolConfig, ConfUtil.redisHost)   //单节点的redis使用

  //redis集群
  val hosts = ConfUtil.redisHost.split(",")
  val jedisClusterNodes = new java.util.HashSet[HostAndPort]()
  for (host <- hosts) {
    jedisClusterNodes.add(new HostAndPort(host, 6379))
  }
  private lazy val jedisCluster = new JedisCluster(jedisClusterNodes)

  def getJedis() = {
    //jedisCluster.select(ConfUtil.selectDBIndex)
    jedisCluster

    //以下是单机redis带联接池
    // val jedis = jedisPool.getResource
    //jedis.select(ConfUtil.selectDBIndex)
    //jedis
  }
}