package cn.edu.jxau.service.filter;

import cn.edu.jxau.model.vo.RuleTreeNodeRelationVO;

import java.util.List;

/**
 * @description: 抽象规则过滤器
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
public abstract class AbstractRuleFilterExec implements IRuleFilterExec{

    private static final Long NOT_MATCH = -1L;

    /**
     * ruleType （等于、大于、小于、大于等于、小于等于）
     */
    private static final int EQUALS = 0;
    private static final int GREATER_THEN = 1;
    private static final int LESS_THEN = 2;
    private static final int GREATER_THEN_OR_EQUALS = 3;
    private static final int LESS_THEN_OR_EQUALS = 4;

    @Override
    public Long filter(String matterValue, List<RuleTreeNodeRelationVO> ruleTreeNodeRelationVOList) {
        for(RuleTreeNodeRelationVO ruleTreeNodeRelationVO : ruleTreeNodeRelationVOList) {
            if(decision(ruleTreeNodeRelationVO, matterValue)) {
                return ruleTreeNodeRelationVO.getChildId();
            }
        }
        return NOT_MATCH;
    }

    protected boolean decision(RuleTreeNodeRelationVO ruleTreeNodeRelationVO, String matterValue) {
        Integer ruleType = ruleTreeNodeRelationVO.getRuleType();
        Integer ruleVal = Integer.valueOf(ruleTreeNodeRelationVO.getRuleValue());
        Integer matterVal = Integer.valueOf(matterValue);
        switch (ruleType) {
            case EQUALS:
                return ruleVal.equals(matterVal);
            case GREATER_THEN:
                return ruleVal.compareTo(matterVal) < 0;
            case LESS_THEN:
                return ruleVal.compareTo(matterVal) > 0;
            case GREATER_THEN_OR_EQUALS:
                return ruleVal.compareTo(matterVal) <= 0;
            case LESS_THEN_OR_EQUALS:
                return ruleVal.compareTo(matterVal) >= 0;
            default:
                return false;
        }
    }
}
