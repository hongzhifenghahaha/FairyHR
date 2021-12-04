package org.fairysoftw.fairyhr;

import org.fairysoftw.fairyhr.mapper.*;
import org.fairysoftw.fairyhr.model.*;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.ScheduleService;
import org.fairysoftw.fairyhr.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 数据库的四个Service接口的测试类，进行单元测试，集成测试和逻辑覆盖。
 * 使用的Spring环境为“test”，
 * 具有事务性和回滚功能，测试执行后不会对数据库产生任何影响，即该测试具有无后效性。
 *
 * @version 2.0
 */
@SpringBootTest()
@Profile("test")
@MapperScan("org.fairysoftw.fairyhr.mapper")
@Transactional
@Rollback
class ServiceTest {
    /*
    被测试的4个Service类。
     */
    private final DepartmentService departmentService;
    private final LeaveRequestService leaveRequestService;
    private final ScheduleService scheduleService;
    private final UserService userService;

    /*
    多表关联的Mapper类，用于检查Service执行对应的操作后，是否在Mapper中也有相应操作。
     */
    private final UserAttendanceScheduleMapper userAttendanceScheduleMapper;
    private final UserAttendanceLeaveMapper userAttendanceLeaveMapper;
    private final UserAttendanceTimeMapper userAttendanceTimeMapper;
    private final DepartmentUserMapper departmentUserMapper;
    private final DepartmentLeaveRequestMapper departmentLeaveRequestMapper;
    private final DepartmentManagerMapper departmentManagerMapper;

    /*
    SimpleDateFormat类用于生成对应格式的Date
     */
    private final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");

    /**
     * 构造函数，会自动装配service对应的实体
     */
    @Autowired
    ServiceTest(DepartmentService departmentService, LeaveRequestService leaveRequestService, ScheduleService scheduleService, UserService userService, UserAttendanceScheduleMapper userAttendanceScheduleMapper, UserAttendanceLeaveMapper userAttendanceLeaveMapper, UserAttendanceTimeMapper userAttendanceTimeMapper, DepartmentUserMapper departmentUserMapper, DepartmentLeaveRequestMapper departmentLeaveRequestMapper, DepartmentManagerMapper departmentManagerMapper) {
        this.departmentService = departmentService;
        this.leaveRequestService = leaveRequestService;
        this.scheduleService = scheduleService;
        this.userService = userService;

        this.userAttendanceScheduleMapper = userAttendanceScheduleMapper;
        this.userAttendanceLeaveMapper = userAttendanceLeaveMapper;
        this.userAttendanceTimeMapper = userAttendanceTimeMapper;
        this.departmentUserMapper = departmentUserMapper;
        this.departmentLeaveRequestMapper = departmentLeaveRequestMapper;
        this.departmentManagerMapper = departmentManagerMapper;
    }

    /**
     * ScheduleService的测试类，主要测试它的增删改查功能是否正常。
     * 具有Rollback注解，使其不会对下面的其他测试产生影响。
     * @throws ParseException SimpleDateFormat在日期转换出错时会抛出这个异常。
     */
    @Rollback
    @Test
    void scheduleServiceTest() throws ParseException {

//      测试selectAll()方法
        assertTrue(scheduleService.selectAll().size() > 0);

//      测试selectById()方法
        assertNotNull(scheduleService.selectById("0"));
        assertEquals(sdf3.parse("09:00:00"), scheduleService.selectById("1").getStartTime());

//      测试insert()方法
        Schedule schedule1 = new Schedule("11", sdf3.parse("10:00:00"), sdf3.parse("18:30:00"), null, null, ScheduleFrequency.WEEKLY, 1);
        assertEquals(1, scheduleService.insert(schedule1));
        assertEquals(schedule1, scheduleService.selectById("11"));

        Schedule schedule2 = new Schedule("12", sdf3.parse("11:00:00"), sdf3.parse("17:30:00"), sdf1.parse("2021-11-28"), sdf1.parse("2021-11-29"), ScheduleFrequency.WEEKLY, 1);
        assertEquals(1, scheduleService.insert(schedule2));
        assertEquals(schedule2, scheduleService.selectById("12"));

//      测试update()方法
        Schedule schedule3 = scheduleService.selectById("11");
        assertNotNull(schedule3);
        schedule3.setFrequencyValue(3);
        assertEquals(1, scheduleService.update(schedule3));
        assertEquals(3, scheduleService.selectById("11").getFrequencyValue());
        assertEquals(schedule3, scheduleService.selectById("11"));

//      测试deleteById()方法
        assertEquals(1, scheduleService.deleteById("11"));
        assertEquals(1, scheduleService.deleteById("12"));
        assertNull(scheduleService.selectById("11"));
        assertNull(scheduleService.selectById("12"));
    }


