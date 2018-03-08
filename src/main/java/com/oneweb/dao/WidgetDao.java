package com.oneweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oneweb.model.HeadingWidget;
import com.oneweb.model.HtmlWidget;
import com.oneweb.model.ImageWidget;
import com.oneweb.model.Widget;
import com.oneweb.model.WidgetType;
import com.oneweb.model.YoutubeWidget;

public class WidgetDao extends BaseDao {
	
	public static WidgetDao instance = null;

	public static WidgetDao getInstance() {
		if (instance == null) {
			instance = new WidgetDao();
		}
		return instance;
	}

	private WidgetDao() {
	}

	public int createWidgetForPage(int pageId, Widget widget) {
		int result = -1;
		String sql = null;
		try(Connection connection = getConnection()) {
			PreparedStatement statement = null;
			if(widget instanceof HeadingWidget) {
				sql = "INSERT into widget(name,width,height,cssClass,cssStyle,text,`order`,type,page,size) VALUES (?,?,?,?,?,?,?,?,?,?)";
				statement = prepareCreateUpdateWidgetStatement(connection, sql, widget);
				statement.setString(8, widget.getType().getDbValue());
				statement.setInt(9, pageId);
				statement.setInt(10, ((HeadingWidget) widget).getSize());
			}
			else if(widget instanceof HtmlWidget) {
				sql = "INSERT into widget(name,width,height,cssClass,cssStyle,text,`order`,type,page,html) VALUES (?,?,?,?,?,?,?,?,?,?)";
				statement = prepareCreateUpdateWidgetStatement(connection, sql, widget);
				statement.setString(8, widget.getType().getDbValue());
				statement.setInt(9, pageId);
				statement.setString(10, ((HtmlWidget) widget).getHtml());
			}
			else if(widget instanceof ImageWidget) {
				sql = "INSERT into widget(name,width,height,cssClass,cssStyle,text,`order`,type,page,src) VALUES (?,?,?,?,?,?,?,?,?,?)";
				statement = prepareCreateUpdateWidgetStatement(connection, sql, widget);
				statement.setString(8, widget.getType().getDbValue());
				statement.setInt(9, pageId);
				statement.setString(10, ((ImageWidget) widget).getSrc());
			}
			else if(widget instanceof YoutubeWidget) {
				sql = "INSERT into widget(name,width,height,cssClass,cssStyle,text,`order`,type,page,url,shareable,expandable) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				statement = prepareCreateUpdateWidgetStatement(connection, sql, widget);
				statement.setString(8, widget.getType().getDbValue());
				statement.setInt(9, pageId);
				statement.setString(10, ((YoutubeWidget) widget).getUrl());
				statement.setBoolean(11, ((YoutubeWidget) widget).getShareable());
				statement.setBoolean(12, ((YoutubeWidget) widget).getExpandable());
			}
			
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
	
	public List<Widget> findAllWidgets(){
		List<Widget> widgets = new ArrayList<Widget>();
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM widget";
			PreparedStatement statement = connection.prepareStatement(sql);
			try(ResultSet results = statement.executeQuery()){
				while (results.next()) {
					widgets.add(createWidgetFromResultSet(results));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return widgets;
	}

	public Widget findWidgetById(int widgetId) {
		Widget widget = null;
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM widget WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, widgetId);
			try(ResultSet results = statement.executeQuery()){
				if(results.next()) {
					widget = createWidgetFromResultSet(results);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return widget;
	}

	public List<Widget> findWidgetsForPage(int pageId){
		List<Widget> widgets = new ArrayList<Widget>();
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM widget WHERE page = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, pageId);
			try(ResultSet results = statement.executeQuery()) {
				while (results.next()) {
					widgets.add(createWidgetFromResultSet(results));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return widgets;
	}
	
	public int updateWidget(int widgetId, Widget widget) {
		int result = -1;
		int oldOrder = findWidgetById(widgetId).getOrder();
		int newOrder = widget.getOrder();
		int widgetPageId = findWidgetsPageIdByWidgetId(widgetId);
		
		// Updates Order relatively
		String sql1= "UPDATE widget SET `order`= CASE WHEN `id`= ? THEN ?  WHEN (? > ? AND `order`<= ? AND `order`>= ? AND `id` != ?)  THEN `order`+ 1 WHEN (? < ? AND `order`> ? AND `order`<= ? AND `id` != ?) THEN `order`- 1  ELSE `order` END WHERE `page`= ?";
		// Updates Other fields of widget
		String sql2 = "UPDATE widget SET name=?,width=?,height=?,cssClass=?,cssStyle=?,text=?";
		
		try(Connection connection = getConnection()) {
			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setInt(1, widgetId);
			statement1.setInt(2, newOrder);
			statement1.setInt(3, oldOrder);
			statement1.setInt(4, newOrder);
			statement1.setInt(5, oldOrder);
			statement1.setInt(6, newOrder);
			statement1.setInt(7, widgetId);
			statement1.setInt(8, oldOrder);
			statement1.setInt(9, newOrder);
			statement1.setInt(10, oldOrder);
			statement1.setInt(11, newOrder);
			statement1.setInt(12, widgetId);
			statement1.setInt(13, widgetPageId);
			statement1.executeUpdate();
			
			PreparedStatement statement2 = null;
			if(widget instanceof HeadingWidget) {
				sql2=sql2+",size=? WHERE id=?";
				statement2 = prepareCreateUpdateWidgetStatement(connection, sql2, widget);
				statement2.setInt(7, ((HeadingWidget) widget).getSize());
				statement2.setInt(8, widgetId);
			}
			else if(widget instanceof HtmlWidget) {
				sql2=sql2+",html=? WHERE id=?";
				statement2 = prepareCreateUpdateWidgetStatement(connection, sql2, widget);
				statement2.setString(7, ((HtmlWidget) widget).getHtml());
				statement2.setInt(8, widgetId);
			}
			else if(widget instanceof ImageWidget) {
				sql2=sql2+",src=? WHERE id=?";
				statement2 = prepareCreateUpdateWidgetStatement(connection, sql2, widget);
				statement2.setString(7, ((ImageWidget) widget).getSrc());
				statement2.setInt(8, widgetId);
			}
			else if(widget instanceof YoutubeWidget) {
				sql2=sql2+",url=?,sharable=?,expandable=? WHERE id=?";
				statement2 = prepareCreateUpdateWidgetStatement(connection, sql2, widget);
				statement2.setString(7, ((YoutubeWidget) widget).getUrl());
				statement2.setBoolean(8, ((YoutubeWidget) widget).getShareable());
				statement2.setBoolean(9, ((YoutubeWidget) widget).getExpandable());
				statement2.setInt(10, widgetId);
			}
			statement2.executeUpdate();
			try (ResultSet rs2 = statement2.getGeneratedKeys()) {
				if (rs2 != null && rs2.next()) {
				    result = rs2.getInt(1);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private int findWidgetsPageIdByWidgetId(int widgetId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "SELECT * FROM widget WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, widgetId);
			try(ResultSet results = statement.executeQuery()) {
				while (results.next()) {
					result=results.getInt("page");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteWidget(int widgetId) {
		int result = -1;
		try(Connection connection = getConnection()) {
			String sql = "DELETE FROM widget WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, widgetId);
			result = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Helper functions
	
	private PreparedStatement prepareCreateUpdateWidgetStatement(Connection connection, String sql, Widget widget) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, widget.getName());
		statement.setInt(2, widget.getWidth());
		statement.setInt(3, widget.getHeight());
		statement.setString(4, widget.getCssClass());
		statement.setString(5, widget.getCssStyle());
		statement.setString(6, widget.getText());
		statement.setInt(7, widget.getOrder());
		return statement;
	}
	
	private Widget createWidgetFromResultSet(ResultSet results) throws SQLException {
		Widget widget = null;
		int id=results.getInt("id");
		String name = results.getString("name");
		int width = results.getInt("width");
		int height = results.getInt("height");
		String cssClass = results.getString("cssClass");
		String cssStyle = results.getString("cssStyle");
		String text = results.getString("text");
		int order = results.getInt("order");
		WidgetType type = WidgetType.get(results.getString("type"));
		String url = results.getString("url");
		boolean shareable = results.getBoolean("shareable");
		boolean expandable = results.getBoolean("expandable");
		String src = results.getString("src");
		int size = results.getInt("size");
		String html = results.getString("html");
		
		if(type == WidgetType.HEADING) {
			widget = new HeadingWidget(id,name,width,height,cssClass,cssStyle,text,order,size);
		} else if(type == WidgetType.HTML){
			widget = new HtmlWidget(id,name,width,height,cssClass,cssStyle,text,order,html);
		} else if(type == WidgetType.IMAGE) {
			widget = new ImageWidget(id,name,width,height,cssClass,cssStyle,text,order,src);
		} else if(type == WidgetType.YOUTUBE) {
			widget = new YoutubeWidget(id,name,width,height,cssClass,cssStyle,text,order,url,shareable,expandable);
		}
		return widget;
	}

}