package cn.edu.jxau.service.engine.impl;

import cn.edu.jxau.model.aggregates.RuleTreeRich;
import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.model.res.RuleFilterRes;
import cn.edu.jxau.model.vo.RuleTreeNodeVO;
import cn.edu.jxau.repository.IRuleFilterRepository;
import cn.edu.jxau.service.engine.AbstractRuleFilterProcess;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Component
@Service
public class RuleFilterProcess extends AbstractRuleFilterProcess {

    @Resource
    private IRuleFilterRepository ruleFilterRepository;

    @Override
    public RuleFilterRes process(RuleFilterReq req) {
        Long treeId = req.getTreeId();

        RuleTreeRich ruleTreeRich = ruleFilterRepository.queryRuleTreeRich(treeId);

        RuleTreeNodeVO result = engineDecisionMaker(req, ruleTreeRich);

        return new RuleFilterRes("200", "ok", result);
    }
}
