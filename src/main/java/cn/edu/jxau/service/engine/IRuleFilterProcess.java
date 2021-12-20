package cn.edu.jxau.service.engine;

import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.model.res.RuleFilterRes;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
public interface IRuleFilterProcess {

    /**
     * 规则过滤处理器
     * @param req
     * @return
     */
    RuleFilterRes process(RuleFilterReq req);
}
