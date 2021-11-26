package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.User;

import java.util.List;

@Mapper
public interface DepartmentManagerMapper {
    @Select("SELECT * FROM user JOIN " +
            "(SELECT * FROM department_manager " +
            "WHERE d_id = #{d_id}) AS d_manager " +
            "ON d_manager.manager_id = user.id")
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
}
