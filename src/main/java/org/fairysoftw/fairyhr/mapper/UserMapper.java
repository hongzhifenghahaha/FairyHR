package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.model.User;

import java.util.List;

/**
 * 映射类,实现{@link User}类与数据库中的user表的映射。
 *
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    /**
     * 选出user表中的所有行，并映射成{@link User}对象列表。
     *
     * @return user表中所有用户。
     */
    @Select("SELECT * FROM user")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "password", column = "passwd"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "residentId", column = "resident_id"),
            @Result(property = "emailAddr", column = "email_addr"),
            @Result(property = "schedules", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "attendanceTimes", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceTimeMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaves", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceLeaveMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.LeaveRequestMapper.selectByUserId", fetchType = FetchType.LAZY)),
    })
    List<User> selectAll();

    /**
     * 选出user表中id=#{id}的行，并映射成{@link User}对象。
     *
     * @param id 要选出的用户的id。
     * @return user表中id=#{id}的用户。
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "password", column = "passwd"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "residentId", column = "resident_id"),
            @Result(property = "emailAddr", column = "email_addr"),
            @Result(property = "schedules", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "attendanceTimes", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceTimeMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaves", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceLeaveMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.LeaveRequestMapper.selectByUserId", fetchType = FetchType.LAZY)),
    })
    User selectById(@Param("id") String id);

    /**
     * 把{@link User}对象插入到user表中。
     *
     * @param user 要插入的{@link User}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("""
            INSERT IGNORE INTO
            user(id, user_name, phone_number, passwd, resident_id, email_addr, address, position, deleted)
            VALUES(#{id}, #{name}, #{phoneNumber}, #{password}, #{residentId}, #{emailAddr}, #{address}, #{position}, #{deleted})""")
    int insert(User user);

    /**
     * 删除user表中id=#{id}的行。
     *
     * @param id 要删除的用户的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    /**
     * 根据{@link User}对象的值更新对应的user表中的行。
     *
     * @param user 用于更新行的User对象。
     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
     */
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
