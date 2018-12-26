package com.zynsun.openplatform.dubboRestLoginFilter.aspect;

import com.zynsun.openplatform.context.PlatformContext;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.LoadConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-23 12:52
 **/

@Aspect
public class LoginAspect {

    @Pointcut("@annotation(com.zynsun.openplatform.dubboRestLoginFilter.annotation.Login)")
    public void loginPointCut() {

    }

    @Around("loginPointCut()")
    public Object before(ProceedingJoinPoint point){
        Map<String,Object> errorMap = new HashMap<>();
        try {
            Object[] params = point.getArgs();
            Map<String,Object> map = null;
            if(params != null){
                for (int i = 0;i < params.length;i++) {
                    if (params[i] instanceof Map) {
                        map = (Map<String, Object>) params[i];
                        break;
                    }
                }
            }
            String token = (String) map.get("token");
            if (BeanUtil.isEmpty(token)) {
                errorMap.put("code","001");
                errorMap.put("msg","token不能为空！");
                errorMap.put("success",false);
                return errorMap;
            }
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");
            headers.set("Authorization", new StringBuilder("Bearer ").append(token).toString());
            String verifyUrl = LoadConfig.get("VERIFY_LOGIN_URL");
            ResponseEntity<HashMap> response = template.exchange(verifyUrl, HttpMethod.GET, new HttpEntity<>(headers), HashMap.class);
            if(response != null && response.getBody() != null && response.getBody().get("userName") != null && !BeanUtil.isEmpty(response.getBody().get("userName").toString())) {
                //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                //request.getSession().setAttribute("user",response.getBody().get("userName"));
                PlatformContext.setVariable("loginName",response.getBody().get("userName"));
                PlatformContext.setVariable("userName",response.getBody().get("fullName"));
                return point.proceed();
            }else {
                errorMap.put("code","002");
                errorMap.put("msg","登录校验未通过！");
                errorMap.put("success",false);
                return errorMap;
            }
        }catch (Throwable e) {
            e.printStackTrace();
            errorMap.put("code","002");
            errorMap.put("msg","登录校验异常！");
            errorMap.put("success",false);
            return errorMap;
        }



    }
}
