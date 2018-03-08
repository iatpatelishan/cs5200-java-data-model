package com.oneweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.oneweb.dao.*;
import com.oneweb.model.*;

public class Hw_jdbc_patel_ishan {
	
	private static final DeveloperDao developerDao = DeveloperDao.getInstance();
	private static final WebsiteDao websiteDao = WebsiteDao.getInstance();
	private static final PageDao pageDao = PageDao.getInstance();
	private static final WidgetDao widgetDao = WidgetDao.getInstance();
	private static final RoleDao roleDao = RoleDao.getInstance();
	// private static final PrivilegeDao privilegeDao = PrivilegeDao.getInstance();
	private static final UserDao userDao = UserDao.getInstance();
	
	public static void main(String[] args) throws IOException {
		System.out.println("Main method execution started!");
		
		// Truncate Database
		BaseDao.truncateDatabase();
		System.out.println("Database Truncated");
		
		/////////////////////////////////////////////////////////////////////////////////////////
		//                                                                                     //
		// Exercise Your Data Model and DAOs                                                   //
		//                                                                                     //
		/////////////////////////////////////////////////////////////////////////////////////////
		
		// Q-1 | Create the following developers and users. 
		//       Insert into the correct tables depending on the type

		Developer alice = new Developer("Alice","Wonder","alice","alice","alice@wonder.com","4321rewq");
		alice.setId(developerDao.createDeveloper(alice));
		
		Developer bob = new Developer("Bob","Marley","bob","bob","bob@marley.com","5432trew");
		bob.setId(developerDao.createDeveloper(bob));
		
		Developer charlie = new Developer("Charles","Garcia","charlie","charlie","chuch@garcia.com","6543ytre");
		charlie.setId(developerDao.createDeveloper(charlie));
		
		User dan = new User("Dan","Martin","dan","dan","dan@martin.com","7654fda");
		dan.setId(userDao.createUser(dan));
		
		User ed = new User("Ed","Karaz","ed","ed","ed@kar.com","5678dfgh");
		ed.setId(userDao.createUser(ed));
		
		System.out.println("Developers and Users Created!");
		
		
		// Q-2 | Create the following web sites for the developers above. 
		//       For both the created field and updated field, use the date your assignment will be
		//       graded, e.g., do not hardcode it
		
		Website facebook = new Website("Facebook", "an online social media and social networking service", 1234234);
		facebook = websiteDao.findWebsiteById(websiteDao.createWebsiteForDeveloper(alice.getId(),facebook));
		
		Website twitter = new Website("Twitter", "an online news and social networking service", 4321543);
		twitter = websiteDao.findWebsiteById(websiteDao.createWebsiteForDeveloper(bob.getId(),twitter));
		
		Website wikipedia = new Website("Wikipedia", "a free online encyclopedia", 3456654);
		wikipedia = websiteDao.findWebsiteById(websiteDao.createWebsiteForDeveloper(charlie.getId(),wikipedia));
		
		Website cnn = new Website("CNN", "an American basic cable and satellite television news channel", 6543345);
		cnn = websiteDao.findWebsiteById(websiteDao.createWebsiteForDeveloper(alice.getId(),cnn));
		
		Website cnet = new Website("CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", 5433455);
		cnet =  websiteDao.findWebsiteById(websiteDao.createWebsiteForDeveloper(bob.getId(),cnet));
		
		Website gizmodo = new Website("Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", 4322345);
		gizmodo = websiteDao.findWebsiteById(websiteDao.createWebsiteForDeveloper(charlie.getId(),gizmodo));
		
		System.out.println("Websites Created!");
		
		roleDao.assignWebsiteRole(bob.getId(), facebook.getId(), Role.EDITOR.getDbValue());
		roleDao.assignWebsiteRole(charlie.getId(), facebook.getId(), Role.ADMIN.getDbValue());
		
		roleDao.assignWebsiteRole(charlie.getId(), twitter.getId(), Role.EDITOR.getDbValue());
		roleDao.assignWebsiteRole(alice.getId(), twitter.getId(), Role.ADMIN.getDbValue());
		
		roleDao.assignWebsiteRole(alice.getId(), wikipedia.getId(), Role.EDITOR.getDbValue());
		roleDao.assignWebsiteRole(bob.getId(), wikipedia.getId(), Role.ADMIN.getDbValue());
		
		roleDao.assignWebsiteRole(bob.getId(), cnn.getId(), Role.EDITOR.getDbValue());
		roleDao.assignWebsiteRole(charlie.getId(), cnn.getId(), Role.ADMIN.getDbValue());
		
		roleDao.assignWebsiteRole(charlie.getId(), cnet.getId(), Role.EDITOR.getDbValue());
		roleDao.assignWebsiteRole(alice.getId(), cnet.getId(), Role.ADMIN.getDbValue());
		
		roleDao.assignWebsiteRole(alice.getId(), gizmodo.getId(), Role.EDITOR.getDbValue());
		roleDao.assignWebsiteRole(bob.getId(), gizmodo.getId(), Role.ADMIN.getDbValue());
		
		System.out.println("Roles Assigned to Websites");
		
		
		// Q-3 | Create the following pages for the web sites above. 
		//       Use the semester's start date for the created field. 
		//       Use the assignment's due date for the updated field.
		
		Page home = new Page("Home","Landing page",123434);
		home = pageDao.findPageById(pageDao.createPageForWebsite(cnet.getId(), home));
		
		Page about = new Page("About","Website description",234545);
		about = pageDao.findPageById(pageDao.createPageForWebsite(gizmodo.getId(), about));
		
		Page contact = new Page("Contact","Addresses, phones, and contact info",345656);
		contact = pageDao.findPageById(pageDao.createPageForWebsite(wikipedia.getId(), contact));
		
		Page preferences = new Page("Preferences","Where users can configure their preferences",456776);
		preferences = pageDao.findPageById(pageDao.createPageForWebsite(cnn.getId(), preferences));
		
		Page profile = new Page("Profile","Users can configure their personal information",567878);
		profile = pageDao.findPageById(pageDao.createPageForWebsite(cnet.getId(), profile));
		
		System.out.println("Pages Created!");
		
		roleDao.assignPageRole(alice.getId(), home.getId(), Role.EDITOR.getDbValue());
		roleDao.assignPageRole(bob.getId(), home.getId(), Role.REVIEWER.getDbValue());
		roleDao.assignPageRole(charlie.getId(), home.getId(), Role.WRITER.getDbValue());
		
		roleDao.assignPageRole(bob.getId(), about.getId(), Role.EDITOR.getDbValue());
		roleDao.assignPageRole(charlie.getId(), about.getId(), Role.REVIEWER.getDbValue());
		roleDao.assignPageRole(alice.getId(), about.getId(), Role.WRITER.getDbValue());
		
		roleDao.assignPageRole(charlie.getId(), contact.getId(), Role.EDITOR.getDbValue());
		roleDao.assignPageRole(alice.getId(), contact.getId(), Role.REVIEWER.getDbValue());
		roleDao.assignPageRole(bob.getId(), contact.getId(), Role.WRITER.getDbValue());
		
		roleDao.assignPageRole(alice.getId(), preferences.getId(), Role.EDITOR.getDbValue());
		roleDao.assignPageRole(bob.getId(), preferences.getId(), Role.REVIEWER.getDbValue());
		roleDao.assignPageRole(charlie.getId(), preferences.getId(), Role.WRITER.getDbValue());
		
		roleDao.assignPageRole(bob.getId(), profile.getId(), Role.EDITOR.getDbValue());
		roleDao.assignPageRole(charlie.getId(), profile.getId(), Role.REVIEWER.getDbValue());
		roleDao.assignPageRole(alice.getId(), profile.getId(), Role.WRITER.getDbValue());
		
		
		System.out.println("Roles Assigned to Pages");
		
		// Q-4 | Create the following widgets for the pages shown
		
		HeadingWidget head123 = new HeadingWidget();
		head123.setName("head123");
		head123.setText("Welcome");
		head123.setOrder(0);
		head123=(HeadingWidget) widgetDao.findWidgetById(widgetDao.createWidgetForPage(home.getId(), head123));
	
		HtmlWidget post234 = new HtmlWidget();
		post234.setName("post234");
		post234.setHtml("<p>Lorem</p>");
		post234.setOrder(0);
		post234=(HtmlWidget) widgetDao.findWidgetById(widgetDao.createWidgetForPage(about.getId(), post234));

		HeadingWidget head345 = new HeadingWidget();
		head345.setName("head345");
		head345.setText("Hi");
		head345.setOrder(1);
		head345=(HeadingWidget) widgetDao.findWidgetById(widgetDao.createWidgetForPage(contact.getId(), head345));

		HtmlWidget intro456 = new HtmlWidget();
		intro456.setName("intro456");
		intro456.setHtml("<h1>Hi</h1>");
		intro456.setOrder(2);
		intro456=(HtmlWidget) widgetDao.findWidgetById(widgetDao.createWidgetForPage(contact.getId(), intro456));

		ImageWidget image345 = new ImageWidget();
		image345.setName("image345");
		image345.setWidth(50);
		image345.setHeight(100);
		image345.setSrc("/img/567.png");
		image345.setOrder(3);
		image345=(ImageWidget) widgetDao.findWidgetById(widgetDao.createWidgetForPage(contact.getId(), image345));

		YoutubeWidget video456 = new YoutubeWidget();
		video456.setName("video456");
		video456.setWidth(400);
		video456.setHeight(300);
		video456.setUrl("https://youtu.be/h67VX51QXiQ");
		video456.setOrder(0);
		video456=(YoutubeWidget) widgetDao.findWidgetById(widgetDao.createWidgetForPage(preferences.getId(), video456));

		System.out.println("Widgets Created!");
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////
		//                                                                                     //
		// Implement Updates                                                                   //
		//                                                                                     //
		/////////////////////////////////////////////////////////////////////////////////////////
		
		
		// Q-1 | Update developer - Update Charlie's primary phone number to 333-444-5555
		if(askQuestion("Update Charlie's primary phone number to 333-444-5555")) {
			developerDao.updateDeveloperPrimaryPhone(charlie.getId(), "333-444-5555");
		}
		
		// Q-2 | Update widget - Update the relative order of widget head345 on the page so that it's new order is 3.
		//       Note that the other widget's order needs to update as well
		if(askQuestion("Update the relative order of widget head345 on the page so that it's new order is 3")) {
			head345.setOrder(3);
			widgetDao.updateWidget(head345.getId(), head345);
		}
		
		
		// Q-3 | Update page - Append 'CNET - ' to the beginning of all CNET's page titles
		if(askQuestion("Append 'CNET - ' to the beginning of all CNET's page titles")) {
			for(Page p: pageDao.findPagesForWebsite(cnet.getId())) {
				p.setTitle("CNET - "+p.getTitle());
				pageDao.updatePage(p.getId(), p);
			}
		}
		
		
		// Q-4 | Update roles - Swap Charlie's and Bob's role in CNET's Home page
		if(askQuestion("Swap Charlie's and Bob's role in CNET's Home page")) {
			roleDao.swapPageRoleOfDevelopers(home.getId(), charlie.getId(), bob.getId());
		}
		
		
		System.out.println("Updates Implemented!");
		
		
		/////////////////////////////////////////////////////////////////////////////////////////
		//                                                                                     //
		// Implement Deletes                                                                   //
		//                                                                                     //
		/////////////////////////////////////////////////////////////////////////////////////////
		
		// Q-1 | Delete developer - Delete Alice's primary address
		if(askQuestion("Delete Alice's primary address")) {
			developerDao.deleteDeveloperPrimaryAddress(alice.getId());
		}
		
		
		// Q-2 | Delete widget - Remove the last widget in the Contact page.
		//       The last widget is the one with the highest value in the order field
		if(askQuestion("Remove the last widget in the Contact page")) {
			int deleteWidgetId=-1;
			int deleteMaxOrder=-1;
			for(Widget w: widgetDao.findWidgetsForPage(contact.getId())) {
				if(w.getOrder()>deleteMaxOrder) {
					deleteMaxOrder=w.getOrder();
					deleteWidgetId=w.getId();
				}
			}
			widgetDao.deleteWidget(deleteWidgetId);
		}
		// This can also be implemeted using below given sql query :- 
		// SELECT id FROM widget WHERE page=`contact.getId()` ORDER BY `order` DESC LIMIT 1
		// Since no specific instructions are given used already exisiting Dao Methods
		
		
		// Q-3 | Delete page - Remove the last updated page in Wikipedia
		if(askQuestion("Remove the last updated page in Wikipedia")) {
			pageDao.deletePage(pageDao.getLastUpdatedPageId(wikipedia.getId()));
		}
		
		
		// Q-4 | Delete website - Remove the CNET web site, as well as all related roles and privileges
		//       relating developers to the Website and Pages
		if(askQuestion("Remove the CNET web site and its related data")) {
			websiteDao.deleteWebsite(cnet.getId());
		}
		
		
		System.out.println();
		System.out.println("Deletes Implemented!");
		System.out.println("Main method execution completed!");
	}
	
	private static boolean askQuestion(String q) throws IOException {
		System.out.println();
		System.out.println("Want to execute? "+ q);
		System.out.println("Enter 1 For Yes, 0 For No : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int i = Integer.parseInt(br.readLine());
		
		if(i==0) {
			return false;
		}
		else {
			System.out.println("Executing...");
			return true;
		}
	}
}