package com.zynsun.openplatform.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 缓存
 */
@Aspect
public class RedisCacheAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Pointcut("@annotation(com.zynsun.openplatform.cache.Cache) || @annotation(com.zynsun.openplatform.cache.Cache.List)")
    public void cacheAspect() {
    }

    @Pointcut("@annotation(com.zynsun.openplatform.cache.CacheClear) || @annotation(com.zynsun.openplatform.cache.CacheClear.List)")
    public void cacheClearAspect() {
    }

    @Around(value = "cacheAspect() || cacheClearAspect()")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {

        if (!System.getProperty("cache", "true").equals("true")){ // 默认启用缓存，可以禁用
            return joinPoint.proceed();
        }

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 1. 清除缓存
        {
            List<CacheClear> cacheClearList = new ArrayList<>();
            CacheClear $cache = method.getAnnotation(CacheClear.class);
            if ($cache != null) {
                cacheClearList.add($cache);
            }
            CacheClear.List $cacheList = method.getAnnotation(CacheClear.List.class);
            if ($cacheList != null) {
                cacheClearList.addAll(Arrays.asList($cacheList.value()));
            }

            // 从缓存中删除
            for (CacheClear cacheClear : cacheClearList) {
                String key = cacheClear.name() + ":" + parseKey(cacheClear.key(), method, joinPoint.getArgs());
                try {
                    CacheHelper.delByPattern(key);
                    LOGGER.info("[清除缓存数据] KEY：{}", key);
                } catch (Exception e) {
                    LOGGER.warn("[清除缓存数据] 清除失败！KEY：{}，原因：{}", key, e.getMessage());
                }
            }
        }

        // 2. 设置或获取缓存
        {
            List<Cache> cacheList = new ArrayList<>();
            Cache $cache = method.getAnnotation(Cache.class);
            if ($cache != null) {
                cacheList.add($cache);
            }
            Cache.List $cacheList = method.getAnnotation(Cache.List.class);
            if ($cacheList != null) {
                cacheList.addAll(Arrays.asList($cacheList.value()));
            }

            // 从缓存中获取数据
            for (Cache cache : cacheList) {
                String key = cache.name() + ":" + parseKey(cache.key(), method, joinPoint.getArgs());
                try {
                    Object result = CacheHelper.getByTTL(key, 3 * 1000L); // 过期时间还剩3秒的话，则从缓存中获取
                    if (result != null) {
                        LOGGER.info("[获取缓存数据] 存在缓存数据！KEY：{}", key);
                        return result;
                    }
                } catch (Exception e) {
                    LOGGER.warn("[获取缓存数据] 获取失败！KEY：{}，原因：{}", key, e.getMessage());
                }
            }

            // 执行
            Object result = joinPoint.proceed();

            // 保存返回结果到缓存中
            for (Cache cache : cacheList) {
                String key = cache.name() + ":" + parseKey(cache.key(), method, joinPoint.getArgs());
                try {
                    CacheHelper.setByKey(key, result, cache.expire());
                    LOGGER.info("[保存缓存数据] KEY：{}", key);
                } catch (Exception e) {
                    LOGGER.warn("[保存缓存数据] 保存失败！KEY：{}，原因：{}", key, e.getMessage());
                }
            }

            return result;
        }
    }

    private static String parseKey(String key, Method method, Object[] args) {
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = discoverer.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
