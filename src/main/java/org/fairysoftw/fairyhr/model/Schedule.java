package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private String id;
    private Long startTime;
    private Long endTime;
    private Date startDate;
    private Date endDate;
    private ScheduleFrequency frequency;
    private int frequencyValue;
}
