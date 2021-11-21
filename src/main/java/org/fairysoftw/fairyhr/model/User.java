package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String phoneNumber;
    private String password; // TODO: use hashed password with salt later
    private String residentId;
    private String emailAddr;
    private String address;
    private String position;
    private List<Schedule> schedules;
    private List<Date> attendancesTime;
    private List<Schedule> leaves;
    private boolean deleted;
}
