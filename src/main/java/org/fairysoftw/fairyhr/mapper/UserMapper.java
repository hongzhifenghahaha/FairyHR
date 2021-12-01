package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import org.fairysoftw.fairyhr.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "password", column = "passwd"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "residentId", column = "resident_id"),
            @Result(property = "emailAddr", column = "email_addr"),
            @Result(property = "schedules", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "attendancesTime", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceTimeMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaves", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceLeaveMapper.selectByUserId", fetchType = FetchType.LAZY)),
    })
    List<User> selectAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "password", column = "passwd"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "residentId", column = "resident_id"),
            @Result(property = "emailAddr", column = "email_addr"),
            @Result(property = "schedules", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "attendancesTime", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceTimeMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaves", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceLeaveMapper.selectByUserId", fetchType = FetchType.LAZY)),
    })
    User selectById(@Param("id") String id);

    @Insert("""
            INSERT IGNORE INTO
            user(id, user_name, phone_number, passwd, resident_id, email_addr, address, position, deleted)
            VALUES(#{id}, #{name}, #{phoneNumber}, #{password}, #{residentId}, #{emailAddr}, #{address}, #{position}, #{deleted})""")
    int insert(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    @Update("""
            UPDATE user SET
            user_name = #{name},
            phone_number = #{phoneNumber},
            passwd = #{password},
            resident_id = #{residentId},
            email_addr = #{emailAddr},
            address = #{address},
            position = #{position},
            deleted = #{deleted}
            WHERE id = #{id}""")
    int update(User user);
}
