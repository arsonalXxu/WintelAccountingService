package org.arsonal.accounting.manager;

import lombok.val;
import org.arsonal.accounting.converter.persistence2Commons.UserInfoP2CConverter;
import org.arsonal.accounting.dao.UserInfoDAO;
import org.arsonal.accounting.exception.ResourceNotFountException;
import org.arsonal.accounting.model.persistence.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class UserInfoManagerTest {
    UserInfoManager userInfoManager;
    @Mock
    UserInfoDAO userInfoDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        UserInfoP2CConverter userInfoP2CConverter = new UserInfoP2CConverter();
        userInfoManager = new UserInfoManagerImpl(userInfoDAO, userInfoP2CConverter);
    }

    @Test
    void testGetUserInfoByUserId() {
        // Arrange
        val userId = 1L;
        val username = "hardcore";
        val password = "hardcore";
        val now = LocalDate.now();

        val userInfo = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .createTime(now)
                .build();
        // Act
        doReturn(userInfo).when(userInfoDAO).getUserInfoById(userId);
        val result = userInfoManager.getUserInfoByUserId(userId);
        // Assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password)
                .hasFieldOrPropertyWithValue("id", userId);

        verify(userInfoDAO).getUserInfoById(userId);

    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() {
        // Arrange
        long userId = -1L;
        // Act
        // Assert
        doReturn(null).when(userInfoDAO).getUserInfoById(userId);
        assertThrows(ResourceNotFountException.class, () -> userInfoManager.getUserInfoByUserId(userId));
        verify(userInfoDAO).getUserInfoById(userId);
    }
}