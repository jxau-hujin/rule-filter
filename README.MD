# 规则过滤引擎



![规则过滤引擎](https://typora-1300369948.cos.ap-shanghai.myqcloud.com//halo/img/%E8%A7%84%E5%88%99%E8%BF%87%E6%BB%A4%E5%BC%95%E6%93%8E.png)



## 为什么需要规则过滤引擎？

避免大量代码硬编码，使规则可编排，提高代码可读性、可复用性
朝着高内聚低耦合目标发展



```java
// 传入参数 User，针对年龄、性别进行过滤出【男，25岁以下】人群

// IF-ELSE 版本
if("男".equals(user.getSex())) {
  if(25 <= user.getAge()) {
		return true;
  }
}

// 使用规则执行引擎
ruleFilter(user, treeId);
```

1. 使用规则执行引擎屏蔽过滤细节，调用方无需关心实现细节，仅仅需要选定规则树即可，高内聚

   IF-ELSE 版本大量判断逻辑交织在业务代码中，耦合性高不易于修改且可读性差

2. 面对当前复杂的需求变更，IF-ELSE 版本显得力不从心而规则引擎支持灵活变更
   例如如果还需要判断【是否已婚】

   在IF-ELSE 版本中需要变更代码增加 IF 嵌套

   在规则执行引擎中仅需要变更规则树节点即可

3. 规则执行引擎支持可视化编排但是 IF-ELSE 版本因为嵌套在业务代码中不支持前端可视化编排

综上是为什么选择规则过滤引擎的理由



## 规则过滤引擎是什么？

根据自定义的规则对输入参数进行量化最终得到结果



![规则引擎执行流程](https://typora-1300369948.cos.ap-shanghai.myqcloud.com//halo/img/%E8%A7%84%E5%88%99%E5%BC%95%E6%93%8E%E6%89%A7%E8%A1%8C%E6%B5%81%E7%A8%8B.png)





## 规则过滤引擎使用场景

- 用户身份校验
- 风控



![规则过滤引擎使用场景](https://typora-1300369948.cos.ap-shanghai.myqcloud.com//halo/img/%E8%A7%84%E5%88%99%E8%BF%87%E6%BB%A4%E5%BC%95%E6%93%8E%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF.png)



## 实现细节



![规则过滤引擎类图](https://typora-1300369948.cos.ap-shanghai.myqcloud.com//halo/img/%E8%A7%84%E5%88%99%E8%BF%87%E6%BB%A4%E5%BC%95%E6%93%8E%E7%B1%BB%E5%9B%BE.png)

### 设计层面



#### 组合模式

将规则过滤执行引擎分为 「执行引擎」和「规则过滤器」

由执行引擎来组合规则过滤器达到规则过滤的目的

满足单一职责原则



#### 策略模式

面对各式各样的「规则过滤器」，需要统一的进行管理结合业务的语义（根据非叶子节点的属性值来获取对应的规则过滤器）因此采用策略模式

通过 「EngineConfig」类来保存 K-V，在执行引擎执行时直接根据 K 来命中过滤器



### 代码层面

根据面向对象思想，将「逻辑过滤器」「引擎执行器」分别定义接口及方法

后分别定义其抽象类实现共性方法同时针对通用逻辑抽取在抽象类中做到逻辑收口（模板方法设计模式）

「逻辑过滤器」「引擎执行器」通过「EngineConfig」连接起来共同组成规则过滤引擎



### 数据库层面



设计 3 个表，规则树表、规则树节点表、规则树节点关系表


![image-20220125114249310](https://typora-1300369948.cos.ap-shanghai.myqcloud.com//halo/img/image-20220125114249310.png)


```sql
/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : rule_filter

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 25/01/2022 11:40:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rule_tree
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree`;
CREATE TABLE `rule_tree` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增 Id',
  `tree_id` bigint NOT NULL COMMENT '规则树 Id',
  `tree_root_id` bigint NOT NULL COMMENT '规则树根节点 Id',
  `tree_name` varchar(64) NOT NULL COMMENT '规则树名称',
  `tree_desc` varchar(128) NOT NULL DEFAULT '' COMMENT '规则树描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for rule_tree_node
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node`;
CREATE TABLE `rule_tree_node` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增 id',
  `tree_id` bigint NOT NULL COMMENT '规则树 id',
  `node_id` bigint NOT NULL COMMENT '规则树节点 id',
  `node_type` int NOT NULL COMMENT '规则树节点类型 0 非叶子节点 1 叶子节点',
  `node_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '规则树节点值',
  `rule_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '过滤器 key',
  `rule_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '过滤器描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for rule_tree_node_relation
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node_relation`;
CREATE TABLE `rule_tree_node_relation` (
  `id` bigint NOT NULL COMMENT '自增 id',
  `tree_id` bigint NOT NULL COMMENT '规则树 id',
  `parent_id` bigint DEFAULT NULL COMMENT '父节点 id',
  `child_id` bigint DEFAULT NULL COMMENT '子节点 id',
  `rule_type` int NOT NULL COMMENT '规则比较类型',
  `rule_value` varchar(64) NOT NULL COMMENT '规则比较值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;

```





### Demo

需求

应当参与活动的用户做一次筛选。并不是所有的用户都能参与到活动

注：此部分规则通常适用于贷款产品，而非本次题目中的存款产品。
引入目的是希望参赛者实现一个简单的、可灵活配置规则的风险决策引擎
规则名称	规则描述	对象	风险控制
逾期记录	近3年逾期2次以上（金额小于1000元，3天内还清的除外）	个人客户	拒绝
个人客户工作状态异常	状态为“无业／失业”	个人客户	拒绝
个人客户被列入失信人名单	个人客户被列入当前严重违法失信被执行人名单，未执行完毕的。	个人客户	拒绝
个人客户年龄	小于18岁	个人客户	拒绝

数据规则说明：
在分析条件时，可以“数字”为维度进行展开，如“逾期记录”条件中，3年、2次、1000元、3天等字眼。

拦截举例说明：
用户A年龄为16岁，在登录后秒杀活动不会对其开放。

功能实现说明：
对于拦截功能，可以从以下方面思考：
只使用一张规则表是否是最优解?
如果将“规则名称”单独拆分成表是否对三高特性有提升？
一定要用到数据库吗?
用户准入结果是实时判断更好，还是提前存到数据库后再实时查询更好？
用黑名单拦截更好，还是白名单准入更好？
此功能是否可以从业务逻辑中分离，成为一个通用的模块？