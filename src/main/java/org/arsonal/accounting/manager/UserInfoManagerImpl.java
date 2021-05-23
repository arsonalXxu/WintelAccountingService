package org.arsonal.accounting.manager;

import org.arsonal.accounting.converter.persistence2commons.UserInfoP2CConverter;
import org.arsonal.accounting.dao.UserInfoDao;
import org.arsonal.accounting.exception.InvalidParameterException;
import org.arsonal.accounting.exception.ResourceNotFountException;
import org.arsonal.accounting.model.common.UserInfo;


import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoManagerImpl implements UserInfoManager {
    public static final int HASH_ITERATIONS = 1000;
    private final UserInfoDao userInfoDao;
    private final UserInfoP2CConverter userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(UserInfoDao userInfoDao, UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        // org.arsonal.accounting.model.persistence.UserInfo，实际返回这种类型，val可以自动类型判断
        val userInfo = Optional.ofNullable(userInfoDao.getUserInfoById(userId))
            .orElseThrow(() -> new ResourceNotFountException(String.format("User id %s was not found", userId)));
        return userInfoP2CConverter.convert(userInfo);
    }

    @Override
    public void login(String username, String password) {
        // Get subject
        Subject subject = SecurityUtils.getSubject();
        // Construct token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        // login
        subject.login(usernamePasswordToken);
    }

    @Override
    public UserInfo getUserInfoByUserName(String username) {
        val userInfo = Optional.ofNullable(userInfoDao.getUserInfoByUserName(username))
            .orElseThrow(() -> new ResourceNotFountException(String.format("User name %s was not found", username)));
        return userInfoP2CConverter.convert(userInfo);
    }

    @Override
    public UserInfo register(String username, String password) {
        final org.arsonal.accounting.model.persistence.UserInfo userInfo =
            userInfoDao.getUserInfoByUserName(username);

        if (userInfo != null) {
            throw new InvalidParameterException(String.format("The user %s was registered.", username));
        }

        // set random salt
        String salt = UUID.randomUUID().toString();
        String encryptedPassword = new Sha256Hash(password, salt, HASH_ITERATIONS).toBase64();
        val newUserInfo = org.arsonal.accounting.model.persistence.UserInfo.builder()
            .username(username)
            .password(encryptedPassword)
            .salt(salt)
            .createTime(LocalDate.now())
            .build();

        userInfoDao.createNewUser(newUserInfo);

        return userInfoP2CConverter.convert(newUserInfo);
    }
}
