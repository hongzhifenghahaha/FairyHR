package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.model.User;

import java.util.List;

/**
 * 关系映射类，实现{@link org.fairysoftw.fairyhr.model.Department}与{@link org.fairysoftw.fairyhr.model.Department#leaveRequests}
 * 的关联关系的映射。对应的数据库中的表为department_leave_request。
 *
 * @version 1.0
 */
@Mapper
public interface DepartmentLeaveRequestMapper {

    /**
     * 根据department的id来选出与该department关联的所有leaveRequest。
     *
     * @param d_id department的id。
     * @return 与该department关联的所有leaveRequest构成的列表。
     */
    @Select("""
            SELECT * FROM leave_request JOIN
            (SELECT * FROM department_leave_request
            WHERE d_id = #{d_id}) AS d_leave_request
            ON d_leave_request.request_id = leave_request.id""")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById")),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "submitTime", column = "submit_time"),
            @Result(property = "checker", column = "checker_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.UserMapper.selectById")),
            @Result(property = "checkTime", column = "check_time"),
    })
    List<LeaveRequest> selectByDepartmentId(@Param("d_id") String d_id);

    /**
     * 根据leaveRequest的id来选出与该leaveRequest关联的所有department。
     *
     * @param request_id leaveRequest的id。
     * @return 与该leaveRequest的关联所有department构成的列表。
     */
    @Select("""
            SELECT * FROM department JOIN
            (SELECT * FROM department_leave_request
            WHERE request_id = #{request_id}) AS d_leave_request
            ON d_leave_request.d_id = department.id""")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "d_name"),
            @Result(property = "department", column = "d_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.DepartmentMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "managers", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentUserMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
    })
    Department selectByLeaveRequestId(@Param("request_id") String request_id);

    /**
     * 把某一对 department-leaveRequest 关系插入到department_leave_request表中。
     *
     * @param request_id leaveRequest的id。
     * @param d_id department的id值。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("INSERT IGNORE INTO department_leave_request(d_id, request_id) VALUES(#{d_id}, #{request_id})")
    int insert(@Param("request_id") String request_id, @Param("d_id") String d_id);

    /**
     * 把某一对 department-leaveRequest 关系从department_leave_request表中删除。
     * @param request_id leaveRequest的id。
     * @param d_id department的id值。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM department_leave_request WHERE d_id = #{d_id} AND request_id = #{request_id}")
    int delete(@Param("request_id") String request_id, @Param("d_id") String d_id);

    /**
     * 根据leaveRequest的id来删除与该leaveRequest关联的所有department。
     *
     * @param request_id leaveRequest的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM department_leave_request WHERE request_id = #{request_id}")
    int deleteByRequestId(@Param("request_id") String request_id);
}
