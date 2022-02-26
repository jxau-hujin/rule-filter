package cn.edu.jxau.demo.filter;

import cn.edu.jxau.demo.model.OverdueRecords;
import cn.edu.jxau.demo.service.OverdueRecordsService;
import cn.edu.jxau.model.req.RuleFilterReq;
import cn.edu.jxau.service.filter.AbstractRuleFilterExec;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Component
public class OverdueRecordsRuleFilterExec extends AbstractRuleFilterExec {

    private static final String KEYWORD = "userId";

    private static BigDecimal limitBalance = new BigDecimal(1000);

    private static Long limitCount = 2L;

    private static Integer ACCEPT = 1;

    private static Integer REJECT = -1;


    @Resource
    private OverdueRecordsService overdueRecordsService;

    @Override
    public Integer matterValue(RuleFilterReq req) {
        Map<String, String> argMap = req.getArgMap();

        if(!check(argMap, KEYWORD)) {
            return Integer.MAX_VALUE;
        }
        String userId = argMap.get(KEYWORD);
        List<OverdueRecords> overdueRecords = overdueRecordsService.queryByUserId(userId);


        Long count = overdueRecords.stream()
                .filter((overdueRecord -> rule(overdueRecord)))
                .count();
        if(count > limitCount) {
            return REJECT;
        }

        return ACCEPT;
    }

    private boolean rule(OverdueRecords overdueRecord) {

        // 近三年逾期记录
        LocalDate lastThreeYears = LocalDate.now().minusYears(3);

        LocalDate overdueDate = overdueRecord.getOverdueDate();

        if(overdueDate.isBefore(lastThreeYears)) {
            return false;
        }

        BigDecimal overdueBalance = overdueRecord.getOverdueBalance();

        // 已还清 && 逾期金额小于 1000
        if(overdueRecord.getIsPaid() && overdueBalance.compareTo(limitBalance) < 0) {
            // 逾期 3 天之外还清
            LocalDate lastThreeDays = overdueDate.plusDays(3);

            LocalDate paidDate = overdueRecord.getPaidDate();

            if(paidDate.isBefore(lastThreeDays)) {
                return false;
            }
        }

        return true;
    }
}
