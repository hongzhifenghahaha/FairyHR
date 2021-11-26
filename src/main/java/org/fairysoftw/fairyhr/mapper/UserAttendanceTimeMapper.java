package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.fairysoftw.fairyhr.model.AttendanceTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAttendanceTimeMapper {
    @Select("SELECT * FROM user_attendance_time JOIN WHERE user_id = #{user_id}")
    @Results({
            @Result(property = "time", column = "time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class)
    })
    List<AttendanceTime> selectByUserId(@Param("user_id") String user_id);
}
