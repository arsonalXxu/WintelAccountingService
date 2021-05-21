package org.arsonal.accounting.dao.mapper;

import org.arsonal.accounting.model.persistence.UserInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    @Select("SELECT id, username, password, create_time, update_time FROM hcas_userinfo WHERE id=#{userId};")
    public UserInfo getUserInfoByUserId(@Param("userId") Long userId);
}
