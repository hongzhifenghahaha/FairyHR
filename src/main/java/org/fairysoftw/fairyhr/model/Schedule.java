package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @NonNull
    private String id;
    @NonNull
    private Date startTime;
    @NonNull
    private Date endTime;
    @Nullable
    private Date startDate;
    @Nullable
    private Date endDate;
    @NonNull
    private ScheduleFrequency frequency;
    @Nullable
    private int frequencyValue;
}
