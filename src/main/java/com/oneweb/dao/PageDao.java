package com.oneweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oneweb.model.Page;

public class PageDao extends BaseDao {
	
	public static PageDao instance = null;

	public static PageDao getInstance() {
		if (instance == null) {
			instance = new PageDao();
		}
		return instance;
	}

	private PageDao() {
	}

	public int createPageForWebsite(int websiteId, Page page) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "INSERT into page (title, description, created, updated, views, website) VALUES (?,?,curdate(),curdate(),?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getDescription());
			statement.setInt(3, page.getViews());
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
	
	public List<Page> findAllPages(){
		List<Page> pages = new ArrayList<Page>();
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM pages";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				pages.add(createPageFromResultSet(results));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pages;
	}

	public Page findPageById(int pageId) {
		Page page = null;
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM page WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, pageId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				page = createPageFromResultSet(results);
			}
			results.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}
	
	public List<Page> findPagesForWebsite(int websiteId){
		List<Page> pages = new ArrayList<Page>();
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM page WHERE website = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, websiteId);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				pages.add(createPageFromResultSet(results));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pages;
	}
	
	public int updatePage(int pageId, Page page) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "UPDATE page SET title=?, description=?, updated=curdate(), views=? WHERE id=? ";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getDescription());
			statement.setInt(3, page.getViews());
			statement.setInt(4, pageId);
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
	
	public int deletePage(int pageId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE FROM page WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, pageId);
			result = statement.executeUpdate();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getLastUpdatedPageId(int websiteId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "SELECT p.id FROM page p, website w WHERE p.website=w.id AND w.id = ? AND p.updated= ( SELECT temp.latestdate FROM ( SELECT max(page.updated) AS latestdate FROM `page`) AS temp)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, websiteId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				result=results.getInt("id");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// HELPER
	
	private Page createPageFromResultSet(ResultSet results) throws SQLException {
		Page page = new Page();
		int pageId = results.getInt("id");
		page.setId(pageId);
		page.setTitle(results.getString("title"));
		page.setDescription(results.getString("description"));
		page.setCreated(results.getDate("created"));
		page.setUpdated(results.getDate("updated"));
		page.setViews(results.getInt("views"));
		page.setWidgets(WidgetDao.getInstance().findWidgetsForPage(pageId));
		return page;
	}
}