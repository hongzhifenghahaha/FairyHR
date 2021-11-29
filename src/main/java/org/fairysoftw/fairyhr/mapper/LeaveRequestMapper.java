package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.model.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface LeaveRequestMapper {
    @Select("SELECT * FROM leave_request")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "startTime", column = "start_time"),// typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "endTime", column = "end_time"),// typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "submitTime", column = "submit_time"),// typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "checker", column = "checker_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "checkTime", column = "check_time"),// typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
    })
    List<LeaveRequest> selectAll();

    @Select("SELECT * FROM leave_request " +
            "WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "startTime", column = "start_time"),// typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "endTime", column = "end_time"),// typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "submitTime", column = "submit_time"),// typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "checker", column = "checker_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "checkTime", column = "check_time"),// typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
    })
    LeaveRequest selectById(@Param("id") String id);

    @Insert("<script>" +
            "INSERT INTO leave_request(id, user_id, start_time, end_time, submit_time, reason, status, checker_id, check_time, check_opinion) " +
            "VALUES(#{id}, #{user.id}," +
            "#{startTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}," +
            "#{endTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}," +
            "#{submitTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}," +
            "#{reason}, #{status}, " +
            "<if test='checker=null'> NULL </if> <if test='checker!=null'>#{checker.id}</if>," +
            "#{checkTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}," +
            "#{checkOpinion})" +
            "</script>")
    int insert(@Param("leaveRequest") LeaveRequest leaveRequest);

    @Delete("DELETE FROM leave_request WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    @Update("<script>" +
            "UPDATE leave_request SET" +
            "id = #{id}, user_id = #{user.id}, " +
            "start_time = #{startTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}, " +
            "end_time = #{endTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}, " +
            "submit_time = #{submitTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}, " +
            "reason = #{reason}, status = #{status}, " +
            "checker_id = <if test='checker=null'> NULL </if> <if test='checker!=null'>#{checker.id}</if>, " +
            "check_time = #{checkTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}, " +
            "check_opinion = #{checkOpinion}" +
            "</script>")
    int update(@Param("leaveRequest") LeaveRequest leaveRequest);
}
