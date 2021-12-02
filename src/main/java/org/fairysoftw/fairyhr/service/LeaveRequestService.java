package org.fairysoftw.fairyhr.service;

import org.fairysoftw.fairyhr.model.Department;
import org.fairysoftw.fairyhr.model.LeaveRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
     * 插入新的请假申请，参数leaveRequest中的{@link org.fairysoftw.fairyhr.model.LeaveRequest#id}、
     * {@link org.fairysoftw.fairyhr.model.LeaveRequest#user}、
     * {@link org.fairysoftw.fairyhr.model.LeaveRequest#startTime}、
     * {@link org.fairysoftw.fairyhr.model.LeaveRequest#endTime}、
     * {@link org.fairysoftw.fairyhr.model.LeaveRequest#submitTime}不能为null。
     * <br><br>
     * 必须保证{@link org.fairysoftw.fairyhr.model.LeaveRequest#user}在数据库中存在，该方法不会对于user进行插入或更新。
     *
     * @param leaveRequest 新的请假申请实例
     * @return 插入的记录条数
     */
    int insert(LeaveRequest leaveRequest);

    /**
     * 插入多条的请假申请，
     * 参数要求参考{@link org.fairysoftw.fairyhr.service.LeaveRequestService#insert(LeaveRequest)}
     *
     * @param leaveRequests 请假申请实例列表
     * @return 插入的记录条数
     */
    int insert(List<LeaveRequest> leaveRequests);

    /**
     * 更新请假申请，若不存在id相同的请假申请，则不执行任何操作。
     * <br><br>
     * 参数leaveRequest中的{@link org.fairysoftw.fairyhr.model.LeaveRequest#id}、
     * {@link org.fairysoftw.fairyhr.model.LeaveRequest#user}、
     * {@link org.fairysoftw.fairyhr.model.LeaveRequest#startTime}、
     * {@link org.fairysoftw.fairyhr.model.LeaveRequest#endTime}、
     * {@link org.fairysoftw.fairyhr.model.LeaveRequest#submitTime}不能为null。
     * <br><br>
     * 必须保证{@link org.fairysoftw.fairyhr.model.LeaveRequest#user}在数据库中存在，该方法不会对于user进行插入或更新。
     *
     * @param leaveRequest 请假申请实例
     * @return 更新的记录条数
     */
    int update(LeaveRequest leaveRequest);

    /**
     * 根据id删除请假申请，不会删除该请假申请对应的用户。
     * 删除的同时接触与部门的关联。
     *
     * @param id 请假申请id
     * @return 删除的记录条数
     */
    int deleteById(String id);

    /**
     * 删除某个用户发出的请假申请，不会删除该请假申请对应的用户。
     * 删除的同时接触与部门的关联。
     *
     * @param user_id 用户id
     * @return 删除的记录条数
     */
    int deleteByUserId(String user_id);
}
