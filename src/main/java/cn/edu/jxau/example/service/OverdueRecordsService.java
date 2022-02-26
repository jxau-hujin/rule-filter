package cn.edu.jxau.example.service;

import cn.edu.jxau.example.model.OverdueRecords;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Service
public class OverdueRecordsService {

    public List<OverdueRecords> queryByUserId(String userId) {
        Calendar instance = Calendar.getInstance();
        instance.set(2022, 2, 25);

        List<OverdueRecords> overdueRecordsList = new ArrayList<>();
//        overdueRecordsList.add(new OverdueRecords("1", userId, 0, instance.getTime(), new BigDecimal(10000), false, null));
//        overdueRecordsList.add(new OverdueRecords("2", userId, 0, instance.getTime(), new BigDecimal(10000), false, null));
//        overdueRecordsList.add(new OverdueRecords("3", userId, 0, instance.getTime(), new BigDecimal(1000), true, instance.getTime()));
//        overdueRecordsList.add(new OverdueRecords("4", userId, 0, instance.getTime(), new BigDecimal(1000), true, instance.getTime()));
        return overdueRecordsList;
    }
}
