package com.naresh.demounittesting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.naresh.demounittesting.model.User;

@Repository
public class UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User login(User userObj) {
		System.out.println("UserDAO->login");
		String sql = "select id,name,email from users where email = ? and password = ?";
		Object[] params = { userObj.getEmail(), userObj.getPassword() };
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				return toRow(rs);
			});
		} catch (DataAccessException e) {
			
		}
		return user;
	}

	public List<User> list() {
		String sql = "select id,name,email from users";
		Object[] params = {};
		List<User> user = jdbcTemplate.query(sql, params, (rs, rowNum) -> {
			return toRow(rs);
		});
		return user;
	}

	private User toRow(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setName(rs.getString("name"));
		u.setEmail(rs.getString("email"));
		return u;
	}
}
