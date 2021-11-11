package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecord {
    private List<Schedule> schedules;
    private List<Date> attendances;
    private List<Schedule> leaves;
}
