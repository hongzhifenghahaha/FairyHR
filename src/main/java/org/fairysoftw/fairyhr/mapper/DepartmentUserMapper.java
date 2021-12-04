package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关系映射类，实现{@link org.fairysoftw.fairyhr.model.Department}与{@link org.fairysoftw.fairyhr.model.Department#users}
 * 的关联关系的映射。对应的数据库中的表为department_user。
 *
 * @version 1.0
 */
@Mapper
public interface DepartmentUserMapper {

    /**
     * 根据department的id来选出与该department关联的所有user。
     *
     * @param d_id department的id。
     * @return 与该department关联的所有user构成的列表。
     */
    @Select("""
            SELECT * FROM user JOIN
            (SELECT * FROM department_user
            WHERE d_id = #{d_id}) AS d_user
            ON d_user.user_id = user.id""")
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

    /**
     * 根据user的id来选出与该user关联的所有department。
     *
     * @param user_id user的id。
     * @return 与该user的关联所有department构成的列表。
     */
    @Select("""
            SELECT * FROM department JOIN
            (SELECT * FROM department_user
            WHERE user_id = #{user_id}) AS user_d
            ON user_d.d_id = department.id""")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "d_name"),
            @Result(property = "password", column = "passwd"),
            @Result(property = "department", column = "d_id", one = @One(select = "org.fairysoftw.fairyhr.mapper.DepartmentMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "managers", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentUserMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
            @Result(property = "leaveRequests", column = "id", many = @Many(select = "org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper.selectByDepartmentId", fetchType = FetchType.LAZY)),
    })
    List<Department> selectByUserId(@Param("user_id") String user_id);

    /**
     * 把某一对 department-user 关系插入到department_user表中。
     *
     * @param user_id user的id。
     * @param d_id department的id值。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("INSERT IGNORE INTO department_user VALUES(#{user_id}, #{d_id})")
    int insert(@Param("user_id") String user_id, @Param("d_id") String d_id);

    /**
     * 把某一对 department-user 关系从department_user表中删除。
     * @param user_id user的id。
     * @param d_id department的id值。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM department_user WHERE user_id = #{user_id} AND d_id = #{d_id}")
    int delete(@Param("user_id") String user_id, @Param("d_id") String d_id);

    /**
     * 根据user的id来删除与该user关联的所有department。
     *
     * @param user_id user的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM department_user WHERE user_id = #{user_id}")
    int deleteByUserId(@Param("user_id") String user_id);

    /**
     * 根据department的id来删除与该department关联的所有user。
     *
     * @param d_id department的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM department_user WHERE d_id = #{d_id}")
    int deleteByDepartmentId(@Param("d_id") String d_id);
}
