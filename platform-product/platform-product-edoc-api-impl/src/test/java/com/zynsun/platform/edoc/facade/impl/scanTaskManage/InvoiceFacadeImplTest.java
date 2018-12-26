package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.InvoiceFacade;
import com.zynsun.platform.edoc.service.InvoiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-06-29 14:11
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/root.xml"})
public class InvoiceFacadeImplTest {

    @Autowired
    InvoiceFacade invoiceFacade;
    @Autowired
    InvoiceService invoiceService;


    @Test
    public void extractInv() throws Exception {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setVersion(4L);
        invoiceDTO.setId(589L);
        invoiceDTO.setInvCode("3300181130");
        invoiceDTO.setInvNo("02211063");
        invoiceFacade.extractInv(invoiceDTO);

    }

    @Test
    public void isNoAndCodeInVaildRenRenLeTest() {
        /*Invoice invoice = new Invoice();
        invoice.setInvType("1");
        invoice.setInvCode("1");
        invoice.setInvNo("1");
        invoice.setInvAmount("1");
        invoice.setInvDate("1");
        invoice.setCheckCode("1");*/
        Invoice invoice = invoiceService.queryById(1L);
        invoiceService.invoiceCheckRule(invoice);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + invoice.getCheckStatus());
    }

    @Test
    public  void testcharAt() {
        String invCode = "1100182130";
        System.out.println(invCode.charAt(0));
        System.out.println("1".equals(String.valueOf(invCode.charAt(0))));

    }

}