package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.ScheduleMapper;
import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleServiceImpl(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

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
        return scheduleMapper.insert(schedule);
    }

    @Override
    public int update(Schedule schedule) {
        return scheduleMapper.update(schedule);
    }
}
