# Sharing Blog API 设计
- author : 谌师立

### 1. 博客列表

#### 请求URL :
    http://localhost/SharingBlog/blogs
    
#### 请求方式
    GET

#### 参数类型 : *query*
|参数名    |参数类型   |是否必选   |说明 |
|:---    |:---    |:---    |:---    |
|category   |string |N  |博客的分类，根据分类来获取博客列表  |
注：category应该和分类表中的标签一致，否者返回空数据

#### 返回数据 :
    name : 博客名称
    like : 点赞数

#### 返回实例 :
~~~json
{
  "code" : 0,
  "count": 2,
  "data" : [
           {"name": "MySql性能调优", "like": 500},
           {"name": "MySql基本语法", "like": 60}
           ]
}