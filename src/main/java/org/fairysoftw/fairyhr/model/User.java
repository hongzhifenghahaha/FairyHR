package org.fairysoftw.fairyhr.model;

import lombok.*;
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

/**
 * 用户model类
 * 构造函数，getter，setter，equals,toString()等方法通过lombok自动生成。
 *
 * @version 1.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户id，非空。
     */
    @NonNull
    private String id;

    /**
     * 用户姓名，非空。
     */
    @NonNull
    private String name;

    /**
     * 用户电话号码，可不填。
     */
    @Nullable
    private String phoneNumber;

    /**
     * 用户密码，非空。
     */
    @NonNull
    private String password; // TODO: use hashed password with salt later

    /**
     * 用户身份证号码，可不填。
     */
    @Nullable
    private String residentId;

    /**
     * 用户邮箱地址，可不填。
     */
    @Nullable
    private String emailAddr;

    /**
     * 用户住址，可不填。
     */
    @Nullable
    private String address;

    /**
     * 用户在部门中的职位，可不填。
     */
    @Nullable
    private String position;

    /**
     * 用户的时间表，可为空。
     */
    @Nullable
    private List<Schedule> schedules;

    /**
     * 用户的出勤时间表，可为空
     */
    @Nullable
    private List<AttendanceTime> attendanceTimes;

    /**
     * 用户的请假时间表，可为空
     */
    @Nullable
    private List<Schedule> leaves;

    /**
     * 用户的请假申请列表，可为空。
     * 为了避免equals方法循环依赖，在equals方法中不比较该属性。
     */
    @Nullable
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<LeaveRequest> leaveRequests;

    /**
     * 删除标志，非空。
     */
    @NonNull
    private boolean deleted;
}
