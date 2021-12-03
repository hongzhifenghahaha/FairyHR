package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper;
import org.fairysoftw.fairyhr.mapper.LeaveRequestMapper;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final LeaveRequestMapper leaveRequestMapper;
    private final DepartmentLeaveRequestMapper departmentLeaveRequestMapper;
    private final UserService userService;

    @Autowired
    public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, DepartmentLeaveRequestMapper departmentLeaveRequestMapper, @Lazy UserService userService) {
        this.leaveRequestMapper = leaveRequestMapper;
        this.departmentLeaveRequestMapper = departmentLeaveRequestMapper;
        this.userService = userService;
    }

    @Override
    public List<LeaveRequest> selectAll() {
        return leaveRequestMapper.selectAll();
    }

    @Override
    public LeaveRequest selectById(String id) {
        return leaveRequestMapper.selectById(id);
    }

    @Override
    public int insert(LeaveRequest leaveRequest) {
        return leaveRequestMapper.insert(leaveRequest);
    }

    @Override
    public int insert(List<LeaveRequest> leaveRequests) {
        int i = 0;
        if (leaveRequests != null) {
            for (var leaveRequest : leaveRequests) {
                i += insert(leaveRequest);
            }
        }
        return i;
    }

    @Override
    public int update(LeaveRequest leaveRequest) {
        return leaveRequestMapper.update(leaveRequest);
    }

    @Override
    public int deleteById(String id) {
        departmentLeaveRequestMapper.deleteByRequestId(id);
        return leaveRequestMapper.deleteById(id);
    }

    @Override
    public int deleteByUserId(String user_id) {
        var requests = leaveRequestMapper.selectByUserId(user_id);
        int ret = 0;
        for (var request: requests) {
            ret += deleteById(request.getId());
        }
        return ret;
    }
}
