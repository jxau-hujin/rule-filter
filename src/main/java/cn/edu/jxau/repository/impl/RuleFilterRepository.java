package cn.edu.jxau.repository.impl;

import cn.edu.jxau.dao.RuleFilterDao;
import cn.edu.jxau.model.aggregates.RuleTreeRich;
import cn.edu.jxau.model.converter.Po2VoConverter;
import cn.edu.jxau.model.po.RuleTree;
import cn.edu.jxau.model.po.RuleTreeNode;
import cn.edu.jxau.model.po.RuleTreeNodeRelation;
import cn.edu.jxau.model.vo.RuleTreeNodeRelationVO;
import cn.edu.jxau.model.vo.RuleTreeNodeVO;
import cn.edu.jxau.repository.IRuleFilterRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Repository
public class RuleFilterRepository implements IRuleFilterRepository {

    @Resource
    private RuleFilterDao ruleFilterDao;

    @Override
    public RuleTree queryRuleTree(Long treeId) {
        return ruleFilterDao.queryRuleTree(treeId);
    }

    @Override
    public List<RuleTreeNode> queryRuleTreeNodeList(Long treeId) {
        return ruleFilterDao.queryRuleTreeNodeList(treeId);
    }

    @Override
    public List<RuleTreeNodeRelation> queryRuleTreeNodeChildList(Long nodeId) {
        return ruleFilterDao.queryRuleTreeNodeChildList(nodeId);
    }

    @Override
    public RuleTreeRich queryRuleTreeRich(Long treeId) {
        RuleTree ruleTree = queryRuleTree(treeId);

        // 根据 tree_id 查询规则树节点
        List<RuleTreeNode> ruleTreeNodeList = queryRuleTreeNodeList(treeId);

        List<RuleTreeNodeVO> ruleTreeNodeVOList = ruleTreeNodeList.stream()
                .map((ruleTreeNode -> Po2VoConverter.convertRuleTreeNode(ruleTreeNode)))
                .collect(Collectors.toList());

        // 根据 node_id 查询规则树关系，并进行树化
        Map<Long, RuleTreeNodeVO> ruleTreeNodeVOMap = new HashMap<>();

        ruleTreeNodeVOList.stream()
                .forEach(ruleTreeNodeVO -> {

                    List<RuleTreeNodeRelation> ruleTreeNodeRelationList =
                            queryRuleTreeNodeChildList(ruleTreeNodeVO.getNodeId());

                    List<RuleTreeNodeRelationVO> ruleTreeNodeRelationVOList = ruleTreeNodeRelationList.stream()
                            .map((ruleTreeNodeRelation -> Po2VoConverter.convertRuleTreeNodeRelation(ruleTreeNodeRelation)))
                            .collect(Collectors.toList());

                    ruleTreeNodeVO.setRuleTreeNodeRelationVOList(ruleTreeNodeRelationVOList);

                    ruleTreeNodeVOMap.put(ruleTreeNodeVO.getNodeId(), ruleTreeNodeVO);
                });


        RuleTreeRich ruleTreeRich = new RuleTreeRich();
        ruleTreeRich.setRuleTree(Po2VoConverter.convertRuleTree(ruleTree));
        ruleTreeRich.setRuleTreeNodeMap(ruleTreeNodeVOMap);

        return ruleTreeRich;
    }
}
