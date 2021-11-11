package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private User user;
    private Date start_time;
    private Date end_time;
    private Date summit_time;
    private String reason;
    private String status;
    private User checker;
    private Date check_time;
    private String check_opinion;
}
