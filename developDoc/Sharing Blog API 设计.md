# Sharing Blog API 设计
- author : 谌师立

### 1. 获取博客列表  

#### 身份验证
    否

#### 请求URL
    http://localhost/SharingBlog/blogs[?category-id=XXX]  
    
#### 请求方式
    GET  

#### 请求实例  
    http://localhost/SharingBlog/blogs
    http://localhost/SharingBlog/blogs?category-id=ss15xd

#### 参数类型 : *query*
|参数名    |参数类型   |是否必选   |说明 |
|:---    |:---    |:---    |:---    |
|category-id   |string |N  |博客的分类id，根据分类来获取博客列表  |  

注：category应该和分类表中的标签id一致，否者服务器将返回空数据

#### 返回数据
    count : 数据数量
    blog-id : 博客唯一标识符，在查询特定博客时，需要该数据
    blog-name : 博客名称
    like : 点赞数
    

#### 返回实例
~~~json
{
  "code" : 0,
  "info" : "success",
  "count": 2,
  "data" : [
              {
                "blog-id" : "bo155sa",
                "blog-name": "MySql性能调优",
                "like": 500
              },
              {
                "blog-id" : "bo985ax",
                "blog-name": "MySql基本语法",
                "like": 60
              }
           ]
}
~~~  

### 2.获取分类列表  

#### 身份验证  
    否  

#### 请求URL
    http://localhost/SharingBlog/categories

#### 请求方式
    GET  

#### 返回数据
    category-id : 分类的唯一标识符，在查询分类下的博客时，需要提交这个数据
    category-name : 分类名称

#### 返回实例
~~~json
{
  "code" : 0,
  "info" : "success",
  "count": 2,
  "data" : [
            {
            "category-id" : "ss15xd",
            "category-name" : "SQL"
            },
            {
             "category-id" : "sh36d",
             "category-name" : "Linux"
            }
           ]
}
~~~

### 3.获取博客  

#### 身份验证  
    否  

#### 请求URL  
    http://localhost/SharingBlog/blogs/:blog-id  
   
#### 请求实例  
    http://localhost/SharingBlog/blogs/bo155sa
    
#### 请求方式  
    GET  
    
#### 参数类型 : *path*
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|blog-id    |string |Y  |博客的id  |  

#### 返回数据  
    blog-name : 博客标题
    author : 博客作者
    released-time : 博客的发布时间
    like : 博客的点赞数
    data : 博客的内容

#### 返回实例  
~~~json
{
  "code" : 0,
  "info" : "success",
  "blog-name" : "Java垃圾收集机制详解",
  "author" : "XXX",
  "released-time" : "yyyy-mm-dd",
  "like" : 520,
  "data" : "#Java GC发展历程  \n......\n #Java虚拟机内存划分  \n......"
}
~~~

### 4.登录

#### 身份验证
    否  

#### 请求URL
    http://localhost/SharingBlog/user/login 
    
#### 请求方式
    POST  
    
#### 参数类型 : *body*
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|user_name  |string |Y  |账号名    |
|password   |string |Y  |密码 |  

#### 返回类型  
    token : 令牌，接下来的每次请求都将令牌提交至服务器  
    info : 提示信息，如果code码为1，则此字段有效，内容包括登录失败原因
    
#### 返回实例  
- 登陆成功
~~~json
{
  "code" : 0,
  "info" : "success",
  "token" : "SAG665DBSD515SD981D"
}
~~~
- 登录失败  
~~~json
{
  "code" : 1,
  "info" : "failure, account or password error",
  "token" : ""
}
~~~

### 5.获取用户信息  

#### 身份验证  
    否

#### 请求URL  
    http://localhost/SharingBlog/user/information?user_id=XXXXX  
    
#### 请求方式  
    GET  
    
#### 请求实例  
    http://localhost/SharingBlog/user/information?user_id=114515
      
#### 参数类型 : *query*  
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|user_id  |int |Y  |用户id   |  

#### 返回类型 : *json*
    display-name : 用户名
    user-name : 账号名
    description : 个人描述

#### 返回实例  
~~~json
{
  "code" : 0,
  "info" : "success",
  "user-id" : 1,
  "user-name" : "1170983543@qq.com",
  "display-name" : "Chen",
  "description" : "Java后端程序员"
}
~~~  

### 6.获取用户头像  

#### 身份验证  
    否

#### 请求URL  
    http://localhost/SharingBlog/user/portrait?user_id=XXXX
    
#### 请求方式  
    GET  
    
#### 请求实例  
    http://localhost/SharingBlog/user/portrait?user_id=1  
    
#### 参数类型 : *query*  
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|user_id  |string |Y  |账号id   |  

#### 返回类型 : *Binary*  

### 7.注册  

#### 身份验证  
    否  
    
#### 请求URL  
    http://localhost/SharingBlog/user/register  
    
#### 请求方式  
    POST