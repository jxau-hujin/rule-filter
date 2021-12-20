package cn.edu.jxau.service.engine;

import cn.edu.jxau.service.filter.IRuleFilterExec;
import cn.edu.jxau.service.filter.impl.AgeRuleFilterExec;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
public class RuleFilterConfig {

    protected static Map<String, IRuleFilterExec> ruleFilterExecMap = new ConcurrentHashMap<>();

    @Resource
    private AgeRuleFilterExec ageRuleFilterExec;

    @PostConstruct
    public void init() {
        ruleFilterExecMap.put("age", ageRuleFilterExec);
    }
}
