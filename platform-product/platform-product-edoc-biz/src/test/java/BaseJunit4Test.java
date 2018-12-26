import com.zynsun.platform.edoc.model.BillAccountModel;
import com.zynsun.platform.edoc.service.BillAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 10:48 2017/12/27
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class BaseJunit4Test {


    @Autowired
    private BillAccountService billAccountService;

    @Test
    public void addBillTest(){
        BillAccountModel billAccountModel = new BillAccountModel();
        billAccountModel.setEdocNo("BXD123456");
        billAccountModel.setEdocType("0");
        billAccountModel.setBillAmountTotal(new BigDecimal("100000"));
        billAccountModel.setEdocIsMatched("0");
        billAccountModel.setEdocLevel("0");
        billAccountModel.setRemarks("测试一下");
        billAccountModel.setEdocTaskStatus("0");
        billAccountService.addBillAccount(billAccountModel);
    }

    @Test
    public void delBillTest(){
        BillAccountModel billAccountModel = new BillAccountModel();
        billAccountModel.setId(1L);
        billAccountModel.setEdocNo("BXD123456");
        billAccountModel.setEdocType("0");
        billAccountService.delBilLAccount(billAccountModel);
    }

}
