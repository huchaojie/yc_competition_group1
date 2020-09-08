package com.yc.projects.bike;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.projects.yc74ibike.bean.Bike;
import com.yc.projects.yc74ibike.bean.User;
import com.yc.projects.yc74ibike.config.AppConfig;
import com.yc.projects.yc74ibike.dao.BikeDao;
import com.yc.projects.yc74ibike.dao.UserDao;
import com.yc.projects.yc74ibike.service.BikeService;
import com.yc.projects.yc74ibike.service.UserService;
import com.yc.projects.yc74ibike.service.impl.BikeServiceImpl;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class AppTest extends TestCase {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private BikeDao bikeDao;
	@Autowired
	private BikeService bikeService;
	@Autowired
	private MongoTemplate mongoTemplate;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;

	@Test
	public void testFindUser() {
		User user = new User();
		user.setName("肖");
		System.out.println(userDao.findUser(user));
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setOpenId("123123");
		user.setIdNum("666");
		user.setDeposit(299.0);
		user.setBalance(1000.0);
		user.setPhoneNum("123456");
		boolean b = userDao.updateUser(user);
		System.out.println(b);
	}

	@Test // 插入用户 mysql
	public void testAddUSer() {
		User user = new User();
		user.setOpenId("123123");
		user.setIdNum("99999");
		user.setName("肖理达");
		user.setStatus(2);
		boolean b = userDao.addUser(user);
		System.out.println(b);
	}

	@Test // 准备测试数据
	public void test1() {
		Random r = new Random();
		double x = 28.2043941;
		double y = 112.959854;
		for (int i = 0; i < 100; i++) {
			x += 0.0000010;
			for (int j = 0; j < 100; j++) {
				y += 0.0000003;
				Double loc[] = new Double[] { Double.valueOf(x), Double.valueOf(y) };
				Bike b = new Bike();
				b.setStatus(r.nextInt(4));
				b.setLoc(loc);
				b.setQrcode("");
				mongoTemplate.insert(b);
			}
		}
	}

	@Test
	public void testMongoTemplate() {
		System.out.println(mongoTemplate.getDb().getName());
		System.out.println(mongoTemplate.getCollectionNames());
	}

	@Test
	public void testRedisTemplateCluster() {
		stringRedisTemplate.opsForValue().set("hello", "world");
		stringRedisTemplate.opsForValue().set("hello2", "world");
	}

	@Test
	public void checkVerifyCode() {
		String key = "17673163336";
		String code = stringRedisTemplate.opsForValue().get(key);
		System.out.println(code);
	}

	@Test
	public void testRedisTemplate() {
		System.out.println(redisTemplate);
	}

	@Test
	public void testgenVerifyCode() throws Exception {
		userService.genVerifyCode("86", "17673163336");
	}

	@Test
	public void testNearBikes() {
		Bike bike = new Bike();
		bike.setLatitude(28.189122);
		bike.setLongitude(112.943867);
		bike.setStatus(1);
		List<Bike> findNearAll = bikeService.findNearAll(bike);
		System.out.println(findNearAll);
	}

	@Test
	public void testDataSource() throws SQLException {
		assertNotNull(dataSource);
		assertNotNull(dataSource.getConnection());
	}

	@Test
	public void testAddNewBike() {
		Bike b = new Bike();
		Bike result = bikeDao.addBike(b);
		assertNotNull(result);
		System.out.println(result.getBid());
	}

	@Test
	public void testUpdateBike() {
		Bike b = bikeDao.findBike("1");
		b.setLatitude(20.9);
		b.setLongitude(22.2);
		b.setStatus(2);
		bikeDao.updateBike(b);
	}

	@Test
	public void testServiceOpen() {
		Bike b = bikeService.findByBid("2");
		bikeService.open(b);
	}

	@Test
	public void testServiceAddNewBike() {
		Bike b = new Bike();
		Bike result = bikeService.addNewBike(b);
		System.out.println(result.getQrcode());
	}
}
