package org.arsonal.accounting.dao;

import lombok.RequiredArgsConstructor;
import org.arsonal.accounting.dao.mapper.UserInfoMapper;
import org.arsonal.accounting.model.persistence.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoDAOImpl implements UserInfoDAO {

    private final UserInfoMapper userInfoMapper;

    // 这里userInfoMapper注入会提示错误，可以用上面的注解@RequiredArgsConstructor(onConstructor = @__(@Autowired))
    // 消除，其实这个报错不影响运行；
//    @Autowired
//    public UserInfoDAOImpl(UserInfoMapper userInfoMapper) {
//        this.userInfoMapper = userInfoMapper;
//    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.getUserInfoByUserId(id);
    }
}
