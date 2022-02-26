package cn.edu.jxau.controller;

import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.model.res.RuleFilterRes;
import cn.edu.jxau.service.engine.IRuleFilterProcess;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@RestController
public class RuleFilterController {

    @Resource
    private IRuleFilterProcess ruleFilterProcess;

    @RequestMapping(path = "/rule/filter", method = RequestMethod.GET)
    public RuleFilterRes ruleFilter(Long treeId, String age) {
        Map<String, String> map = new HashMap<>(16);
        map.put("age", age);
        RuleFilterRes result = ruleFilterProcess.process(new RuleFilterReq(treeId, map));
        return result;
    }

    @RequestMapping(path = "/rule/filter/loan", method = RequestMethod.GET)
    public RuleFilterRes loanRuleFilter(Long treeId, String userId) {
        Map<String, String> map = new HashMap<>(16);
        map.put("userId", userId);
        RuleFilterRes result = ruleFilterProcess.process(new RuleFilterReq(treeId, map));
        return result;
    }

    @RequestMapping(path = "/rule/filter/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "Welcome Rule-filter";
    }
}
