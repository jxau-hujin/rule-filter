package cn.edu.jxau.demo.filter;

import cn.edu.jxau.demo.service.DefaultersService;
import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.service.filter.AbstractRuleFilterExec;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Component
public class DefaultersRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "userId";

    private static Integer ACCEPT = 1;

    private static Integer REJECT = -1;

    @Resource
    private DefaultersService defaultersService;

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }
        String userId = argMap.get(KEYWORD);

        if(defaultersService.isDefaulters(userId)) {
            return REJECT;
        }
        return ACCEPT;
    }
}
