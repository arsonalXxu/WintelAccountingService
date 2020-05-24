package org.arsonal.accounting.dao;

import org.arsonal.accounting.model.persistence.UserInfo;


public interface UserInfoDAO {
    UserInfo getUserInfoById(Long id);
}
