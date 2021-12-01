package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @NonNull
    private String id;
    @NonNull
    private String name;
    @Nullable
    private Department department;
    @NonNull
    private boolean deleted;
    @Nullable
    private List<User> managers;
    @Nullable
    private List<User> users;
    @Nullable
    private List<LeaveRequest> leaveRequests;
}
