package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private AttendanceRecord checkTable;
}
