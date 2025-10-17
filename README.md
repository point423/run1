# 「用户-圈子-社区」数据管理项目文档
## 一、项目概述
本项目基于 SpringBoot + MyBatis + MySQL 技术栈开发，聚焦「用户-圈子-社区」领域的数据管理场景。核心目标是实现用户、圈子、社区的持久化存储与交互，并通过外键关联管理“圈子成员-用户-圈子”的多对多关系。用户可通过命令行 curl 或网页输入调用 RESTful 接口，完成数据的查询、插入、更新、删除操作。

## 二、数据表设计
项目共设计 4 张数据表，通过外键关联实现业务逻辑，具体结构如下：

| 表名 | 核心功能 | 关键字段（示例） | 关联关系 |
| ---- | ---- | ---- | ---- |
| user（用户表） | 存储用户基本信息 | id、username、password、email | 与 circle_user 表通过 user.id 外键关联 |
| circle（圈子表） | 存储圈子基本信息 | id、name、description、create_time | 与 circle_user 表通过 circle.id 外键关联 |
| community（社区表） | 存储社区基本信息 | id、name、note、ownerid | - |
| circle_user（圈子用户关联表） | 实现“用户-圈子”多对多关系 | id、user_id、circle_id | 分别关联 user.id 和 circle.id 外键 |

## 三、项目架构
采用 Mapper + Service + Controller 分层架构，各层职责清晰，具体如下：

1. **Mapper 层**：负责与数据库交互，通过 MyBatis 实现 SQL 语句的映射，完成数据的 CRUD 基础操作。
2. **Service 层**：封装核心业务逻辑，调用 Mapper 层接口，处理业务规则（如权限校验、数据合法性判断）。
3. **Controller 层**：提供 RESTful API，接收 HTTP 请求，调用 Service 层处理逻辑，返回标准化响应结果。

## 四、核心接口示例
### 1. 查询指定圈子
- **请求方式**：GET
- **请求地址**：`http://localhost:8080/api/circles/3001`
- **说明**：将地址中“3001”替换为目标圈子的 id，即可查询该圈子的详细信息。

### 2. 新增社区
- **请求方式**：POST
- **请求地址**：`http://localhost:8080/api/communitys`
- **请求头**：Content-Type: application/json; charset=utf-8
- **请求体（示例）**：
```json
{
  "id": 3001,
  "name": "足球社区",
  "note": "讨论的社区",
  "ownerid": 14
}
```
- **curl 命令（示例）**：
```powershell
curl -Method POST `
  -Uri "http://localhost:8080/api/communitys" `
  -ContentType "application/json; charset=utf-8" `
  -Body '{"id": 3001, "name": "足球社区", "note": "讨论的社区", "ownerid": 14}' `
  -Verbose
```

### 3. 其他操作说明
- **更新数据**：采用 PUT 或 PATCH 方法，请求地址格式与查询类似（需包含目标数据 id），请求体传入需更新的字段及值。
- **删除数据**：采用 DELETE 方法，请求地址包含目标数据 id，无需请求体。
