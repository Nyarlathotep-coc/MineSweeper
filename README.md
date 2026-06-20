# MineSweeper

一个基于 Java Swing + MySQL 的扫雷小游戏，支持用户注册登录、游戏统计记录。

## 功能

- 用户注册与登录
- 10×10 网格扫雷（10 颗雷）
- 左键揭开格子，右键标记/取消标记地雷
- 游戏胜负统计（记录胜场/负场）
- 游戏结束后显示统计面板

## 环境要求

| 依赖 | 版本 |
|---|---|
| Java | JDK 8 或更高 |
| MySQL | 8.0 或更高 |
| IDE（可选） | MyEclipse / Eclipse |

## 快速开始

### 1. 配置数据库

```sql
-- 创建数据库
CREATE DATABASE minesweeper;
USE minesweeper;

-- 导入表结构
SOURCE db/minesweeper.sql;
```

或直接导入项目自带的 SQL 文件：

```bash
mysql -u root -p minesweeper < db/minesweeper.sql
```

默认预置了一个测试账户：**Player1 / 123**

### 2. 修改数据库连接配置

编辑 src/util/DBUtil.java，根据你的 MySQL 配置修改：

```java
private static final String URL = "jdbc:mysql://localhost:3306/minesweeper";
private static final String USER = "root";
private static final String PASSWORD = "root";
```

### 3. 导入 Eclipse / MyEclipse

1. **File → Import → General → Existing Projects into Workspace**
2. 选择项目根目录
3. 在 **Java Build Path → Libraries** 中，确保 JRE 指向 **JDK 8+**（如 jdk1.8.0_20 或 JavaSE-1.8）
4. 检查 lib/mysql-connector-j-9.3.0.jar 已正确引入

### 4. 运行

打开 src/gui/WelcomeScreen.java，右键 **Run As → Java Application**。

也可以直接运行 WelcomeScreen 的 main 方法。

## 项目结构

```
MineSweeper/
├── db/
│   └── minesweeper.sql          # 数据库建表脚本
├── lib/
│   └── mysql-connector-j-9.3.0.jar  # MySQL JDBC 驱动
├── src/
│   ├── dao/                      # 数据访问层
│   │   ├── GameDAO.java
│   │   ├── GameStatsDAO.java
│   │   ├── ScoreDAO.java
│   │   └── UserDAO.java
│   ├── entity/                   # 实体类
│   │   ├── Game.java
│   │   ├── GameStats.java
│   │   ├── GridCell.java
│   │   ├── GridPosition.java
│   │   ├── Score.java
│   │   ├── Session.java
│   │   └── User.java
│   ├── gui/                      # 图形界面
│   │   ├── WelcomeScreen.java    # 入口：主菜单
│   │   ├── LoginScreen.java
│   │   ├── RegisterScreen.java
│   │   ├── MineSweeperGame.java  # 游戏主界面
│   │   └── GameOverPanel.java
│   ├── service/
│   │   └── GameService.java      # 游戏逻辑服务
│   └── util/
│       └── DBUtil.java           # 数据库连接工具
├── .classpath
├── .project
└── .gitignore
```

## 技术栈

- **前端**：Java Swing
- **后端**：JDBC + MySQL
- **数据库驱动**：MySQL Connector/J 9.3.0
- **开发环境**：MyEclipse 2014 / Eclipse

## 数据库表结构

### users（用户表）

| 字段 | 类型 | 说明 |
|---|---|---|
| id | INT (PK, AUTO_INCREMENT) | 用户 ID |
| username | VARCHAR(255) | 用户名（唯一） |
| password | VARCHAR(255) | 密码 |

### game_stats（游戏统计表）

| 字段 | 类型 | 说明 |
|---|---|---|
| user_id | INT (PK) | 用户 ID |
| wins | INT | 胜场数 |
| losses | INT | 负场数 |


