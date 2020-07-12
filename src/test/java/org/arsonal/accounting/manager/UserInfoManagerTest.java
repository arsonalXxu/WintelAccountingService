package org.arsonal.accounting.manager;

import org.arsonal.accounting.converter.persistence2Commons.UserInfoP2CConverter;
import org.arsonal.accounting.dao.UserInfoDAO;
import org.arsonal.accounting.dao.UserInfoDAOImpl;
import org.arsonal.accounting.dao.mapper.UserInfoMapper;
import org.arsonal.accounting.exception.InvalidParameterException;
import org.arsonal.accounting.model.common.UserInfo;
import org.arsonal.accounting.utils.UserInfoMapperTestImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserInfoManagerTest {
    UserInfoManager userInfoManager;

    @BeforeEach
    void setUp() {
        UserInfoMapper userInfoMapper = new UserInfoMapperTestImpl();
        UserInfoDAO userInfoDAO = new UserInfoDAOImpl(userInfoMapper);
        UserInfoP2CConverter userInfoP2CConverter = new UserInfoP2CConverter();
        userInfoManager = new UserInfoManagerImpl(userInfoDAO, userInfoP2CConverter);
    }

    @Test
    void testGetUserInfoByUserId() {
        // Arrange
        long userId = 1L;
        // Act
        UserInfo userInfo = userInfoManager.getUserInfoByUserId(userId);
        // Assert
        Assertions.assertEquals(userId, userInfo.getId());
        Assertions.assertEquals("hardcore", userInfo.getUsername());
        Assertions.assertEquals("hardcore", userInfo.getPassword());


    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() {
        // Arrange
        long userId = -1L;
        // Act
        // Assert
        assertThrows(InvalidParameterException.class, () -> userInfoManager.getUserInfoByUserId(userId));
    }

    @Test
    void testListAddMethod() {
        // mock creation
        List mockedList = mock(List.class);

        when(mockedList.get(0)).thenReturn("hello ");
        when(mockedList.get(1)).thenReturn("hardcore!");
        when(mockedList.get(2)).thenThrow(new InvalidParameterException("invalid params"));
        doReturn("hello world!").when(mockedList).get(3);
//        mockedList.get(999);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();
        mockedList.clear();
        mockedList.clear();

        // verify判断，mockList调用add,clear方法
        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        // 判断mockList调用clear方法3次
        verify(mockedList, times(3)).clear();
//        verify(mockedList).clear();

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));
//        Assertions.assertThrows(InvalidParameterException.class, () -> mockedList.get(2));
//        verify(mockedList, never()).get(999);

        verify(mockedList, times(2)).get(anyInt());
    }
}
