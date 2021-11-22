package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.ScheduleMapper;
import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public HomeServiceImpl(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<String> getSchedulesString() {
        return scheduleMapper.selectAll()
                .stream()
                .map(Schedule::toString)
                .collect(Collectors.toList());
    }
}
