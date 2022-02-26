package cn.edu.jxau.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户信息
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String id;

    /**
     *  0 无业
     *  1 失业
     *  2 就业
     */
    private Integer workStatus;

    private Integer age;
}
