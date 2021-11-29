package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper;
import org.fairysoftw.fairyhr.mapper.DepartmentManagerMapper;
import org.fairysoftw.fairyhr.mapper.DepartmentMapper;
import org.fairysoftw.fairyhr.mapper.DepartmentUserMapper;
import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentMapper departmentMapper;
    private final DepartmentUserMapper departmentUserMapper;
    private final DepartmentManagerMapper departmentManagerMapper;
    private final DepartmentLeaveRequestMapper departmentLeaveRequestMapper;
    private final LeaveRequestService leaveRequestService;
    private final UserService userService;

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


    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public Department selectById(String id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public int insert(Department department) {
        var ret = departmentMapper.insert(department);
        insertCascade(department);
        return ret;
    }

    @Override
    public int update(Department department) {
        var ret = departmentMapper.update(department);
        insertCascade(department);
        return ret;
    }

    @Override
    public int deleteById(String id) {
        var department = departmentMapper.selectById(id);
        department.setDeleted(true);
        return this.update(department);
    }

    private void insertCascade(Department department) {
        leaveRequestService.insert(department.getLeaveRequests());
        userService.insert(department.getUsers());
        userService.insert(department.getManagers());
        for(var user: department.getUsers()) {
            departmentUserMapper.insert(user.getId(), department.getId());
        }
        for(var manager: department.getManagers()) {
            departmentManagerMapper.insert(manager.getId(), department.getId());
        }
        for(var leaveRequest: department.getLeaveRequests()) {
            departmentLeaveRequestMapper.insert(leaveRequest.getId(), department.getId());
        }
    }
}
