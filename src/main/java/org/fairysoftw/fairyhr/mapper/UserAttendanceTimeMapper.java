package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.model.AttendanceTime;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 关系映射类，实现{@link org.fairysoftw.fairyhr.model.User}与{@link org.fairysoftw.fairyhr.model.User#attendanceTimes}
 * 的关联关系的映射。对应的数据库中的表为user_attendance_time。
 *
 * @version 1.0
 */
@Mapper
public interface UserAttendanceTimeMapper {

    /**
     * 根据user的id来选出与该user关联的所有attendanceTime。
     *
     * @param user_id user的id。
     * @return 该user的所有attendanceTime构成的列表。
     */
    @Select("SELECT * FROM user_attendance_time WHERE user_id = #{user_id}")
    @Results({
            @Result(property = "time", column = "time")
    })
    List<AttendanceTime> selectByUserId(@Param("user_id") String user_id);

    /**
     * 把某一对 user-attendanceTime 关系插入到user_attendance_time表中
     *
     * @param user_id user的id
     * @param time attendanceTime的time值
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("""
            INSERT IGNORE INTO user_attendance_time(user_id, time)
            VALUES(#{user_id}, #{time})""")
    int insert(@Param("user_id") String user_id, @Param("time") Date time);

    /**
     * 把某一对 user-attendanceTime 关系从user_attendance_time表中删除
     * @param user_id user的id
     * @param time attendanceTime的time值
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("""
            DELETE FROM user_attendance_time
            WHERE user_id = #{user_id} AND
                time = #{time}""")
    int delete(@Param("user_id") String user_id, @Param("time") Date time);

    /**
     * 根据user的id来删除与该user关联的所有attendanceTime。
     *
     * @param user_id user的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("""
            DELETE FROM user_attendance_time
            WHERE user_id = #{user_id}""")
    int deleteByUserId(@Param("user_id") String user_id);
}
