package org.arsonal.accounting.converter.commons2Service;

import com.google.common.base.Converter;
import lombok.NoArgsConstructor;
import org.arsonal.accounting.model.common.UserInfo;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserInfoC2SConverter extends Converter<UserInfo, org.arsonal.accounting.model.service.UserInfo> {
    @Override
    protected org.arsonal.accounting.model.service.UserInfo doForward(UserInfo userInfo) {
        return org.arsonal.accounting.model.service.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .build();
    }

    @Override
    protected UserInfo doBackward(org.arsonal.accounting.model.service.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .password(userInfo.getPassword())
                .username(userInfo.getUsername())
                .build();
    }
}