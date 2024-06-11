package cn.effine.strategy.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 生成巡检计划任务结果
 */
@Builder
@Data
public class PatrolTask {

    /**
     * 巡检任务ID
     */
    private String id;

    /**
     * 计划ID
     */
    private String planId;

    /**
     * 名称
     */
    private String name;

    /**
     * 计划开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "开始时间")
    private Date startTime;

    /**
     * 计划结束时间
     */
    private Date endTime;

    /**
     * 计划开始执行时间
     */
    private Date execStartTime;

    /**
     * 计划结束执行时间
     */
    private Date execEndTime;

    /**
     * 计划执行状态
     *
     * @see TaskStatusEnum
     */
    private Integer taskStatus;


    // TODO effine 以下为测试字段（方便查看日志）
    private String startTime4Test;
    private String endTime4Test;
    private String execStartTime4Test;
    private String execEndTime4Test;

}
