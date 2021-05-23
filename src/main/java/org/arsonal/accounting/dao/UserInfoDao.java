package org.arsonal.accounting.dao;

import org.arsonal.accounting.model.persistence.UserInfo;


public interface UserInfoDao {
    /**
     * Get user information by user id.
     * @param id the specific user id.
     * @return
     */
    UserInfo getUserInfoById(Long id);

    /****
     * Get user information by user name.
     * @param username the specific user name
     * @return
     */
    UserInfo getUserInfoByUserName(String username);

    /**
     * create new user.
     * @param userInfo user info
     */
    void createNewUser(UserInfo userInfo);
}
