package cn.edu.jxau.model.converter;

import cn.edu.jxau.model.po.RuleTree;
import cn.edu.jxau.model.po.RuleTreeNode;
import cn.edu.jxau.model.po.RuleTreeNodeRelation;
import cn.edu.jxau.model.vo.RuleTreeNodeRelationVO;
import cn.edu.jxau.model.vo.RuleTreeNodeVO;
import cn.edu.jxau.model.vo.RuleTreeVO;

/**
 * @description: PO VO 对象转换器
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
public class Po2VoConverter {

    public static RuleTreeVO convertRuleTree(RuleTree ruleTree) {
        RuleTreeVO ruleTreeVO = new RuleTreeVO();
        ruleTreeVO.setTreeId(ruleTree.getTreeId());
        ruleTreeVO.setTreeRootId(ruleTree.getTreeRootId());
        ruleTreeVO.setTreeName(ruleTree.getTreeName());
        ruleTreeVO.setTreeDesc(ruleTree.getTreeDesc());
        return ruleTreeVO;
    }

    public static RuleTreeNodeVO convertRuleTreeNode(RuleTreeNode ruleTreeNode) {
        RuleTreeNodeVO ruleTreeNodeVO = new RuleTreeNodeVO();
        ruleTreeNodeVO.setTreeId(ruleTreeNode.getTreeId());
        ruleTreeNodeVO.setNodeId(ruleTreeNode.getNodeId());
        ruleTreeNodeVO.setNodeType(ruleTreeNode.getNodeType());
        ruleTreeNodeVO.setNodeValue(ruleTreeNode.getNodeValue());
        ruleTreeNodeVO.setRuleKey(ruleTreeNode.getRuleKey());
        ruleTreeNodeVO.setRuleDesc(ruleTreeNode.getRuleDesc());
        return ruleTreeNodeVO;
    }

    public static RuleTreeNodeRelationVO convertRuleTreeNodeRelation(RuleTreeNodeRelation ruleTreeNodeRelation) {
        RuleTreeNodeRelationVO ruleTreeNodeRelationVO = new RuleTreeNodeRelationVO();
        ruleTreeNodeRelationVO.setTreeId(ruleTreeNodeRelation.getTreeId());
        ruleTreeNodeRelationVO.setRuleType(ruleTreeNodeRelation.getRuleType());
        ruleTreeNodeRelationVO.setRuleValue(ruleTreeNodeRelation.getRuleValue());
        ruleTreeNodeRelationVO.setChildId(ruleTreeNodeRelation.getChildId());
        ruleTreeNodeRelationVO.setParentId(ruleTreeNodeRelation.getParentId());
        return ruleTreeNodeRelationVO;
    }
}
