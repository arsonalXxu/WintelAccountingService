package org.arsonal.accounting.manager;

import org.arsonal.accounting.model.common.UserInfo;

// 也可以叫Service，真正处理逻辑的地方
public interface UserInfoManager {

    UserInfo getUserInfoByUserId(Long userId);
}
