package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.zynsun.platform.edoc.facade.scanTaskManage.AccountingArchivesBorrowFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-06-28 15:40
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/root.xml"})
public class AccountingArchivesBorrowFacadeImplTest {
    @Autowired
    AccountingArchivesBorrowFacade accountingArchivesBorrowFacade;

    @Test
    public void finishExamineWorkTask() throws Exception {
        accountingArchivesBorrowFacade.finishExamineWorkTask(null,null,null);
    }

}