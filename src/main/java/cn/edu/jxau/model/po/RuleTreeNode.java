package cn.edu.jxau.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:规则树节点
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNode {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 规则树 ID
     */
    private Long treeId;

    /**
     * 规则树节点 ID
     */
    private Long nodeId;

    /**
     * 节点类型 0: 非叶子节点 1：叶子节点
     */
    private Integer nodeType;

    /**
     * 节点值 nodeType == 1 时成立
     */
    private String nodeValue;

    /**
     * 对应规则过滤器 Key
     */
    private String ruleKey;

    /**
     * 对应规则过滤器描述
     */
    private String ruleDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
