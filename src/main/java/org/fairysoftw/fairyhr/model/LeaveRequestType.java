package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * 请假类型model类，用于记录签到。
 * 构造函数，getter，setter，equals等方法通过lombok自动生成。
 *
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestType {
    /**
     * 请假类型的名称。
     */
    @NonNull
    private String name;
}
