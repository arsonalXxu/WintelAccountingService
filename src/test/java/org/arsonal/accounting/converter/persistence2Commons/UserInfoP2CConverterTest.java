package org.arsonal.accounting.converter.persistence2Commons;

import lombok.val;
import org.arsonal.accounting.model.persistence.UserInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UserInfoP2CConverterTest {
    private UserInfoP2CConverter converter = new UserInfoP2CConverter();

    @Test
    public void testDoForward() {
        // Arrange
        val userId = 100L;
        val username = "hardcore";
        val password = "password";
        val now = LocalDate.now();

        val userInfoPersistence = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .createTime(now)
                .build();

        // Act
        val result = converter.convert(userInfoPersistence);

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
        val password = "password";

        val userInfoCommons = org.arsonal.accounting.model.common.UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        // Act
        val result = converter.reverse().convert(userInfoCommons);

        // Assert
        Assertions.assertThat(result)
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password)
                .hasFieldOrPropertyWithValue("createTime", LocalDate.now());
    }
}