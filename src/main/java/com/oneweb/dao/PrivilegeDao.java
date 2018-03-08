package com.oneweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrivilegeDao extends BaseDao {
	
	public static PrivilegeDao instance = null;

	public static PrivilegeDao getInstance() {
		if (instance == null) {
			instance = new PrivilegeDao();
		}
		return instance;
	}

	private PrivilegeDao() {
	}

	public int assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "INSERT into websitePrivilege (privilege, developer, website) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, priviledgeId);
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
	
	public int assignPagePriviledge(int developerId, int pageId, int priviledgeId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "INSERT into pagePrivilege (privilege, developer, website) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, priviledgeId);
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
	
	public int deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE from websitePrivilege WHERE role=? AND developer=? AND page=?";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, priviledgeId);
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

	public int deletePagePriviledge(int developerId, int pageId, int priviledgeId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE from pagePrivilege WHERE role=? AND developer=? AND page=?";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, priviledgeId);
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
	
}