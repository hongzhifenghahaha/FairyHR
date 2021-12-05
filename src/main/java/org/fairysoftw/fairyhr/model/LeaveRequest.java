package org.fairysoftw.fairyhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

/**
 * 请假申请model类，由用户提出申请，由管理员负责审核。
 * 构造函数，getter，setter，equals等方法通过lombok自动生成。
 *
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {

    /**
     * 请假申请的id，非空。
     */
    @NonNull
    private String id;

    /**
     * 提出请假申请的user，非空。
     */
    @NonNull
    private User user;

    /**
     * 请假的开始时间，非空。
     */
    @NonNull
    private Date startTime;

    /**
     * 请假的结束时间，非空。
     */
    @NonNull
    private Date endTime;

    /**
     * 请假的提交时间，非空。
     */
    @NonNull
    private Date submitTime;

    /**
     * 请假的原因，可为空。
     */
    @Nullable
    private String reason;

    @NonNull
    private String type;

    /**
     * 请假申请的审批状态，可为空。
     */
    @Nullable
    private String status;

    /**
     * 请假的审批人，未审批前为空。
     */
    @Nullable
    private User checker;

    /**
     * 审批时间，未审批前为空。
     */
    @Nullable
    private Date checkTime;

    /**
     * 审批选项，未审批前为空。
     */
    @Nullable
    private String checkOpinion;
}
