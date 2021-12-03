package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.model.AttendanceTime;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper
public interface UserAttendanceTimeMapper {
    @Select("SELECT * FROM user_attendance_time WHERE user_id = #{user_id}")
    @Results({
            @Result(property = "time", column = "time")
    })
    List<AttendanceTime> selectByUserId(@Param("user_id") String user_id);

    @Insert("""
            INSERT IGNORE INTO user_attendance_time(user_id, time)
            VALUES(#{user_id}, #{time})""")
    int insert(@Param("user_id") String user_id, @Param("time") Date time);

    @Delete("""
            DELETE FROM user_attendance_time
            WHERE user_id = #{user_id} AND
                time = #{time}""")
    int delete(@Param("user_id") String user_id, @Param("time") Date time);

    @Delete("""
            DELETE FROM user_attendance_time
            WHERE user_id = #{user_id}""")
    int deleteByUserId(@Param("user_id") String user_id);
}
