import com.google.gson.Gson;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.platform.edoc.domain.EdocCredentials;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.model.EdocHeaderModel;
import com.zynsun.platform.edoc.model.EdocImageModel;
import com.zynsun.platform.edoc.model.InvoiceItemModel;
import com.zynsun.platform.edoc.model.InvoiceModel;
import com.zynsun.platform.edoc.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jary on 2018/11/21/021.
 */
public class InterfaceTest extends BaseTest {
    @Autowired
    EdocHeaderService edocHeaderService;
    @Autowired
    EdocImageService edocImageService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    InvoiceItemService invoiceItemService;

    /**
     * 0603 调阅具体的影像任务数据和图片接口
     */
    @Test
    public void getTaskDetailsInter(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code","200");
        hashMap.put("msg","调用成功");

        EdocHeaderModel edocHeaderModel = new EdocHeaderModel();
        edocHeaderModel.setId(1L);
        List<EdocHeaderModel> edocHeaderModelList = edocHeaderService.getBillImagesByBillHeaderId(edocHeaderModel);
        for (EdocHeaderModel headerModel : edocHeaderModelList){
           List<EdocImageModel> edocImageList = headerModel.getEdocImageList();
           for (EdocImageModel edocImageModel :edocImageList){
               Invoice invoice = invoiceService.queryInvoiceByImgId(edocImageModel.getId());
               if (BeanUtil.isEmpty(invoice)) continue;
               InvoiceModel invoiceModel = DozerUtil.map(invoice, InvoiceModel.class);
               if (!BeanUtil.isEmpty(invoiceModel)){
                   edocImageModel.setInvoiceModel(invoiceModel);
                   InvoiceItem invoiceItem = new InvoiceItem();
                   List<InvoiceItem> query = invoiceItemService.query(invoiceItem);
                invoiceModel.setInvoiceItemList(query);
               }
           }
       }
        hashMap.put("result",edocHeaderModelList);

        System.out.println(new Gson().toJson(hashMap));
    }

    @Test
    public void getTaskListInter(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code","200");
        hashMap.put("msg","调用成功");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("A1001,B1002'");
        hashMap.put("result",stringBuilder);
        System.out.println(new Gson().toJson(hashMap));
    }

    /**
     * 606 调阅具体某张发票的数据和图片接口
     */
    @Test
    public void getInvoiceDetailsInter(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code","200");
        hashMap.put("msg","调用成功");
        InvoiceModel invoiceModel=null;
        Invoice invoice = invoiceService.queryByInvId(3L);
        if (!BeanUtil.isEmpty(invoice)){
             invoiceModel = DozerUtil.map(invoice, InvoiceModel.class);
            EdocImage edocImage = edocImageService.queryById(invoice.getEdocImageId());
            invoiceModel.setImageUrl(edocImage.getImageUrl());
            if (!BeanUtil.isEmpty(invoiceModel)){
                InvoiceItem invoiceItem = new InvoiceItem();
                List<InvoiceItem> query = invoiceItemService.query(invoiceItem);
                invoiceModel.setInvoiceItemList(query);
            }
        }


        hashMap.put("result",invoiceModel);

        System.out.println(new Gson().toJson(hashMap));
    }

    /**
     * 607 提供按照发票日期范围、扫描日期范围、销方税号进行发票数据的获取
     */
    @Test
    public void getInvoiceListInter(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code","200");
        hashMap.put("msg","调用成功");
        List<InvoiceModel> invoiceModelList = new ArrayList<>();
        InvoiceModel invoiceModel=null;
        Invoice invoice = invoiceService.queryByInvId(3L);
        if (!BeanUtil.isEmpty(invoice)){
            invoiceModel = DozerUtil.map(invoice, InvoiceModel.class);
            invoiceModelList.add(invoiceModel);
            invoiceModelList.add(invoiceModel);
        }
        hashMap.put("result",invoiceModelList);

        System.out.println(new Gson().toJson(hashMap));
    }

    /**
     * 凭证回写接口入参
     */
    @Test
    public void updateVoucherDetailsInter(){
        Map<String, Object> hashMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        hashMap.put("credentialsNum","NN2018000001_7048");
        hashMap.put("accountingLedger","123456");
        hashMap.put("type","1");
        hashMap.put("createDate","2018-08-01");
        hashMap.put("status","1");
        hashMap.put("edocNo","1111111111");
        hashMap.put("erpId","1111111111");
        mapList.add(hashMap);
        mapList.add(hashMap);
        System.out.println(new Gson().toJson(mapList));
    }
    @Autowired
    EdocCredentialsService edocCredentialsService;
    /**
     * 凭证回写接口出参
     */
    @Test
    public void updateVoucherDetailsInter1(){
        Map<String, Object> hashMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        hashMap.put("code","200");
        hashMap.put("msg","调用成功");

        Map<String, Object> hashMap1 = new HashMap<>();
        hashMap1.put("credentialsNum","NN2018000001_7048");
        hashMap1.put("edocNo","1111111111");
        hashMap1.put("erpId","1111111111");
        hashMap1.put("code","Y");
        mapList.add(hashMap1);
        mapList.add(hashMap1);
        List<EdocCredentials> edocCredentials = edocCredentialsService.queryByEdocId(3L);
        hashMap.put("result",mapList);
        System.out.println(new Gson().toJson(hashMap));
    }

}

