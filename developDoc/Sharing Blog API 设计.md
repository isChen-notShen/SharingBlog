# Sharing Blog API 设计
- author : 谌师立  





## 用户  



### 1.登录

#### 身份验证
    否  

#### 请求URL
    http://localhost/user/login 

#### 请求方式
    POST  

#### 参数类型 : *body*
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|userName  |string |Y  |账号名    |
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



### 2.注册  

#### 身份验证  
    否  

#### 请求URL  
    http://localhost/user/register  

#### 请求方式  
    POST

#### 参数类型 : *body*  
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|userName  |string |Y  |注册的账号  |
|password   |string |Y  |注册的密码  |

#### 返回类型 : *json*  
    code : 请求响应码
    info : 响应信息  

#### 返回实例  
~~~json
{
  "code" : 0,
  "info" : "注册成功"
}
~~~
~~~json
{
  "code" : 4,
  "info" : "账号名重复"
}
~~~



### 3.注销登录  

#### 身份验证  
    是 : 请求头携带token  

#### 请求URL  
    http://localhost/user/logout  

#### 请求方式  
    POST  

#### 返回类型 : *json*  
    code : 请求响应  
    info : 响应信息  

#### 返回实例  
~~~json
{
  "code" : 0,
  "info" : "注销成功"
}
~~~



### 4.获取用户头像  

#### 身份验证  
    否

#### 请求URL  
    http://localhost/user/portrait?userId=XXXX

#### 请求方式  
    GET  

#### 请求实例  
    http://localhost/user/portrait?userId=1  

#### 参数类型 : *query*  
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|userId  |string |Y  |账号id   |

#### 返回类型 : *Binary*  



### 5.获取用户信息  

#### 身份验证  
    否

#### 请求URL  
    http://localhost/user/information?userId=xxx||userName=xxx  

#### 请求方式  
    GET  

#### 请求实例  
    http://localhost/user/info?userId=114515
    http://localhost/user/info?userName=1170983543@qq.com

#### 参数类型 : *query*  
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|userId  |int |N  |用户id   |
|userName   |String |N  |用户名    |

#### 返回类型 : *json*
    displayName : 用户名
    userName : 账号名
    description : 个人描述

#### 返回实例  
~~~json
{
  "code" : 0,
  "info" : "success",
  "userId" : 1,
  "userName" : "1170983543@qq.com",
  "displayName" : "Chen",
  "description" : "Java后端程序员"
}
~~~





## 博客



### 1. 获取博客列表  

#### 身份验证
    否

#### 请求URL
    http://localhost/blogs[?categoryId=XXX]  

#### 请求方式
    GET  

#### 请求实例  
    http://localhost/blogs
    http://localhost/blogs?categoryId=ss15xd

#### 参数类型 : *query*
|参数名    |参数类型   |是否必选   |说明 |
|:---    |:---    |:---    |:---    |
|categoryId   |string |N  |博客的分类id，根据分类来获取博客列表  |

注：category应该和分类表中的标签id一致，否者服务器将返回空数据

#### 返回数据
    count : 数据数量
    blogId : 博客唯一标识符，在查询特定博客时，需要该数据
    blogName : 博客名称
    like : 点赞数


#### 返回实例
~~~json
{
  "code" : 0,
  "info" : "success",
  "count": 2,
  "data" : [
              {
                "blogId" : "bo155sa",
                "blogName": "MySql性能调优",
                "author"  : "Chen",
                "comment" : 15,
                "like": 500
              },
              {
                "blogId" : "bo985ax",
                "blogName": "MySql基本语法",
                "author"  : "Chen",
                "comment" : 24,
                "like": 60
              }
           ]
}
~~~



### 2.获取博客  

#### 身份验证  
    否  

#### 请求URL  
    http://localhost/blogs/:blogId  

#### 请求实例  
    http://localhost/blogs/bo155sa

#### 请求方式  
    GET  

#### 参数类型 : *path*
|参数名    |参数类型   |是否必选   |说明 |
|:---   |:---   |:---   |:---   |
|blogId    |string |Y  |博客的id  |

#### 返回数据  
    blogName : 博客标题
    author : 博客作者
    releasedTime : 博客的发布时间
    like : 博客的点赞数
    data : 博客的内容

#### 返回实例  
~~~json
{
  "code" : 0,
  "info" : "success",
  "blogName" : "Java垃圾收集机制详解",
  "author" : "XXX",
  "releasedTime" : "yyyy-mm-dd",
  "like" : 520,
  "data" : "#Java GC发展历程  \n......\n #Java虚拟机内存划分  \n......"
}
~~~





## 分类  



### 1.获取分类列表  

#### 身份验证  
    否  

#### 请求URL
    http://localhost/categories

#### 请求方式
    GET  

#### 返回数据
    categoryId : 分类的唯一标识符，在查询分类下的博客时，需要提交这个数据
    categoryName : 分类名称

#### 返回实例
~~~json
{
  "code" : 0,
  "info" : "success",
  "count": 2,
  "data" : [
            {
            "categoryId" : "ss15xd",
            "categoryName" : "SQL"
            },
            {
             "categoryId" : "sh36d",
             "categoryName" : "Linux"
            }
           ]
}
~~~