package com.zynsun.platform.edoc.message;

import com.alibaba.fastjson.JSON;
import com.jeesuite.kafka.handler.MessageHandler;
import com.jeesuite.kafka.message.DefaultMessage;
import com.zynsun.platform.edoc.domain.EdocHeader;
//import com.zynsun.platform.quartz.facade.KafkaMessageFacade;
//import com.zynsun.platform.workflow.facade.WfProcessFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhouhong 处理影像任务启动任务监听器
 * @create 2018/1/17
 * @modify
 * 两阶段处理的同一份数据、一般情况第二阶段处理即可。
 * 如果要触发重试机制请不要try catch异常
 * onProcessError 返回true表示业务自身处理错误，否则框架会每30秒重新执行一次，最多重试3次(貌似配置生产者的retries不管用，都是3次)
 */
public class EdocStartProMessageHandler implements MessageHandler {
    private static final Logger EDOC_WORKFLOW_LISTENER_LOGGER = LoggerFactory.getLogger(EdocStartProMessageHandler.class);
//    @Autowired
//    private WfProcessFacade wProcessFacade;
//    @Autowired
//    private KafkaMessageFacade kafkaMessageFacade;

    @Override
    public void p1Process(DefaultMessage defaultMessage) {
        //第一阶段处理是同步处理，即在fetch线程处理
    }

    @Override
    public void p2Process(DefaultMessage defaultMessage) {
        EDOC_WORKFLOW_LISTENER_LOGGER.info("消费端监听开始消费消息");
        //第二阶段处理是异步处理，在处理线程池排队处理
        EdocHeader edocHeader = JSON.parseObject(String.valueOf(defaultMessage.getBody()),EdocHeader.class);
        //启动流程
//        wProcessFacade.startProcess("createAccount",String.valueOf(edocHeader.getId()));
        EDOC_WORKFLOW_LISTENER_LOGGER.info("billheaderId:{},启动流程成功",edocHeader.getId());

//        kafkaMessageFacade.delSuccessKafkaMessage(defaultMessage.getMsgId());
    }

    @Override
    public boolean onProcessError(DefaultMessage defaultMessage) {
        System.out.println("ignore error message : "+defaultMessage);
        return false;
    }
}
