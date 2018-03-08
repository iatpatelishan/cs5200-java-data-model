package com.oneweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleDao extends BaseDao {
	
	public static RoleDao instance = null;

	public static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}

	private RoleDao() {
	}

	public int assignWebsiteRole(int developerId, int websiteId, int roleId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "INSERT into websiteRole (role, developer, website) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, roleId);
			statement.setInt(2, developerId);
			statement.setInt(3, websiteId);
			result = statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs != null && rs.next()) {
				    result = rs.getInt(1);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int assignPageRole(int developerId, int pageId, int roleId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "INSERT into pageRole (role, developer, page) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, roleId);
			statement.setInt(2, developerId);
			statement.setInt(3, pageId);
			result = statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs != null && rs.next()) {
				    result = rs.getInt(1);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE from websiteRole WHERE role=? AND developer=? AND page=?";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, roleId);
			statement.setInt(2, developerId);
			statement.setInt(3, websiteId);
			result = statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs != null && rs.next()) {
				    result = rs.getInt(1);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deletePageRole(int developerId, int pageId, int roleId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE from pageRole WHERE role=? AND developer=? AND page=?";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, roleId);
			statement.setInt(2, developerId);
			statement.setInt(3, pageId);
			result = statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs != null && rs.next()) {
				    result = rs.getInt(1);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int swapPageRoleOfDevelopers(int pageId, int developer1Id, int developer2Id) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "UPDATE pageRole pr SET developer = CASE developer WHEN (?) THEN (?) WHEN (?) THEN (?) END WHERE developer IN (?,?) AND page=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, developer1Id);
			statement.setInt(2, developer2Id);
			statement.setInt(3, developer2Id);
			statement.setInt(4, developer1Id);
			statement.setInt(5, developer1Id);
			statement.setInt(6, developer2Id);
			statement.setInt(7, pageId);
			result = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}