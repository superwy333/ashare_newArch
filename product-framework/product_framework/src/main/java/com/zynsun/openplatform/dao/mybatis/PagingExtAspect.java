package com.zynsun.openplatform.dao.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zynsun.openplatform.domain.PageModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * 分页增强拦截器：自动根据请求参数和返回参数处理分页
 *
 * @ClassName: PagingExtAspect
 * @author gongdaowen
 * @date 2017年03月31日
 *
 */
@Aspect
public class PagingExtAspect {

    private static final Integer PAGE_SIZE_LIMIT = 200000; // 每次查询的最大限制数量

    @Pointcut("execution(public * com.zynsun..*Mapper.*(..)))")
    public void mapperAspect() {
    }

    @Around(value = "mapperAspect()")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        // 1. 判断方法返回值是否是Page类型
        if(method.getReturnType().equals(Page.class)){
            // 2. 判断请求参数中是否有PageModel类型的参数
            Object[] args = joinPoint.getArgs();
            for(Object object : args){
                if(object != null && PageModel.class.isAssignableFrom(object.getClass())){
                    PageModel pageModel = (PageModel) object;
                    Assert.notNull(pageModel.getPage(), "分页参数[当前页]不能为空");
                    Assert.notNull(pageModel.getPageSize(), "分页参数[分页大小]不能为空");
                    Assert.isTrue(pageModel.getPageSize() <= PAGE_SIZE_LIMIT, "分页参数[分页大小]太大");
                    if(pageModel.getPageSize() == -1){
                        pageModel.setPageSize(PAGE_SIZE_LIMIT);
                    }
                    // 设置请求参数
                    PageHelper.startPage(pageModel.getPage(), pageModel.getPageSize());
                    break;
                }
            }
        }
        return joinPoint.proceed();
    }

}
