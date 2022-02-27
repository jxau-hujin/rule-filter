package cn.edu.jxau.example.service;

import cn.edu.jxau.example.model.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author：gezellighied.h
 * @date: 2022/2/26
 */
@Service
public class UserInfoService {

    public UserInfo queryByUserId(String userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(20);
        userInfo.setWorkStatus(2);

        return userInfo;
    }
}
