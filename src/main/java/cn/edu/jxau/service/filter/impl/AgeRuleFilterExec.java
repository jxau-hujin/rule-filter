package cn.edu.jxau.service.filter.impl;

import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.service.filter.AbstractRuleFilterExec;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 年龄规则过滤器
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
public class AgeRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "age";

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }
        String matterValue = argMap.get(KEYWORD);

        return Integer.valueOf(matterValue);
    }
}
