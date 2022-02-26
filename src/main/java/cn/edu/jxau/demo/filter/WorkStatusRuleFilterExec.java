package cn.edu.jxau.demo.filter;

import cn.edu.jxau.demo.service.UserInfoService;
import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.service.filter.AbstractRuleFilterExec;
import cn.edu.jxau.demo.model.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Component
public class WorkStatusRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "userId";

    private static Integer ACCEPT = 1;

    private static Integer REJECT = -1;

    @Resource
    private UserInfoService userInfoService;

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }
        String userId = argMap.get(KEYWORD);
        UserInfo userInfo = userInfoService.queryByUserId(userId);

        if(userInfo.getWorkStatus() == 0 || userInfo.getWorkStatus() == 1) {
            return REJECT;
        }
        return ACCEPT;
    }

}
