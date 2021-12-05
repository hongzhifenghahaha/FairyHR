package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper;
import org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper;
import org.fairysoftw.fairyhr.mapper.DepartmentMapper;
import org.fairysoftw.fairyhr.mapper.DepartmentUserMapper;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link org.fairysoftw.fairyhr.service.DepartmentService}的逻辑实现类，
 * 通过MyBatis与数据库的交互，具体的交互在mapper中实现。
 *
 * @version 1.0
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    /*
    用到的一些mapper类和service类
     */
    private final DepartmentMapper departmentMapper;
    private final DepartmentUserMapper departmentUserMapper;
    private final DepartmentManagerMapper departmentManagerMapper;
    private final DepartmentLeaveRequestMapper departmentLeaveRequestMapper;
    private final LeaveRequestService leaveRequestService;
    private final UserService userService;

    /**
     * 构造函数，通过spring实现自动装配。
     */
    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper,
                                 DepartmentUserMapper departmentUserMapper,
                                 DepartmentManagerMapper departmentManagerMapper,
                                 DepartmentLeaveRequestMapper departmentLeaveRequestMapper,
                                 LeaveRequestService leaveRequestService, UserService userService) {
        this.departmentMapper = departmentMapper;
        this.departmentUserMapper = departmentUserMapper;
        this.departmentManagerMapper = departmentManagerMapper;
        this.departmentLeaveRequestMapper = departmentLeaveRequestMapper;
        this.leaveRequestService = leaveRequestService;
        this.userService = userService;
    }

    /*
    覆写方法的注释详见对应的接口，这里不再重复写注释。
     */

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public Department selectById(String id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public List<Department> selectByUserId(String user_id) {
        return departmentUserMapper.selectByUserId(user_id);
    }

    @Override
    public Department selectByLeaveRequestId(String request_id) {
        return departmentLeaveRequestMapper.selectByLeaveRequestId(request_id);
    }

    @Override
    public int insert(Department department) {
        if (department == null) {
            return 0;
        }
        var ret = departmentMapper.insert(department);
        if (ret != 0) {
            insertCascade(department);
        }
        return ret;
    }

    @Override
    public int update(Department department) {
        if (department == null) {
            return 0;
        }
        var ret = departmentMapper.update(department);
        updateCascade(department);
        return ret;
    }

    @Override
    @Rollback
    public int deleteById(String id) {
        var department = departmentMapper.selectById(id);
        if(department != null) {
            department.getLeaveRequests().clear();
            department.getUsers().clear();
            department.getManagers().clear();
            department.setDeleted(true);
            department.setDepartment(null);
            this.update(department);
            return departmentMapper.deleteById(id);
        }
        else {
            return 0;
        }
    }

    /**
     * 级联插入department。
     * <br><br>
     * 即插入department之后，也会把它的user列表，manager列表，leaveRequest列表中的所有元素都插入到数据库中（如果它们不在数据库中）
     *
     * @param department 执行级联插入操作的department
     */
    private void insertCascade(Department department) {
        if (department.getUsers() != null) {
            for (var user : department.getUsers()) {
                if (userService.insert(user) == 0) {
                    userService.update(user);
                }
                departmentUserMapper.insert(user.getId(), department.getId());
            }
        }
        if (department.getManagers() != null) {
            for (var manager : department.getManagers()) {
                departmentManagerMapper.insert(manager.getId(), department.getId());
            }
        }
        if (department.getLeaveRequests() != null) {
            for (var leaveRequest : department.getLeaveRequests()) {
                if (leaveRequestService.insert(leaveRequest) == 0) {
                    leaveRequestService.update(leaveRequest);
                }
                departmentLeaveRequestMapper.insert(leaveRequest.getId(), department.getId());
            }
        }
    }

    /**
     * 级联更新department。
     * <br><br>
     * 即更新department之后，也会把它的user列表，manager列表，leaveRequest列表中的所有元素在数据库中更新。
     * <br><br>
     * 注意，department的delete操作之后也会执行updateCascade操作，所以如果是删除department,其关联的实体也会被执行删除操作。
     * 但实际上删除department的同时只会删除该部门下的所有leaveRequest，而对于manager与user则不会真的删除，只是它们的关联关系被删除了。
     *
     * @param department 执行级联更新操作的department
     */
    private void updateCascade(Department department) {
        if (department.getUsers() != null) {
            var originUsers = departmentUserMapper.selectByDepartmentId(department.getId());
            var nowUsers = department.getUsers();
            for (var user : nowUsers) {
                if (userService.insert(user) == 0) {
                    userService.update(user);
                }
                departmentUserMapper.insert(user.getId(), department.getId());
            }
            for (var user : originUsers) {
                if (nowUsers.stream().noneMatch((u) -> u.getId().equals(user.getId()))) {
                    departmentUserMapper.delete(user.getId(), department.getId());
                    departmentManagerMapper.delete(user.getId(), department.getId());
                }
            }
        }

        if (department.getManagers() != null) {
            var originManages = departmentManagerMapper.selectByDepartmentId(department.getId());
            var nowManagers = department.getManagers();
            for (var manager : nowManagers) {
                departmentManagerMapper.insert(manager.getId(), department.getId());
            }

            for (var manager : originManages) {
                if (nowManagers.stream().noneMatch((u) -> u.getId().equals(manager.getId()))) {
                    departmentManagerMapper.delete(manager.getId(), department.getId());
                }
            }
        }

        if (department.getLeaveRequests() != null) {
            var originLeaveRequests = departmentLeaveRequestMapper.selectByDepartmentId(department.getId());
            var nowLeaveRequests = department.getLeaveRequests();
            for (var leaveRequest : nowLeaveRequests) {
                if (leaveRequestService.insert(leaveRequest) == 0) {
                    leaveRequestService.update(leaveRequest);
                }
                departmentLeaveRequestMapper.insert(leaveRequest.getId(), department.getId());
            }
            for (var request : originLeaveRequests) {
                if (nowLeaveRequests.stream().noneMatch((r) -> r.getId().equals(request.getId()))) {
                    leaveRequestService.deleteById(request.getId());
                }
            }
        }
    }
}
