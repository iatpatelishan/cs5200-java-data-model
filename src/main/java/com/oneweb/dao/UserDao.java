package com.oneweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.oneweb.model.User;

public class UserDao extends BaseDao {
	
	public static UserDao instance = null;

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	private UserDao() {
	}

	public int createUser(User user) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "INSERT INTO person (firstName, lastName, username, password, email, dob) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getEmail());
			statement.setDate(6, user.getDateOfBirth());
			result = statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs != null && rs.next()) {
				    result = rs.getInt(1);
				}
			}
			statement.close();
			
			if(result!=-1) {
				int result2 = -1;
				String sql2 = "INSERT INTO user (id,userKey,userAgreement) VALUES (?,?,?)";
				PreparedStatement statement2 = connection.prepareStatement(sql2);
				statement2.setInt(1, result);
				statement2.setString(2, user.getUserKey());
				statement2.setBoolean(3, user.isUserAgreement());
				result2 = statement2.executeUpdate();
				if(result2==-1) {
					throw new SQLException("Data inserted in person table but not in user table");
				}
			}
			else {
				throw new SQLException("Data not inserted in person table");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}