package com.yc.projects.yc74ibike.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.projects.yc74ibike.bean.User;
import com.yc.projects.yc74ibike.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addUser(User user) {
		if (user.getOpenId() != null) {
			String sql = "insert into user values(?,?,?,?,?,?,?,?)";
			int update = jdbcTemplate.update(sql, new Object[] { user.getOpenId(), user.getName(), user.getIdNum(),
					user.getDeposit(), user.getBalance(), user.getPhoneNum(), user.getUuid(), user.getStatus() });
			if (update > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		if (user.getOpenId() != null) {
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			int i = 0;
			sb.append("update user set ");
			if (user.getBalance() != null) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("balance=?");
				params.add(user.getBalance());
				i++;
			}
			if (user.getDeposit() != null) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("deposit=?");
				params.add(user.getDeposit());
				i++;
			}
			if (user.getIdNum() != null) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("idNum=?");
				params.add(user.getIdNum());
				i++;
			}
			if (user.getName() != null) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("name=?");
				params.add(user.getName());
				i++;
			}
			if (user.getStatus() != null) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("status=?");
				params.add(user.getStatus());
				i++;
			}
			if (user.getUuid() != null) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("uuid=?");
				params.add(user.getUuid());
				i++;
			}
			if (user.getPhoneNum() != null) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("phoneNum=?");
				params.add(user.getPhoneNum());
				i++;
			}
			sb.append(" where openId=? ");
			params.add(user.getOpenId());
			int update = jdbcTemplate.update(sb.toString(), params.toArray());
			if (update > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<User> findUser(User user) {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<>();
		sb.append("select * from user where 1=1 ");
		if (user.getBalance() != null) {
			sb.append(" and balance=? ");
			params.add(user.getBalance());
		}
		if (user.getDeposit() != null) {
			sb.append(" and deposit=? ");
			params.add(user.getDeposit());
		}
		if (user.getIdNum() != null) {
			sb.append(" and idNum like ? ");
			params.add("%" + user.getIdNum() + "%");
		}
		if (user.getName() != null) {
			sb.append(" and name like ? ");
			params.add("%" + user.getName() + "%");
		}
		if (user.getOpenId() != null) {
			sb.append(" and openId=? ");
			params.add(user.getOpenId());
		}
		if (user.getPhoneNum() != null) {
			sb.append(" and phoneNum=? ");
			params.add(user.getPhoneNum());
		}
		if (user.getStatus() != null) {
			sb.append(" and status=? ");
			params.add(user.getStatus());
		}
		if (user.getUuid() != null) {
			sb.append(" and uuid=? ");
			params.add(user.getUuid());
		}

		return jdbcTemplate.query(sb.toString(), params.toArray(), new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setBalance(rs.getDouble("balance"));
				user.setDeposit(rs.getDouble("deposit"));
				user.setIdNum(rs.getString("idNum"));
				user.setName(rs.getString("name"));
				user.setOpenId(rs.getString("openId"));
				user.setPhoneNum(rs.getString("phoneNum"));
				user.setStatus(rs.getInt("status"));
				user.setUuid(rs.getString("uuid"));
				return user;
			}
		});
	}

}
