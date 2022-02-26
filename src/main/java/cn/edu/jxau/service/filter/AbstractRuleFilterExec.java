package cn.edu.jxau.service.filter;

import cn.edu.jxau.model.vo.RuleTreeNodeRelationVO;
import org.apache.dubbo.common.utils.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    protected boolean check(Map<String, String> argMap, String keyword) {
        if(argMap.isEmpty()) {
            return false;
        }
        if(StringUtils.isBlank(argMap.get(keyword))) {
            return false;
        }
        return true;

    }


    @Override
    public Long filter(Integer matterValue, List<RuleTreeNodeRelationVO> ruleTreeNodeRelationVOList) {
        if(Objects.equals(Integer.MAX_VALUE, matterValue)) {
            return NOT_MATCH;
        }

        for(RuleTreeNodeRelationVO ruleTreeNodeRelationVO : ruleTreeNodeRelationVOList) {
            if(decision(ruleTreeNodeRelationVO, matterValue)) {
                return ruleTreeNodeRelationVO.getChildId();
            }
        }
        return NOT_MATCH;
    }

    protected boolean decision(RuleTreeNodeRelationVO ruleTreeNodeRelationVO, Integer matterValue) {
        Integer ruleType = ruleTreeNodeRelationVO.getRuleType();
        Integer ruleVal = Integer.valueOf(ruleTreeNodeRelationVO.getRuleValue());
        switch (ruleType) {
            case EQUALS:
                return ruleVal.equals(matterValue);
            case GREATER_THEN:
                return ruleVal.compareTo(matterValue) < 0;
            case LESS_THEN:
                return ruleVal.compareTo(matterValue) > 0;
            case GREATER_THEN_OR_EQUALS:
                return ruleVal.compareTo(matterValue) <= 0;
            case LESS_THEN_OR_EQUALS:
                return ruleVal.compareTo(matterValue) >= 0;
            default:
                return false;
        }
    }
}
