package com.zynsun.openplatform.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.zynsun.openplatform.context.PlatformContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

import java.util.Map;

/**
 * 平台上下文信息过滤器
 * @author david
 * @create 2017/5/17 21:40
 */
public class PlatformContextFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformContextFilter.class);
    /**
     * 序列化
     */
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    /**
     * 反序列化
     */
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    /**
     * 平台上下文
     */
    private static final String PLATFORM_CONTEXT_KEY = "_PLATFORM_CONTEXT_";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            RpcContext context = RpcContext.getContext();
            if(context.isConsumerSide()) { // 消费端
                // 获取上下文变量
                Map<String, Object> variables = PlatformContext.getVariables();
                LOGGER.debug("[用户跟踪service] {}", variables);
                // 转换成字符串
                String varString = serialize(variables);
                // 绑定到Dubbo Attachment中
                context.setAttachment(PLATFORM_CONTEXT_KEY, varString);
            }
            if(context.isProviderSide()){ // 提供端
                // 获取Dubbo Attachment中的变量
                String varString = context.getAttachment(PLATFORM_CONTEXT_KEY);
                // 转换成上下文变量
                Map variables = (Map) deserialize(varString);
                LOGGER.debug("[用户跟踪service] {}", variables);
                // 存放到上下文变量中
                if(variables != null) {
                    for (Object key : variables.keySet()) {
                        PlatformContext.setVariable(key.toString(), variables.get(key));
                    }
                }
            }
        } catch (Exception e) {
            throw new RpcException(e);
        }
        return invoker.invoke(invocation);
    }

    private Object deserialize(String string) {
        if (string == null) {
            return null;
        }
        try {
            return deserializer.convert(string.getBytes("ISO-8859-1"));
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot deserialize", ex);
        }
    }

    private String serialize(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return new String(serializer.convert(object), "ISO-8859-1");
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot serialize", ex);
        }
    }
}
