package cn.effine.strategy.task;

import cn.effine.common.util.BooleanUtil;
import cn.effine.common.util.CollectionUtil;
import cn.effine.strategy.constant.Constant;
import cn.effine.strategy.dto.PatrolTask;
import cn.effine.strategy.enums.TaskStatusEnum;
import cn.effine.strategy.support.LockSupport;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 巡检任务定时执行
 *
 * @author effine
 * @Date 2023/10/11 14:54
 * @email iballad#163.com
 */
@Slf4j
@Component
public class PatrolExecTask {
    @Resource
    private LockSupport lockSupport;

    /**
     * 巡检任务执行线程池
     */
    private static final ThreadPoolExecutor PATROL_EXEC_POOL = new ThreadPoolExecutor(5, 10,
            1L, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(50),
            new ThreadFactoryBuilder().setNameFormat("PatrolTaskExecThread-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 任务执行方法(每分钟执行一次)
     */
    //@Scheduled(cron = "0 */1 * * * ?")
    public void exec() {
        // 查询待执行的巡检任务
        List<PatrolTask> list = this.buildPatrolTask4Fake();
        if (CollectionUtil.isEmpty(list)) {
            log.info("没有查询到待执行的巡检任务列表");
            return;
        }

        // 多线程执行任务细节
        list.forEach(item -> PATROL_EXEC_POOL.submit(() -> {
            try {
                boolean status = this.execUnit(item);
                log.info("巡检任务执行结果：id={}, status={}", item.getId(), status);
            } catch (Exception e) {
                log.error("多线程中执行单次任务异常：{}", JSON.toJSONString(item), e);
            }
        }));
    }

    /**
     * 巡检任务执行单元；执行步骤：
     * 1.具体的任务逻辑代码
     * 2.修改巡检任务状态 {@link TaskStatusEnum}
     *
     * @param task 巡检任务
     * @return 任务是否执行成功
     */
    private boolean execUnit(PatrolTask task) {
        if (Objects.isNull(task)) {
            return false;
        }

        String lockKey = String.format(Constant.PATROL_TASK_EXEC_CLOCK_PREFIX, task.getId());
        // 加锁: 巡检任务ID加锁，避免多次定时任务扫到同一条记录，多次执行
        boolean isObtainLock = lockSupport.lock(lockKey);
        try {
            if (isObtainLock) {
                // 执行具体业务逻辑
                log.info("执行巡检任务：taskId={}", task.getId());
            }
            return true;
        } catch (Exception e) {
            log.error("执行巡检任务时发生异常：{}", JSON.toJSONString(task), e);
            return false;
        } finally {
            // 释放锁
            boolean isUnClock = lockSupport.unLock(lockKey);
            if (BooleanUtil.isFalse(isUnClock)) {
                log.error("执行巡检任务释放锁失败，需人工介入查看原因: lockKey={}", lockKey);
            }
        }
    }

    /**
     * 构建假数据-巡检任务列表
     *
     * @return 巡检任务列表
     */
    private List<PatrolTask> buildPatrolTask4Fake() {
        List<PatrolTask> list = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            list.add(PatrolTask.builder().id(String.valueOf(i + 1)).build());
        }
        return list;
    }
}
