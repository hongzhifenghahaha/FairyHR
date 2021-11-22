package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum ScheduleFrequency {
    ONCE(0),
    DAILY(1),
    WEEKLY(2),
    MONTHLY(3),
    YEARLY(4);

    @Getter
    private final Integer value;
}
