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
public class RuleTreeVO implements Serializable {

    /**
     * 规则树 ID
     */
    private Long treeId;

    /**
     * 规则树 根节点 ID
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
}
