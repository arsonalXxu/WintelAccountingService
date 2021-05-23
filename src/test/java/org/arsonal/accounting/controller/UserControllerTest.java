package org.arsonal.accounting.controller;

import lombok.val;
import org.arsonal.accounting.converter.commons2service.UserInfoC2SConverter;
import org.arsonal.accounting.manager.UserInfoManager;
import org.arsonal.accounting.model.common.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserInfoManager userInfoManager;
    private UserInfoC2SConverter userInfoC2SConverter = new UserInfoC2SConverter();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userInfoManager, userInfoC2SConverter)).build();
    }
    @Test
    void testGetUserInfoByUserId() throws Exception {
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";
        val userInfo = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();
        Mockito.doReturn(userInfo).when(userInfoManager).getUserInfoByUserId(userId);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/{id}", "100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":100,\"username\":\"hardcore\",\"password\":\"hardcore\"}"));

        Mockito.verify(userInfoManager).getUserInfoByUserId(userId);
    }

}