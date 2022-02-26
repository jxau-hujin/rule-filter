package cn.edu.jxau.demo.filter;

import cn.edu.jxau.demo.service.UserInfoService;
import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.service.filter.AbstractRuleFilterExec;
import cn.edu.jxau.demo.model.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 个人客户年龄过滤器
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Component
public class AgeRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "userId";

    @Resource
    private UserInfoService userInfoService;

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }

        UserInfo userInfo = userInfoService.queryByUserId(argMap.get(KEYWORD));

        return Integer.valueOf(userInfo.getAge());
    }
}


