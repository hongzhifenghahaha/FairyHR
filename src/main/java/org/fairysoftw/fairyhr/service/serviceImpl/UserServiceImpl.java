package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.UserAttendanceLeaveMapper;
import org.fairysoftw.fairyhr.mapper.UserAttendanceScheduleMapper;
import org.fairysoftw.fairyhr.mapper.UserAttendanceTimeMapper;
import org.fairysoftw.fairyhr.mapper.UserMapper;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.ScheduleService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserAttendanceScheduleMapper userAttendanceScheduleMapper;
    private final UserAttendanceLeaveMapper userAttendanceLeaveMapper;
    private final UserAttendanceTimeMapper userAttendanceTimeMapper;
    private final ScheduleService scheduleService;
    private final LeaveRequestService leaveRequestService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserAttendanceScheduleMapper userAttendanceScheduleMapper, UserAttendanceLeaveMapper userAttendanceLeaveMapper, UserAttendanceTimeMapper userAttendanceTimeMapper, ScheduleService scheduleService, LeaveRequestService leaveRequestService) {
        this.userMapper = userMapper;
        this.userAttendanceScheduleMapper = userAttendanceScheduleMapper;
        this.userAttendanceLeaveMapper = userAttendanceLeaveMapper;
        this.userAttendanceTimeMapper = userAttendanceTimeMapper;
        this.scheduleService = scheduleService;
        this.leaveRequestService = leaveRequestService;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    @Nullable
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public int deleteById(String id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int insert(User user) {
        if (user == null) {
            return 0;
        }
        int ret = userMapper.insert(user);
        insertCascade(user);
        return ret;
    }

    @Override
    public int insert(List<User> users) {
        int i = 0;
        if(users != null) {
            for (var user : users) {
                i += insert(user);
            }
        }
        return i;
    }

    @Override
    public int update(User user) {
        if (user == null) {
            return 0;
        }
        int ret = userMapper.update(user);
        insertCascade(user);
        return ret;
    }

    private void insertCascade(User user) {
        if (user == null) {
            return;
        }
        if (user.getSchedules() != null) {
            for (var schedule : user.getSchedules()) {
                scheduleService.insert(schedule);
                userAttendanceScheduleMapper.insert(user.getId(), schedule.getId());
            }
        }
        if (user.getLeaves() != null) {
            for (var leave : user.getLeaves()) {
                scheduleService.insert(leave);
                userAttendanceLeaveMapper.insert(user.getId(), leave.getId());
            }
        }
        if (user.getAttendanceTimes() != null) {
            for (var attendance : user.getAttendanceTimes()) {
                userAttendanceTimeMapper.insert(user.getId(), attendance.getTime());
            }
        }
        if (user.getLeaveRequests() != null) {
            for (var leaveRequest: user.getLeaveRequests()) {
                leaveRequestService.insert(leaveRequest);
            }
        }
    }
}
