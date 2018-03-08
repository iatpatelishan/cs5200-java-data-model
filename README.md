

Consider the UML class diagram shown below. This Java Spring Project creates the corresponding Java Data Model that implements the equivalent relational model, fulfills the use cases, implements the relations, and enforces the constraints. Also SQL Schema for the given class diagram is under resources folder of this repository.

It also implements Data Access Objects (DAOs) that encapsulate access to the database applying best practices.

![Class Diagram](https://i.imgur.com/4v2qv7E.png)



## Models Implemented
 - Person.java
 - Phone.java
 - Address.java
 - User.java
 - Developer.java
 - Website.java
 - Page.java
 - Widget.java
 - HeadingWidget.java
 - HtmlWidget.java
 - ImageWidget.java
 - YouTubeWidget.java
 - Role.java
 - Priviledge.java
 - WidgetType.java


 
 ## DAOs Implemented
Following is the _**list of some of the methods in DAOs**_ that encapsulate the access to the database for each of the entities and relations in the UML class diagram
 
### DeveloperDao.java
DeveloperDao.java implements a class that encapsulates all database interaction between the Developer and Person entities and relations

1. **int createDeveloper(Developer developer)**

   inserts properties in developer instance parameter in tables Developer and Person

2. **Collection<Developer> findAllDevelopers()**

   returns all joined records from Developer and Person tables as a Collection of Developer instances

3. **Developer findDeveloperById(int developerId)**

   returns a joined record from Developer and Person tables whose id field is equal to the developerId parameter

4. **Developer findDeveloperByUsername(String username)**

   returns a joined record from Developer and Person tables whose username field matches the parameter

5. **Developer findDeveloperByCredentials(String username, String password)**
   
   returns a joined record from Developer and Person tables whose username and password fields match the parameters

6. **int updateDeveloper(int developerId, Developer developer)**
   
   updates records in Developer and Person tables whose id field is equal to developerId parameter. New record field values are set to the values in the developer instance parameter

7. **int deleteDeveloper(int developerId)**

   deletes records from Developer and Person tables whose id field is equal to developerId parameter

### WebsiteDao.java

WebsiteDao.java implements a class that encapsulates all database interaction between the Website and Developer entities and relations.

1. **int createWebsiteForDeveloper(int developerId, Website website)**
   
   inserts properties in website instance parameter into the Website table. The website's developerId foreign key refer to Developer table primary key id whose value is equal to the developerId parameter

2. **Collection<Website> findAllWebsites()**

   returns all records from Website table as a Collection of Website instances

3. **Collection<Website> findWebsitesForDeveloper(int developerId)**

   returns all records from Website table as a Collection of Website instances whose developerId is equal to the developerId parameter

4. **Website findWebsiteById(int websiteId)**
   
   returns a record from Website table whose id field is equal to the websiteId parameter

5. **int updateWebsite(int websiteId, Website website)**

   updates record in Website table whose id field is equal to websiteId parameter. New record field values are set to the values in the website instance parameter

6. **int deleteWebsite(int websiteId)**

   deletes record from Website table whose id field is equal to websiteId parameter


### PageDao.java
PageDao.java implements a class that encapsulates all database interaction between the Website and Page entities and relations

1. **int createPageForWebsite(int websiteId, Page page)**
   
   inserts properties in page instance parameter into the Page table. The page's websiteId foreign key refer to Website table primary key id whose value is equal to the websiteId parameter

2. **Collection<Page> findAllPages()**

   returns all records from Page table as a Collection of Page instances

3. **Page findPageById(int pageId)**

   returns a record from Page table whose id field is equal to the pageId parameter

4. **Collection<Page> findPagesForWebsite(int websiteId)**

   returns all records from Page table as a Collection of Page instances whose websiteId is equal to the websiteId parameter

5. **int updatePage(int pageId, Page page)**

   updates record in Page table whose id field is equal to pageId parameter. New record field values are set to the values in the page instance parameter

6. **int deletePage(int pageId)**
   deletes record from Page table whose id field is equal to pageId parameter

### WidgetDao.java
WidgetDao.java implements a class that encapsulates all database interaction between the Widget and Page entities and relations

1. **int createWidgetForPage(int pageId, Widget widget)**
   
   inserts properties in widget instance parameter into the Widget table. The widget's pageId foreign key refer to Page table primary key id whose value is equal to the pageId parameter

2. **Collection<Widget> findAllWidgets()**

   returns all records from Widget table as a Collection of Widget instances

3. **Widget findWidgetById(int widgetId)**

   returns a record from Widget table whose id field is equal to the widgetId parameter

4. **Collection<Widget> findWidgetsForPage(int pageId)**

   returns all records from Widget table as a Collection of Widget instances whose pageId is equal to the pageId parameter

5. **int updateWidget(int widgetId, Widget widget)**

   updates record in Widget table whose id field is equal to widgetId parameter. New record field values are set to the values in the widget instance parameter

6. **int deleteWidget(int widgetId)**

   deletes record from Widget table whose id field is equal to widgetId parameter

### RoleDao.java
RoleDao.java implements a class that encapsulates all database access that manages website and page roles assigned to Developer

1. **assignWebsiteRole(int developerId, int websiteId, int roleId)**

   inserts into table Role a record that assigns a developer whose id is developerId, the role with roleId, to the website with websiteId

2. **assignPageRole(int developerId, int pageId, int roleId)**

   inserts into table Role a record that assigns a developer whose id is developerId, the role with roleId, to the page with pageId

3. **deleteWebsiteRole(int developerId, int websiteId, int roleId)**

   deletes from table Role a record that removes roleId from developerId, on websiteId

4. **deletePageRole(int developerId, int pageId, int roleId)**

   deletes from table Role a record that removes roleId from developerId, on pageId


### PrivilegeDao.java

PrivilegeDao.java implements a class that encapsulates all database access that manages website and page privileges assigned to Developer

1. **assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId)**

   inserts into table Priviledge a record that assigns a developer whose id is developerId, the priviledge with priviledgeId, to the website with websiteId

2. **assignPagePriviledge(int developerId, int pageId, int priviledgeId)**

   inserts into table Priviledge a record that assigns a developer whose id is developerId, the priviledge with priviledgeId, to the page with pageId

3. **deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId)**

   deletes from table Priviledge a record that removes priviledgeId from developerId, on websiteId

4. **deletePagePriviledge(int developerId, int pageId, int priviledgeId)**

   deletes from table priviledge a record that removes priviledgeId from developerId, on pageId
