package com.rai.his.mapper;

import com.rai.his.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select id, username, password, type from users where removed = 0")
    List<User> getAll();

    @Select("select id, username, password, type from users where removed = 0 and username = #{username}")
    User findByUsername(@Param("username") String username);
}
