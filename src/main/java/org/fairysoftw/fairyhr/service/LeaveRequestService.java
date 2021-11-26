package org.fairysoftw.fairyhr.service;

import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface LeaveRequestService {

    /**
     * 查询所有的请假申请
     *
     * @return 包含所有请假申请实例的列表
     */
    List<LeaveRequest> selectAll();

    /**
     * 查询id符合的请假申请
     *
     * @param id 请假申请id
     * @return id符合的请假申请，若没有符合的请假申请，则返回null
     */
    @Nullable
    LeaveRequest selectById(String id);

    /**
     * 插入新的请假申请
     *
     * @param leaveRequest 新的请假申请实例
     * @return 插入的记录条数
     */
    int insert(LeaveRequest leaveRequest);

    /**
     * 插入新的请假申请
     *
     * @param leaveRequests 请假申请实例列表
     * @return 插入的记录条数
     */
    int insert(List<LeaveRequest> leaveRequests);

    /**
     * 更新请假申请
     *
     * @param leaveRequest 请假申请实例
     * @return 更新的记录条数
     */
    int update(LeaveRequest leaveRequest);

    /**
     * 根据id删除请假申请
     *
     * @param id 请假申请id
     * @return 删除的记录条数
     */
    int deleteById(String id);
}
