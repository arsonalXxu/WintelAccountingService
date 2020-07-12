package org.arsonal.accounting.manager;

import lombok.val;
import org.arsonal.accounting.converter.persistence2Commons.UserInfoP2CConverter;
import org.arsonal.accounting.dao.UserInfoDAO;
import org.arsonal.accounting.exception.InvalidParameterException;
import org.arsonal.accounting.exception.ResourceNotFountException;
import org.arsonal.accounting.model.common.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoManagerImpl implements UserInfoManager {
    private final UserInfoDAO userInfoDAO;
    private final UserInfoP2CConverter userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(UserInfoDAO userInfoDAO, UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDAO = userInfoDAO;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        // org.arsonal.accounting.model.persistence.UserInfo，实际返回这种类型，val可以自动类型判断
        val userInfo = Optional.ofNullable(userInfoDAO.getUserInfoById(userId))
                .orElseThrow(() -> new ResourceNotFountException(String.format("User id %s was not found", userId)));
        return userInfoP2CConverter.convert(userInfo);
    }
}
