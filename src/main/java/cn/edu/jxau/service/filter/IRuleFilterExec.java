package cn.edu.jxau.service.filter;

import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.model.vo.RuleTreeNodeRelationVO;

import java.util.List;

/**
 * @description: 规则过滤执行器
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
public interface IRuleFilterExec {

    /**
     * 规则过滤
     * @param matterValue
     * @param ruleTreeNodeRelationVOList
     * @return
     */
    Long filter(Integer matterValue, List<RuleTreeNodeRelationVO> ruleTreeNodeRelationVOList);

    /**
     * arg 参数转换
     * @param req
     * @return
     */
    Integer matterValue(RuleFilterReq req);
}
