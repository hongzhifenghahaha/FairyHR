package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    @NonNull
    private String id;
    @NonNull
    private User user;
    @NonNull
    private Date startTime;
    @NonNull
    private Date endTime;
    @NonNull
    private Date submitTime;
    @Nullable
    private String reason;
    @Nullable
    private String status;
    @Nullable
    private User checker;
    @Nullable
    private Date checkTime;
    @Nullable
    private String checkOpinion;
}
