package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 部门model类，维护成树状，有利于分层管理公司。
 * 构造函数，getter，setter，equals等方法通过lombok自动生成。
 *
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    /**
     * 部门id，非空。
     */
    @NonNull
    private String id;

    /**
     * 部门名，非空。
     */
    @NonNull
    private String name;

    /**
     * 该部门的上级部门，若没有则为null。
     */
    @Nullable
    private Department department;

    /**
     * 删除标志，非空。
     */
    @NonNull
    private boolean deleted;

    /**
     * 该部门的管理员列表，未指定管理员时为空。
     */
    @Nullable
    private List<User> managers;

    /**
     * 该部门的用户列表，未指定时为空。
     */
    @Nullable
    private List<User> users;

    /**
     * 所属该部门的所有请假申请，没有申请时为空。
     */
    @Nullable
    private List<LeaveRequest> leaveRequests;
}
