package org.arsonal.accounting.manager;

import org.arsonal.accounting.model.common.UserInfo;

// 也可以叫Service，真正处理逻辑的地方
public interface UserInfoManager {
    /**
     * Get user information by user id.
     * @param userId the specific user id.
     * @return
     */
    UserInfo getUserInfoByUserId(Long userId);

    /**
     * Login with username and password.
     * @param username username
     * @param password the related password
     */
    void login(String username, String password);

    /**
     * Get user information by user name.
     * @param username the specific user name
     * @return
     */
    UserInfo getUserInfoByUserName(String username);

    /***
     * Register new user with username and password.
     * @param username username
     * @param password the related password
     * @return
     */
    UserInfo register(String username, String password);
}
