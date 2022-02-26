package cn.edu.jxau.service.engine;

import cn.edu.jxau.service.filter.IRuleFilterExec;
import cn.edu.jxau.example.filter.AgeRuleFilterExec;
import cn.edu.jxau.example.filter.DefaultersRuleFilterExec;
import cn.edu.jxau.example.filter.OverdueRecordsRuleFilterExec;
import cn.edu.jxau.example.filter.WorkStatusRuleFilterExec;

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

    @Resource
    private DefaultersRuleFilterExec defaultersRuleFilterExec;

    @Resource
    private OverdueRecordsRuleFilterExec overdueRecordsRuleFilterExec;

    @Resource
    private WorkStatusRuleFilterExec workStatusRuleFilterExec;

    @PostConstruct
    public void init() {
        ruleFilterExecMap.put("ageRule", ageRuleFilterExec);
        ruleFilterExecMap.put("defaultersRule", defaultersRuleFilterExec);
        ruleFilterExecMap.put("overdueRecordsRule", overdueRecordsRuleFilterExec);
        ruleFilterExecMap.put("workStatusRule", workStatusRuleFilterExec);
    }
}
