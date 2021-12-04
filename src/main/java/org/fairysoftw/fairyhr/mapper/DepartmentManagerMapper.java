package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fairysoftw.fairyhr.model.User;

import java.util.List;

/**
 * 关系映射类，实现{@link org.fairysoftw.fairyhr.model.Department}与{@link org.fairysoftw.fairyhr.model.Department#managers}
 * 的关联关系的映射。对应的数据库中的表为department_manager。
 *
 * @version 1.0
 */
@Mapper
public interface DepartmentManagerMapper {
    /**
     * 根据department的id来选出与该department关联的所有manager。
     *
     * @param d_id department的id。
     * @return 与该department关联的所有manager构成的列表。
     */
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

    /**
     * 把某一对 department-manager 关系插入到department_manager表中。
     *
     * @param manager_id manager的id。
     * @param d_id department的id值。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("INSERT IGNORE INTO department_manager(manager_id, d_id) VALUES(#{manager_id}, #{d_id})")
    int insert(@Param("manager_id") String manager_id, @Param("d_id") String d_id);

    /**
     * 把某一对 department-manager 关系从department_manager表中删除。
     * @param manager_id manager的id。
     * @param d_id department的id值。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM department_manager WHERE manager_id = #{manager_id} AND d_id = #{d_id}")
    int delete(@Param("manager_id") String manager_id, @Param("d_id") String d_id);

    /**
     * 根据department的id来删除与该department关联的所有manager。
     *
     * @param d_id department的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM department_manager WHERE d_id = #{d_id}")
    int deleteByDepartmentId(@Param("d_id") String d_id);
}
