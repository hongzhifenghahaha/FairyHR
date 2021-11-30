package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler;
import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.model.ScheduleFrequency;
import org.fairysoftw.fairyhr.model.User;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    @Select("SELECT * FROM schedule")
    @Results({
            @Result(property = "startTime", column = "start_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "endTime", column = "end_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "frequency", column = "frequency", typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler.class, javaType = org.fairysoftw.fairyhr.model.ScheduleFrequency.class),
            @Result(property = "frequencyValue", column = "frequency_value")
    })
    List<Schedule> selectAll();

    @Select("select * from schedule where id=#{id}")
    @Results({
            @Result(property = "startTime", column = "start_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "endTime", column = "end_time", typeHandler = org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler.class),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "frequency", column = "frequency", typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler.class, javaType = org.fairysoftw.fairyhr.model.ScheduleFrequency.class),
            @Result(property = "frequencyValue", column = "frequency_value")
    })
    Schedule selectById(@Param("id") String id);

    @Insert("INSERT INTO schedule(id, start_time, end_time, start_date, end_date, frequency, frequency_value)" +
            "VALUES(#{id}, " +
            "#{startTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}," +
            "#{endTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}," +
            "#{frequency, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}," +
            "#{startDate}, #{endDate}, #{frequencyValue})")
    int insert(Schedule schedule);

    @Delete("DELETE FROM schedule WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    @Update("UPDATE schedule SET" +
            "start_time = #{startTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}," +
            "end_time = #{endTime, typeHandler=org.fairysoftw.fairyhr.mapper.typeHandler.TimeTypeHandler}," +
            "frequency = #{frequency, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}," +
            "start_date = #{startDate}, end_date = #{endDate}, frequency_value = #{frequencyValue}" +
            "WHERE id = #{id}")
    int update(Schedule schedule);
}
