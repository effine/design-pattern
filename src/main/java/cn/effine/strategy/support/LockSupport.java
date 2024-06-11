package cn.effine.strategy.support;

import cn.effine.common.util.StringUtil;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 锁服务类(多台服务器建议使用分布式锁)
 *
 * @author effine
 * @Date 2023/10/11 16:07
 * @email iballad#163.com
 */
@Slf4j
@Component
public class LockSupport {

    /**
     * 本地构建线程安全的锁列表
     */
    private static final Set<String> LOCAL_LOCK_SET = Sets.newConcurrentHashSet();


    /**
     * 加锁方法
     *
     * @param key 锁名称
     * @return 是否加锁成功
     */
    public boolean lock(String key) {
        if (StringUtil.isBlank(key)) {
            throw new RuntimeException("加锁名称为空，请检查");
        }
        return LOCAL_LOCK_SET.add(key);
    }

    /**
     * 释放锁
     *
     * @param key 锁名称
     * @return 是否释放锁成功
     */
    public boolean unLock(String key) {
        if (StringUtil.isBlank(key)) {
            throw new RuntimeException("释放锁名称为空，请检查");
        }
        if (LOCAL_LOCK_SET.contains(key)) {
            log.info("释放锁成功: key={}", key);
            return LOCAL_LOCK_SET.remove(key);
        }
        return true;
    }

}
