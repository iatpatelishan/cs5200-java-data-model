package com.oneweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oneweb.model.Developer;

public class DeveloperDao extends BaseDao {
	
	private WebsiteDao websiteDao = WebsiteDao.getInstance();
	
	public static DeveloperDao instance = null;

	public static DeveloperDao getInstance() {
		if (instance == null) {
			instance = new DeveloperDao();
		}
		return instance;
	}

	private DeveloperDao() {
	}

	public int createDeveloper(Developer developer) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "INSERT INTO `person`(`firstName`, `lastName`, `username`, `password`, `email`, `dob`) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, developer.getFirstName());
			statement.setString(2, developer.getLastName());
			statement.setString(3, developer.getUsername());
			statement.setString(4, developer.getPassword());
			statement.setString(5, developer.getEmail());
			statement.setDate(6, developer.getDateOfBirth());
			result = statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs != null && rs.next()) {
				    result = rs.getInt(1);
				}
			}
			statement.close();
			
			if(result!=-1) {
				int result2 = -1;
				String sql2 = "INSERT INTO developer (id,developerKey) VALUES (?,?)";
				PreparedStatement statement2 = connection.prepareStatement(sql2);
				statement2.setInt(1, result);
				statement2.setString(2, developer.getDeveloperKey());
				result2 = statement2.executeUpdate();
				if(result2==-1) {
					throw new SQLException("Data inserted in person table but not in developer table");
				}
			}
			else {
				throw new SQLException("Data not inserted in person table");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Developer> findAllDevelopers() {
		List<Developer> developers = new ArrayList<Developer>();
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM developer d, person p WHERE d.id = p.id";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				developers.add(createDeveloperFromResultSet(results));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return developers;
	}

	public Developer findDeveloperById(int developerId) {
		Developer developer = null;
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM developer d, person p WHERE d.id = p.id AND d.id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, developerId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				developer = createDeveloperFromResultSet(results);
			}
			results.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return developer;
	}

	public Developer findDeveloperByUsername(String username) {
		Developer developer = null;
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM developer d, person p WHERE d.id = p.id AND p.username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				developer = createDeveloperFromResultSet(results);
			}
			results.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return developer;
	}

	public Developer findDeveloperByCredentials(String username, String password) {
		Developer developer = null;
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM developer d, person p WHERE d.id = p.id AND p.username = ? AND p.password = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				developer = createDeveloperFromResultSet(results);
			}
			results.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return developer;
	}

	public int updateDeveloper(int developerId, Developer developer) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "UPDATE developer d, person p SET firstName=?, lastName=?, username=?, password=?, email=?, dob=?, developerKey=? WHERE p.id=? AND p.id=d.id";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, developer.getFirstName());
			statement.setString(2, developer.getLastName());
			statement.setString(3, developer.getUsername());
			statement.setString(4, developer.getPassword());
			statement.setString(5, developer.getEmail());
			statement.setDate(6, developer.getDateOfBirth());
			statement.setInt(7, developerId);
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

	public int deleteDeveloper(int developerId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE FROM person WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, developerId);
			result = statement.executeUpdate();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Extra  
	public int updateDeveloperPrimaryPhone(int developerId, String phone) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "UPDATE phone SET `primary` = CASE WHEN (phone=?) THEN 1 ELSE 0 END WHERE person=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, phone);
			statement.setInt(2, developerId);
			result = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteDeveloperPrimaryAddress(int developerId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE FROM `address` WHERE `person`=? AND `primary`=TRUE";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, developerId);
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
	
	private Developer createDeveloperFromResultSet(ResultSet results) throws SQLException {
		int id = results.getInt("id");	
		Developer developer = new Developer();
		developer.setId(id);
		developer.setFirstName(results.getString("firstName"));
		developer.setLastName(results.getString("lastName"));
		developer.setUsername(results.getString("username"));
		developer.setPassword(results.getString("password"));
		developer.setEmail(results.getString("email"));
		developer.setDateOfBirth(results.getDate("dob"));
		developer.setDeveloperKey(results.getString("developerKey"));
		developer.setWebsites(websiteDao.findWebsitesForDeveloper(id));
		return developer;
	}
	
}
