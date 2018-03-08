package com.oneweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class BaseDao {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-spring2018-patel.cni5dmmnohd1.us-east-2.rds.amazonaws.com:3306/hw3_patel_ishan_spring_2018";
	private static final String USERNAME = "ishan";
	private static final String PASSWORD = "cs5200-spring2018-patel";

	static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static int truncateDatabase() {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sqlqueries = "SET FOREIGN_KEY_CHECKS = 0; TRUNCATE `address`; TRUNCATE `developer`; TRUNCATE `page`; TRUNCATE `pagePriviledge`; TRUNCATE `pageRole`; TRUNCATE `person`; TRUNCATE `phone`; TRUNCATE `user`; TRUNCATE `website`; TRUNCATE `websitePriviledge`; TRUNCATE `websiteRole`; TRUNCATE `widget`; SET FOREIGN_KEY_CHECKS = 1;";
			List<String> sqlquerylist = Arrays.asList(sqlqueries.split("\\s*;\\s*"));
			for(String sql : sqlquerylist) {
				PreparedStatement statement = connection.prepareStatement(sql);
				result = statement.executeUpdate();
				statement.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
