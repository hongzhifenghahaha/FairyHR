package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

/**
 * 时间表model类，用于描述用户的日程和请假。
 * 构造函数，getter，setter，equals等方法通过lombok自动生成。
 *
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    /**
     * 时间表的id，非空。
     */
    @NonNull
    private String id;

    /**
     * 开始时间，非空。
     */
    @NonNull
    private Date startTime;

    /**
     * 结束时间，非空。
     */
    @NonNull
    private Date endTime;

    /**
     * 开始日期，非空。
     */
    @Nullable
    private Date startDate;

    /**
     * 结束日期，非空。
     */
    @Nullable
    private Date endDate;

    /**
     * 该时间表所描述的日程周期，详见{@link org.fairysoftw.fairyhr.model.ScheduleFrequency}，非空。
     */
    @NonNull
    private ScheduleFrequency frequency;

    /**
     * 该时间表所描述的是日程周期的第几天。
     * <br>
     * 如果为{@link org.fairysoftw.fairyhr.model.ScheduleFrequency#ONCE}，则该值没有用。
     * <br>
     * 如果为{@link org.fairysoftw.fairyhr.model.ScheduleFrequency#DAILY}，则该值也没有用,可以默认视为1。
     * <br>
     * 如果为{@link org.fairysoftw.fairyhr.model.ScheduleFrequency#WEEKLY}，则该值范围为(1,2,3,4,5,6,7)，表示星期一到星期七。
     * <br>
     * 如果为{@link org.fairysoftw.fairyhr.model.ScheduleFrequency#MONTHLY}，则该值范围为[1,31]，表示该月的某一天。
     * <br>
     * 如果为{@link org.fairysoftw.fairyhr.model.ScheduleFrequency#YEARLY}，则该值范围为[1,366]，表示该年的某一天。
     */
    @Nullable
    private int frequencyValue;
}
