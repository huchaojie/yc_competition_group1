package com.yc.projects.bikemanage;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.projects.bikemanage.bean.AccessLog;
import com.yc.projects.bikemanage.bean.Admin;
import com.yc.projects.bikemanage.bean.Bike;
import com.yc.projects.bikemanage.bean.QueryObject;
import com.yc.projects.bikemanage.bean.User;
import com.yc.projects.bikemanage.config.AppConfig;
import com.yc.projects.bikemanage.dao.AccessLogAnalysisDao;
import com.yc.projects.bikemanage.service.AccessLogAnalysisService;
import com.yc.projects.bikemanage.service.AdminManageService;
import com.yc.projects.bikemanage.service.BikeManageService;
import com.yc.projects.bikemanage.service.UserManageService;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class AppTest extends TestCase {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private MongoTemplate mongoTemplate;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private BikeManageService bikeManageService;
	@Autowired
	private UserManageService userManageService;
	@Autowired
	private AdminManageService adminManageService;
	@Autowired
	private AccessLogAnalysisDao accessLogAnalysisDao;
	@Autowired
	private AccessLogAnalysisService accessLogAnalysisService;

	@Test
	public void testAddAdmin() {
		Admin admin=new Admin();
		admin.setName("肖理达");
		admin.setPassword("123456");
		adminManageService.addAdmin(admin);
	}
	@Test
	public void testFindPVUV() {
		String[] pvuv = accessLogAnalysisService.findPVUV();
		String string = Arrays.deepToString(pvuv);
		System.out.println(string);
	}

	@Test
	public void testFindAccessLog() {
		List<AccessLog> list = accessLogAnalysisDao.findAccessLog(1, 10);
		System.out.println(list);
	}

	@Test
	public void testFindCount() {
		AccessLog accessLog = new AccessLog();
		// accessLog.setRemoteIp("");
		// accessLog.setRequestmethod("");
		accessLog.setRequesturl("");
		List<QueryObject> list = accessLogAnalysisDao.findCount(null);
		System.out.println(list);
	}

	@Test
	public void testLogin() {
		Admin admin = new Admin();
		admin.setName("肖理达");
		admin.setPassword("123456");
		boolean b = adminManageService.login(admin);
		System.out.println(b);
	}

	@Test
	public void testSearchAdmin() {
		Admin admin = new Admin();
		admin.setName("肖理达");
		admin.setPassword("123456");
		Object list = adminManageService.searchAdmin(admin, 1, 20).get("list");
		System.out.println(list);
	}

	@Test
	public void test2() {
		Random r = new Random();
		for (int i = 1; i <= 10000; i++) {
			User user = new User();
			user.setStatus(r.nextInt(3) + 1);
			user.setOpenId(r.nextInt(1000000000) + 10000 + "");
			if (user.getStatus() == 3) {
				user.setIdNum(r.nextInt(1000000000) + 10000 + "");
				user.setName("肖" + i);
			}
			user.setPhoneNum(r.nextInt(1000000000) + 10000 + "");
			user.setBalance(r.nextDouble() * 1000);
			if (user.getStatus() >= 2) {
				user.setDeposit(299.0);
			}
			userManageService.addUser(user);
		}
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setOpenId("o1FsG5pbE-d5K8qzZ77mO2KW99Ok");
		user.setName("肖理达");
		boolean b = userManageService.updateUser(user);
		System.out.println(b);
	}

	@Test
	public void testSearchUser() {
		User user = new User();
		user.setName("今");
		Object list = userManageService.searchUser(user, 1, 20).get("list");
		System.out.println(list);
	}

	@Test
	public void testaddUser() {
		User user = new User();
		user.setName("肖理达");
		user.setPhoneNum("123456");
		user.setStatus(3);
		user.setIdNum("1111");
		userManageService.addUser(user);
	}

	@Test
	public void testupdateBike() {
		Bike bike = new Bike();
		bike.setBid("5ef81b7cf3837f44dcd03285");
		bike.setStatus(3);
		boolean b = bikeManageService.updateBike(bike);
		System.out.println(b);
	}

	@Test
	public void testSearchBike() {
		Bike bike = new Bike();
		bike.setBid("5ef81b7cf3837f44dcd03285");
		bike.setStatus(3);
		Map<String, Object> map = bikeManageService.searchBike(bike, 1, 20);
		System.out.println(map.get("list"));

	}

	@Test // 准备测试数据
	public void test1() {
		double x = 28.2043941;
		double y = 112.959854;
		for (int i = 0; i < 100; i++) {
			x += 0.0000010;
			for (int j = 0; j < 100; j++) {
				y += 0.0000003;
				Double loc[] = new Double[] { Double.valueOf(x), Double.valueOf(y) };
				Bike b = new Bike();
				b.setStatus(1);
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
	public void testDataSource() throws SQLException {
		JdbcTemplate template = new JdbcTemplate(dataSource);
	}

}
