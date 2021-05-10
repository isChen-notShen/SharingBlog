## 数据库设计  
- author : 谌师立  

### 基本信息  
- DBMS : MySQL
- 数据库名 : sharing_blog

### 数据库用户  
- 用户名 : sharing_blog_rw_user  
- 权限 : 对sharing_blog数据库中的所有表具有读写权限  

### 表  

- user表  *描述的是用户的账号信息*

|字段名    |数据类型   |非空 |主键 |外键 |外键引用   |唯一 |默认值    |备注 |  
|:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |
|id    |int    |Y  |Y  |N  |   |Y  |   |用户表主键  |
|name   |varchar(18)    |Y  |N  |N  |   |Y  |   |账号名    |
|password   |varchar(16)    |Y  |N  |N  |   |N  |"123456" |用户密码   |
|description    |tinytext    |N  |N  |N  |   |N  |  |简介  |  

- focus表  *描述的是账号的关注信息*  

|字段名    |数据类型   |非空 |主键 |外键 |外键引用   |唯一 |默认值    |备注 |  
|:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |
|follower_uid   |int    |Y  |Y  |Y  |user.id   |N  |   |关注人    |
|celebrity_uid  |int    |Y  |Y  |Y  |user.id   |N  |   |被关注人   |  
		
- post表  *描述的是文章的信息*  

|字段名    |数据类型   |非空 |主键 |外键 |外键引用   |唯一 |默认值    |备注 |  
|:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |
|id    |int    |Y  |Y  |N  |   |Y  |   |文章表的主键 |
|user_id    |int    |Y  |N  |Y  |user.id   |N  |   |文章作者   |
|category_id    |int    |Y  |N  |Y  |category.id    |N  |   |文章分类   |
|name   |varchar(20)    |Y  |N  |N  |   |N  |   |文章名    |
|like   |unsigned smallint   |Y  |N  |N  |   |N  |0  |点赞数    |
|data   |text   |Y  |N  |N  |   |N  |   |文章内容   |

- tag表  *描述的是标签的信息*  

|字段名    |数据类型   |非空 |主键 |外键 |外键引用   |唯一 |默认值    |备注 |  
|:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |
|id |int    |Y  |Y  |N  |   |Y  |   |标签的id  |
|name   |varchar(10)    |Y  |N  |N  |   |Y  |   |标签名    |  

- post_tag  *描述的是文章与标签的关系*  

|字段名    |数据类型   |非空 |主键 |外键 |外键引用   |唯一 |默认值    |备注 |  
|:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |
|post_id    |int    |Y  |Y  |Y  |post.id    |N  |   |文章id   |
|tag_id |int    |Y  |Y  |Y  |tag.id |N  |   |标签id   |  
		
- category表  *描述的分类的信息*  

|字段名    |数据类型   |非空 |主键 |外键 |外键引用   |唯一 |默认值    |备注 |  
|:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |
|id |int    |Y  |Y  |N  |   |Y  |   |分类id   |
|name   |varchar(15)    |Y  |N  |N  |   |Y  |   |分类名    |  
	
- comment表  *描述的是评论的信息*  

|字段名    |数据类型   |非空 |主键 |外键 |外键引用   |唯一 |默认值    |备注 |  
|:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |:---   |
|id |int    |Y  |Y  |N  |   |Y  |   |评论id   |
|user_id    |int    |Y  |N  |Y  |user.id    |N  |   |评论者的id |
|release   |datetime   |Y   |N  |N  |   |N  |   |评论发布时间 |
|like   |unsigned smallint   |Y  |N  |N  |   |N  |0  |点赞数    |
|data   |tinytext   |Y  |N  |N  |   |N  |   |评论内容   |  