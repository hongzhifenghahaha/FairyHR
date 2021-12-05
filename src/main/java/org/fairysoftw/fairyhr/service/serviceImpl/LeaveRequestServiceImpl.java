package org.fairysoftw.fairyhr.service.serviceImpl;

import org.fairysoftw.fairyhr.mapper.DepartmentLeaveRequestMapper;
import org.fairysoftw.fairyhr.mapper.LeaveRequestMapper;
import org.fairysoftw.fairyhr.mapper.LeaveRequestTypeMapper;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.fairysoftw.fairyhr.service.LeaveRequestService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link org.fairysoftw.fairyhr.service.LeaveRequestService}的逻辑实现类，
 * 通过MyBatis与数据库的交互，具体的交互在mapper中实现。
 *
 * @version 1.0
 */
@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    /*
    用到的一些mapper类和service类
     */
    private final LeaveRequestMapper leaveRequestMapper;
    private final LeaveRequestTypeMapper leaveRequestTypeMapper;
    private final DepartmentLeaveRequestMapper departmentLeaveRequestMapper;
    private final UserService userService;

    /**
     * 构造函数，通过spring实现自动装配。
     */
    @Autowired
    public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, LeaveRequestTypeMapper leaveRequestTypeMapper, DepartmentLeaveRequestMapper departmentLeaveRequestMapper, @Lazy UserService userService) {
        this.leaveRequestMapper = leaveRequestMapper;
        this.leaveRequestTypeMapper = leaveRequestTypeMapper;
        this.departmentLeaveRequestMapper = departmentLeaveRequestMapper;
        this.userService = userService;
    }

    /*
    覆写方法的注释详见对应的接口，这里不再重复写注释。
     */

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

    @Override
    public List<String> selectAllType() {
        return leaveRequestTypeMapper.selectAll();
    }

    @Override
    public int insertType(String name) {
        return leaveRequestTypeMapper.insert(name);
    }

    @Override
    public int deleteType(String name) {
        return leaveRequestTypeMapper.delete(name);
    }
}
