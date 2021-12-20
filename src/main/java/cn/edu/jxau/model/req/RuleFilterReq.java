package cn.edu.jxau.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2021/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleFilterReq implements Serializable {

    /**
     * 使用的规则树 ID
     */
    private Long treeId;

    /**
     * 参数 Map
     */
    private Map<String, String> argMap;
}
