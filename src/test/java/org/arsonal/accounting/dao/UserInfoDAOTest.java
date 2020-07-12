package org.arsonal.accounting.dao;

import lombok.val;
import org.arsonal.accounting.dao.mapper.UserInfoMapper;
import org.arsonal.accounting.model.persistence.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 加入下面这个注解就不用在setup中写MockitoAnnotations.initMocks(this);
@ExtendWith(MockitoExtension.class)
class UserInfoDAOTest {

    private UserInfoDAO userInfoDAO;
    @Mock
    private UserInfoMapper userInfoMapper;

    @BeforeEach
    public void setup() {
        userInfoDAO = new UserInfoDAOImpl(userInfoMapper);
    }

    @Test
    void getUserInfoById() {
        //Arrange
        val userId = 100L;
        val username = "hardcore";
        val password = "password";
        val now = LocalDate.now();
        val userInfo = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .createTime(now)
                .build();
        Mockito.doReturn(userInfo).when(userInfoMapper).getUserInfoByUserId(userId);
        // Act
        val result = userInfoDAO.getUserInfoById(userId);

        // Assert
        assertEquals(userInfo, result);
        Mockito.verify(userInfoMapper).getUserInfoByUserId(userId);
    }
}