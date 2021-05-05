# Sharing Blog API 设计
- author : 谌师立

### 1. 获取博客列表

#### 请求URL
    http://localhost/SharingBlog/blogs
    
#### 请求实例  
    http://localhost/SharingBlog/blogs
    http://localhost/SharingBlog/blogs?category-id=ss15xd
    
#### 请求方式
    GET

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

#### 请求URL  
    http://localhost/SharingBlog/blog/:blog-id  
   
#### 请求实例  
    http://localhost/SharingBlog/blog/bo155sa
    
#### 请求方式  
    GET  
    
#### 参数类型 : *path*
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|blog-id    |string |Y  |博客的id  |