本项目基于 SpringBoot + MyBatis+MySQL 技术栈开发，聚焦于「用户 - 圈子 - 社区」领域的数据管理，旨在实现用户、圈子、社区的持久化存储与交互，并通过外键关联支持 “圈子成员 - 用户，圈子” 的多对多关系管理。用户可通过 命令行curl 或者网页输入调用 RESTful 接口，完成数据的查询、插入、更新、删除操作。
项目设计 4 张数据表，通过外键关联实现业务逻辑：用户表（user）：存储用户基本信息，圈子表（circle）：存储圈子基本信息，社区表（community）：存储社区基本信息，圈子用户关联表（circle_user）：通过外键关联 user 表和 circle 表，实现 “用户 - 圈子” 的多对多关系（一个用户可加入多个圈子，一个圈子可包含多个用户）。
项目采用 Mapper + Service + Controller 分层架构，实现了与数据库交互，封装核心业务逻辑，提供 RESTful API，接收 HTTP 请求，返回响应。
可完成查询、插入、更新、删除四类操作，例如：
查询指定圈子：
http://localhost:8080/api/circles/3001

新增社区（POST 请求）：
curl -Method POST `
>>   -Uri "http://localhost:8080/api/communitys" `
>>   -ContentType "application/json; charset=utf-8" `
>>   -Body '{"id": 3001, "name": "足球社区", "note": "讨论的社区", "ownerid": 14}' `                     
>>   -Verbose
查询指定圈子：
http://localhost:8080/api/circles/3001
