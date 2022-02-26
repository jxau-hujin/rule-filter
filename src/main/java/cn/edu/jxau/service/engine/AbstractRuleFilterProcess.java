package cn.edu.jxau.service.engine;


import cn.edu.jxau.model.aggregates.RuleTreeRich;
import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.model.vo.RuleTreeNodeRelationVO;
import cn.edu.jxau.model.vo.RuleTreeNodeVO;
import cn.edu.jxau.model.vo.RuleTreeVO;
import cn.edu.jxau.service.filter.IRuleFilterExec;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
public abstract class AbstractRuleFilterProcess extends RuleFilterConfig implements IRuleFilterProcess{

    protected RuleTreeNodeVO engineDecisionMaker(RuleFilterReq req, RuleTreeRich ruleTreeRich) {
        RuleTreeVO ruleTreeRoot = ruleTreeRich.getRuleTree();
        Map<Long, RuleTreeNodeVO> ruleTreeNodeMap = ruleTreeRich.getRuleTreeNodeMap();

        Long rootNodeId = ruleTreeRoot.getTreeRootId();
        RuleTreeNodeVO ruleTreeNodeVO = ruleTreeNodeMap.get(rootNodeId);

        while(ruleTreeNodeVO.getNodeType() != 1) {

            String ruleKey = ruleTreeNodeVO.getRuleKey();
            IRuleFilterExec iRuleFilterExec = ruleFilterExecMap.get(ruleKey);

            Integer matterValue = iRuleFilterExec.matterValue(req);
            List<RuleTreeNodeRelationVO> ruleTreeNodeRelationVOList = ruleTreeNodeVO.getRuleTreeNodeRelationVOList();

            Long nextNodeId = iRuleFilterExec.filter(matterValue, ruleTreeNodeRelationVOList);
            if(nextNodeId == null || nextNodeId == -1) {
                return null;
            }
            ruleTreeNodeVO = ruleTreeNodeMap.get(nextNodeId);
        }

        return ruleTreeNodeVO;
    }
}
