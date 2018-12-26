package com.zynsun.platform.edoc.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeesuite.kafka.handler.MessageHandler;
import com.jeesuite.kafka.message.DefaultMessage;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.service.InvoiceService;
//import com.zynsun.platform.quartz.facade.KafkaMessageFacade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 16:07 2018/1/24
 * @Modified By:
 */
public class ExpressInvMessageHandler implements MessageHandler {


    @Autowired
    private InvoiceService invoiceService;
//    @Autowired
//    private KafkaMessageFacade kafkaMessageFacade;

    @Override
    public void p1Process(DefaultMessage defaultMessage) {

    }

    @Override
    public void p2Process(DefaultMessage defaultMessage) {
        //将消息体转为JSONObject对象
        JSONObject object = JSON.parseObject(String.valueOf(defaultMessage.getBody()));
        //根据billId查询发票数据
        Invoice invoice = invoiceService.queryById(Long.parseLong(String.valueOf(object.get("billId"))));
        if(!BeanUtil.isEmpty(invoice)){
            //修改发票实物状态
            invoice.setPhysicalStatus(String.valueOf(object.get("physicalStatus")));
            //修改发票数据
            invoiceService.updateByIdSelective(invoice);
        }
//        kafkaMessageFacade.delSuccessKafkaMessage(defaultMessage.getMsgId());
    }

    @Override
    public boolean onProcessError(DefaultMessage defaultMessage) {
        return false;
    }
}
