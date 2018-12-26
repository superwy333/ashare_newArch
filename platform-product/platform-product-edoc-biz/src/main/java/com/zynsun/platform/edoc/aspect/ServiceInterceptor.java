package com.zynsun.platform.edoc.aspect;

import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.model.BaseEdocModel;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import com.zynsun.platform.edoc.service.EdocImageService;
import com.zynsun.platform.edoc.service.InvoiceItemService;
import com.zynsun.platform.edoc.service.InvoiceService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 17:01 2017/12/26
 * @Modified By:
 */
//@Aspect
//@Component
public class ServiceInterceptor {

    @Autowired
    private EdocHeaderService edocHeaderService;
    @Autowired
    private EdocImageService edocImageService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceItemService invoiceItemService;

    private static String[] types = {"java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float"};


    //@Pointcut("execution(* com.zynsun.platform.edoc.service.*.save*(..)) || execution(* com.zynsun.platform.edoc.service.*.add*(..))")
    public void serviceMethodPointcut() {
    }

    //@Pointcut("execution(* com.zynsun.platform.edoc.service.*.del*(..))")
    public void delMethodPointcut() {
    }


    //@Around("serviceMethodPointcut()")
    public Object ServiceInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (args.length == 1 && args[0] != null && args[0].getClass().getGenericSuperclass().equals(BaseEdocModel.class)) {
            edocHeaderService.add(DozerUtil.map(args[0], EdocHeader.class));
        }
        return pjp.proceed();
    }

    //@Around("delMethodPointcut()")
    public Object delInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        if (args.length == 1 && args[0] != null && args[0].getClass().getGenericSuperclass().equals(BaseEdocModel.class)) {
            EdocHeader edocHeader = DozerUtil.map(args[0], EdocHeader.class);
            edocHeader.setId(null);
            if (StringUtils.isNotEmpty(edocHeader.getEdocNo()) && StringUtils.isNotEmpty(edocHeader.getEdocType())) {
                List<EdocHeader> edocHeaderList = edocHeaderService.query(edocHeader);
                if (!BeanUtil.isEmpty(edocHeader) && edocHeaderList.size() == 1) {
                    edocHeaderService.remove(edocHeaderList.get(0));
                    edocImageService.delImagesByEdocHeaderId(edocHeaderList.get(0).getId());
                    invoiceService.deleteInvoiceByEdocHeaderId(edocHeaderList.get(0).getId());
                    invoiceItemService.deleteInvoiceItemByEdocHeaderId(edocHeaderList.get(0).getId());
                }
            } else {
                throw new RuntimeException("影像任务单证编号和单证类型不能为空");
            }
        }
        return pjp.proceed();
    }
}
