package com.zynsun.platform.edoc.aspect;

import com.sun.xml.bind.v2.TODO;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.annotation.Validata;
import com.zynsun.platform.edoc.utils.ValidatorUtil;
import exception.ParamInvalidException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 10:58 2017/12/28
 * @Modified By:
 */
@Component
@Aspect
public class MapperInterceptor {

    @Before("execution(* com.zynsun.platform.edoc.mapper.*.insert*(..))")
    public void ServiceInterceptor(JoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        ExecuteResult<String> errorResult = new ExecuteResult<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i].getClass().isAnnotationPresent(Validata.class)) {
                    Map<String, StringBuffer> errorMap = ValidatorUtil.validate(args[i]);
                    if (!BeanUtil.isEmpty(errorMap)) {
                        for (Map.Entry<String, StringBuffer> m : errorMap.entrySet()) {
                            errorResult.addFieldError(m.getKey(), m.getValue().toString());
                        }
                        throw new ParamInvalidException("数据校验失败", errorResult);
                    }
                }else if(args[i].getClass().newInstance() instanceof List){
                    Iterator iterator = ((List)args[i]).iterator();
                    while (iterator.hasNext()){
                        if(iterator.next().getClass().isAnnotationPresent(Validata.class)){
                            //TODO
                        }
                    }
                }
            }
        }
    }
}
