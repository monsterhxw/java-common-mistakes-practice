package monsterhxw.concurrenttool.threadlocal.controller;

import cn.hutool.core.map.MapUtil;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XueweiHuang
 * @created 2021-03-07
 */
@RestController
@RequestMapping("/threadlocal")
public class ThreadLocalMisuseController {

    private static final ThreadLocal<Integer> CURRENT_USER = ThreadLocal.withInitial(() -> null);

    @GetMapping(path = "/wrong")
    public Map<String, Object> wrong(@RequestParam(name = "userId") Integer userId) {
        // 设置用户信息之前先查询一次 ThreadLocal 中的用户信息
        String before = Thread.currentThread().getName() + ":" + CURRENT_USER.get();
        // 设置用户信息到 ThreadLocal
        CURRENT_USER.set(userId);

        // 设置用户信息之后再查询一次 ThreadLocal 的用户信息
        String after = Thread.currentThread().getName() + ":" + CURRENT_USER.get();

        // 汇总输出两次查询结果
        Map<String, Object> result = MapUtil.newHashMap(2);
        result.put("before", before);
        result.put("after", after);
        return result;
    }

    @GetMapping(path = "/right")
    public Map<String, Object> right(@RequestParam(name = "userId") Integer userId) {
        // 设置用户信息之前先查询一次 ThreadLocal 中的用户信息
        String before = Thread.currentThread().getName() + ":" + CURRENT_USER.get();
        // 设置用户信息到 ThreadLocal
        CURRENT_USER.set(userId);
        try {
            // 设置用户信息之后再查询一次 ThreadLocal 的用户信息
            String after = Thread.currentThread().getName() + ":" + CURRENT_USER.get();
            // 汇总输出两次查询结果
            Map<String, Object> result = MapUtil.newHashMap(2);
            result.put("before", before);
            result.put("after", after);
            return result;
        } finally {
            CURRENT_USER.remove();
        }
    }
}
