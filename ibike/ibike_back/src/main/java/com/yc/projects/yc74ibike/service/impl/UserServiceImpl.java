package com.yc.projects.yc74ibike.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.mongodb.client.result.UpdateResult;
import com.yc.projects.yc74ibike.bean.User;
import com.yc.projects.yc74ibike.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	Logger logger = Logger.getLogger(UserServiceImpl.class);

	// 操作redis中的v是对象类型的数据
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	MongoTemplate mongoTemplate;

	// 操作redis中的字符串类型数据
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void genVerifyCode(String nationCode, String phoneNum) throws Exception {
		String code = (int) ((Math.random() * 9 + 1) * 1000) + "";
		logger.info("生成的验证码为：" + code);
		// 调用短信接口发送短信
		// SmsUtils.sendSms(code, new String[] { nationCode + phoneNum });
		stringRedisTemplate.opsForValue().set(phoneNum, code, 120, TimeUnit.SECONDS);

	}

	@Override
	public boolean verify(User user) {
		boolean flag = false;
		String phoneNum = user.getPhoneNum();
		String verifyCode = user.getVerifyCode();
		String code = stringRedisTemplate.opsForValue().get(phoneNum);
		String openId = user.getOpenId();
		String uuid = user.getUuid();
		 System.out.println(user);
		if (verifyCode != null && verifyCode.equals(code)) {
			int status = 1;
			UpdateResult result = mongoTemplate.updateFirst(new Query(Criteria.where("openId").is(openId)),
					new Update().set("status", status).set("phoneNum", phoneNum), User.class);
			if (result.getModifiedCount() == 1) {
				return true;
			} else {
				return false;
			}
		}
		return flag;
	}

	@Override
	public boolean deposit(User user) {
		int status = 2;
		int money = 299;
		UpdateResult result = mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())),
				new Update().set("status", status).set("deposit", money), User.class);
		if (result.getModifiedCount() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean identity(User user) {
		// TODO:调用第三方实名认证接口
		int status = 3;
		UpdateResult result = mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())),
				new Update().set("status", status).set("name", user.getName()).set("idNum", user.getIdNum()),
				User.class);
		if (result.getModifiedCount() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean recharge(double balance, String phoneNum) {
		boolean flag = true;
		try {
			UpdateResult result = mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(phoneNum)),
					new Update().inc("balance", balance), User.class);
			System.out.println(result.getModifiedCount());
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<User> selectMember(String openid) {
		Query q = new Query(Criteria.where("openId").is(openid));
		return this.mongoTemplate.find(q, User.class, "user");
	}

	@Override
	public void addMember(User u) {
		mongoTemplate.insert(u);
	}

	@Override
	public String redisSessionKey(String openId, String sessionKey) {
		String rsession = UUID.randomUUID().toString();
		// (3) 首先根据openId，取出来之前存的openId对应的sessionKey的值。
		String oldSeesionKey = stringRedisTemplate.opsForValue().get(openId);
		if (oldSeesionKey != null && !"".equals(oldSeesionKey)) {
			logger.info("oldSeesionKey==" + oldSeesionKey);
			// (4) 删除之前openId对应的缓存
			stringRedisTemplate.delete(oldSeesionKey);
			logger.info("老的openId删除以后==" + stringRedisTemplate.opsForValue().get(oldSeesionKey));
		}
		// (5) 开始缓存新的sessionKey： 格式: { uuid:{ "openId":openId,"sessionKey":sessionKey }
		// }
		Gson g = new Gson();
		Map<String, String> m = new HashMap<String, String>();
		m.put("openId", openId);
		m.put("sessionKey", sessionKey);
		String s = g.toJson(m);
		// stringRedisTemplate.opsForValue().set(rsession, s, 30*24*60*60,
		// TimeUnit.SECONDS);
		stringRedisTemplate.opsForValue().set(rsession, s, 5 * 60, TimeUnit.SECONDS);

		// (6) 开始缓存新的openId与session对应关系 ： {openId: rsession}
		// stringRedisTemplate.opsForValue().set(openId, rsession, 30*24*60*60,
		// TimeUnit.SECONDS);
		stringRedisTemplate.opsForValue().set(openId, rsession, 5 * 60, TimeUnit.SECONDS);
		return rsession;
	}

}
