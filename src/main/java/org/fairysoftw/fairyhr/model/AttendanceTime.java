package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * 出勤时间model类，用于记录签到。
 * 构造函数，getter，setter，equals等方法通过lombok自动生成。
 *
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceTime {

    /**
     * Date类型的时间，非空。
     */
    @NonNull
    private Date time;
}
