package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Period;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Long start_time;
    private Long end_time;
    private Date start_date;
    private Date end_date;
    private ScheduleFrequency frequency;
}
