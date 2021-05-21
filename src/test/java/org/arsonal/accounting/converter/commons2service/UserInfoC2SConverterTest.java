package org.arsonal.accounting.converter.commons2service;

import lombok.val;
import org.arsonal.accounting.model.common.UserInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserInfoC2SConverterTest {
    private UserInfoC2SConverter converter = new UserInfoC2SConverter();

    @Test
    void testDoForward() {
        // Arrange
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";
        val userInfoCommon = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        // Act
        val result = converter.convert(userInfoCommon);

        // Assert
        Assertions.assertThat(result)
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);
    }

    @Test
    void testDoBackward() {
        // Arrange
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";
        val userInfoCommon = org.arsonal.accounting.model.service.UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        // Act
        val result = converter.reverse().convert(userInfoCommon);

        // Assert
        Assertions.assertThat(result)
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);
    }
}