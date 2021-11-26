package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private String id;
    private User user;
    private Date startTime;
    private Date endTime;
    private Date summitTime;
    private String reason;
    private String status;
    private User checker;
    private Date checkTime;
    private String checkOpinion;
}
