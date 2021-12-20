package cn.edu.jxau.repository;


import cn.edu.jxau.model.aggregates.RuleTreeRich;
import cn.edu.jxau.model.po.RuleTree;
import cn.edu.jxau.model.po.RuleTreeNode;
import cn.edu.jxau.model.po.RuleTreeNodeRelation;

import java.util.List;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
public interface IRuleFilterRepository {

    /**
     * 查询规则树
     * @param treeId
     * @return
     */
    RuleTree queryRuleTree(Long treeId);

    /**
     * 查询规则树节点
     * @param treeId
     * @return
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    /**
     * 查询规则树节点子节点
     * @param nodeId
     * @return
     */
    List<RuleTreeNodeRelation> queryRuleTreeNodeChildList(Long nodeId);

    /**
     * 查询规则树聚合对象
     * @param treeId
     * @return
     */
    RuleTreeRich queryRuleTreeRich(Long treeId);
}
