package com.yc.projects.bikemanage.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.projects.bikemanage.bean.AccessLog;
import com.yc.projects.bikemanage.bean.QueryObject;
import com.yc.projects.bikemanage.dao.AccessLogAnalysisDao;

@Repository
public class AccessLogAnalysisDaoImpl implements AccessLogAnalysisDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier(value = "jdbchive")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<AccessLog> findAccessLog(int pageNum, int pageSize) {
		String sql = "select * from (select row_number() over (order by remoteIp) as rnum,accesslog.* from accesslog) t where rnum between "
				+ (pageNum - 1) * pageSize + " and " + pageNum * pageSize;
		List<AccessLog> list = jdbcTemplate.query(sql, new RowMapper<AccessLog>() {
			@Override
			public AccessLog mapRow(ResultSet rs, int rowNum) throws SQLException {
				AccessLog al = new AccessLog();
				al.setBrowsername(rs.getString("browsername"));
				al.setContentBytes(rs.getInt("contentBytes"));
				al.setHandleTime(rs.getInt("handleTime"));
				al.setLoginRemoteName(rs.getString("loginRemoteName"));
				al.setRefer(rs.getString("refer"));
				al.setRemoteIp(rs.getString("remoteIp"));
				al.setRequestmethod(rs.getString("requestmethod"));
				al.setRequestprotocol(rs.getString("requestprotocol"));
				al.setRequesturl(rs.getString("requesturl"));
				al.setResponseCode(rs.getString("responseCode"));
				al.setTimestamps(rs.getLong("timestamps"));
				al.setAuthrizedName(rs.getString("authrizedName"));
				return al;
			}
		});
		return list;
	}

	@Override
	public List<QueryObject> findCount(AccessLog accessLog) {

		StringBuffer sb = new StringBuffer("select ");
		if (accessLog == null) {
			sb.append("count(*) as count from accesslog ");
		} else if (accessLog.getRequestmethod() != null) {
			sb.append("  requestmethod as data,count(*) as count from accesslog group by requestmethod sort by count desc");
		} else if (accessLog.getRemoteIp() != null) {
			sb.append(" remoteip as data,count(*) as count from accesslog group by remoteip sort by count desc");
		} else if (accessLog.getRequesturl() != null) {
			sb.append(" requesturl as data,count(*) as count from accesslog group by requesturl sort by count desc");
		}
		List<QueryObject> list = jdbcTemplate.query(sb.toString(), new RowMapper<QueryObject>() {

			@Override
			public QueryObject mapRow(ResultSet rs, int rowNum) throws SQLException {
				QueryObject object = new QueryObject();
				if (accessLog != null) {
					object.setData(rs.getString("data"));
				}
				object.setCount(rs.getInt("count") + 0L);
				return object;
			}
		});
		return list;
	}

}
