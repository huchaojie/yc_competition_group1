package com.yc.projects.bikemanage.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
@ComponentScan(basePackages = "com.yc")
@EnableTransactionManagement
public class AppConfig {

	/*
	 * @Bean public RedisTemplate redisTemplate() { JedisConnectionFactory conn =
	 * new JedisConnectionFactory(); conn.setDatabase(0);
	 * conn.setHostName("localhost"); conn.setPort(6379); conn.setPassword("");
	 * conn.setUsePool(true); conn.afterPropertiesSet(); RedisTemplate<byte[],
	 * byte[]> redisTemplate = new RedisTemplate<>();
	 * redisTemplate.setConnectionFactory(conn); return redisTemplate; }
	 * 
	 * @Bean public StringRedisTemplate stringRedisTemplate() {
	 * JedisConnectionFactory conn = new JedisConnectionFactory();
	 * conn.setDatabase(0); conn.setHostName("localhost"); conn.setPort(6379);
	 * conn.setPassword(""); conn.setUsePool(true); conn.afterPropertiesSet();
	 * StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
	 * stringRedisTemplate.setConnectionFactory(conn);
	 * stringRedisTemplate.afterPropertiesSet(); return stringRedisTemplate;
	 * 
	 * }
	 */	
	@Bean // 键[字符串]: 值[对象]
	public RedisTemplate redsiTemplate(JedisConnectionFactory conn) {
		RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
		template.setConnectionFactory(conn);
		template.afterPropertiesSet();
		return template;
	}

	@Bean // 键[字符串]: 值[字符串]
	public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory conn) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(conn);
		template.afterPropertiesSet();
		return template;
	}

	@Bean // MongoTemplate由spring 托管
	@Primary
	public MongoTemplate template() {
		return new MongoTemplate(factory());
	}

	/**
	 * 功能描述: 创建数据库名称对应的工厂，数据库名称可以通过配置文件导入
	 * 
	 * @param
	 * @return:org.springframework.data.mongodb.MongoDbFactory
	 * @since: v1.0
	 */
	@Bean("mongoDbFactory")
	public MongoDbFactory factory() {
		return new SimpleMongoDbFactory(client(), "yc74ibike");
	}

	/**
	 * 功能描述: 配置client，client中传入的ip和端口可以通过配置文件读入
	 *
	 * @param
	 * @return:com.mongodb.MongoClient
	 */
	@Bean("mongoClient")
	public MongoClient client() {
		List<ServerAddress> list = new ArrayList<ServerAddress>();
		ServerAddress sa1 = new ServerAddress("192.168.213.201", 23000);
		ServerAddress sa2 = new ServerAddress("192.168.213.202", 23000);
		ServerAddress sa3 = new ServerAddress("192.168.213.203", 23000);
		list.add(sa1);
		list.add(sa2);
		list.add(sa3);

		return new MongoClient(list);
		// return new MongoClient("192.168.0.200", 27017);
	}

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
		dataSource.setUrl("jdbc:hive2://node3:10000/yc74ibike");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	@Autowired
	public DataSourceTransactionManager tx(DriverManagerDataSource ds) {
		DataSourceTransactionManager dtm = new DataSourceTransactionManager();
		dtm.setDataSource(ds);
		return dtm;
	}

}
