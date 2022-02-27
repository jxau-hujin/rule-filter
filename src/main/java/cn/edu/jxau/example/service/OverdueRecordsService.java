package cn.edu.jxau.example.service;

import cn.edu.jxau.example.model.OverdueRecords;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Service
public class OverdueRecordsService {

    public List<OverdueRecords> queryByUserId(String userId) {
        LocalDate now = LocalDate.now();

        List<OverdueRecords> overdueRecordsList = new ArrayList<>();
        overdueRecordsList.add(new OverdueRecords("1", userId, 0, now, new BigDecimal(10000), false, null));
        overdueRecordsList.add(new OverdueRecords("2", userId, 0, now, new BigDecimal(10000), false, null));
        overdueRecordsList.add(new OverdueRecords("3", userId, 0, now, new BigDecimal(999), false, now));
        overdueRecordsList.add(new OverdueRecords("4", userId, 0, now, new BigDecimal(999), true, now));
        return overdueRecordsList;
    }
}
