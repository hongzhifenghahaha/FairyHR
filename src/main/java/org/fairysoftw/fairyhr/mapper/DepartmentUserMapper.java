package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DepartmentUserMapper {
    @Select("""
            SELECT * FROM user JOIN
            (SELECT * FROM department_user
            WHERE d_id = #{d_id}) AS d_user
            ON d_user.user_id = user.id""")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "residentId", column = "resident_id"),
            @Result(property = "emailAddr", column = "email_addr"),
            @Result(property = "schedules", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "attendancesTime", column = "attendanceTime", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceTimeMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaves", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectByUserId", fetchType = FetchType.LAZY)),
    })
    List<User> selectByDepartmentId(@Param("d_id") String d_id);

    @Insert("INSERT IGNORE INTO department_user VALUES(#{user_id}, #{d_id})")
    int insert(@Param("user_id") String user_id, @Param("d_id") String d_id);

    @Delete("DELETE FROM department_user WHERE user_id = #{user_id} AND d_id = #{d_id}")
    int delete(@Param("user_id") String user_id, @Param("d_id") String d_id);
}
