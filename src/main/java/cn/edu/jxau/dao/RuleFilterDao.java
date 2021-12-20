package cn.edu.jxau.dao;

import cn.edu.jxau.model.po.RuleTree;
import cn.edu.jxau.model.po.RuleTreeNode;
import cn.edu.jxau.model.po.RuleTreeNodeRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 规则过滤 Dao
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Mapper
public interface RuleFilterDao {

    /**
     * 查询规则树    table：rule_tree
     * @param treeId
     * @return
     */
    RuleTree queryRuleTree(Long treeId);

    /**
     * 查询规则树节点 List table: rule_tree_node
     * @param treeId
     * @return
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    /**
     * 查询规则树节点子节点 List  table：rule_tree_node_relation
     * @param nodeId
     * @return
     */
    List<RuleTreeNodeRelation> queryRuleTreeNodeChildList(Long nodeId);
}
