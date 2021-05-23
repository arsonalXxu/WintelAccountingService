package org.arsonal.accounting.controller;

import org.arsonal.accounting.converter.commons2service.UserInfoC2SConverter;
import org.arsonal.accounting.manager.UserInfoManager;
import org.arsonal.accounting.model.service.UserInfo;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1.0/users")
@Slf4j
public class UserController {
    // 1.不要有过多的处理逻辑；
    // 2.参数校验越早越好；
    private UserInfoManager userInfoManager;
    private final UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    /**
     * Get user information by specific user id.
     * @param userId the user id.
     * @return user info response entity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfoByUserId(@PathVariable("id") Long userId) {
        log.debug("Get user info by user id {}", userId);
        //try {

        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfo));
        /*} catch (ResourceNotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder()
                            .errorCode("USER_NOT_FOUND")
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .errorType(ServiceException.ErrorType.Client)
                            .message(e.getMessage())
                            .build());

        }*/
    }

    @PostMapping()
    public ResponseEntity<UserInfo> register(@RequestParam("username") String username,
                                             @RequestParam("password") String password) {
        val userInfo = userInfoManager.register(username, password);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfo));
    }
}
