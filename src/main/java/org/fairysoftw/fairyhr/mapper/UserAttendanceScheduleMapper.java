package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.fairysoftw.fairyhr.model.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAttendanceScheduleMapper {
    @Select("SELECT * FROM schedule JOIN " +
            "(SELECT * " +
            "FROM user_attendance_schedule " +
            "WHERE user_id = #{user_id}) AS user_schedule " +
            "ON user_schedule.schedule_id = schedule.id")
    @Results({
            @Result(property = "startTime", column = "start_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "endTime", column = "end_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "frequency", column = "frequency", typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler.class, javaType = org.fairysoftw.fairyhr.model.ScheduleFrequency.class),
            @Result(property = "frequencyValue", column = "frequency_value")
    })
    List<Schedule> selectByUserId(@Param("user_id") String user_id);
}
