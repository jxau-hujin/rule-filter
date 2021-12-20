package cn.edu.jxau.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 规则树
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleTree {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 规则树 ID
     */
    private Long treeId;

    /**
     * 规则树根节点 ID
     */
    private Long treeRootId;

    /**
     * 规则树名称
     */
    private String treeName;

    /**
     * 规则树描述
     */
    private String treeDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
