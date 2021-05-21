package org.arsonal.accounting.dao;

import org.arsonal.accounting.model.persistence.UserInfo;


public interface UserInfoDao {
    UserInfo getUserInfoById(Long id);
}
