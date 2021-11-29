package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
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
    private List<AttendanceTime> attendanceTimes;
    private List<Schedule> leaves;
    private boolean deleted;
}
