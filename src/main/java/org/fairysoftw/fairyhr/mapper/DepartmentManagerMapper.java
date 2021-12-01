package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.User;

import java.util.List;

@Mapper
public interface DepartmentManagerMapper {
    @Select("""
            SELECT * FROM user JOIN
            (SELECT * FROM department_manager
            WHERE d_id = #{d_id}) AS d_manager
            ON d_manager.manager_id = user.id""")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "password", column = "passwd"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "residentId", column = "resident_id"),
            @Result(property = "emailAddr", column = "email_addr"),
            @Result(property = "schedules", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "attendancesTime", column = "attendanceTime", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceTimeMapper.selectByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "leaves", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper.selectByUserId", fetchType = FetchType.LAZY)),
    })
    List<User> selectByDepartmentId(@Param("d_id") String d_id);

    @Insert("INSERT IGNORE INTO department_manager(manager_id, d_id) VALUES(#{manager_id}, #{d_id})")
    int insert(@Param("manager_id") String manager_id, @Param("d_id") String d_id);

    @Select("DELETE FROM department_manager WHERE manager_id = #{manager_id} AND d_id = #{d_id}")
    int delete(@Param("manager_id") String manager_id, @Param("d_id") String d_id);
}
