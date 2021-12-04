package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 枚举类，用于表示某时间表所描述的日程周期，有五个枚举值，分别表示一次性日程，每日日程，每周日程，月度日程，年度日程。
 * 构造函数，getter，setter，equals,toString()等方法通过lombok自动生成。
 *
 * @version 1.0
 */
@ToString
@AllArgsConstructor
public enum ScheduleFrequency {
    /**
     * 一次性日程安排
     */
    ONCE(0),

    /**
     * 每日日程安排
     */
    DAILY(1),

    /**
     * 每周日程安排
     */
    WEEKLY(2),

    /**
     * 月度日程安排
     */
    MONTHLY(3),

    /**
     * 年度日程安排
     */
    YEARLY(4);

    /**
     * 枚举变量对应的整数值
     */
    @Getter
    private final Integer value;
}
