package org.arsonal.accounting.converter.persistence2Commons;

import com.google.common.base.Converter;
import lombok.NoArgsConstructor;
import org.arsonal.accounting.model.persistence.UserInfo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
public class UserInfoConverter extends Converter<UserInfo, org.arsonal.accounting.model.common.UserInfo> {
    @Override
    protected org.arsonal.accounting.model.common.UserInfo doForward(UserInfo userInfo) {
        return org.arsonal.accounting.model.common.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(org.arsonal.accounting.model.common.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .createTime(LocalDate.now())
                .updateTime(LocalDate.now())
                .build();
    }
}
