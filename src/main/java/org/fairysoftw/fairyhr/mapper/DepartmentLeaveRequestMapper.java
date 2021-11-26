package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.User;

import java.util.List;

@Mapper
public interface DepartmentLeaveRequestMapper {
    @Select("SELECT * FROM leave_request JOIN " +
            "(SELECT * FROM department_leave_request " +
            "WHERE d_id = #{d_id}) AS d_leave_request " +
            "ON d_leave_request.request_id = leave_request.id")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById")),
            @Result(property = "startTime", column = "start_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "endTime", column = "end_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "summitTime", column = "summit_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "checker", column = "checker_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById")),
            @Result(property = "checkTime", column = "check_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
    })
    List<User> selectByDepartmentId(@Param("d_id") String d_id);

    @Insert("INSERT IGNORE INTO department_leave_request(d_id, request_id) VALUES(#{d_id}, #{request_id})")
    int insert(@Param("d_id") String d_id, @Param("request_id") String request_id);
}
