package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.model.AttendanceTime;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserAttendanceTimeMapper {
    @Select("SELECT * FROM user_attendance_time WHERE user_id = #{user_id}")
    @Results({
            @Result(property = "time", column = "time",
                    typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.DatetimeTypeHandler.class)
    })
    List<AttendanceTime> selectByUserId(@Param("user_id") String user_id);

    @Insert("""
            INSERT IGNORE INTO user_attendance_time(user_id, time)
            VALUES(#{user_id}, #{time, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.DatetimeTypeHandler})""")
    int insert(@Param("user_id") String user_id, @Param("time") LocalDateTime time);

    @Delete("""
            DELETE FROM user_attendance_time
            WHERE user_id = #{user_id} AND
                time = #{time, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.DatetimeTypeHandler}""")
    int delete(@Param("user_id") String user_id, @Param("time") LocalDateTime time);
}
