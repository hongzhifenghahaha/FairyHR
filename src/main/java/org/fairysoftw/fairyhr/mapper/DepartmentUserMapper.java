package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DepartmentUserMapper {
    @Select("SELECT * FROM user JOIN " +
            "(SELECT * FROM department_user " +
            "WHERE d_id = #{d_id}) AS d_user " +
            "ON d_user.user_id = user.id")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "residentId", column = "resident_id"),
            @Result(property = "emailAddr", column = "email_addr"),
            @Result(property = "schedules", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectScheduleByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "attendancesTime", column = "attendanceTime", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceTimeMapper.selectAttendanceTimeByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaves", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectScheduleByUserId", fetchType = FetchType.LAZY)),
    })
    List<User> selectByDepartmentId(@Param("d_id") String d_id);

    @Insert("INSERT IGNORE INTO department_user VALUES(#{user_id}, #{d_id})")
    int insert(@Param("user_id") String user_id, @Param("d_id") String d_id);
}
