package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceTime {
    @NonNull
    private Date time;
}
