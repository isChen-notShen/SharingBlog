# Sharing Blog 响应码说明  

Sharing Blog 项目的前后端响应码分为：  

- Response 状态码  
该状态码于HTTP规范的状态码含义一致
- Json code码  
Json中的code字段的含义，由前端后端开发人员商讨得出，并在本页做出说明

## code : 0
请求资源成功

## code : -1
请求非法，如：未登录访问资源

## code : 1
请求资源不存在，此时的json数据应该未空

<!--TODO-->
## code : X
其他响应码还未制订