    /**
     * UserService的测试类，主要测试它的增删改查功能是否正常。
     * 具有Rollback注解，使其不会对下面的其他测试产生影响。
     * @throws ParseException SimpleDateFormat在日期转换出错时会抛出这个异常。
     */
    @Rollback
    @Test
    void userServiceTest() throws ParseException {

//      测试SelectAll()方法
        assertTrue(userService.selectAll().size() > 0);

//      测试selectById()方法
        assertEquals("张三", userService.selectById("0").getName());
        assertEquals("李四", userService.selectById("1").getName());
        assertEquals("王五", userService.selectById("2").getName());

//      在user所有的关联外部实体的属性都为null的情况下，测试insert()方法
        User user2 = new User("6", "ls2", "12345", "ls2", "54321", "ls2@gmail.com", "Beijing", "宣发部成员", null, null, null, null, false);
        assertEquals(1, userService.insert(user2));
        assertEquals("ls2@gmail.com", userService.selectById("6").getEmailAddr());

//      测试insert()方法的级联插入功能
        ArrayList<AttendanceTime> attendanceTimes = new ArrayList<>();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AttendanceTime attendanceTime1 = new AttendanceTime(s.parse("2021-10-01 09:00:00"));
        AttendanceTime attendanceTime2 = new AttendanceTime(s.parse("2021-10-02 09:00:00"));
        attendanceTimes.add(attendanceTime1);
        attendanceTimes.add(attendanceTime2);

        ArrayList<Schedule> schedules = new ArrayList<>();
        Schedule schedule1 = new Schedule("11", sdf3.parse("10:00:00"), sdf3.parse("18:30:00"), null, null, ScheduleFrequency.WEEKLY, 1);
        Schedule schedule2 = new Schedule("12", sdf3.parse("10:00:00"), sdf3.parse("18:30:00"), null, null, ScheduleFrequency.WEEKLY, 2);
        schedules.add(schedule1);
        schedules.add(schedule2);

        ArrayList<Schedule> leaves = new ArrayList<>();
        Schedule leave1 = new Schedule("13", sdf3.parse("10:00:00"), sdf3.parse("18:30:00"), null, null, ScheduleFrequency.WEEKLY, 3);
        Schedule leave2 = new Schedule("14", sdf3.parse("10:00:00"), sdf3.parse("18:30:00"), null, null, ScheduleFrequency.WEEKLY, 4);
        leaves.add(leave1);
        leaves.add(leave2);

        User user1 = new User("7", "ls3", "12345", "ls3", "54321", "ls3@gmail.com", "Beijing", "宣发部成员", schedules, attendanceTimes, leaves, null, false);
        assertEquals(1, userService.insert(user1));
        assertEquals(sdf3.parse("10:00:00"), scheduleService.selectById("11").getStartTime());
        assertEquals(sdf3.parse("10:00:00"), scheduleService.selectById("12").getStartTime());
        assertEquals(sdf3.parse("10:00:00"), scheduleService.selectById("13").getStartTime());
        assertEquals(sdf3.parse("10:00:00"), scheduleService.selectById("14").getStartTime());
        assertEquals(schedule1, scheduleService.selectById("11"));
        assertEquals(schedule2, scheduleService.selectById("12"));
        assertEquals(leave1, scheduleService.selectById("13"));
        assertEquals(leave2, scheduleService.selectById("14"));
        assertEquals("ls3", userService.selectById("7").getPassword());
        assertEquals(attendanceTimes, userService.selectById("7").getAttendanceTimes());
        assertEquals(schedules, userService.selectById("7").getSchedules());
        assertEquals(leaves, userService.selectById("7").getLeaves());

//      测试insert()插入一个user列表
        ArrayList<User> users = new ArrayList<>();
        User user3 = new User("8", "ls4", "12345", "ls4", "54321", "ls4@gmail.com", "Beijing", "宣发部成员", null, null, null, null, false);
        User user4 = new User("9", "ls5", "12345", "ls5", "54321", "ls5@gmail.com", "Beijing", "宣发部成员", null, null, null, null, false);
        users.add(user3);
        users.add(user4);

        assertEquals(2, userService.insert(users));
        assertEquals("ls4@gmail.com", userService.selectById("8").getEmailAddr());
        assertEquals("ls5@gmail.com", userService.selectById("9").getEmailAddr());

//      测试update()方法
        User user5 = userService.selectById("8");
        assertNotNull(user5);
        user5.setPassword("ls4444");
        assertEquals(1, userService.update(user5));
        assertEquals(user5, userService.selectById("8"));


//      测试delete()方法的级联删除功能
        assertEquals(1, userService.deleteById("6"));
        assertEquals(1, userService.deleteById("8"));
        assertEquals(1, userService.deleteById("9"));
        assertNull(userService.selectById("6"));
        assertNull(userService.selectById("8"));
        assertNull(userService.selectById("9"));

        assertEquals(1, userService.deleteById("7"));
        assertNull(userService.selectById("7"));
        assertEquals(0, userAttendanceScheduleMapper.selectByUserId("7").size());
        assertEquals(0, userAttendanceLeaveMapper.selectByUserId("7").size());
        assertEquals(0, userAttendanceTimeMapper.selectByUserId("7").size());

        assertEquals(1, scheduleService.deleteById("11"));
        assertEquals(1, scheduleService.deleteById("12"));
        assertEquals(1, scheduleService.deleteById("13"));
        assertEquals(1, scheduleService.deleteById("14"));
        assertNull(scheduleService.selectById("11"));
        assertNull(scheduleService.selectById("12"));
        assertNull(scheduleService.selectById("13"));
        assertNull(scheduleService.selectById("14"));
    }


