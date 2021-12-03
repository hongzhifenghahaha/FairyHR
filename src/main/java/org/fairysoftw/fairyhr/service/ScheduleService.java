package org.fairysoftw.fairyhr.service;

import org.fairysoftw.fairyhr.model.Schedule;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleService {
    /**
     * 查询所有的时间表
     *
     * @return 包含所有时间表实例的列表
     */
    List<Schedule> selectAll();

    /**
     * 查询id符合的时间表
     *
     * @param id 时间表id
     * @return id符合的时间表，若没有符合的时间表，则返回null
     */
    @Nullable
    Schedule selectById(String id);

    /**
     * 根据id删除时间表，若不存在id相同的请假申请，则不执行任何操作。
     *
     * @param id 时间表id
     * @return 删除的记录条数
     */
    int deleteById(String id);

    /**
     * 插入新的时间表，参数中的{@link org.fairysoftw.fairyhr.model.Schedule#id}、
     * {@link org.fairysoftw.fairyhr.model.Schedule#startTime}、
     * {@link org.fairysoftw.fairyhr.model.Schedule#endTime}不为null
     *
     * @param schedule 新的时间表实例
     * @return 插入的记录条数
     */
    int insert(Schedule schedule);

    /**
     * 根据id更新时间表，若不存在id相同的请假申请，则不执行任何操作。
     * <br><br>
     * 参数中的{@link org.fairysoftw.fairyhr.model.Schedule#id}、
     * {@link org.fairysoftw.fairyhr.model.Schedule#startTime}、
     * {@link org.fairysoftw.fairyhr.model.Schedule#endTime}不为null
     *
     * @param schedule 时间表实例
     * @return 更新的记录条数
     */
    int update(Schedule schedule);
}
