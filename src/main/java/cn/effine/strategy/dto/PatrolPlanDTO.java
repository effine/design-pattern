package cn.effine.strategy.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 巡检计划参数
 */
@Builder
@Data
public class PatrolPlanDTO implements Serializable {

    /**
     * 计划ID
     */
    @JSONField(ordinal = 1, name = "计划ID")
    private String planId;

    /**
     * 巡检周期单位
     *
     * @see PatrolCycleUnitEnum
     */
    @JSONField(ordinal = 2, name = "巡检周期单位(0单次、1时、2天、3月)")
    private Integer patrolCycleUnit;

    /**
     * 巡检周期值
     */
    @JSONField(ordinal = 3, name = "巡检周期值")
    private Integer patrolCycle;

    /**
     * 开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", ordinal = 4, name = "开始时间")
    private Date startTime;

    /**
     * 结束时间类型
     *
     * @see EndTimeTypeEnum
     */
    @JSONField(ordinal = 5, name = "结束时间类型(0无、1重复次数、2指定时间)")
    private Integer endTimeType;

    /**
     * 重复次数
     */
    @JSONField(ordinal = 6, name = "重复次数")
    private Integer repeatTime;

    /**
     * 结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", ordinal = 7, name = "结束时间")
    private Date endTime;

    /**
     * 巡检开始时间(工作时间)
     */
    @JSONField(format = "HH:mm:ss", ordinal = 8, name = "巡检开始时间")
    private Date patrolStartTime;

    /**
     * 巡检结束时间(工作时间)
     */
    @JSONField(format = "HH:mm:ss", ordinal = 9, name = "巡检结束时间")
    private Date patrolEndTime;

    /**
     * 工作日历
     *
     * @see WorkCalendarEnum
     */
    @JSONField(ordinal = 10, name = "工作日历(0每天、1工作日、2非工作日)")
    private Integer workCalendar;

    /**
     * 提前时间（计算周期时间上提前）
     */
    @JSONField(ordinal = 11, name = "提前时间")
    private Integer noAdvance;

    /**
     * 提前时间单位
     *
     * @see AppendTimeUnitEnum
     */
    @JSONField(ordinal = 12, name = "提前时间单位(1分、2时)")
    private Integer noAdvanceUnit;

    /**
     * 延后时间（计算周期时间上延后）
     */
    @JSONField(ordinal = 13, name = "延后时间")
    private Integer noPostpone;

    /**
     * 延后时间单位
     *
     * @see AppendTimeUnitEnum
     */
    @JSONField(ordinal = 14, name = "延后时间单位(1分、2时)")
    private Integer noPostponeUnit;

    /**
     * 是否生成过期（结束时间比当前时间早）的巡检任务
     */
    @JSONField(ordinal = 15, name = "是否生成过期任务")
    private Boolean isGenerate4Expire;

}
