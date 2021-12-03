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

import java.util.List;
import java.util.stream.Collectors;

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
    public int deleteById(String id) {
        var department = departmentMapper.selectById(id);
        if(department != null) {
            var leaveRequestsId = department.getLeaveRequests().
                    stream().
                    map(LeaveRequest::getId).
                    collect(Collectors.toList());
            for(var requestId: leaveRequestsId) {
                leaveRequestService.deleteById(requestId);
            }
            departmentUserMapper.deleteByDepartmentId(id);
            departmentManagerMapper.deleteByDepartmentId(id);
            department.setDeleted(true);
            return this.update(department);
        }
        else {
            return 0;
        }
    }

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
