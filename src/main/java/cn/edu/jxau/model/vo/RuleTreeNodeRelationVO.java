package cn.edu.jxau.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeRelationVO implements Serializable {

    /**
     * 规则树 ID
     */
    private Long treeId;

    /**
     * 规则树父节点 ID
     */
    private Long parentId;

    /**
     * 规则树子节点 ID
     */
    private Long childId;

    /**
     * 规则比较类型
     */
    private Integer ruleType;

    /**
     * 规则比较基准值
     */
    private String ruleValue;
}
