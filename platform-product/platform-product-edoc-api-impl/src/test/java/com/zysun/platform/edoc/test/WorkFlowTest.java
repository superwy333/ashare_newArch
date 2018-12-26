package com.zysun.platform.edoc.test;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.BillAccountModelDTO;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.BillAccountFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
//import com.zynsun.platform.workflow.dto.WfTaskDTO;
//import com.zynsun.platform.workflow.facade.WfProcessFacade;
//import com.zynsun.platform.workflow.facade.WfProcessManageFacade;
//import com.zynsun.platform.workflow.facade.WfTaskFacade;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 2017/12/25
 * @modify
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/root.xml"})
public class WorkFlowTest {

    @Autowired
    private BillAccountFacade billAccountFacade;
//    @Autowired
//    private WfProcessManageFacade wfProcessManageFacade;
//    @Autowired
//    private WfProcessFacade wProcessFacade;
//    @Autowired
//    private WfTaskFacade wfTaskFacade;
    @Autowired
    private EdocHeaderFacade edocHeaderFacade;
    @Test
    public void startProcess() throws IOException {
        byte[] fileData=IOUtils.toByteArray(new ClassPathResource("test4.zip").getInputStream());
//        wfProcessManageFacade.deployProcess("test4",fileData);
    }

    @Test
    public void  createBillAccount(){
        BillAccountModelDTO  billAccountModelDTO=new BillAccountModelDTO();
        billAccountModelDTO.setEdocNo("BXD123456777");
        billAccountModelDTO.setEdocType("0");
        billAccountModelDTO.setBillAmountTotal(new BigDecimal("100000"));
        billAccountModelDTO.setEdocIsMatched("0");
//        billAccountModelDTO.setEdocLevel("0");
        billAccountModelDTO.setRemarks("测试一下");
        billAccountModelDTO.setEdocTaskStatus("0");
        billAccountFacade.addBillAccount(billAccountModelDTO);
//        wProcessFacade.startProcess("myProcess_1","111");
    }

    @Test
    public void start2(){
//        wProcessFacade.startProcess("EdocTest","729");
    }

    @Test
    public void complete(){
        Map<String , Object> varibales = new HashMap<>();
        varibales.put("msg",0);
//        wfTaskFacade.completeTask(null,"235007","task1",varibales);
    }

    @Test
    public void showTask(){
//        System.out.println(result);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void kafkaTest(){
        EdocHeaderDTO edocHeaderDTO = new EdocHeaderDTO();
        edocHeaderDTO.setEdocNo("111111");
        edocHeaderDTO.setEdocType("01");
        edocHeaderDTO.setEdocTaskStatus("0");
        edocHeaderDTO.setEdocIsMatched("0");
//        edocHeaderDTO.setEdocLevel("0");
        edocHeaderDTO.setStartProcessFlag(true);
        edocHeaderFacade.edocClientSaveEdocHeader(edocHeaderDTO);
    }
}
