package org.arsonal.accounting.utils;

import org.arsonal.accounting.dao.mapper.UserInfoMapper;
import org.arsonal.accounting.model.persistence.UserInfo;

import java.time.LocalDate;

public class UserInfoMapperTestImpl implements UserInfoMapper {
    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        return UserInfo.builder()
                .id(userId)
                .username("hardcore")
                .password("hardcore")
                .createTime(LocalDate.now())
                .build();
    }
}
