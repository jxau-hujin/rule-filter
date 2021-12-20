package cn.edu.jxau.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeVO implements Serializable {

    /**
     * 规则树 ID
     */
    private Long treeId;

    /**
     * 规则树节点 ID
     */
    private Long nodeId;

    /**
     * 规则树节点类型
     */
    private Integer nodeType;

    /**
     * 规则树节点值
     */
    private String nodeValue;

    /**
     * 规则过滤器 Key
     */
    private String ruleKey;

    /**
     * 规则过滤器描述
     */
    private String ruleDesc;

    /**
     * 规则树节点关系
     */
    private List<RuleTreeNodeRelationVO> ruleTreeNodeRelationVOList;
}