    /**
     * LeaveRequestService的测试类，主要测试它的增删改查功能是否正常。
     * 具有Rollback注解，使其不会对下面的其他测试产生影响。
     * @throws ParseException SimpleDateFormat在日期转换出错时会抛出这个异常。
     */
    @Rollback
    @Test
    void leaveRequestServiceTest() throws ParseException {

//      测试selectAll()方法
        assertTrue(leaveRequestService.selectAll().size() > 0);

//      测试selectById()方法
        assertEquals("参加朋友婚宴", leaveRequestService.selectById("0").getReason());

//      测试LeaveRequest带有新创建的user时，insert()方法的级联插入功能
        User user1 = new User("6", "zs2", "12345", "zs2", "54321", "zs2@gmail.com", "ShangHai", "研发部成员", null, null, null, null, false);
        assertEquals(1, userService.insert(user1));
        LeaveRequest leaveRequest1 = new LeaveRequest("1", user1,
                sdf2.parse("2021-11-23 10:00:00"),
                sdf2.parse("2021-11-23 17:00:00"), sdf2.parse("2021-11-22 10:00:00"), "sick2", "待审核", null, null, null);
        assertEquals(1, leaveRequestService.insert(leaveRequest1));
        assertEquals("sick2", leaveRequestService.selectById("1").getReason());
        assertEquals(sdf2.parse("2021-11-23 10:00:00"), leaveRequestService.selectById("1").getStartTime());
        assertEquals(userService.selectById("6"), leaveRequestService.selectById("1").getUser());

//      测试leaveRequestService直接带现存的user时，insert()方法的插入功能
        LeaveRequest leaveRequest2 = new LeaveRequest("2", userService.selectById("3"),
                sdf2.parse("2021-11-23 10:00:00"),
                sdf2.parse("2021-11-23 17:00:00"), sdf2.parse("2021-11-22 10:00:00"), "sick2", "待审核", null, null, null);
        assertEquals(1, leaveRequestService.insert(leaveRequest2));
        assertEquals(leaveRequest2, leaveRequestService.selectById("2"));
        assertTrue(userService.selectById("3").equals(leaveRequestService.selectById("2").getUser()));
        assertEquals(sdf2.parse("2021-11-23 10:00:00"), leaveRequestService.selectById("2").getStartTime());

//      测试insert()一个leaveRequest列表
        ArrayList<LeaveRequest> leaveRequests = new ArrayList<>();

        LeaveRequest leaveRequest3 = new LeaveRequest("3", userService.selectById("2"),
                sdf2.parse("2021-11-23 10:00:00"),
                sdf2.parse("2021-11-23 17:00:00"), sdf2.parse("2021-11-22 10:00:00"), "sick3", "待审核", null, null, null);

        LeaveRequest leaveRequest4 = new LeaveRequest("4", userService.selectById("1"),
                sdf2.parse("2021-11-23 10:00:00"),
                sdf2.parse("2021-11-23 17:00:00"), sdf2.parse("2021-11-22 10:00:00"), "sick4", "待审核", null, null, null);
        leaveRequests.add(leaveRequest3);
        leaveRequests.add(leaveRequest4);

        assertEquals(2, leaveRequestService.insert(leaveRequests));

        assertEquals("sick3", leaveRequestService.selectById("3").getReason());
        assertEquals(userService.selectById("2"), leaveRequestService.selectById("3").getUser());

        assertEquals("sick4", leaveRequestService.selectById("4").getReason());
        assertEquals(userService.selectById("1"), leaveRequestService.selectById("4").getUser());


//      测试update()方法
        LeaveRequest leaveRequest5 = leaveRequestService.selectById("2");
        assertNotNull(leaveRequest5);
        leaveRequest5.setReason("sick222");
        assertEquals(1, leaveRequestService.update(leaveRequest5));
        assertEquals("sick222", leaveRequestService.selectById("2").getReason());
        assertEquals(leaveRequest5, leaveRequestService.selectById("2"));

//      测试delete()方法的级联删除功能
        assertEquals(1, leaveRequestService.deleteById("1"));
        assertEquals(1, leaveRequestService.deleteById("2"));
        assertEquals(1, leaveRequestService.deleteById("3"));
        assertEquals(1, leaveRequestService.deleteById("4"));
        assertNull(leaveRequestService.selectById("1"));
        assertNull(leaveRequestService.selectById("2"));
        assertNull(leaveRequestService.selectById("3"));
        assertNull(leaveRequestService.selectById("4"));
        assertNull(departmentLeaveRequestMapper.selectByLeaveRequestId("1"));
        assertNull(departmentLeaveRequestMapper.selectByLeaveRequestId("2"));
        assertNull(departmentLeaveRequestMapper.selectByLeaveRequestId("3"));
        assertNull(departmentLeaveRequestMapper.selectByLeaveRequestId("4"));
    }


