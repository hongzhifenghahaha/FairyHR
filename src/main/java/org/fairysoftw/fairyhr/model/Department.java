package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private String id;
    private String name;
    private Department department;
    private boolean deleted;
    private List<User> managers;
    private List<User> users;
    private List<LeaveRequest> leaveRequests;
}
