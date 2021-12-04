package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.model.Schedule;

import java.util.List;

/**
 * 映射类,实现{@link LeaveRequest}类与数据库中的leave_request表。
 *
 * @version 1.0
 */
@Mapper
public interface LeaveRequestMapper {
    /**
     * 选出leave_request表中的所有行，并映射成{@link LeaveRequest}对象列表。
     *
     * @return leave_request表中所有请假申请。
     */
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

    /**
     * 选出leave_request表中id=#{id}的行，并映射成{@link LeaveRequest}对象。
     *
     * @param id 要选出的请假申请的id。
     * @return leave_request表中id=#{id}的请假申请。
     */
    @Select("SELECT * FROM leave_request WHERE id = #{id}")
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

    /**
     * 选出leave_request表中所有id=#{user_id}的行，并映射成{@link LeaveRequest}对象列表。
     *
     * @param user_id 用户的id
     * @return 该user的所有请假申请。
     */
    @Select("SELECT * FROM leave_request WHERE user_id = #{user_id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "submitTime", column = "submit_time"),
            @Result(property = "checker", column = "checker_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "checkTime", column = "check_time"),
    })
    List<LeaveRequest> selectByUserId(@Param("user_id") String user_id);

    /**
     * 把{@link LeaveRequest}对象插入到leave_request表中。
     *
     * @param leaveRequest 要插入的{@link LeaveRequest}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("""
            <script>
            INSERT IGNORE INTO leave_request(id, user_id, start_time, end_time, submit_time, reason, status, checker_id, check_time, check_opinion)
            VALUES(#{id}, #{user.id},#{startTime}, #{endTime}, #{submitTime}, #{reason}, #{status},
            <if test='#{checker}==null'> NULL </if> <if test='#{checker}!=null'>#{checker.id}</if>,
            #{checkTime}, #{checkOpinion})
            </script>""")
    int insert(LeaveRequest leaveRequest);

    /**
     * 删除leave_request表中id=#{id}的行。
     *
     * @param id 要删除的请假申请的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM leave_request WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    /**
     * 根据{@link LeaveRequest}对象的值更新对应的leave_request表中的行。
     *
     * @param leaveRequest 用于更新行的LeaveRequest对象。
     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
     */
    @Update("""
            <script>
            UPDATE leave_request SET
            start_time = #{startTime},
            end_time = #{endTime},
            submit_time = #{submitTime},
            reason = #{reason},
            status = #{status},
            checker_id = <if test='#{checker}==null'> NULL </if> <if test='#{checker}!=null'>#{checker.id}</if>,
            check_time = #{checkTime},
            check_opinion = #{checkOpinion}
            WHERE id = #{id} AND user_id = #{user.id}
            </script>""")
    int update(LeaveRequest leaveRequest);
}
