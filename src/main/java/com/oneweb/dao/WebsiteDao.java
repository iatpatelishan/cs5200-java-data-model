package com.oneweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oneweb.model.Website;

public class WebsiteDao extends BaseDao {

	public static WebsiteDao instance = null;

	public static WebsiteDao getInstance() {
		if (instance == null) {
			instance = new WebsiteDao();
		}
		return instance;
	}

	private WebsiteDao() {
	}
	
	public int createWebsiteForDeveloper(int developerId, Website website) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "INSERT into website (name, description, created, updated, visits, developer) VALUES (?,?,curdate(),curdate(),?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, website.getName());
			statement.setString(2, website.getDescription());
			statement.setInt(3, website.getVisits());
			statement.setInt(4, developerId);
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

	public List<Website> findWebsitesForDeveloper(int id) {
		List<Website> websites = new ArrayList<Website>();
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM `website` WHERE `developer`=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				websites.add(createWebsiteFromResultSet(results));
			}
			results.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return websites;
	}
	
	public List<Website> findAllWebsites() {
		List<Website> websites = new ArrayList<Website>();
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM website";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				websites.add(createWebsiteFromResultSet(results));
			}
			results.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return websites;
	}
	
	public Website findWebsiteById(int websiteId) {
		Website website = null;
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM website WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, websiteId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				website = createWebsiteFromResultSet(results);
			}
			results.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return website;
	}

	public int updateWebsite(int websiteId, Website website) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "UPDATE website SET name=?, description=?, updated=curdate(), visits=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, website.getName());
			statement.setString(2, website.getDescription());
			statement.setInt(3, website.getVisits());
			statement.setInt(4, websiteId);
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
	
	public int deleteWebsite(int websiteId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE FROM website WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, websiteId);
			result = statement.executeUpdate();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// HELPER
	
	private Website createWebsiteFromResultSet(ResultSet results) throws SQLException {
		int wbid = results.getInt("id");
		Website website = new Website();
		website.setId(wbid);
		website.setName(results.getString("name"));
		website.setDescription(results.getString("description"));
		website.setCreated(results.getDate("created"));
		website.setUpdated(results.getDate("updated"));
		website.setVisits(results.getInt("visits"));
		website.setPages(PageDao.getInstance().findPagesForWebsite(wbid));
		return website;
	}

}