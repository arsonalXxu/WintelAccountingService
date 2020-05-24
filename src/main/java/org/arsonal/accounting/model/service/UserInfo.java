package org.arsonal.accounting.model.service;

import lombok.Builder;
import lombok.Data;

// Service这层是给前端用的
@Data
@Builder
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
