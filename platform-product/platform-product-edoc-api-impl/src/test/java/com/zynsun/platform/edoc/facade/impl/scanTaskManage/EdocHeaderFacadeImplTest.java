package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-26 17:20
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/root.xml"})
public class EdocHeaderFacadeImplTest {

    @Autowired
    private EdocHeaderFacade edocHeaderFacade;

    @Test
    public void parseEdocReport() throws Exception {
        Map<String,Object> queryTime = new HashMap<>();
        queryTime.put("startTime","");
        queryTime.put("endTime","");
        edocHeaderFacade.parseEdocReport(queryTime);
    }


}
