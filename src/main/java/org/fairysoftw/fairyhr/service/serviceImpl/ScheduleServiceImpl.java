package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.ScheduleMapper;
import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link org.fairysoftw.fairyhr.service.ScheduleService}的逻辑实现类，
 * 通过MyBatis与数据库的交互，具体的交互在mapper中实现。
 *
 * @version 1.0
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    /*
    用到的mapper类
     */
    private final ScheduleMapper scheduleMapper;

    /**
     * 构造函数，通过spring实现自动装配。
     */
    @Autowired
    public ScheduleServiceImpl(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    /*
    覆写方法的注释详见对应的接口，这里不再重复写注释。
     */

    @Override
    public List<Schedule> selectAll() {
        return scheduleMapper.selectAll();
    }

    @Override
    @Nullable
    public Schedule selectById(String id) {
        return scheduleMapper.selectById(id);
    }

    @Override
    public int deleteById(String id) {
        return scheduleMapper.deleteById(id);
    }

    @Override
    public int insert(Schedule schedule) {
        if (schedule != null) {
            return scheduleMapper.insert(schedule);
        }
        else {
            return 0;
        }
    }

    @Override
    public int update(Schedule schedule) {
        if (schedule != null) {
            return scheduleMapper.update(schedule);
        }
        else {
            return 0;
        }
    }
}
