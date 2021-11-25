package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.ScheduleMapper;
import org.fairysoftw.fairyhr.mapper.UserMapper;
import org.fairysoftw.fairyhr.model.Schedule;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {
    private final ScheduleMapper scheduleMapper;
    private final UserMapper userMapper;

    @Autowired
    public HomeServiceImpl(ScheduleMapper scheduleMapper, UserMapper userMapper) {
        this.scheduleMapper = scheduleMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<String> getSchedulesString() {
        return scheduleMapper.selectAll()
                .stream()
                .map(Schedule::toString)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getUserString() {
        return userMapper.selectAll()
                .stream()
                .map(User::toString)
                .collect(Collectors.toList());
    }
}
