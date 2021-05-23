package org.arsonal.accounting.model.common;

import lombok.Builder;
import lombok.Data;

// 给Service/manager内部流转使用，即Service/manager层
@Data
@Builder
public class UserInfo {
    private Long id;
    private String username;
    private String password;
    private String salt;
}
