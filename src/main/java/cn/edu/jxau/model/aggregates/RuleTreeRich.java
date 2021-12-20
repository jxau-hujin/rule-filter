package cn.edu.jxau.model.aggregates;

import cn.edu.jxau.model.vo.RuleTreeNodeVO;
import cn.edu.jxau.model.vo.RuleTreeVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeRich {

    /**
     * 规则树
     */
    private RuleTreeVO ruleTree;

    /**
     * 规则树节点（加快查找速度）
     */
    private Map<Long, RuleTreeNodeVO> ruleTreeNodeMap;
}
