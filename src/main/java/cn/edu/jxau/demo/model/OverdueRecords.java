package cn.edu.jxau.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @description: 逾期记录
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverdueRecords {

    private String id;

    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 0 个人客户
     * 1 企业客户
     */
    private Integer userType;

    /**
     * 违约日期
     */
    private LocalDate overdueDate;

    /**
     * 违约金额
     */
    private BigDecimal overdueBalance;

    /**
     * 是否支付
     */
    private Boolean isPaid;

    /**
     * 还清日期
     */
    private LocalDate paidDate;

}
