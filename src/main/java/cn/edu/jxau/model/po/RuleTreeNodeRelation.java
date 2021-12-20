package cn.edu.jxau.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 规则树关系
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeRelation {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 规则树 ID
     */
    private Long treeId;

    /**
     * 父节点 ID
     */
    private Long parentId;

    /**
     * 子节点 ID
     */
    private Long childId;

    /**
     * 规则类型
     * 0：==
     * 1：>
     * 2: <
     * 3: >=
     * 4: <=
     * 5: unknown
     */
    private Integer ruleType;

    /**
     * 规则比较基准值
     */
    private String ruleValue;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
