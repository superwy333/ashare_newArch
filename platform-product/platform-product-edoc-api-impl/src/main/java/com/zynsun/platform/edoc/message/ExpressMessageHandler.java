package com.zynsun.platform.edoc.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeesuite.kafka.handler.MessageHandler;
import com.jeesuite.kafka.message.DefaultMessage;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.service.EdocHeaderService;
//import com.zynsun.platform.quartz.facade.KafkaMessageFacade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:15 2018/1/24
 * @Modified By:
 */
public class ExpressMessageHandler implements MessageHandler{

    @Autowired
    private EdocHeaderService edocHeaderService;
//    @Autowired
//    private KafkaMessageFacade kafkaMessageFacade;


    @Override
    public void p1Process(DefaultMessage defaultMessage) {

    }

    @Override
    public void p2Process(DefaultMessage defaultMessage) {
        //将消息体转为JSONObject对象
        JSONObject object = JSON.parseObject(String.valueOf(defaultMessage.getBody()));
        //根据billId查询影像任务
        EdocHeader edocHeader = edocHeaderService.queryById(Long.parseLong(String.valueOf(object.get("billId"))));
        if(!BeanUtil.isEmpty(edocHeader)){
            //修改影像任务实物状态
            edocHeader.setEdocPhysicalStatus(String.valueOf(object.get("physicalStatus")));
            //修改影像任务
            edocHeaderService.updateByIdSelective(edocHeader);
        }
//        kafkaMessageFacade.delSuccessKafkaMessage(defaultMessage.getMsgId());
    }

    @Override
    public boolean onProcessError(DefaultMessage defaultMessage) {
        return false;
    }
}
