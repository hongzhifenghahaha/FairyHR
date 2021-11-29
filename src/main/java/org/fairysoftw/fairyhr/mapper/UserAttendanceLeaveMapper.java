package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.model.Schedule;

import java.util.List;

@Mapper
public interface UserAttendanceLeaveMapper {
    @Select("SELECT * FROM schedule JOIN " +
            "(SELECT * " +
            "FROM user_attendance_leave " +
            "WHERE user_id = #{user_id}) AS user_leave " +
            "ON user_leave.schedule_id = schedule.id")
    @Results({
            @Result(property = "startTime", column = "start_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "endTime", column = "end_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "frequency", column = "frequency", typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler.class, javaType = org.fairysoftw.fairyhr.model.ScheduleFrequency.class),
            @Result(property = "frequencyValue", column = "frequency_value")
    })
    List<Schedule> selectByUserId(@Param("user_id") String user_id);

    @Insert("INSERT IGNORE INTO user_attendance_leave(user_id, schedule_id) " +
            "VALUES(#{user_id}, #{schedule_id})")
    int insert(@Param("user_id") String user_id, @Param("schedule_id") String schedule_id);
}
