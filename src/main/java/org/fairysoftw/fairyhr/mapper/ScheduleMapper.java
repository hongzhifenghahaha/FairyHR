package org.fairysoftw.fairyhr.mapper;

import org.apache.ibatis.annotations.*;
import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.model.ScheduleFrequency;
import org.fairysoftw.fairyhr.model.User;

import java.util.List;

/**
 * 映射类,实现{@link Schedule}类与数据库中的schedule表的映射。
 *
 * @version 1.0
 */
@Mapper
public interface ScheduleMapper {
    /**
     * 选出schedule表中的所有行，并映射成{@link Schedule}对象列表。
     *
     * @return schedule表中所有时间表。
     */
    @Select("SELECT * FROM schedule")
    @Results({
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "frequency", column = "frequency", typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler.class, javaType = org.fairysoftw.fairyhr.model.ScheduleFrequency.class),
            @Result(property = "frequencyValue", column = "frequency_value")
    })
    List<Schedule> selectAll();

    /**
     * 选出schedule表中id=#{id}的行，并映射成{@link Schedule}对象。
     *
     * @param id 要选出的时间表的id。
     * @return schedule表中id=#{id}的时间表。
     */
    @Select("select * from schedule where id=#{id}")
    @Results({
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "frequency", column = "frequency", typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler.class, javaType = org.fairysoftw.fairyhr.model.ScheduleFrequency.class),
            @Result(property = "frequencyValue", column = "frequency_value")
    })
    Schedule selectById(@Param("id") String id);

    /**
     * 把{@link Schedule}对象插入到schedule表中。
     *
     * @param schedule 要插入的{@link Schedule}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("""
            INSERT IGNORE INTO schedule(id, start_time, end_time, frequency, start_date, end_date,  frequency_value)
            VALUES(#{id},
            #{startTime},
            #{endTime},
            #{frequency, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler},
            #{startDate}, #{endDate}, #{frequencyValue})""")
    int insert(Schedule schedule);

    /**
     * 删除schedule表中id=#{id}的行。
     *
     * @param id 要删除的时间表的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM schedule WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    /**
     * 根据{@link Schedule}对象的值更新对应的schedule表中的行。
     *
     * @param schedule 用于更新行的Schedule对象。
     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
     */
    @Update("""
            UPDATE schedule SET
            start_time = #{startTime},
            end_time = #{endTime},
            frequency = #{frequency, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler},
            start_date = #{startDate}, end_date = #{endDate}, frequency_value = #{frequencyValue}
            WHERE id = #{id}""")
    int update(Schedule schedule);
}
