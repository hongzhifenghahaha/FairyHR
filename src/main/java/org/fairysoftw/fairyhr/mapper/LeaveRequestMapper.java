package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.LeaveRequest;

import java.util.List;

@Mapper
public interface LeaveRequestMapper {
    @Select("SELECT * FROM leave_request")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "submitTime", column = "submit_time"),
            @Result(property = "checker", column = "checker_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "checkTime", column = "check_time"),
    })
    List<LeaveRequest> selectAll();

    @Select("SELECT * FROM leave_request " +
            "WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "submitTime", column = "submit_time"),
            @Result(property = "checker", column = "checker_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "checkTime", column = "check_time"),
    })
    LeaveRequest selectById(@Param("id") String id);

    @Insert("<script>" +
            "INSERT INTO leave_request(id, user_id, start_time, end_time, submit_time, reason, status, checker_id, check_time, check_opinion) " +
            "VALUES(#{id}, #{user.id},#{startTime}, #{endTime}, #{submitTime}, #{reason}, #{status}, " +
            "<if test='#{checker}==null'> NULL </if> <if test='#{checker}!=null'>#{checker.id}</if>," +
            "#{checkTime}, #{checkOpinion})" +
            "</script>")
    int insert(LeaveRequest leaveRequest);

    @Delete("DELETE FROM leave_request WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    @Update("<script>" +
            "UPDATE leave_request SET" +
            "id = #{id}, " +
            "user_id = #{user.id}, " +
            "start_time = #{startTime}, " +
            "end_time = #{endTime}, " +
            "submit_time = #{submitTime}, " +
            "reason = #{reason}, " +
            "status = #{status}, " +
            "checker_id = <if test='#{checker}==null'> NULL </if> <if test='#{checker}!=null'>#{checker.id}</if>, " +
            "check_time = #{checkTime}, " +
            "check_opinion = #{checkOpinion}" +
            "</script>")
    int update(LeaveRequest leaveRequest);
}
