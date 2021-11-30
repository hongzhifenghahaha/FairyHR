package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.LeaveRequestMapper;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final LeaveRequestMapper leaveRequestMapper;
    private final UserService userService;

    @Autowired
    public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, UserService userService) {
        this.leaveRequestMapper = leaveRequestMapper;
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
        var ret = leaveRequestMapper.insert(leaveRequest);
        userService.insert(leaveRequest.getUser());
        return ret;
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
        userService.insert(leaveRequest.getUser());
        return leaveRequestMapper.update(leaveRequest);
    }

    @Override
    public int deleteById(String id) {
        return leaveRequestMapper.deleteById(id);
    }
}
