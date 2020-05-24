package org.arsonal.accounting.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.arsonal.accounting.converter.commons2Service.UserInfoC2SConverter;
import org.arsonal.accounting.manager.UserInfoManager;
import org.arsonal.accounting.model.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@Slf4j
public class UserController {
    // 1.不要有过多的处理逻辑；
    // 2.参数校验越早越好；
    private UserInfoManager userInfoManager;
    private final UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/{id}")
    public UserInfo getUserInfoByUserId(@PathVariable("id") Long userId) {
        log.debug("Get user info by user id {}", userId);
        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return userInfoC2SConverter.convert(userInfo);
    }
}
