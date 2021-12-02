package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NonNull
    private String id;
    @NonNull
    private String name;
    @Nullable
    private String phoneNumber;
    @NonNull
    private String password; // TODO: use hashed password with salt later
    @Nullable
    private String residentId;
    @Nullable
    private String emailAddr;
    @Nullable
    private String address;
    @Nullable
    private String position;
    @Nullable
    private List<Schedule> schedules;
    @Nullable
    private List<AttendanceTime> attendanceTimes;
    @Nullable
    private List<Schedule> leaves;
    @Nullable
    private List<LeaveRequest> leaveRequests;
    @NonNull
    private boolean deleted;
}
