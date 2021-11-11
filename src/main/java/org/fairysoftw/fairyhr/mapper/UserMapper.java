package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import org.fairysoftw.fairyhr.entity.User;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    @Select("select * from User")
    List<User> selectAll();

    @Select("select * from user where id=#{id}")
    User selectById(@Param("id") String id);

    int insert(@Param("user") User user);

    int deleteById(@Param("id") String id);

    int update(@Param("user") User user);
}