    /**
     * DepartmentService的测试类，主要测试它的增删改查功能是否正常。
     * 具有Rollback注解，使其不会对下面的其他测试产生影响。
     * @throws ParseException SimpleDateFormat在日期转换出错时会抛出这个异常。
     */
    @Rollback
    @Test
    void departmentServiceTest() throws ParseException {

//      测试selectAll()方法
        assertTrue(departmentService.selectAll().size() > 0);

//      测试selectById()方法
        assertEquals("青青股份有限公司", departmentService.selectById("0").getName());
        assertEquals("研发部", departmentService.selectById("1").getName());

//      测试用insert()方法插入一个不与其他外部实体管理的department
        Department parentDepartment1 = departmentService.selectById("0");
        Department department1 = new Department("6", "后勤部", parentDepartment1, false, null, null, null);
        departmentService.insert(department1);
        assertEquals("后勤部", departmentService.selectById("6").getName());

//      测试insert()方法的级联插入功能
        Department parentDepartment2 = departmentService.selectById("6");

        ArrayList<User> managers = new ArrayList<>();
        User manager1 = new User("14", "ww2", "22345", "ww2", "12432", "ww2@gmail.com", "Guangzhou", "调研部经理", null, null, null, null, false);
        User manager2 = new User("15", "ww3", "22345", "ww3", "12432", "ww3@gmail.com", "Guangzhou", "调研部经理", null, null, null, null, false);
        managers.add(manager1);
        managers.add(manager2);

        ArrayList<User> users = new ArrayList<>();
        users.add(manager1);
        users.add(manager2);

        ArrayList<LeaveRequest> leaveRequests = new ArrayList<>();
        LeaveRequest leaveRequest1 = new LeaveRequest("6", userService.selectById("3"),
                sdf2.parse("2021-11-23 10:00:00"),
                sdf2.parse("2021-11-23 17:00:00"), sdf2.parse("2021-11-22 10:00:00"), "sick6", "待审核", null, null, null);

        LeaveRequest leaveRequest2 = new LeaveRequest("7", userService.selectById("4"),
                sdf2.parse("2021-11-23 10:00:00"),
                sdf2.parse("2021-11-23 17:00:00"), sdf2.parse("2021-11-22 10:00:00"), "sick7", "待审核", null, null, null);
        leaveRequests.add(leaveRequest1);
        leaveRequests.add(leaveRequest2);

        Department department2 = new Department("7", "调研部", parentDepartment2, false, managers, users, leaveRequests);
        assertEquals(1, departmentService.insert(department2));
        assertEquals("ww2", userService.selectById("14").getName());
        assertEquals("ww3", userService.selectById("15").getName());
        assertEquals(parentDepartment2, departmentService.selectById("7").getDepartment());
        assertEquals(userService.selectById("3"), leaveRequestService.selectById("6").getUser());
        assertEquals(userService.selectById("4"), leaveRequestService.selectById("7").getUser());
        assertEquals(sdf2.parse("2021-11-23 10:00:00"), leaveRequestService.selectById("6").getStartTime());
        assertEquals(sdf2.parse("2021-11-23 10:00:00"), leaveRequestService.selectById("7").getStartTime());
        assertEquals("调研部", departmentService.selectById("7").getName());
        assertEquals(departmentService.selectById("6"), departmentService.selectById("7").getDepartment());
        assertEquals(departmentService.selectById("7"), departmentService.selectByLeaveRequestId("6"));
        assertEquals(departmentService.selectById("7"), departmentService.selectByLeaveRequestId("7"));
        assertTrue(departmentService.selectByUserId("14").contains(departmentService.selectById("7")) && departmentService.selectByUserId("14").size() == 1);
        assertTrue(departmentService.selectByUserId("15").contains(departmentService.selectById("7")) && departmentService.selectByUserId("14").size() == 1);

//      测试update()方法
        Department department3 = departmentService.selectById("6");
        assertNotNull(department3);
        department3.setName("科创部");
        assertEquals(1, departmentService.update(department3));
        assertEquals(department3, departmentService.selectById("6"));

        Department department4 = departmentService.selectById("7");
        assertNotNull(department4);
        department4.setName("科创部");
        assertEquals(1, departmentService.update(department4));
        assertEquals(department4, departmentService.selectById("7"));

//      测试delete()方法的级联删除功能
        assertEquals(1, departmentService.deleteById("6"));
        assertTrue(departmentService.selectById("6").isDeleted());
        assertEquals(0, departmentManagerMapper.selectByDepartmentId("6").size());
        assertEquals(0, departmentUserMapper.selectByDepartmentId("6").size());
        assertEquals(0, departmentLeaveRequestMapper.selectByDepartmentId("6").size());

        assertEquals(1, departmentService.deleteById("7"));
        assertTrue(departmentService.selectById("7").isDeleted());
        assertEquals(0, departmentUserMapper.selectByDepartmentId("7").size());
        assertEquals(0, departmentManagerMapper.selectByDepartmentId("7").size());
        assertEquals(0, departmentLeaveRequestMapper.selectByDepartmentId("7").size());
        assertNull(leaveRequestService.selectById("6"));
        assertNull(leaveRequestService.selectById("7"));
    }
}
