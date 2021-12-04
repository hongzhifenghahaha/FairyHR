package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.model.Schedule;

import java.util.List;

/**
 * 关系映射类，实现{@link org.fairysoftw.fairyhr.model.User}与{@link org.fairysoftw.fairyhr.model.User#schedules}
 * 的关联关系的映射。对应的数据库中的表为user_attendance_schedule。
 *
 * @version 1.0
 */
@Mapper
public interface UserAttendanceScheduleMapper {

    /**
     * 根据user的id来选出与该user关联的所有schedule。
     *
     * @param user_id user的id。
     * @return 该user的所有schedule构成的列表。
     */
    @Select("SELECT * FROM schedule JOIN " +
            "(SELECT * " +
            "FROM user_attendance_schedule " +
            "WHERE user_id = #{user_id}) AS user_schedule " +
            "ON user_schedule.schedule_id = schedule.id")
    @Results({
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "frequency", column = "frequency",
                    typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler.class,
                    javaType = org.fairysoftw.fairyhr.model.ScheduleFrequency.class),
            @Result(property = "frequencyValue", column = "frequency_value")
    })
    List<Schedule> selectByUserId(@Param("user_id") String user_id);

    /**
     * 把某一对 user-schedule 关系插入到user_attendance_schedule表中
     *
     * @param user_id user的id
     * @param schedule_id schedule的id值
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("INSERT IGNORE INTO user_attendance_schedule(user_id, schedule_id) VALUES(#{user_id}, #{schedule_id})")
    int insert(@Param("user_id") String user_id, @Param("schedule_id") String schedule_id);

    /**
     * 把某一对 user-schedule 关系从user_attendance_schedule表中删除
     * @param user_id user的id
     * @param schedule_id schedule的id值
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM user_attendance_schedule WHERE user_id = #{user_id} AND schedule_id = #{schedule_id}")
    int delete(@Param("user_id") String user_id, @Param("schedule_id") String schedule_id);

    /**
     * 根据user的id来删除与该user关联的所有schedule。
     *
     * @param user_id user的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM user_attendance_schedule WHERE user_id = #{user_id}")
    int deleteByUserId(@Param("user_id") String user_id);
}
