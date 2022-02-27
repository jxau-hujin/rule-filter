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





### 实战

#### 需求

应当参与活动的用户做一次筛选。并不是所有的用户都能参与到活动
​

注：此部分规则通常适用于贷款产品，而非本次题目中的存款产品。
引入目的是希望参赛者实现一个简单的、可灵活配置规则的风险决策引擎



| **规则名称**             | **规则描述**                                               | **对象** | **风险控制** |
| ------------------------ | ---------------------------------------------------------- | -------- | ------------ |
| 逾期记录                 | 近3年逾期2次以上（金额小于1000元，3天内还清的除外）        | 个人客户 | 拒绝         |
| 个人客户工作状态异常     | 状态为“无业／失业”                                         | 个人客户 | 拒绝         |
| 个人客户被列入失信人名单 | 个人客户被列入当前严重违法失信被执行人名单，未执行完毕的。 | 个人客户 | 拒绝         |
| 个人客户年龄             | 小于18岁                                                   | 个人客户 | 拒绝         |

数据规则说明：
在分析条件时，可以“数字”为维度进行展开，如“逾期记录”条件中，3年、2次、1000元、3天等字眼。
​

拦截举例说明：
用户A年龄为16岁，在登录后秒杀活动不会对其开放。
​

#### 实现

该需求非常符合规则执行引擎的使用场景，分为四个规则
「逾期记录」、「个人客户工作状态异常」、「个人客户被列入失信人名单」、「个人客户年龄」
​

因此在 `cn.edu.jxau.example`下分别创建 `filter：存放规则过滤器实现类`、`model：业务模型`、`service：业务 Service`
​

##### 逾期记录

```java
@Component
public class OverdueRecordsRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "userId";

    private static BigDecimal limitBalance = new BigDecimal(1000);

    private static Long limitCount = 2L;

    private static Integer ACCEPT = 1;

    private static Integer REJECT = -1;


    @Resource
    private OverdueRecordsService overdueRecordsService;

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }
        String userId = argMap.get(KEYWORD);
        List<OverdueRecords> overdueRecords = overdueRecordsService.queryByUserId(userId);


        Long count = overdueRecords.stream()
                .filter((overdueRecord -> rule(overdueRecord)))
                .count();
        if(count > limitCount) {
            return REJECT;
        }

        return ACCEPT;
    }

    private boolean rule(OverdueRecords overdueRecord) {

        // 近三年逾期记录
        LocalDate lastThreeYears = LocalDate.now().minusYears(3);

        LocalDate overdueDate = overdueRecord.getOverdueDate();

        if(overdueDate.isBefore(lastThreeYears)) {
            return false;
        }

        BigDecimal overdueBalance = overdueRecord.getOverdueBalance();

        // 已还清 && 逾期金额小于 1000
        if(overdueRecord.getIsPaid() && overdueBalance.compareTo(limitBalance) < 0) {
            // 逾期 3 天之外还清
            LocalDate lastThreeDays = overdueDate.plusDays(3);

            LocalDate paidDate = overdueRecord.getPaidDate();

            if(paidDate.isBefore(lastThreeDays)) {
                return false;
            }
        }

        return true;
    }
}
```


##### 个人客户工作状态异常

```java
@Component
public class WorkStatusRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "userId";

    private static Integer ACCEPT = 1;

    private static Integer REJECT = -1;

    @Resource
    private UserInfoService userInfoService;

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }
        String userId = argMap.get(KEYWORD);
        UserInfo userInfo = userInfoService.queryByUserId(userId);

        if(userInfo.getWorkStatus() == 0 || userInfo.getWorkStatus() == 1) {
            return REJECT;
        }
        return ACCEPT;
    }

}
```


##### 个人客户被列入失信人名单

```java
@Component
public class DefaultersRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "userId";

    private static Integer ACCEPT = 1;

    private static Integer REJECT = -1;

    @Resource
    private DefaultersService defaultersService;

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }
        String userId = argMap.get(KEYWORD);

        if(defaultersService.isDefaulters(userId)) {
            return REJECT;
        }
        return ACCEPT;
    }
}
```


##### 个人客户年龄

```java
@Component
public class AgeRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "userId";

    @Resource
    private UserInfoService userInfoService;

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }

        UserInfo userInfo = userInfoService.queryByUserId(argMap.get(KEYWORD));

        return Integer.valueOf(userInfo.getAge());
    }
}
```

##### 运行结果测试

风控通过 </br>
<img width="619" alt="image" src="https://user-images.githubusercontent.com/43172976/155868211-a8e57b5b-4cf6-4a32-b1ac-980e310b8f99.png">

逾期记录不通过 </br>
<img width="617" alt="image" src="https://user-images.githubusercontent.com/43172976/155868214-e24235b7-0b9e-49c2-b6b8-55dcddb85592.png">


个人客户工作状态异常 </br>
<img width="619" alt="image" src="https://user-images.githubusercontent.com/43172976/155868221-287accd0-a0fe-4a5b-9b13-cee0a6db104e.png">


个人客户被列入失信人名单 </br>
<img width="619" alt="image" src="https://user-images.githubusercontent.com/43172976/155868224-b3f829d2-4e39-40f1-ab8a-590b157ed70a.png">


个人客户年龄不符合 </br>
<img width="620" alt="image" src="https://user-images.githubusercontent.com/43172976/155868226-741a4fe9-a2bc-44d0-b912-5b00b2c3ee40.png">


#### 思考

##### 只使用一张规则表是否是最优解?

只使用一张规则表不符合数据库范式要求，会存在数据冗余以及数据更新导致不一致的情况，因此规则执行引擎中拆分为「规则树」「规则节点」「规则节点关系」


##### 如果将“规则名称”单独拆分成表是否对三高特性有提升？

不会，因为规则执行引擎对于表的依赖大多是`读`而非`写`，因此即使不拆分也不会导致行记录锁定，但是为了范式的要求还是进行了拆分


##### 一定要用到数据库吗?

是，因为规则是提前预设好，所以从业务场景上来说是需要持久化存储的，而且业务特定是`读多写少`可以提前将其加载到缓存中，避免从数据库中读数据


##### 用户准入结果是实时判断更好，还是提前存到数据库后再实时查询更好？

根据业务需求来确定，如果对于实时判断的 RT 没有要求，那么实时判断也可以（实时判断会调用外部 API 来获取数据进行判断），如果有要求可以定时任务提前计算好并存储数据库中，在查询时可以从缓存查询加快查询速度，前提是客户数据变更不频繁，本例中用户的数据就属于变更不频繁的场景


##### 用黑名单拦截更好，还是白名单准入更好？

黑名单拦截更好，因为考虑到大多数人的信用记录还是良好的，黑名单相对更少，而白名单更多


##### 此功能是否可以从业务逻辑中分离，成为一个通用的模块？

规则执行引擎正是为了避免业务硬编码而分离出来的通用模块
​

##### 规则执行引擎的粒度

粒度一：对于复杂的规则更倾向于使用者继承`AbstractRuleFilterExec`重写 `matterValue`方法
​

粒度二：对于简单的规则更倾向于使用者通过设定节点的比较类型以及比较值，而`matterValue`则直接返回量化后的数据
​

对于本需求来说
​

`逾期记录`和`个人客户被列入失信人名单`可以使用`粒度一`
`个人客户工作状态异常`和`个人客户年龄`可以使用`粒度二`
​
