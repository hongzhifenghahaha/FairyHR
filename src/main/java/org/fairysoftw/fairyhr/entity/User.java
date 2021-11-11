package org.fairysoftw.fairyhr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class User {
    private String id;
    private String name;
    private String phoneNumber;
    private String password; // TODO: use hashed password with salt later
    private String residentId;
    private String emailAddr;
    private String address;
    private String position;
}
