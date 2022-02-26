package cn.edu.jxau.demo.service;

import cn.edu.jxau.demo.model.UserInfo;
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
        userInfo.setAge(18);
        userInfo.setWorkStatus(2);

        return userInfo;
    }
}
