package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.*;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.ScheduleService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link org.fairysoftw.fairyhr.service.UserService}的逻辑实现类，
 * 通过MyBatis与数据库的交互，具体的交互在mapper中实现。
 *
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    /*
    用到的一些mapper类和service类
     */
    private final UserMapper userMapper;
    private final UserAttendanceScheduleMapper userAttendanceScheduleMapper;
    private final UserAttendanceLeaveMapper userAttendanceLeaveMapper;
    private final UserAttendanceTimeMapper userAttendanceTimeMapper;
    private final DepartmentUserMapper departmentUserMapper;
    private final ScheduleService scheduleService;
    private final LeaveRequestService leaveRequestService;

    /**
     * 构造函数，通过spring实现自动装配。
     */
    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserAttendanceScheduleMapper userAttendanceScheduleMapper, UserAttendanceLeaveMapper userAttendanceLeaveMapper, UserAttendanceTimeMapper userAttendanceTimeMapper, DepartmentUserMapper departmentUserMapper, ScheduleService scheduleService, LeaveRequestService leaveRequestService) {
        this.userMapper = userMapper;
        this.userAttendanceScheduleMapper = userAttendanceScheduleMapper;
        this.userAttendanceLeaveMapper = userAttendanceLeaveMapper;
        this.userAttendanceTimeMapper = userAttendanceTimeMapper;
        this.departmentUserMapper = departmentUserMapper;
        this.scheduleService = scheduleService;
        this.leaveRequestService = leaveRequestService;
    }

    /*
    覆写方法的注释详见对应的接口，这里不再重复写注释。
     */

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
        leaveRequestService.deleteByUserId(id);
        userAttendanceTimeMapper.deleteByUserId(id);
        userAttendanceScheduleMapper.deleteByUserId(id);
        userAttendanceLeaveMapper.deleteByUserId(id);
        departmentUserMapper.deleteByUserId(id);
        return userMapper.deleteById(id);
    }

    @Override
    public int insert(User user) {
        if (user == null) {
            return 0;
        }
        int ret = userMapper.insert(user);
        if(ret != 0) {
            insertCascade(user);
        }
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
        updateCascade(user);
        return ret;
    }

    /**
     * 级联插入user。
     * <br><br>
     * 即插入user之后，也会把它的schedule列表，leave列表，attendanceTime列表中的所有元素都插入到数据库中（如果它们不在数据库中）
     *
     * @param user 执行级联插入操作的user
     */
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
    }

    /**
     * 级联更新user。
     * <br><br>
     * 即更新user之后，也会把它的schedule列表，leave列表，attendanceTime列表中的所有元素在数据库中更新。
     * <br><br>
     * 注意，user的delete操作之后也会执行updateCascade操作，所以如果是删除user,其关联的实体也会被执行删除操作。
     * 删除user的时候会同时删除该用户的请假记录与考勤纪律，而不会删除与该用户关联的时间表，只是它们的关联关系被删除了。
     *
     * @param user 执行级联更新操作的user
     */
    private void updateCascade(User user) {
        if (user == null) {
            return;
        }
        if (user.getSchedules() != null) {
            var origin_schedules = userAttendanceScheduleMapper.selectByUserId(user.getId());
            var now_schedules = user.getSchedules();
            for (var schedule : now_schedules) {
                scheduleService.insert(schedule);
                userAttendanceScheduleMapper.insert(user.getId(), schedule.getId());
            }
            for (var schedule: origin_schedules) {
                if(!now_schedules.contains(schedule)) {
                    userAttendanceScheduleMapper.delete(user.getId(), schedule.getId());
                }
            }
        }
        if (user.getLeaves() != null) {
            for (var leave : user.getLeaves()) {
                scheduleService.insert(leave);
                userAttendanceLeaveMapper.insert(user.getId(), leave.getId());
            }
        }
        if (user.getAttendanceTimes() != null) {
            var origin_attendances = userAttendanceTimeMapper.selectByUserId(user.getId());
            var now_attendances = user.getAttendanceTimes();
            for (var attendance: now_attendances) {
                userAttendanceTimeMapper.insert(user.getId(), attendance.getTime());
            }
            for (var attendance : origin_attendances) {
                if(!now_attendances.contains(attendance)) {
                    userAttendanceTimeMapper.delete(user.getId(), attendance.getTime());
                }
            }
        }
    }
}
