package org.fairysoftw.fairyhr;

import org.fairysoftw.fairyhr.model.AttendanceTime;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.ScheduleService;
import org.fairysoftw.fairyhr.service.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;

@SpringBootTest()
@Profile("test")
@MapperScan("org.fairysoftw.fairyhr.mapper")
class ServiceTest {
    private final DepartmentService departmentService;
    private final LeaveRequestService leaveRequestService;
    private final ScheduleService scheduleService;
    private final UserService userService;

    @Autowired
    ServiceTest(DepartmentService departmentService, LeaveRequestService leaveRequestService, ScheduleService scheduleService, UserService userService) {
        this.departmentService = departmentService;
        this.leaveRequestService = leaveRequestService;
        this.scheduleService = scheduleService;
        this.userService = userService;
    }

    @Test
    void contextLoads() {
        assertNotEquals(0, departmentService.selectAll().size());
        Department myDepartment = departmentService.selectById("2");
        int i = 1;
    }

}
