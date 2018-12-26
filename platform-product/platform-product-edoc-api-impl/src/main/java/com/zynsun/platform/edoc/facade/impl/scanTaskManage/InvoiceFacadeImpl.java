package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zynsun.openplatform.context.PlatformContext;
import com.zynsun.openplatform.dubboRestLoginFilter.annotation.Login;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.shiro.LoginUser;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.*;
import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.dto.scanTask.InvDiffColumnDTO;
import com.zynsun.platform.edoc.dto.scanTask.InvoiceItemDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.InvoiceFacade;
import com.zynsun.platform.edoc.model.InvDiffColumnModel;
import com.zynsun.platform.edoc.model.InvoiceItemModel;
import com.zynsun.platform.edoc.model.InvoiceModel;
import com.zynsun.platform.edoc.service.*;
import com.zynsun.platform.utils.EdocClientUtil;
//import com.zynsun.platform.utils.EdocEnum;
import com.zynsun.platform.utils.EnumUtil;
import constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacpp.annotation.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import scala.collection.immutable.Stream;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 9:22 2018/1/24
 * @Modified By:
 */
@Path("invoiceFacade")
@Service("invoiceFacade")
public class InvoiceFacadeImpl implements InvoiceFacade {

    private static final Logger INVOICE_LOGGER = LoggerFactory.getLogger(InvoiceFacadeImpl.class);

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private EdocHeaderService edocHeaderService;

    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private EdocInvDiffService edocInvDiffService;

    @Autowired
    private EdocImageService edocImageService;

    /*@Autowired
    private MdLegalCompanyFacade mdLegalCompanyFacade;*/
    /*@Autowired
    private SysUserFacade sysUserFacade;*/
    /*@Autowired
    private DataDictCliFacade dataDictCliFacade;*/
    @Autowired
    private EdocAttachService edocAttachService;
    @Autowired
    private EdocHeaderFacade edocHeaderFacade;


    @Login
    @POST
    @Path("invoiceCombobox")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> invoiceCombobox(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        try {

            Integer processType = (Integer)map.get("processType");
            if (BeanUtil.isEmpty(processType)) {
                executeResult.put("code","001");
                executeResult.put("msg","processType为空！");
                executeResult.put("success",false);
                return executeResult;
            }
            switch (processType) {
                case 1: result = EnumUtil.IntEnumToMap(InvoiceDTO.InvCheckRealEnum.class); break;
                case 2: result = EnumUtil.IntEnumToMap(InvoiceDTO.InvCheckDetailsEnum.class); break;
                case 3: result = EnumUtil.IntEnumToMap(InvoiceDTO.InvTypeEnum.class); break;
                case 4: result = EnumUtil.IntEnumToMap(EdocHeaderDTO.EdocTypeEnum.class); break;
                case 5: result = EnumUtil.IntEnumToMap(InvoiceDTO.RedOrBlueEnum.class); break;
            }
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            INVOICE_LOGGER.error("获取下拉框数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","获取下拉框数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    /*@POST
    @Path("checkRealCombobox")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> checkRealCombobox(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            Map<String,Object> result = new HashMap<>();
            List<Map<String,Object>> combobox = new ArrayList<>();
            for (InvoiceDTO.InvCheckRealEnum item : InvoiceDTO.InvCheckRealEnum.values()) {
                Map<String,Object> combboboxItem = new HashMap<>();
                combboboxItem.put("id",item.getCode());
                combboboxItem.put("value",item.getName());
                combobox.add(combboboxItem);
            }
            result.put("combobox",combobox);
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            INVOICE_LOGGER.error("获取验真状态下拉框数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","获取验真状态下拉框数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }*/

    private String convertor(Object o) {
        if (o instanceof String) return (String)o;
        if (o instanceof Long) return String.valueOf(o);
        if (o instanceof Integer) return String.valueOf(o);
        return null;
    }

    @Login
    @POST
    @Path("updateInv")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> updateInv(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        Long invoiceId = null;
        try {
            String invoiceIdStr = convertor(map.get("id"));
            // String invoiceIdStr = (String)map.get("id");
            String edocImageIdStr = convertor(map.get("edocImageId"));
            String invCode = convertor(map.get("invCode"));
            String invNo = convertor(map.get("invNo"));
            String invDate = convertor(map.get("invDate"));
            String invAmount = convertor(map.get("invAmount"));
            String invType = convertor(map.get("invType"));
            String redOrBule = convertor(map.get("redOrBule"));
            String invTax = convertor(map.get("invTax"));
            String invTotal = convertor(map.get("invTotal"));
            String sellerName = convertor(map.get("sellerName"));
            String sellerTaxCode = convertor(map.get("sellerTaxCode"));
            String buyerName = convertor(map.get("buyerName"));
            String buyerTaxCode = convertor(map.get("buyerTaxCode"));
            String machineCode = convertor(map.get("machineCode"));
            String cipher = convertor(map.get("cipher"));
            String checkCode = convertor(map.get("checkCode"));
            String edocImageType = convertor(map.get("edocImageType"));
            String checkDetails = convertor(map.get("checkDetails"));
            String checkStatus = convertor(map.get("checkStatus"));
            if (BeanUtil.isEmpty(edocImageIdStr)) {
                executeResult.put("code","001");
                executeResult.put("msg","影像ID不能为空！");
                executeResult.put("success",false);
                return executeResult;
            }
            if (BeanUtil.isEmpty(edocImageType)) {
                executeResult.put("code","001");
                executeResult.put("msg","文件类型不能为空！");
                executeResult.put("success",false);
                return executeResult;
            }
            EdocImage edocImage = edocImageService.queryById(Long.valueOf(edocImageIdStr));
            if (!edocImage.getEdocImageType().equals(edocImageType)) { // 如果提交的文件类型不一样，则更新
                edocImage.setEdocImageType(edocImageType);
                edocImageService.updateByIdSelective(edocImage);
            }

            if (!BeanUtil.isEmpty(edocImageType)
                    &&(Constant.BillTypeCode.VATS_INVOICE.equals(edocImageType)
                    || Constant.BillTypeCode.VATN_INVOICE.equals(edocImageType)
                    ||Constant.BillTypeCode.EVAT_N_E_INV.equals(edocImageType))) { // 如果文件类型是发票，则保存或修改发票数据
                Invoice invoice = new Invoice();
                invoice.setId(BeanUtil.isEmpty(invoiceIdStr)?null:Long.valueOf(invoiceIdStr));
                invoice.setEdocImageId(BeanUtil.isEmpty(edocImageIdStr)?null:Long.valueOf(edocImageIdStr));
                invoice.setInvType(BeanUtil.isEmpty(invType)?null:invType);
                invoice.setInvCode(BeanUtil.isEmpty(invCode)?null:invCode);
                invoice.setInvNo(BeanUtil.isEmpty(invNo)?null:invNo);
                invoice.setInvDate(BeanUtil.isEmpty(invDate)?null:invDate);
                invoice.setInvAmount(BeanUtil.isEmpty(invAmount)?null:invAmount);
                invoice.setInvTax(BeanUtil.isEmpty(invTax)?null:invTax);
                invoice.setInvTotal(BeanUtil.isEmpty(invTotal)?null:invTotal);
                invoice.setRedOrBule(BeanUtil.isEmpty(redOrBule)?null:redOrBule);
                invoice.setBuyerName(BeanUtil.isEmpty(buyerName)?null:buyerName);
                invoice.setBuyerTaxCode(BeanUtil.isEmpty(buyerTaxCode)?null:buyerTaxCode);
                invoice.setSalerName(BeanUtil.isEmpty(sellerName)?null:sellerName);
                invoice.setSalerTaxCode(BeanUtil.isEmpty(sellerTaxCode)?null:sellerTaxCode);
                invoice.setInvMachineNo(BeanUtil.isEmpty(machineCode)?null:machineCode);
                invoice.setCiphertextArea(BeanUtil.isEmpty(cipher)?null:cipher);
                invoice.setCheckCode(BeanUtil.isEmpty(checkCode)?null:checkCode);
                if (!BeanUtil.isEmpty(checkStatus)) {
                    invoice.setCheckStatus(checkStatus);
                }
                if (BeanUtil.isEmpty(invoice.getId())) { // 新增场景
                    Invoice queryConditon = new Invoice();
                    queryConditon.setEdocImageId(invoice.getEdocImageId());
                    List<Invoice> invoiceList = invoiceService.query(queryConditon);
                    for (Invoice i:invoiceList) {
                        invoiceService.remove(i);
                    }
                    invoice.setInvSource(Constant.rrInvSource.MANUALL);
                    if (BeanUtil.isEmpty(invoice.getCheckReal())) {
                        invoice.setCheckReal("1");
                    }
                    invoiceService.add(invoice);
                    invoiceId = invoice.getId();
                }else { // 修改场景
                    Invoice invoiceInDB = invoiceService.queryById(Long.valueOf(invoiceIdStr));
                    invoiceInDB.setInvType(BeanUtil.isEmpty(invType)?null:invType);
                    invoiceInDB.setInvCode(BeanUtil.isEmpty(invCode)?null:invCode);
                    invoiceInDB.setInvNo(BeanUtil.isEmpty(invNo)?null:invNo);
                    invoiceInDB.setInvDate(BeanUtil.isEmpty(invDate)?null:invDate);
                    invoiceInDB.setInvAmount(BeanUtil.isEmpty(invAmount)?null:invAmount);
                    invoiceInDB.setInvTax(BeanUtil.isEmpty(invTax)?null:invTax);
                    invoiceInDB.setInvTotal(BeanUtil.isEmpty(invTotal)?null:invTotal);
                    invoiceInDB.setRedOrBule(BeanUtil.isEmpty(redOrBule)?null:redOrBule);
                    invoiceInDB.setBuyerName(BeanUtil.isEmpty(buyerName)?null:buyerName);
                    invoiceInDB.setBuyerTaxCode(BeanUtil.isEmpty(buyerTaxCode)?null:buyerTaxCode);
                    invoiceInDB.setSalerName(BeanUtil.isEmpty(sellerName)?null:sellerName);
                    invoiceInDB.setSalerTaxCode(BeanUtil.isEmpty(sellerTaxCode)?null:sellerTaxCode);
                    invoiceInDB.setInvMachineNo(BeanUtil.isEmpty(machineCode)?null:machineCode);
                    invoiceInDB.setCiphertextArea(BeanUtil.isEmpty(cipher)?null:cipher);
                    invoiceInDB.setCheckCode(BeanUtil.isEmpty(checkCode)?null:checkCode);
                    if (!BeanUtil.isEmpty(checkStatus)) {
                        invoiceInDB.setCheckStatus(checkStatus);
                    }
                    invoiceService.updateByIdSelective(invoiceInDB);
                    invoiceId = invoiceInDB.getId();
                }
            }else { // 文件类型不是发票或为空，则清空这个影像下的发票数据
                Invoice invoice = new Invoice();
                invoice.setEdocImageId(edocImage.getId());
                List<Invoice> invoiceList = invoiceService.query(invoice);
                if (!BeanUtil.isEmpty(invoiceList)) {
                    for (Invoice i:invoiceList) {
                        invoiceService.remove(i);
                        InvoiceItem invoiceItem = new InvoiceItem();
                        invoiceItem.setInvId(i.getId());
                        List<InvoiceItem> invoiceItemList = invoiceItemService.query(invoiceItem);
                        for (InvoiceItem ii:invoiceItemList) {
                            invoiceItemService.remove(ii);
                        }
                    }
                }
            }
            // 最后对任务做一次异常检查
            /*if ("11".equals(checkDetails) || "13".equals(checkDetails)) {
                edocHeaderFacade.edocAbnormalCheck(edocImage.getEdocHeaderId());
            }*/
            edocHeaderFacade.edocAbnormalCheck(edocImage.getEdocHeaderId());
            if (!BeanUtil.isEmpty(invoiceId)) {
                Invoice invoiceInDB = invoiceService.queryById(invoiceId);
                InvoiceModel invoiceModel = DozerUtil.map(invoiceInDB,InvoiceModel.class);
                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setInvId(invoiceId);
                List<InvoiceItem> invoiceItemList = invoiceItemService.query(invoiceItem);
                invoiceModel.setInvoiceItemList(invoiceItemList);
                parseInvoiceModelForInvUpdate(invoiceModel); // 规整数据类型
                EdocImage edocImageInDB = edocImageService.queryById(invoiceModel.getEdocImageId());
                invoiceModel.setDocId(edocImageInDB.getEdocImageType());
                executeResult.put("result",invoiceModel);
                executeResult.put("docId",edocImageInDB.getEdocImageType());
            }else {
                executeResult.put("result",null);
                executeResult.put("docId",edocImageType);
            }

            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            INVOICE_LOGGER.error("修改发票数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","修改发票数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    private void parseInvoiceModelForInvUpdate(InvoiceModel invoiceModel) {
        /*if (!BeanUtil.isEmpty(invoiceModel.getInvType())) {
            invoiceModel.setInvType(InvoiceDTO.InvTypeEnum.parse(invoiceModel.getInvType()).getName());
        }*/
        if (!BeanUtil.isEmpty(invoiceModel.getCheckReal())) {
            invoiceModel.setCheckReal(InvoiceDTO.InvCheckRealEnum.parse(invoiceModel.getCheckReal()).getName());
        }
        if (!BeanUtil.isEmpty(invoiceModel.getCheckDetails())) {
            invoiceModel.setCheckDetails(InvoiceDTO.InvCheckDetailsEnum.parse(invoiceModel.getCheckDetails()).getName());
        }
        if (!BeanUtil.isEmpty(invoiceModel.getEdocType())) {
            invoiceModel.setEdocType(EdocHeaderDTO.EdocTypeEnum.parse(invoiceModel.getEdocType()).getName());
        }
        if (!BeanUtil.isEmpty(invoiceModel.getRedOrBule())) {
            invoiceModel.setRedOrBule(InvoiceDTO.RedOrBlueEnum.parse(invoiceModel.getRedOrBule()).getName());
        }
        if (!BeanUtil.isEmpty(invoiceModel.getInvSource())) {
            invoiceModel.setInvSource(InvoiceDTO.InvSourceEnum.parse(invoiceModel.getInvSource()).getName());
        }
        if (!BeanUtil.isEmpty(invoiceModel.getCreateTime())) {
            invoiceModel.setCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(invoiceModel.getCreateTime()));
        }
        if (!BeanUtil.isEmpty(invoiceModel.getCheckStatus())) {
            invoiceModel.setCheckStatus(InvoiceDTO.InvoiceCheckStatusEnum.parse(invoiceModel.getCheckStatus()).getName());
        }
    }

    @Login
    @POST
    @Path("invoiceImage")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> invoiceImage(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            String invoiceIdStr = (String)map.get("invoiceId");
            Invoice invoice = invoiceService.queryById(Long.valueOf(invoiceIdStr));
            EdocImage edocImage = edocImageService.queryById(invoice.getEdocImageId());
            if (!BeanUtil.isEmpty(edocImage)) {
                String imageUrl = edocImage.getImageUrl();
                String imageUrlPrefix = LoadConfig.get("EDOC_IMAGE_NGINX_URL");
                executeResult.put("result",imageUrlPrefix + imageUrl);
                executeResult.put("code","000");
                executeResult.put("msg","资料处理成功！");
                executeResult.put("success",true);
            }else {
                executeResult.put("code","001");
                executeResult.put("msg","查询发票影像数据失败！");
                executeResult.put("success",false);
                return executeResult;
            }
        }catch (Exception e) {
            INVOICE_LOGGER.error("查询发票影像数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","查询发票影像数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @Login
    @POST
    @Path("invoceDataGrid")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> invoceDataGrid(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            Integer inputPage = (Integer) map.get("page");
            Integer rows = (Integer) map.get("limit");
            if (BeanUtil.isEmpty(inputPage)||BeanUtil.isEmpty(rows)) {
                executeResult.put("code","001");
                executeResult.put("msg","分页参数不能为空！");
                executeResult.put("success",false);
                return executeResult;
            }
            InvoiceModel invoiceModel = new InvoiceModel();
            invoiceModel.setInvCode(BeanUtil.isEmpty(map.get("invCode"))?null:(String) map.get("invCode"));
            invoiceModel.setInvNo(BeanUtil.isEmpty(map.get("invNo"))?null:(String) map.get("invNo"));
            invoiceModel.setInvType(BeanUtil.isEmpty(map.get("invType"))?null:(String) map.get("invType"));
            invoiceModel.setCheckReal(BeanUtil.isEmpty(map.get("checkReal"))?null:(String) map.get("checkReal"));
            invoiceModel.setCheckDetails(BeanUtil.isEmpty(map.get("checkDetails"))?null:(String) map.get("checkDetails"));

            invoiceModel.setBuyerName(BeanUtil.isEmpty(map.get("invBuyerName"))?null:(String) map.get("invBuyerName"));
            invoiceModel.setBuyerTaxCode(BeanUtil.isEmpty(map.get("invBuyerTaxCode"))?null:(String) map.get("invBuyerTaxCode"));
            invoiceModel.setSalerName(BeanUtil.isEmpty(map.get("invSalerName"))?null:(String) map.get("invSalerName"));
            invoiceModel.setSalerTaxCode(BeanUtil.isEmpty(map.get("invSalerTaxCode"))?null:(String) map.get("invSalerTaxCode"));
            invoiceModel.setInvSource(BeanUtil.isEmpty(map.get("invSource"))?null:(String) map.get("invSource"));

            invoiceModel.setInvAmount(BeanUtil.isEmpty(map.get("invAmount"))?null:(String) map.get("invAmount"));
            /*invoiceModel.setInvAmount(BeanUtil.isEmpty(map.get("canCheckReal"))?null:(String) map.get("canCheckReal"));*/
            invoiceModel.setEdocNo(BeanUtil.isEmpty(map.get("edocNo"))?null:(String) map.get("edocNo"));
            invoiceModel.setEdocType(BeanUtil.isEmpty(map.get("edocType"))?null:(String) map.get("edocType"));
            invoiceModel.setEdocHeaderId(BeanUtil.isEmpty(map.get("taskId"))?null:Long.valueOf((String) map.get("taskId")));

            List<String> invDateList = (List<String>)map.get("invDate");
            if (!BeanUtil.isEmpty(invDateList)) {
                invoiceModel.setInvDateStart(BeanUtil.isEmpty(invDateList.get(0))?null:new SimpleDateFormat("yyyy-MM-dd").parse(invDateList.get(0)));
                invoiceModel.setInvDateEnd(BeanUtil.isEmpty(invDateList.get(1))?null:new SimpleDateFormat("yyyy-MM-dd").parse(invDateList.get(1)));
            }

            List<String> createTimeList = (List<String>)map.get("createTime");
            if (!BeanUtil.isEmpty(createTimeList)) {
                invoiceModel.setCreateTimeStart(BeanUtil.isEmpty(createTimeList.get(0))?null:new SimpleDateFormat("yyyy-MM-dd").parse(createTimeList.get(0)));
                invoiceModel.setCreateTimeEnd(BeanUtil.isEmpty(createTimeList.get(1))?null:new SimpleDateFormat("yyyy-MM-dd").parse(createTimeList.get(1)));
            }

            /*invoiceModel.setInvDateStart(BeanUtil.isEmpty(map.get("invoiceDateStart"))?null:new SimpleDateFormat("yyyy-MM-dd").parse((String)map.get("invoiceDateStart")));
            invoiceModel.setInvDateEnd(BeanUtil.isEmpty(map.get("invoiceDateEnd"))?null:new SimpleDateFormat("yyyy-MM-dd").parse((String)map.get("invoiceDateEnd")));
            invoiceModel.setCreateTimeStart(BeanUtil.isEmpty(map.get("createDateStart"))?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)map.get("createDateStart")));
            invoiceModel.setCreateTimeStart(BeanUtil.isEmpty(map.get("createDateEnd"))?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)map.get("createDateEnd")));*/
            invoiceModel.setPage(inputPage);
            invoiceModel.setPageSize(rows);
            invoiceModel.setIsDataGrid("1"); // 发票列表页只查询规则校验通过和不通过的，不查询冲突的
            Page<InvoiceModel> page = invoiceService.queryInvoicesDataGrid(invoiceModel);

            if (!BeanUtil.isEmpty(page.getResult())) {
                List<InvoiceModel> invoiceModelList = page.getResult();
                for (InvoiceModel i:invoiceModelList) {
                    if (!BeanUtil.isEmpty(i.getInvType())) {
                        i.setInvType(InvoiceDTO.InvTypeEnum.parse(i.getInvType()).getName());
                    }
                    if (!BeanUtil.isEmpty(i.getCheckReal())) {
                        i.setCheckReal(InvoiceDTO.InvCheckRealEnum.parse(i.getCheckReal()).getName());
                    }
                    if (!BeanUtil.isEmpty(i.getCheckDetails())) {
                        i.setCheckDetails(InvoiceDTO.InvCheckDetailsEnum.parse(i.getCheckDetails()).getName());
                    }
                    if (!BeanUtil.isEmpty(i.getEdocType())) {
                        i.setEdocType(EdocHeaderDTO.EdocTypeEnum.parse(i.getEdocType()).getName());
                    }
                    if (!BeanUtil.isEmpty(i.getRedOrBule())) {
                        i.setRedOrBule(InvoiceDTO.RedOrBlueEnum.parse(i.getRedOrBule()).getName());
                    }
                    if (!BeanUtil.isEmpty(i.getInvSource())) {
                        i.setInvSource(InvoiceDTO.InvSourceEnum.parse(i.getInvSource()).getName());
                    }
                    if (!BeanUtil.isEmpty(i.getCreateTime())) {
                        i.setCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(i.getCreateTime()));
                    }
                    InvoiceItem invoiceItem = new InvoiceItem();
                    invoiceItem.setInvId(i.getId());
                    List<InvoiceItem> invoiceItemList = invoiceItemService.query(invoiceItem);
                    i.setInvoiceItemList(invoiceItemList);
                }
            }

            Map<String,Object> result = new HashMap<>();
            result.put("dataGrid",page.getResult());
            invoiceModel.setPageSize(null);
            invoiceModel.setPage(null);
            List<InvoiceModel> invoiceListTotal = invoiceService.queryInvoicesNoPage(invoiceModel);
            result.put("totalRows",BeanUtil.isEmpty(invoiceListTotal)?0:invoiceListTotal.size());
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            INVOICE_LOGGER.error("查询发票分页数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","查询发票分页数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    /*private Map<String,Object> parseDataGridResultMap(Map<String,Object> map, Class<T> clazz) {
        List<T> list = (List<T>) ((Map<String,Object>)map.get("result")).get("dataGrid");
        for (T t:list) {

        }


    }*/

    @Override
    public ExecuteResult save(Object o) {
        return null;
    }

    @Override
    public ExecuteResult<PageInfo> dataGrid(Object o) {
        return null;
    }



    @Override
    public ExecuteResult<PageInfo<InvoiceDTO>> queryInvoices(InvoiceDTO invoiceDTO) {
        ExecuteResult<PageInfo<InvoiceDTO>> result = new ExecuteResult<>();
        Date checkTimeEnd=invoiceDTO.getCheckTimeEnd();
        if(!BeanUtil.isEmpty(checkTimeEnd)) {
            invoiceDTO.setCheckTimeEnd((DateUtil.getNextDay(checkTimeEnd,1)));
        }

        if (!BeanUtil.isEmpty(invoiceDTO.getCreateTimeEnd())) {
            invoiceDTO.setCreateTimeEnd(DateUtil.getNextDay(invoiceDTO.getCreateTimeEnd(), 1));
        }
        try {
            InvoiceModel invoiceModel = DozerUtil.map(invoiceDTO, InvoiceModel.class);
            Page<InvoiceModel> page = invoiceService.queryInvoices(invoiceModel);
            PageInfo<InvoiceDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), InvoiceDTO.class);
            result.setResult(pageInfo);
        } catch (Exception e) {
            INVOICE_LOGGER.error("查询发票分页数据失败", e);
            result.addErrorMessage("查询发票分页数据失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<InvoiceDTO>> queryInvoicesByEdocNo(String edocNo) {
        ExecuteResult<PageInfo<InvoiceDTO>> result = new ExecuteResult<>();
        try {
            Page<InvoiceModel> page = invoiceService.queryInvoicesByEdocNo(edocNo);
            PageInfo<InvoiceDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), InvoiceDTO.class);
            List<InvoiceDTO> invoiceDTOList = pageInfo.getList();
            for (InvoiceDTO invoice:invoiceDTOList) {
                String dept = invoice.getDept();
                String costType = invoice.getCostType();
                if (!BeanUtil.isEmpty(dept)) {
                    String deptAfterChange = "<a onclick=\"getDept( "+invoice.getId()+") \">"+dept+"</a>";
                    invoice.setDept(deptAfterChange);
                }
                if (!BeanUtil.isEmpty(costType)) {
                    String costTypeAfterChange = "<a onclick=\"getCostType( "+invoice.getId()+") \">"+costType+"</a>";
                    invoice.setCostType(costTypeAfterChange);
                }
            }
            pageInfo.setList(invoiceDTOList);
            result.setResult(pageInfo);
        }catch (Exception e) {
            INVOICE_LOGGER.error("查询发票分页数据失败", e);
            result.addErrorMessage("查询发票分页数据失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<InvoiceDTO>> queryInvoicesByEdocNoForCheck(InvoiceDTO invoiceDTO) {
        ExecuteResult<PageInfo<InvoiceDTO>> result = new ExecuteResult<>();
        try {
            InvoiceModel invoiceModel = DozerUtil.map(invoiceDTO, InvoiceModel.class);
            Page<InvoiceModel> page = invoiceService.queryInvoicesByEdocNoForCheck(invoiceModel);
            PageInfo<InvoiceDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), InvoiceDTO.class);
            List<InvoiceDTO> invoiceDTOList = pageInfo.getList();
            for (InvoiceDTO invoice:invoiceDTOList) {
                String dept = invoice.getDept();
                String costType = invoice.getCostType();
                if (!BeanUtil.isEmpty(dept)) {
                    String deptAfterChange = "<a onclick=\"getDept( "+invoice.getId()+") \">"+dept+"</a>";
                    invoice.setDept(deptAfterChange);
                }
                if (!BeanUtil.isEmpty(costType)) {
                    String costTypeAfterChange = "<a onclick=\"getCostType( "+invoice.getId()+") \">"+costType+"</a>";
                    invoice.setCostType(costTypeAfterChange);
                }
            }
            pageInfo.setList(invoiceDTOList);
            result.setResult(pageInfo);
        }catch (Exception e) {
            INVOICE_LOGGER.error("查询发票分页数据失败", e);
            result.addErrorMessage("查询发票分页数据失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<InvoiceDTO> queryInvoiceById(Long id) {
        ExecuteResult<InvoiceDTO> result = new ExecuteResult<>();
        try {
            Invoice invoice = invoiceService.queryByInvId(id);
            InvoiceDTO pageInfo = DozerUtil.map(invoice, InvoiceDTO.class);
            result.setResult(pageInfo);
        } catch (Exception e) {
            INVOICE_LOGGER.error("查询发票信息失败", e);
            result.addErrorMessage("查询发票信息失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<Boolean> checkInvoices(List<Long> ids) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        try {
            for (Long id : ids) {
                String msg = invoiceService.CheckInvoice(id);
                if (StringUtils.isNotBlank(msg)) {
                    result.addErrorMessage(msg);
                }
            }

            //判断是否有错误信息
            if (!result.getErrorMessages().isEmpty()) {
                return result;
            }

            result.setResult(true);
            result.setSuccessMessage("发票验真成功");
        } catch (Exception e) {
            INVOICE_LOGGER.error("发票验真失败", e);
            result.addErrorMessage("发票验真失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<InvoiceDTO> findInvoiceByImgId(Long imgId) {
        ExecuteResult<InvoiceDTO> result = new ExecuteResult<>();
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        try {
            Assert.notNull(imgId, "查询参数不能为空");
            Invoice invoice = invoiceService.queryInvoiceByImgId(imgId);
            if (invoice != null) {
                invoiceDTO = DozerUtil.map(invoice, InvoiceDTO.class);
            }
            result.setResult(invoiceDTO);
            result.setSuccessMessage("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            INVOICE_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Transactional
    @Override
    public ExecuteResult<InvoiceDTO> updateInvNormal(InvoiceDTO invoiceDTO, Long edocImageId, String edocImageType) {
        ExecuteResult<InvoiceDTO> executeResult = new ExecuteResult<>();
        try {
            invoiceService.updateByIdSelective(DozerUtil.map(invoiceDTO,Invoice.class));
            EdocImage edocImage = edocImageService.queryById(edocImageId);
            edocImage.setEdocImageType(edocImageType);
            edocImageService.updateByIdSelective(edocImage);
            executeResult.setResult(invoiceDTO);
            executeResult.setSuccessMessage("发票类型修改成功");
        }catch (Exception e) {
            INVOICE_LOGGER.error("[修改发票]  失败, 失败原因<{}>", e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
        return executeResult;
    }

    @Transactional
    @Override
    public ExecuteResult<InvoiceDTO> updateInv(InvoiceDTO waitEditInvDTO) {
        INVOICE_LOGGER.info("修改发票信息如下：{}" + JSON.toJSONString(waitEditInvDTO));
        ExecuteResult<InvoiceDTO> executeResult = new ExecuteResult<>();
        try {
            Assert.notNull(waitEditInvDTO, "被更新的对象不能为空");
            if (BeanUtil.isEmpty(waitEditInvDTO.getInvCode())
                    ||BeanUtil.isEmpty(waitEditInvDTO.getInvNo())
                    ||BeanUtil.isEmpty(waitEditInvDTO.getInvDate())
                    ||BeanUtil.isEmpty(waitEditInvDTO.getInvAmount())) {
                executeResult.addErrorMessage("发票基本数据：发票代码、发票号码、发票日期、发票金额不能为空，请检查！");
                return executeResult;
            }

            String invAmount = waitEditInvDTO.getInvAmount();
            if (!org.springframework.util.StringUtils.isEmpty(invAmount)) {
                if (invAmount.contains(",")) {
                    invAmount = invAmount.replace(",", "");
                }
            }
            waitEditInvDTO.setInvAmount(invAmount);
            String invTax = waitEditInvDTO.getInvTax();
            if (!org.springframework.util.StringUtils.isEmpty(invTax)) {
                if (invTax.contains(",")) {
                    invTax = invTax.replace(",", "");
                }
            }
            waitEditInvDTO.setInvTax(invTax);
            String invTotalAmount = waitEditInvDTO.getInvTotal();
            if (!org.springframework.util.StringUtils.isEmpty(invTotalAmount)) {
                if (invTotalAmount.contains(",")) {
                    invTotalAmount = invTotalAmount.replace(",", "");
                }
            }
            waitEditInvDTO.setInvTotal(invTotalAmount);

            // 不允许已经是系统验真真票的发票进行修改发票进行
            if (!BeanUtil.isEmpty(waitEditInvDTO.getId())) { // 修改发票场景
                Invoice invoice = invoiceService.queryByInvId(waitEditInvDTO.getId());
                if (!BeanUtil.isEmpty(invoice.getCheckDetails())&&invoice.getCheckDetails().equals("11")) { // 系统验真真票
                    executeResult.addErrorMessage("该发票已经验真完成并且是系统验真真票，不能再次修改！");
                    return executeResult;
                }

            }





            // 做一下保护，防止非多发票场景下出现一张影像对应多条发票
            if (BeanUtil.isEmpty(waitEditInvDTO.getId())&&!BeanUtil.isEmpty(waitEditInvDTO.getEdocImageId())) { // 发票录入，新增
                EdocImage edocImage = edocImageService.queryById(waitEditInvDTO.getEdocImageId());
                if (!edocImage.getEdocImageType().equals(Constant.BillTypeCode.MULTI_INVS)) {// 非多发票场景
                    Invoice invoice = new Invoice();
                    invoice.setEdocImageId(waitEditInvDTO.getEdocImageId());
                    List<Invoice> invoiceList = invoiceService.query(invoice);
                    if (!BeanUtil.isEmpty(invoiceList)) {
                        executeResult.addErrorMessage("该影像已经存在发票，请检查该影响下其他单证类型是否存在发票!");
                        return executeResult;
                    }
                }
            }

            // 发票验重以及checkStatus的处理
            Invoice queryInv = new Invoice();
            queryInv.setId(waitEditInvDTO.getId());
            queryInv.setInvNo(waitEditInvDTO.getInvNo());
            queryInv.setInvCode(waitEditInvDTO.getInvCode());
            queryInv.setInvDate(waitEditInvDTO.getInvDate());
            queryInv.setDeleted(Constant.DeleteFlag.DEL_NO);
            List<Invoice> repeatInvList = invoiceService.queryRepeatInvList(queryInv);
            if (repeatInvList != null && !repeatInvList.isEmpty()) { // 校验发票是否重复(存在相同发票代码、号码且校验通过的未删除发票)
                // 把冲突单据号查寻出来
                Invoice invoice = repeatInvList.get(0);
                Invoice queryCondition = new Invoice();
                queryCondition.setInvNo(invoice.getInvNo());
                queryCondition.setInvCode(invoice.getInvCode());
                List<Invoice> conflicInvoiceList = invoiceService.query(queryCondition);
                List<String> edocNos = new ArrayList<>();
                for (Invoice i:conflicInvoiceList) {
                    EdocImage edocImage = edocImageService.queryById(i.getEdocImageId());
                    if (null != edocImage) {
                        EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
                        if (null != edocHeader&&!edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.INVALID)) {
                            edocNos.add(edocHeader.getEdocNo());
                        }
                    }
                }
                executeResult.setErrorMessages(Arrays.asList("存在相同发票号码、发票代码！冲突单据："+edocNos.toString()));
                return executeResult;
            }
            // 走到这里发票规则校验就是通过的
            waitEditInvDTO.setCheckStatus(Constant.CheckStatus.PASS);
            if (!BeanUtil.isEmpty(waitEditInvDTO.getExtField1())) { // 发票规则校验通过需要清空冲突发票数据
                waitEditInvDTO.setExtField1("");
            }

            // checkReal的处理
            if (BeanUtil.isEmpty(waitEditInvDTO.getCheckDetails())){
                /*waitEditInvDTO.setCheckReal("2");
                waitEditInvDTO.setCheckDetails("11");*/
                waitEditInvDTO.setCheckReal("1");
            }else if (waitEditInvDTO.getCheckDetails().equals("99")) {
                waitEditInvDTO.setCheckReal("1");

            }else {
                waitEditInvDTO.setCheckReal("2");
                // 使用当前登陆人作为发票验真人
                LoginUser currentUser = SubjectUtil.getUser();
                INVOICE_LOGGER.info("invNo:-{}从session中获取的当前登陆人-{}",waitEditInvDTO.getInvNo(),currentUser.getLoginName());
                if (!BeanUtil.isEmpty(currentUser)&&!BeanUtil.isEmpty(currentUser.getLoginName())) {
                    /*SysUserDTO sysUserDTO = sysUserFacade.queryAllByLoginName(currentUser.getLoginName()).getResult();*/
                    /*if (!BeanUtil.isEmpty(sysUserDTO)&&!BeanUtil.isEmpty(sysUserDTO.getUserName())){
                        waitEditInvDTO.setCheckRealOperator(sysUserDTO.getUserName());
                    }else{
                        waitEditInvDTO.setCheckRealOperator(currentUser.getLoginName());
                    }*/
                }else {
                    INVOICE_LOGGER.info("invNo:{}-获取验真人名称失败");
                    waitEditInvDTO.setCheckRealOperator("无法获取验真人");
                }
            }

            // 法人公司匹配检查,手工录入的时候如果用户输入了验真结果并且验真结果为真票，则做法人公司匹配
            if (!BeanUtil.isEmpty(waitEditInvDTO.getCheckDetails())&&waitEditInvDTO.getCheckDetails().equals("18")) { // 只有是人工验真真票的时候才做法人数据匹配
                //boolean flag = checkMdCompanyNewHope(waitEditInvDTO.getBuyerTaxCode(),waitEditInvDTO.getBuyerName());
                /*boolean flag = checkMdCompanyNewHopeNew(waitEditInvDTO);
                if (flag) {
                    waitEditInvDTO.setExtField2("法人公司匹配,发票数据正常");
                }else {
                    waitEditInvDTO.setExtField2("法人公司不匹配,发票数据异常");
                }*/
            }

            InvoiceModel waitEditInvModel = DozerUtil.map(waitEditInvDTO, InvoiceModel.class);

           /* LoginUser currentUser = SubjectUtil.getUser();
            if (!BeanUtil.isEmpty(currentUser)) {
                waitEditInvModel.setCheckRealOperator(currentUser.getLoginName());
            }*/
            Invoice updatedResult = invoiceService.updateInvoice(waitEditInvModel);
            //更新imageTypes
            EdocImage edocImage = edocImageService.queryById(waitEditInvDTO.getEdocImageId());
            String invType = waitEditInvDTO.getInvType();
            switch (invType) {
                case Constant.InvoiceType.VATS_INV:
                    edocImage.setEdocImageType(Constant.BillTypeCode.VATS_INVOICE);
                    break;
                case Constant.InvoiceType.VATN_INV:
                    edocImage.setEdocImageType(Constant.BillTypeCode.VATN_INVOICE);
                    break;
                case Constant.InvoiceType.INV_NORMAL:
                    edocImage.setEdocImageType(Constant.BillTypeCode.EVAT_N_E_INV);
                    break;
                case Constant.InvoiceType.MUTIPART_NORMAL:
                    edocImage.setEdocImageType(Constant.BillTypeCode.MULTI_INVS);
                    break;
                default:
                    edocImage.setEdocImageType(Constant.BillTypeCode.NORMAL_BILL);
            }

            edocImageService.updateByIdSelective(edocImage);

            if (updatedResult.getId() != null) {
                executeResult.setSuccessMessage("[修改发票] 成功.");
                InvoiceDTO dto = DozerUtil.map(updatedResult, InvoiceDTO.class);
                executeResult.setResult(dto);
                return executeResult;
            } else {
                executeResult.setErrorMessages(Arrays.asList("[修改发票] 失败."));
                return executeResult;
            }
        } catch (OptimisticLockException e) {
            INVOICE_LOGGER.error("乐观锁异常", e);
            executeResult.addErrorMessage("发票已经被其他人修改，保存失败!");
            return executeResult;
        } catch (Exception e) {
            INVOICE_LOGGER.error("[修改发票]  失败, 失败原因<{}>", e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<PageInfo<InvDiffColumnDTO>> queryPageInvDiff(InvDiffColumnDTO invDiffColumnDTO) {
        ExecuteResult<PageInfo<InvDiffColumnDTO>> result = new ExecuteResult<>();
        try {
            InvDiffColumnModel model = DozerUtil.map(invDiffColumnDTO, InvDiffColumnModel.class);
            Page<InvDiffColumnModel> models = edocInvDiffService.selectByPage(model);
            PageInfo<InvDiffColumnDTO> pageInfo = DozerUtil.mapPage(models.toPageInfo(), InvDiffColumnDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询发票字段修改记录成功");
        } catch (Exception e) {
            INVOICE_LOGGER.error("分页查询发票字段修改记录失败", e);
            result.addErrorMessage("分页查询发票字段修改记录失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<InvoiceItemDTO>> queryInvItemsByPage(InvoiceItemDTO invoiceItemDTO) {

        ExecuteResult<PageInfo<InvoiceItemDTO>> result = new ExecuteResult<>();
        try {
            Page<InvoiceItem> page = invoiceItemService.queryInvItemsByPage(DozerUtil.map(invoiceItemDTO, InvoiceItemModel.class));
            PageInfo<InvoiceItemDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), InvoiceItemDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询发票明细信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            INVOICE_LOGGER.error("分页查询发票明细信息失败", e);
            result.addErrorMessage("分页查询发票明细信息失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> createInvItem(InvoiceItemDTO invoiceItemDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(invoiceItemDTO, "新增的发票明细不能为空");

            InvoiceItem invoiceItem = DozerUtil.map(invoiceItemDTO, InvoiceItem.class);
            int num = invoiceItemService.add(invoiceItem);
            if (num > 0) {
                result.setSuccessMessage("新增发票明细成功!");
                return result;
            }
            result.setErrorMessages(Arrays.asList("新增发票明细失败!"));
            return result;
        } catch (Exception e) {
            INVOICE_LOGGER.error("对象转换失败,失败原因<{}>", e);
            result.addErrorMessage("系统异常,请联系管理员!");
            return result;
        }
    }

    @Override
    public ExecuteResult<InvoiceItemDTO> findOneInvItem(Long id) {
        ExecuteResult<InvoiceItemDTO> result = new ExecuteResult<>();
        try {
            Assert.notNull(id, "查询参数不能为空");

            InvoiceItem invoiceItem = invoiceItemService.queryById(id);
            InvoiceItemDTO invoiceItemDTO = DozerUtil.map(invoiceItem, InvoiceItemDTO.class);
            result.setResult(invoiceItemDTO);
            result.setSuccessMessage("根据id查询发票明细成功!");
            return result;
        } catch (Exception e) {
            INVOICE_LOGGER.error("对象转换失败,失败原因<{}>", e);
            result.addErrorMessage("系统异常,请联系管理员!");
            return result;
        }
    }

    @Transactional
    @Override
    public ExecuteResult<String> updateInvItem(InvoiceItemDTO invoiceItemDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(invoiceItemDTO, "被更新对象不能为空!");
            //InvoiceItem invoiceItem = DozerUtil.map(invoiceItemDTO, InvoiceItem.class);
            InvoiceItem invoiceItem = invoiceItemService.queryById(invoiceItemDTO.getId());
            invoiceItem.setExtField3(invoiceItemDTO.getExtField3());
            Double d = Double.parseDouble(invoiceItemDTO.getExtField3())-Double.parseDouble(invoiceItemDTO.getExtField5());
           // BigDecimal d = new BigDecimal(invoiceItemDTO.getExtField3()).subtract(new BigDecimal(invoiceItemDTO.getExtField5()));
            DecimalFormat df = new DecimalFormat("#.00");
            invoiceItem.setExtField4(df.format(d));
            invoiceItem.setExtField5(invoiceItemDTO.getExtField5());
            int num = invoiceItemService.updateByIdSelective(invoiceItem);
            if (num > 0) {
                result.setSuccessMessage("修改发票明细成功!");
                //每次更新发票明细的实际报销金额后更新一下发票总体的实际报销金额
                Invoice invoice = invoiceService.queryByInvId(invoiceItem.getInvId());

                InvoiceItem queryCondition = new InvoiceItem();
                queryCondition.setInvId(invoiceItem.getInvId());
                List<InvoiceItem> invoiceItemList = invoiceItemService.query(queryCondition);
                Double actAmountTotal = 0.0;
                Double actTaxTotal = 0.0;
                for (InvoiceItem i:invoiceItemList) {
                    String actItemTotal = i.getExtField3();
                    actAmountTotal = actAmountTotal + Double.parseDouble(actItemTotal);
                    String actItemTax = i.getExtField5();
                    actTaxTotal = actTaxTotal + Double.parseDouble(actItemTax);

                    /*String actAmount = BeanUtil.isEmpty(i.getExtField4())?i.getItemAmount():i.getExtField4(); // 如果本条明细没有实际报销金额和税额，则取全部
                    String actTax = BeanUtil.isEmpty(i.getExtField5())?i.getItemTax():i.getExtField5(); // 同上
                    actAmountTotal = actAmountTotal + Double.valueOf(BeanUtil.isEmpty(actAmount)?"0":actAmount);
                    actTaxTotal = actTaxTotal + Double.valueOf(BeanUtil.isEmpty(actTax)?"0":actTax);*/
                }
                invoice.setExtField3(df.format(actAmountTotal));
                invoice.setExtField5(df.format(actTaxTotal));
                invoiceService.updateByIdSelective(invoice);
                return result;

            }
            result.addErrorMessage("修改发票明细失败");
            return null;
        } catch (Exception e) {
            INVOICE_LOGGER.error("对象转换失败,失败原因<{}>", e);
            result.addErrorMessage("系统异常,请联系管理员!");
            return result;
        }
    }

    @Override
    public ExecuteResult<String> removeInvItem(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(id, "被删除发票明细id不能为空!");
            InvoiceItem waitRemoveInvItem = invoiceItemService.queryById(id);
            Assert.notNull(waitRemoveInvItem, "该发票明细可能已被其他人删除，请刷新后重试!");
            int num = invoiceItemService.remove(waitRemoveInvItem);
            if (num > 0) {
                result.setSuccessMessage("删除发票明细成功!");
                return result;
            }
            result.addErrorMessage("删除发票明细失败!");
            return result;
        } catch (Exception e) {
            INVOICE_LOGGER.error("对象转换失败,失败原因<{}>", e);
            result.addErrorMessage("系统异常,请联系管理员!");
            return result;
        }
    }

    /**
     * 添加发票或者发票附件
     *
     * @param fileNameEnd
     * @param bys
     * @param id
     */
    @Override
    public Long addInvoiceAndEdocImage(String fileNameEnd, byte[] bys, Long id) {
        //根据当前Id查询到当前追加的图片信息
        EdocImage edocImage = edocImageService.queryById(id);
            //根据edocHeaderId查询所有EdocImage
            List<EdocImage> edocImageList = edocImageService.selectAllImagesByEdocHeaderId(edocImage.getEdocHeaderId());
            List<Integer> listNum = new ArrayList<>();
            //取出附件中图片中url的最大 todo:获取最大图片页码号
            for (EdocImage edocImage1 : edocImageList) {
                try {
                    String numPath = edocImage1.getImageUrl().substring(edocImage1.getImageUrl().indexOf("_") + 1, edocImage1.getImageUrl().indexOf("."));
                    Integer num = Integer.parseInt(numPath);
                    listNum.add(num);
                } catch (Exception e) {
                    INVOICE_LOGGER.error("该图片路径无法转换{}", e);
                }
            }
            Collections.sort(listNum);
            String imageNum = listNum.get(listNum.size() - 1) + 1 + "";
            //截取字符串，取中间的"00xx",并且为即将上传的图片准备imageNum
            EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
            String rootPath = edocImage.getImageRootPath();
            //获取图片的imageImages
            String imageUrl = EdocClientUtil.getImagePath(fileNameEnd, edocHeader, imageNum, rootPath);
            //待添加的Images
            EdocImage waitAddEdocImage = new EdocImage();
            //上传图片
            String fullPath = rootPath + imageUrl;
            File file = new File(fullPath);
            try {
                FileOutputStream out = new FileOutputStream(file);
                BufferedOutputStream bufferedOut = new BufferedOutputStream(out);
                bufferedOut.write(bys);
            } catch (Exception e) {
                INVOICE_LOGGER.error("保存图片失败{}", e);
            }
            waitAddEdocImage.setEdocHeaderId(edocImage.getEdocHeaderId());
            waitAddEdocImage.setImageRootPath(rootPath);
            waitAddEdocImage.setImageUrl(imageUrl);
            waitAddEdocImage.setPageNo(edocImage.getPageNo() + 1);
            waitAddEdocImage.setImageName(fileNameEnd);
            // 将当前位置之后的图片的pageOn值都加一
            edocImageService.addEdocImage(waitAddEdocImage, edocImageList, edocImage.getPageNo());
            return edocImage.getEdocHeaderId();
        }

    @Override
    public ExecuteResult<String> deleteImage(long imgId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(imgId, "被删除图片id不能为空");

            EdocImage waitDelImg = edocImageService.queryById(imgId);
            if (BeanUtil.isEmpty(waitDelImg)) {
                result.addErrorMessage("该影像可能已被其他人删除,请刷新后重试");
                return result;
            }
            // 如果这个影像是从附件转换过来的(eodc_image.image_name = edoc_attach.attach_name)，需要同步删除附件
            if (!BeanUtil.isEmpty(waitDelImg.getExtField5())) {
                EdocAttach edocAttach = edocAttachService.queryById(Long.parseLong(waitDelImg.getExtField5()));
                edocAttachService.remove(edocAttach);
            }

            // 发票需删除发票和发票下的附件
            int delInvNum = invoiceService.deleteInvoiceByImgId(imgId);
            if (delInvNum > 0) {
                result.setSuccessMessage("删除成功");
                return result;
            }

            result.addErrorMessage("删除失败");
            return result;
        } catch (Exception e) {
            INVOICE_LOGGER.error("[影像删除失败,失败原因]<{}>", e);
            result.addErrorMessage("系统异常,请联系管理员!");
            return result;
        }
    }

    @Login
    @POST
    @Path("checkRealManual")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> checkRealManual(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            List<String> invoiceIds = (List<String>) map.get("invoiceIds");
            /*List<Invoice> invoiceList = new ArrayList<>();*/
            for (int i=0;i<invoiceIds.size();i++) {
                Invoice invoice = invoiceService.queryById(Long.valueOf(invoiceIds.get(i)));
                if (!BeanUtil.isEmpty(invoice)) {
                    invoice.setCheckReal("2");
                    invoice.setCheckDetails("13");
                    invoice.setExtField2(null);
                    invoiceService.updateById(invoice);
                }

                /*invoiceList.add(invoice);*/
            }
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功!");
            executeResult.put("success",true);
        }catch (Exception e) {
            INVOICE_LOGGER.error("发票人工处理异常{}", e);
            executeResult.put("code","001");
            executeResult.put("msg","资料处理失败！");
            executeResult.put("success",false);
            return executeResult;

        }
        return executeResult;
    }

    @Login
    @POST
    @Path("checkReal")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> checkReal(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            List<String> invoiceIds = (List<String>) map.get("invoiceIds");
            List<Invoice> invoiceList = new ArrayList<>();
            for (int i=0;i<invoiceIds.size();i++) {
                ExecuteResult<InvoiceDTO> checkRealResult = checkReal(Long.valueOf(invoiceIds.get(i)));
                if (checkRealResult.isSuccess()) {
                    executeResult.put("code","000");
                    executeResult.put("msg","资料处理成功!");
                    executeResult.put("success",true);
                }else {
                    executeResult.put("code","001");
                    executeResult.put("msg",checkRealResult.getErrorMessages().get(0));
                    executeResult.put("success",false);
                }
                /*Invoice invoice = invoiceService.queryById(Long.valueOf(invoiceIds.get(i)));
                invoiceList.add(invoice);*/
            }
            /*executeResult.put("result",invoiceList);*/
        }catch (Exception e) {
            INVOICE_LOGGER.error("发票验真异常{}", e);
            executeResult.put("code","001");
            executeResult.put("msg","资料处理失败！");
            executeResult.put("success",false);
        }

        return executeResult;
    }



    @Transactional
    @Override
    public ExecuteResult<InvoiceDTO> checkReal(Long invoiceId) {
        ExecuteResult<InvoiceDTO> result = new ExecuteResult<>();
        try {
            //发票验真平台商用url
            String url = "http://120.24.49.208:9082/vatcloud/invoice/serviceWeb/invCheckWeb/doCheck";
            /*ExecuteResult<SysConfDictItemDTO> checkRealUrlResult = dataDictCliFacade.findByEnNameAndItemCode("INVOICE_CHECK_REAL","CHECK_REAL_URL");
            if (!BeanUtil.isEmpty(checkRealUrlResult.getResult())&&!BeanUtil.isEmpty(checkRealUrlResult.getResult().getDicItemValue())) {
                url = checkRealUrlResult.getResult().getDicItemValue();
            }*/
            INVOICE_LOGGER.info("当前使用的验真平台url为：{}",url);
            Invoice invoice = invoiceService.queryById(invoiceId);
            //只有规则校验通过的发票才能验真
            /*if (invoice == null || !Constant.InvoiceCheckStatus.PASS.equals(invoice.getCheckStatus())) {
                result.addErrorMessage("发票数据没有保存或规则校验未过，无法进行验真！");
                return result;
            }*/
            //校验发票信息完整性
            if (BeanUtil.isEmpty(invoice.getInvCode())) {
                result.addErrorMessage("没有输入发票代码，或者输入之后没有进行保存，无法进行验真！");
                return result;
            }else if (BeanUtil.isEmpty(invoice.getInvNo())){
                result.addErrorMessage("没有输入发票号码，或者输入之后没有进行保存，无法进行验真！");
                return result;

            }else if (BeanUtil.isEmpty(invoice.getInvDate())) {
                result.addErrorMessage("没有输入发票日期，或者输入之后没有进行保存，无法进行验真！");
                return result;

            }else if (BeanUtil.isEmpty(invoice.getInvAmount())) {
                result.addErrorMessage("没有输入发票金额，或者输入之后没有进行保存，无法进行验真！");
                return result;
            }else {
                /*if (invoice.getInvType().equals(Constant.InvoiceType.INV_NORMAL)
                        ||invoice.getInvType().equals(Constant.InvoiceType.VATN_INV)) {
                    if (BeanUtil.isEmpty(invoice.getCheckCode())) {
                        result.addErrorMessage("没有输入校验码，或者输入之后没有进行保存，无法进行验真！");
                        return result;
                    }
                }*/
            }
            //组包
            String request = parseInvoiceCheckRequest(invoice);
            //发包
            String response = HttpClientUtil.httpPostWithJSON(url,request);
            INVOICE_LOGGER.info("invNo:{}-验真返回包体{}",invoice.getInvNo(),response);

            //Assert.notNull(response,"验真平台返回信息为空");
            if (BeanUtil.isEmpty(response)) {
                result.addErrorMessage("验真平台无响应，请稍后再试！");
                return result;
            }
            JSONObject responseJson = JSONObject.parseObject(response);
            Object returnObj = responseJson.get("return");

            // 如果验真平台没有返回返回码，反馈更加友好的提示
            if (BeanUtil.isEmpty(returnObj)) {
                Object errMsg = responseJson.get("errMsg");
                if (!BeanUtil.isEmpty(errMsg)) {
                    result.addErrorMessage(errMsg.toString());
                    invoice.setExtField2(errMsg.toString());
                }else {
                    result.addErrorMessage("验真平台异常，验真失败!");
                    invoice.setExtField2("验真平台异常，验真失败!");
                }
                invoice.setCheckReal("2");
                invoice.setCheckDetails("12");

                // 使用当前登陆人作为发票验真人
                LoginUser currentUser = SubjectUtil.getUser();
                INVOICE_LOGGER.info("invNo:-{}从session中获取的当前登陆人-{}",invoice.getInvNo(),currentUser.getLoginName());
                /*if (!BeanUtil.isEmpty(currentUser)&&!BeanUtil.isEmpty(currentUser.getLoginName())) {
                    SysUserDTO sysUserDTO = sysUserFacade.queryAllByLoginName(currentUser.getLoginName()).getResult();
                    if (!BeanUtil.isEmpty(sysUserDTO)&&!BeanUtil.isEmpty(sysUserDTO.getUserName())){
                        invoice.setCheckRealOperator(sysUserDTO.getUserName());
                    }else{
                        invoice.setCheckRealOperator(currentUser.getLoginName());
                    }
                    INVOICE_LOGGER.info("invNo:{}-获取验真人名称-{}获取验真人登录名-{}",invoice.getInvNo(),sysUserDTO.getUserName(),currentUser.getLoginName());
                }else {
                    INVOICE_LOGGER.info("invNo:{}-获取验真人名称失败");
                    invoice.setCheckRealOperator("无法获取验真人");
                }*/
              /*  LoginUser currentUser = SubjectUtil.getUser();
                if (!BeanUtil.isEmpty(currentUser)) {
                    invoice.setCheckRealOperator(currentUser.getLoginName());
                }*/
                invoiceService.updateByIdSelective(invoice);
                return result;
            }
            // Assert.notNull(returnObj,"验真平台没有返回验真结果返回码，无法判断验真结果");
            //统一返回码数据类型
            String returnCode = returnObj.toString();

            if ("1".equals(returnCode)) {
                //验真成功
                //如果验真平台没有返回checkStatus，则手工更新
                JSONArray jsonBodyArray = (JSONArray) ((JSONObject) responseJson.get("invAllDTO")).get("list");
                if (jsonBodyArray.size()>0&&(BeanUtil.isEmpty(invoice.getCheckReal())||!Constant.InvoiceCheckReal.REAL.equals(invoice.getCheckReal()))) {
                    invoice.setCheckReal("2");
                    invoice.setCheckDetails("11"); // 系统验真真票
                }else {
                    String checkStatus = (String) ((JSONObject) responseJson.get("invAllDTO")).get("checkStatus");
                    invoice.setCheckReal("2");
                    invoice.setCheckDetails("12"); // 验真不通过
                    // TODO: 2018/8/5  获取验真描述信息
                    String retrunMassage = (String) ((JSONObject) responseJson.get("invAllDTO")).get("errMsg");
                    invoice.setExtField2(retrunMassage);
                }
                //清空invoice.check_detail
                //invoice.setCheckDetails("");
                // 填充验真返回数据
                JSONObject jsonBody = (JSONObject)responseJson.get("invAllDTO");

                String invTypeFromCheckRealPlatForm = (String) jsonBody.get("invoiceType");
                String invType = null;
                String edocImageType = null;
                switch (invTypeFromCheckRealPlatForm) {
                    case "01": invType = Constant.InvoiceType.VATS_INV; edocImageType = Constant.BillTypeCode.VATS_INVOICE; break; // 增专发票联
                    case "04": invType = Constant.InvoiceType.VATN_INV; edocImageType = Constant.BillTypeCode.VATN_INVOICE; break; // 增普
                    case "10": invType = Constant.InvoiceType.INV_NORMAL; edocImageType = Constant.BillTypeCode.EVAT_N_E_INV; break; // 电子
                }
                EdocImage edocImage = edocImageService.queryById(invoice.getEdocImageId());
                if (!BeanUtil.isEmpty(invType) && (!invType.equals(invoice.getInvType()) || !edocImage.getEdocImageType().equals(edocImageType))) { // 如果验真平台返回了正确的发票类型并且和系统中的不一致，则以验真平台的为准
                    invoice.setInvType(invType);
                    edocImage.setEdocImageType(edocImageType);
                    edocImageService.updateByIdSelective(edocImage);
                }

                //invoice.setCheckStatus();
                invoice.setBuyerName((String) jsonBody.get("custName"));
                invoice.setSalerName((String) jsonBody.get("compName"));
                invoice.setSalerTaxCode((String) jsonBody.get("comTaxCode"));
                invoice.setBuyerTaxCode((String) jsonBody.get("custTaxCode"));
                //invoice.setInvMachineNo((String) jsonBody.get("machineCode"));
                invoice.setInvAmount((String) jsonBody.get("amountTotal"));
                invoice.setInvTax((String) jsonBody.get("taxTotal"));
                invoice.setInvTotal((String) jsonBody.get("amountTax"));
                invoice.setExtField5(invoice.getInvTax());
                invoice.setExtField3(invoice.getInvTotal());
                invoice.setBuyerAddress((String) jsonBody.get("custAddress"));
                invoice.setBuyerBankAccount((String) jsonBody.get("custBankAccount"));
                invoice.setSalerAddress((String) jsonBody.get("comAddress"));
                invoice.setSalerBankAccount((String) jsonBody.get("comBankAccount"));

                // 如果原来发票没有校验码，验真平台返回里面有校验码，则保存一下校验码
                String checkCode = (String) jsonBody.get("checkCode");
                if (!BeanUtil.isEmpty(checkCode)&&checkCode.length()>=6) {
                    invoice.setCheckCode(checkCode.substring(checkCode.length()-6));
                }


                // 发票默认实际报销价税合计和实际报销税额填写票面数据
                //invoice.setExtField3((String) jsonBody.get("amountTax"));
                invoice.setExtField4(invoice.getInvAmount());
                //invoice.setExtField5((String) jsonBody.get("taxTotal"));
                invoiceService.updateByIdSelective(invoice);

                //清空发票明细
                InvoiceItem queryCondition = new InvoiceItem();
                queryCondition.setInvId(invoiceId);

                List<InvoiceItem> invoiceItemList = invoiceItemService.query(queryCondition);
                if (!BeanUtil.isEmpty(invoiceItemList)) {
                    for (InvoiceItem i:invoiceItemList) {
                        invoiceItemService.remove(i);
                    }
                }
                // 清空原来的发票明细
                /*InvoiceItem invoiceItemQueryCondition = new InvoiceItem();
                invoiceItemQueryCondition.setInvId(invoiceId);
                List<InvoiceItem> invoiceItemListInDB = invoiceItemService.query(invoiceItemQueryCondition);
                for (InvoiceItem ii:invoiceItemListInDB) {
                    invoiceItemService.remove(ii);
                }*/

                // 插入本次验真的发票明细
                for(int i=0;i<jsonBodyArray.size();i++) {
                    JSONObject jsonObject = (JSONObject) jsonBodyArray.get(i);
                    InvoiceItem invoiceItem = new InvoiceItem();
                    invoiceItem.setInvId(invoice.getId());
                    invoiceItem.setItemName((String) jsonObject.get("goodsName"));
                    invoiceItem.setItemSpec((String) jsonObject.get("goodsSpec"));
                    invoiceItem.setItemPrice((String) jsonObject.get("goodsPrice"));
                    invoiceItem.setItemQuantity((String) jsonObject.get("goodsQuantity"));
                    invoiceItem.setItemTax((String) jsonObject.get("taxAmount"));
                    invoiceItem.setItemTaxRate((String) jsonObject.get("taxRate"));
                    invoiceItem.setItemAmount((String) jsonObject.get("goodsAmount"));
                    invoiceItem.setUnit((String) jsonObject.get("goodsUnit"));
                    String itemAmount = (String) jsonObject.get("goodsAmount");
                    String itemTax = (String) jsonObject.get("taxAmount");
                    if (!BeanUtil.isEmpty(itemTax)&&itemTax.contains("*")) { // 规整一下税额，防止出现税额为***的情况
                        itemTax = "0";
                    }
                    //Double d = Double.parseDouble(BeanUtil.isEmpty(itemAmount)?"0":itemAmount) + Double.parseDouble(BeanUtil.isEmpty(itemTax)?"0":itemTax);
                    //2018-09-14
                    BigDecimal d = new BigDecimal(BeanUtil.isEmpty(itemAmount)?"0":itemAmount).add(new BigDecimal(BeanUtil.isEmpty(itemTax)?"0":itemTax));
                    invoiceItem.setItemTotal(d.toString());
                    //明细默认的实际报销价税合计和实际报销税额为票面金额
                    invoiceItem.setExtField3(d.toString());
                    invoiceItem.setExtField4(itemAmount);
                    invoiceItem.setExtField5(itemTax);
                    invoiceItemService.add(invoiceItem);
                }


                //invoiceInfoAfterCheck = saveInvoiceInfoAfterCheck(responseJson,invoiceInfo);
            }else {
                //验真失败
                invoice.setCheckReal("2");
                invoice.setCheckDetails("12");
                invoice.setExtField2(responseJson.getString("errMsg"));
                //invoiceInfo.setCheckStatus(2);
                //invoiceInfo.setErrMsg(responseJson.getString("errMsg"));
                //invoiceInfoDao.update(invoiceInfo);
                //invoiceInfoAfterCheck = invoiceInfo;
            }
            invoice.setCheckTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            // 使用当前登陆人作为发票验真人
            LoginUser currentUser = SubjectUtil.getUser();
            INVOICE_LOGGER.info("invNo:-{}从session中获取的当前登陆人-{}",invoice.getInvNo(),currentUser.getLoginName());
            /*if (!BeanUtil.isEmpty(currentUser)&&!BeanUtil.isEmpty(currentUser.getLoginName())) {
                SysUserDTO sysUserDTO = sysUserFacade.queryAllByLoginName(currentUser.getLoginName()).getResult();
                if (!BeanUtil.isEmpty(sysUserDTO)&&!BeanUtil.isEmpty(sysUserDTO.getUserName())){
                    invoice.setCheckRealOperator(sysUserDTO.getUserName());
                }else{
                    invoice.setCheckRealOperator(currentUser.getLoginName());
                }
                INVOICE_LOGGER.info("invNo:{}-获取验真人名称-{}获取验真人登录名-{}",invoice.getInvNo(),sysUserDTO.getUserName(),currentUser.getLoginName());
            }else {
                INVOICE_LOGGER.info("invNo:{}-获取验真人名称失败");
                invoice.setCheckRealOperator("无法获取验真人");
            }*/
            /*LoginUser currentUser = SubjectUtil.getUser();
            if (!BeanUtil.isEmpty(currentUser)) {
                invoice.setCheckRealOperator(currentUser.getLoginName());
            }*/

            invoiceService.updateByIdSelective(invoice);
            if ("11".equals(invoice.getCheckDetails())) { // 对真票进行税号匹配
                String invoiceTaxCodeCheck = LoadConfig.get("INVOICE_TAXCODE_CHECK");
                if (!BeanUtil.isEmpty(invoiceTaxCodeCheck)&&"1".equals(invoiceTaxCodeCheck)) {
                    EdocImage edocImage = edocImageService.queryById(invoice.getEdocImageId());
                    EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
                    edocHeaderService.checkEdocTaxCodeWhithInvoiceTaxCode(invoice,edocHeader);
                }
            }
            Invoice invoiceInDB = invoiceService.queryById(invoice.getId());
                if ("11".equals(invoice.getCheckDetails())) {
                    invoice.setExtField2(null);
                    invoiceService.updateById(invoice);
                }


            //判断购方公司是否符合新希望法人公司
            //boolean flag = checkMdCompanyNewHope(invoice.getBuyerTaxCode(),invoice.getBuyerName());
            /*boolean flag = checkMdCompanyNewHopeNew(DozerUtil.map(invoice,InvoiceDTO.class));
            Invoice invoiceInDB = invoiceService.queryByInvId(invoice.getId());
            if (invoiceInDB.getCheckDetails().equals("11")) { // 验真通过才进行法人公司匹配
                if (flag) {
                    result.setSuccessMessage("发票验真完成");
                    invoiceInDB.setExtField2("法人公司匹配,发票数据正常");
                }else {
                    result.setSuccessMessage("发票验真完成");
                    invoiceInDB.setExtField2("法人公司不匹配,发票数据异常");
                }
                invoiceService.updateByIdSelective(invoiceInDB);
            }*/
            /*result.setResult(DozerUtil.map(invoiceInDB,InvoiceDTO.class));*/
            result.setSuccessMessage("发票验真完成");
            //result.setSuccessMessage("发票验真成功，invId:"+invoiceId);
        }catch (Exception e) {
            INVOICE_LOGGER.error("[发票验真失败,失败原因]<{}>", e);
            result.setErrorMessages(Arrays.asList("发票验真失败，invId:"+invoiceId));
        }
        return result;
    }

    /*// 根据新希望法人税号判断发票是否和法人公司数据匹配
    private boolean checkMdCompanyNewHope(String taxCode,String companyName) {
        boolean isMatch = false;
        MdLegalCompanyDTO mdLegalCompanyDTO = new MdLegalCompanyDTO();
        mdLegalCompanyDTO.setTaxCode(taxCode);
        List<MdLegalCompanyDTO> mdLegalCompanyDTOList = mdLegalCompanyFacade.queryAllByMdLegalCompanyDTO(mdLegalCompanyDTO).getResult().getList();
        for (MdLegalCompanyDTO m:mdLegalCompanyDTOList) {
            if (!BeanUtil.isEmpty(m.getTaxCode())&&m.getTaxCode().equals(taxCode)&&!BeanUtil.isEmpty(m.getCompanyName())&&m.getCompanyName().equals(companyName)) {
                isMatch = true;
                return isMatch;
            }
        }
        return isMatch;
    }*/

    /*// 根据新希望法人税号判断发票是否和法人公司数据匹配
    private boolean checkMdCompanyNewHopeNew(InvoiceDTO invoiceDTO) {
        boolean isMatch = false;
        if (BeanUtil.isEmpty(invoiceDTO.getEdocImageId())) { // 如果发票没有在影像下面，直接返回false
            return false;
        }
        // 查询发票所在的单据
        EdocImage edocImage = edocImageService.queryById(invoiceDTO.getEdocImageId());
        EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
        if (BeanUtil.isEmpty(edocHeader)) { // 如果发票没有在影像下面，直接返回false
            return false;
        }
        // 第一步 先判断发票的购方信息与单据是否匹配，如果匹配则进入下一步，不匹配则直接返回false
        if (BeanUtil.isEmpty(edocHeader.getCompanyName())||BeanUtil.isEmpty(edocHeader.getCompanyCode())) { // 如果对应单据没有购方信息，返回false
            return false;
        }else {
            if (invoiceDTO.getBuyerName().equals(edocHeader.getCompanyName())) {
                isMatch = true;
            }
        }
        // 第二步 再匹配发票的购方信息是否存在于新希望法人数据库中，如果存在则返回true，否则返回false
        MdLegalCompanyDTO mdLegalCompanyDTO = new MdLegalCompanyDTO();
        mdLegalCompanyDTO.setTaxCode(invoiceDTO.getBuyerTaxCode());
        mdLegalCompanyDTO.setCompanyName(invoiceDTO.getBuyerName());
        List<MdLegalCompanyDTO> mdLegalCompanyDTOList = mdLegalCompanyFacade.queryAllByMdLegalCompanyDTO(mdLegalCompanyDTO).getResult().getList();
        if (BeanUtil.isEmpty(mdLegalCompanyDTOList)) { // 发票购方信息不存在于法人数据库中
            isMatch = false;
        }
        return isMatch;
    }*/



    @Override
    public ExecuteResult<String> checkReal() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //找到所有验真没有通过的发票

            Invoice queryCondition1 = new Invoice();
            queryCondition1.setCheckReal(Constant.CheckStatus.WAITTING);
            List<Invoice> queryResult1 = invoiceService.query(queryCondition1);

            Invoice queryCondition2 = new Invoice();
            queryCondition2.setCheckReal(Constant.CheckStatus.NOT_PASS);
            List<Invoice> queryResult2 = invoiceService.query(queryCondition2);

//            Invoice queryCondition3 = new Invoice();
//            queryCondition3.setCheckReal(null);
            List<Invoice> queryResult3 = invoiceService.queryCheckRealNullList();

            queryResult1.addAll(queryResult2);
            queryResult1.addAll(queryResult3);
            for (Invoice i:queryResult1) {
                // 空指针保护
                String invType = BeanUtil.isEmpty(i.getInvType())?Constant.InvoiceType.VATS_INV:i.getInvType();
                String invAmount = BeanUtil.isEmpty(i.getInvAmount())?"0":i.getInvAmount();
                Long invId = BeanUtil.isEmpty(i.getId())?-1L:i.getId();

                if (Constant.InvoiceType.VATS_INV.equals(invType)||
                        (Constant.InvoiceType.VATN_INV.equals(invType)&&Double.parseDouble(invAmount)>=100.00)||
                        (Constant.InvoiceType.INV_NORMAL.equals(invType)&&Double.parseDouble(invAmount)>=100.00)) {
                    this.checkReal(invId);
                }
            }
            result.setSuccessMessage("发票验真成功");
        }catch (Exception e) {
            INVOICE_LOGGER.error("发票验真失败{}", e);
            result.setErrorMessages(Arrays.asList("发票验真失败"));
        }
        return result;
    }

    /**
     * 组包
     * @param invoice
     * @return
     */
    private String parseInvoiceCheckRequest(Invoice invoice) throws Exception {
        INVOICE_LOGGER.info("invNo:{}-组包开始",invoice.getInvNo());
        JSONObject request = new JSONObject();
        JSONObject invoiceInfoJson = new JSONObject();
        JSONArray invoiceList = new JSONArray();
        invoiceInfoJson.put("amountTotal",invoice.getInvAmount());
        invoiceInfoJson.put("invoiceCode",invoice.getInvCode());
        invoiceInfoJson.put("invoiceDate", invoice.getInvDate());
        invoiceInfoJson.put("invoiceNum",invoice.getInvNo());
        String checkCode = invoice.getCheckCode();
        if (!BeanUtil.isEmpty(checkCode)&&checkCode.length()>=6) {
            invoiceInfoJson.put("checkCode",checkCode.substring(checkCode.length()-6));
        }
        invoiceList.add(invoiceInfoJson);
        // 获取验真帐号和密码
        String checkRealId = "37";
        String checkRealPwd = "jLiwklOfEpRiwZNTaXnGi9iGvh26vOP4Q5ChJsc1CFj4JPxDaXlGleYwI04RF0xqQoctGvC9/Zkb+aNvWZgpbg==";
        /*ExecuteResult<SysConfDictItemDTO> checkRealIdResult = dataDictCliFacade.findByEnNameAndItemCode("INVOICE_CHECK_REAL","CHECK_REAL_ID");
        if (!BeanUtil.isEmpty(checkRealIdResult.getResult())&&!BeanUtil.isEmpty(checkRealIdResult.getResult().getDicItemValue())) {
            checkRealId = checkRealIdResult.getResult().getDicItemValue();
        }

        ExecuteResult<SysConfDictItemDTO> checkRealPwdResult = dataDictCliFacade.findByEnNameAndItemCode("INVOICE_CHECK_REAL","CHECK_REAL_PWD");
        if (!BeanUtil.isEmpty(checkRealPwdResult.getResult())&&!BeanUtil.isEmpty(checkRealPwdResult.getResult().getDicItemValue())) {
            checkRealPwd = checkRealPwdResult.getResult().getDicItemValue();
        }*/
        INVOICE_LOGGER.info("当前使用的验真平台id为:{},pwd为:{}",checkRealId,checkRealPwd);

        request.put("id",checkRealId);
        request.put("invList",invoiceList);
        //request.put("password","WfNGGCX5UADAc1n2vLpHQHH6y90dGILikyGO78mzLPh4pGYbOGfbl9zxxEdDcWw3KsjKN4HE9zt/iJGwYYVbZw==");
        request.put("password",checkRealPwd);
        request.put("type","0");
        String requestString = request.toString();
        INVOICE_LOGGER.info("invNo:{}-组包完成-{}",invoice.getInvNo(),requestString);
        return requestString;
    }

    @Override
    public ExecuteResult<String> addInvoice(InvoiceDTO invoiceDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Invoice invoice = DozerUtil.map(invoiceDTO,Invoice.class);
            if (invoice.getId() != null) {
                invoiceService.updateByIdSelective(invoice);
            } else {
                invoiceService.add(invoice);
            }
        }catch (Exception e) {
            INVOICE_LOGGER.error("新增发票失败{}",e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> createInvInput(InvoiceDTO invoiceDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(invoiceDTO, "发票录入信息不能为空");
            Assert.notNull(invoiceDTO.getEdocImageId(), "图片id不能为空");

            if ("1".equals(invoiceDTO.getInvChildType())) {
                if (BeanUtil.isEmpty(invoiceDTO.getSalerName())||BeanUtil.isEmpty(invoiceDTO.getSalerTaxCode())) {
                    result.addErrorMessage("供应商名称和税号不能为空！");
                    return result;
                }
            }

            EdocImage edocImage = edocImageService.queryById(invoiceDTO.getEdocImageId());
            if (edocImage == null) {
                result.addErrorMessage("获取图片信息失败，请刷新后重试！");
                return result;
            }

            Long id = invoiceDTO.getId();
            if (BeanUtil.isEmpty(id)) {
                if (!edocImage.getEdocImageType().equals(Constant.BillTypeCode.MULTI_INVS)) {
                    Invoice invoice = invoiceService.queryInvoiceByImgId(edocImage.getId());
                    if (!BeanUtil.isEmpty(invoice)) {
                        // result.addErrorMessage("该影像已经存在发票，并且发票对应的单证类型不是多发票，禁止再次录入！请检查该影像其他单证类型下是否存在发票，存在发票号码："+invoice.getInvNo());
                        // return result;
                        // 这种场景下把原来的那张发票删除
                        invoiceService.remove(invoice);
                    }
                }
            }


            Invoice queryRepeatInv = new Invoice();
            queryRepeatInv.setInvCode(invoiceDTO.getInvCode());
            queryRepeatInv.setInvNo(invoiceDTO.getInvNo());
            List<Invoice> repeatInvList = invoiceService.queryRepeatInvList(queryRepeatInv);
            if (repeatInvList != null && !repeatInvList.isEmpty()) {

                // 把冲突单据号查寻出来
                Invoice invoice = repeatInvList.get(0);
                Invoice queryCondition = new Invoice();
                queryCondition.setInvNo(invoice.getInvNo());
                queryCondition.setInvCode(invoice.getInvCode());
                List<Invoice> conflicInvoiceList = invoiceService.query(queryCondition);
                List<String> edocNos = new ArrayList<>();
                for (Invoice i:conflicInvoiceList) {
                    EdocImage edocImage2 = edocImageService.queryById(i.getEdocImageId());
                    if (null != edocImage2) {
                        EdocHeader edocHeader = edocHeaderService.queryById(edocImage2.getEdocHeaderId());
                        if (null != edocHeader&&!edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.INVALID)) {
                            edocNos.add(edocHeader.getEdocNo());
                        }
                    }
                }

                result.addErrorMessage("存在相同发票号码、发票代码！冲突单据："+edocNos.toString());
                return result;
            }

            // 更新图片类型为多发票
            int flag;
            if (Constant.BillTypeCode.MULTI_INVS.equals(edocImage.getEdocImageType())) {
                flag = 1;
            } else {
                edocImage.setEdocImageType(Constant.BillTypeCode.MULTI_INVS);
                // 更新图片类型
                flag = edocImageService.updateByIdSelective(edocImage);
            }

            // 新增发票录入信息
            if (flag == 1) {
                invoiceDTO.setCheckStatus(Constant.CheckStatus.PASS);
                // checkReal的处理
                if (BeanUtil.isEmpty(invoiceDTO.getCheckDetails())){
                /*waitEditInvDTO.setCheckReal("2");
                waitEditInvDTO.setCheckDetails("11");*/
                    invoiceDTO.setCheckReal("1");
                }else if (invoiceDTO.getCheckDetails().equals("99")) {
                    invoiceDTO.setCheckReal("1");

                }else {
                    invoiceDTO.setCheckReal("2");
                    // 使用当前登陆人作为发票验真人
                    LoginUser currentUser = SubjectUtil.getUser();
                    INVOICE_LOGGER.info("invNo:-{}从session中获取的当前登陆人-{}",invoiceDTO.getInvNo(),currentUser.getLoginName());
                    if (!BeanUtil.isEmpty(currentUser)&&!BeanUtil.isEmpty(currentUser.getLoginName())) {
                        /*SysUserDTO sysUserDTO = sysUserFacade.queryAllByLoginName(currentUser.getLoginName()).getResult();
                        if (!BeanUtil.isEmpty(sysUserDTO)&&!BeanUtil.isEmpty(sysUserDTO.getUserName())){
                            invoiceDTO.setCheckRealOperator(sysUserDTO.getUserName());
                        }else{
                            invoiceDTO.setCheckRealOperator(currentUser.getLoginName());
                        }*/
                    }else {
                        INVOICE_LOGGER.info("invNo:{}-获取验真人名称失败");
                        invoiceDTO.setCheckRealOperator("无法获取验真人");
                    }
                }
                // 法人公司匹配检查,手工录入的时候如果用户输入了验真结果并且验真结果为真票，则做法人公司匹配
                if (!BeanUtil.isEmpty(invoiceDTO.getCheckDetails())&&invoiceDTO.getCheckDetails().equals("18")) { // 只有是人工验真真票的时候才做法人数据匹配
                    //boolean flag1 = checkMdCompanyNewHope(invoiceDTO.getBuyerTaxCode(),invoiceDTO.getBuyerName());
                    /*boolean flag1 = checkMdCompanyNewHopeNew(invoiceDTO);
                    if (flag1) {
                        invoiceDTO.setExtField2("法人公司匹配,发票数据正常");
                    }else {
                        invoiceDTO.setExtField2("法人公司不匹配,发票数据异常");
                    }*/
                }

                Invoice waitAddInvInput = DozerUtil.map(invoiceDTO, Invoice.class);
                invoiceService.add(waitAddInvInput);
                result.setSuccessMessage("发票录入成功！");

                //this.checkReal(waitAddInvInput.getId());
                return result;
            }

            result.addErrorMessage("发票录入失败！");


        } catch (Exception e) {
            result.addErrorMessage("发票录入失败！");
            INVOICE_LOGGER.error("发票录入失败，{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<String> removeInv(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(id, "id不能为空");
            Invoice waitDelInv = invoiceService.queryById(id);
            if (waitDelInv == null) {
                result.addErrorMessage("该发票可能已被其他人删除好，请刷新后重试！");
                return result;
            }
            invoiceService.remove(waitDelInv);
            result.setSuccessMessage("删除发票信息成功！");
        } catch (Exception e) {
            result.addErrorMessage("删除发票信息失败！");
            INVOICE_LOGGER.error("删除发票信息失败，{}", e);
        }

        return result;
    }

    /*@Override
    public ExecuteResult<String> importWaitCertInv(byte[] bytes, boolean isReturn) {
        ExecuteResult<String> result = new ExecuteResult<>();
        if (BeanUtil.isEmpty(bytes)) {
            result.addErrorMessage("导入待认证发票信息为空");
            return result;
        }
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);

            ExcelContext context = new ExcelContext("excel/importWaitCertInv.xml");
            ExcelImportResult excelImportResult = context.readExcel("importWaitCertInv", 0, inputStream);

            List<InvoiceDTO> invoiceDTOS = excelImportResult.getListBean();
            Assert.isTrue(invoiceDTOS.size() > 0, "等待认证信息未获取成功");

            for (InvoiceDTO invoiceDTO : invoiceDTOS) {
                if (BeanUtil.isEmpty(invoiceDTO.getInvCode()) || BeanUtil.isEmpty(invoiceDTO.getInvNo()) || BeanUtil.isEmpty(invoiceDTO.getCheckCertifiteTime())) {
                    continue;
                }
                Invoice queryInv = new Invoice();
                queryInv.setInvNo(invoiceDTO.getInvNo());
                queryInv.setInvCode(invoiceDTO.getInvCode());
                queryInv.setCheckCertification(Constant.EdocAuthStatus.WAIT_AUTH);
                List<Invoice> waitUpdateInvs = invoiceService.query(queryInv);
                if (!BeanUtil.isEmpty(waitUpdateInvs)) {
                    Invoice waitUpdateInv = waitUpdateInvs.get(0);

                    waitUpdateInv.setCheckCertifiteTime(invoiceDTO.getCheckCertifiteTime());
                    waitUpdateInv.setCheckCertification(Constant.EdocAuthStatus.AUTH_SUCC);
                    waitUpdateInv.setActCertificationValue(invoiceDTO.getActCertificationValue());
                    invoiceService.updateByIdSelective(waitUpdateInv);
                }
            }
            result.setResult("导入待认证信息成功");
            result.setSuccessMessage("导入待认证信息成功");
        } catch (Exception e) {
            result.addErrorMessage("导入待认证信息失败");
            INVOICE_LOGGER.error("[待认证发票导入]导入待认证信息失败", e);
        }
        return result;
    }*/

    @Override
    public ExecuteResult<String> extractInv(InvoiceDTO invoiceDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //抵账库商用url
            String url = "http://101.201.253.178:8888/vatcloud/invoice/queryInvoiceNum";
            //组包
            String request = combinationData(invoiceDTO);
            //发包
            String response = HttpClientUtil.httpPostWithJSON(url,request);
            INVOICE_LOGGER.info("invNo:{}-验真返回包体{}",invoiceDTO.getInvNo(),response);
            Assert.notNull(response,"验真平台返回信息为空");
            JSONObject responseJson = JSONObject.parseObject(response);
            this.saveInv(responseJson , invoiceDTO);
        }catch (Exception e) {
            INVOICE_LOGGER.error("[发票验真失败,失败原因]<{}>", e);
            result.setErrorMessages(Arrays.asList("发票验真失败，invId:"+invoiceDTO.getId()));
        }
        return result;
    }

    public void saveInv( JSONObject responseJson , InvoiceDTO invoiceDTO) throws Exception{
        JSONArray jsonArray = responseJson.getJSONArray("result");
        if(!jsonArray.isEmpty()){
            JSONObject invJson = jsonArray.getJSONObject(0);
            if(!invJson.getJSONArray("invoiceDetail").isEmpty()){
                invoiceDTO.setCheckDetails("");
                invoiceDTO.setCheckReal("11");
                invoiceDTO.setBuyerName((String) invJson.get("custName"));
                invoiceDTO.setSalerName((String) invJson.get("compName"));
                invoiceDTO.setSalerTaxCode((String) invJson.get("comTaxCode"));
                invoiceDTO.setBuyerTaxCode((String) invJson.get("custTaxCode"));
                invoiceDTO.setInvTax((String) invJson.get("taxTotal"));
                invoiceDTO.setInvTotal((String) invJson.get("amountTax"));
                invoiceDTO.setCheckTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                invoiceService.updateByIdSelective(DozerUtil.map(invoiceDTO , Invoice.class));

                Iterator<Object> invoiceDetail = invJson.getJSONArray("invoiceDetail").iterator();
                List<InvoiceItem> list = new ArrayList<>();
                while(invoiceDetail.hasNext()){
                    JSONObject detailJson = (JSONObject)invoiceDetail.next();
                    InvoiceItem invoiceItem = new InvoiceItem();
                    invoiceItem.setEdocImageId(invoiceDTO.getEdocHeaderId());
                    invoiceItem.setItemName(detailJson.getString("goodsName"));
                    invoiceItem.setItemPrice(detailJson.getString("goodsPrice"));
                    invoiceItem.setItemAmount(detailJson.getString("goodsAmount")); //金额
                    invoiceItem.setItemQuantity(detailJson.getString("goodsQuantity"));
                    invoiceItem.setItemSpec(detailJson.getString("goodsSpec"));
                    invoiceItem.setItemTaxRate(detailJson.getString("taxRate")); // 税率
                    invoiceItem.setItemTax(detailJson.getString("taxAmount"));// 税额
                    invoiceItem.setUnit(detailJson.getString("goodsUnit")); //单位
                    invoiceItem.setCreateBy(SubjectUtil.getUser().getLoginName());
                    invoiceItem.setLastModifyTime(new Date());
                    invoiceItem.setCreateTime(new Date());
                    invoiceItem.setLastModifyBy(SubjectUtil.getUser().getLoginName());
                    list.add(invoiceItem);
                }
                invoiceItemService.addAll(list);
            }else{
                invoiceDTO.setCheckReal("12");
                invoiceDTO.setCheckDetails(responseJson.getString("retMsg"));
                invoiceDTO.setCheckTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                invoiceService.updateById(DozerUtil.map(invoiceDTO , Invoice.class));
            }
        }else {
            invoiceDTO.setCheckDetails(responseJson.getString("retMsg"));
            invoiceDTO.setCheckReal("12");
            invoiceDTO.setCheckTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            invoiceService.updateByIdSelective(DozerUtil.map(invoiceDTO , Invoice.class));
        }
    }

    /**
     * 组包
     * @return
     */
    private String combinationData(InvoiceDTO invoiceDTO) throws Exception {
        INVOICE_LOGGER.info("invNo:{}-组包开始",invoiceDTO.getInvNo());
        JSONObject request = new JSONObject();
        request.put("fpdm" , invoiceDTO.getInvCode());
        request.put("fphm", invoiceDTO.getInvNo());
        INVOICE_LOGGER.info("invNo:{}-组包完成-{}",invoiceDTO.getInvNo(),request.toString());
        return request.toString();
    }

    @Override
    public ExecuteResult<String> invoiceCheckRule() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //找到所有校验状态为空或不为通过的发票
            Invoice queryCondition1 = new Invoice();
            queryCondition1.setCheckStatus(Constant.InvoiceCheckStatus.CONFLICT);
            List<Invoice> queryResult1 = invoiceService.query(queryCondition1);

            Invoice queryCondition2 = new Invoice();
            queryCondition2.setCheckStatus(Constant.InvoiceCheckStatus.NOT_PASS);
            List<Invoice> queryResult2 = invoiceService.query(queryCondition2);

            Invoice queryCondition3 = new Invoice();
            queryCondition3.setCheckStatus(Constant.InvoiceCheckStatus.WAITTING);
            List<Invoice> queryResult3 = invoiceService.query(queryCondition3);

            queryResult1.addAll(queryResult2);
            queryResult1.addAll(queryResult3);
            for (Invoice i:queryResult1) {
                invoiceService.invoiceCheckRule(i);
            }
            result.setSuccessMessage("发票规则校验成功");
        }catch (Exception e) {
            INVOICE_LOGGER.error("发票规则校验异常{}", e);
            result.setErrorMessages(Arrays.asList("发票规则校验异常"));
        }
        return result;
    }

    @Override
    public ExecuteResult<String> invoiceCheckRule(Long invId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(invId,"发票ID不能为空");
            Invoice invoice = invoiceService.queryByInvId(invId);
            if (BeanUtil.isEmpty(invoice)) {
                result.addErrorMessage("没有找到发票");
                return result;
            }
            invoiceService.invoiceCheckRule(invoice);
            result.setSuccessMessage("发票规则校验成功成功");
        }catch (Exception e) {
            INVOICE_LOGGER.error("发票规则校验异常{}", e);
            result.setErrorMessages(Arrays.asList("发票规则校验异常"));
        }
        return result;
    }

    @Override
    public ExecuteResult<String> updateCertStatus(Long edocHeaderId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(edocHeaderId, "单据id不能为空");

            // 查询单据下影像
            List<EdocImage> edocImages = edocImageService.selectAllImagesByEdocHeaderId(edocHeaderId);
            if (BeanUtil.isEmpty(edocImages)) {
                result.addErrorMessage("未找到单据任务id:<" + edocHeaderId + ">对应的影像！");
                return result;
            }
            // 修改影像对应增专票状态
            for (EdocImage edocImage : edocImages) {
                // 增值税专用发票修改认证状态 00:待认证 40:已认证
                if (Constant.BillTypeCode.VATS_INVOICE.equals(edocImage.getEdocImageType())) {
                    Invoice waitUpdateInv = invoiceService.queryInvoiceByImgId(edocImage.getId());
                    String checkCertification = waitUpdateInv.getCheckCertification();
                    // 已经认证处理的不做处理
                    if (checkCertification != null) {
                        if (Constant.EdocAuthStatus.WAIT_AUTH.equals(checkCertification) || Constant.EdocAuthStatus.AUTH_SUCC.equals(checkCertification)) {
                            INVOICE_LOGGER.info("发票号码: {},已经做过认证处理, 不更新认证状态." );
                            continue;
                        }
                    }
                    // 真票才能修改认证状态
                    if (waitUpdateInv != null && ("11".equals(waitUpdateInv.getCheckDetails()) || "18".equals(waitUpdateInv.getCheckDetails()))) {
                        waitUpdateInv.setCheckCertification(Constant.EdocAuthStatus.WAIT_AUTH);
                        invoiceService.updateByIdSelective(waitUpdateInv);
                    }
                }
            }
            result.addErrorMessage("更新发票为待认证状态成功");
        } catch (Exception e) {
            INVOICE_LOGGER.error("【根据单号更新发票认证状态】异常，原因{}", e);
            result.addErrorMessage("根据单号更新发票认证状态失败");
        }
        return result;
    }


    @Override
    public ExecuteResult<PageInfo<InvoiceDTO>> queryInputInvoiceTaxPage(InvoiceDTO invoiceDTO) {
        ExecuteResult<PageInfo<InvoiceDTO>> result = new ExecuteResult<>();
        try {
            if (!BeanUtil.isEmpty(invoiceDTO.getCreateTimeEnd())) {
                invoiceDTO.setCreateTimeEnd(DateUtil.getNextDay(invoiceDTO.getCreateTimeEnd(), 1));
            }
            if (!BeanUtil.isEmpty(invoiceDTO.getCreateTimeEnd())) {
                invoiceDTO.setCreateTimeEnd(DateUtil.getNextDay(invoiceDTO.getCreateTimeEnd(), 1));
            }
            Page<InvoiceModel> page = invoiceService.queryInputInvoiceTaxPage(DozerUtil.map(invoiceDTO,InvoiceModel.class));
            PageInfo<InvoiceDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),InvoiceDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("进项税转出列表分页查询成功");
        }catch (Exception e) {
            INVOICE_LOGGER.error("进项税转出列表分页查询异常{}", e);
            result.setErrorMessages(Arrays.asList("进项税转出列表分页查询异常"));
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<InvoiceDTO>> queryInputInvoiceItem(InvoiceDTO invoiceDTO) {
        ExecuteResult<PageInfo<InvoiceDTO>> result = new ExecuteResult<>();
        try {
            if (!BeanUtil.isEmpty(invoiceDTO.getCreateTimeEnd())) {
                invoiceDTO.setCreateTimeEnd(DateUtil.getNextDay(invoiceDTO.getCreateTimeEnd(), 1));
            }
            if (!BeanUtil.isEmpty(invoiceDTO.getCreateTimeEnd())) {
                invoiceDTO.setCreateTimeEnd(DateUtil.getNextDay(invoiceDTO.getCreateTimeEnd(), 1));
            }
            Page<InvoiceModel> page = invoiceService.queryInputInvoiceItem(DozerUtil.map(invoiceDTO,InvoiceModel.class));
            PageInfo<InvoiceDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),InvoiceDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("进项税转出列表明细分页查询成功");
        }catch (Exception e) {
            INVOICE_LOGGER.error("进项税转出列表明细分页查询异常{}", e);
            result.setErrorMessages(Arrays.asList("进项税转出列表明细分页查询异常"));
        }
        return result;
    }

    @Override
    public ExecuteResult<List<InvoiceItemDTO>> queryInvoiceItemsByInvId(Long invId) {
        ExecuteResult<List<InvoiceItemDTO>> result = new ExecuteResult<>();
        try {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvId(invId);
            List<InvoiceItem>invoiceItemList = invoiceItemService.query(invoiceItem);
            result.setResult(DozerUtil.mapList(invoiceItemList,InvoiceItemDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e) {
            INVOICE_LOGGER.error("查询失败{}", e);
            result.setErrorMessages(Arrays.asList("查询失败"));
        }
        return result;
    }

    @Override
    public ExecuteResult<String> updateInvDeptAndCostType(InvoiceDTO invoiceDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(invoiceDTO, "编辑信息为空");
            Assert.notNull(invoiceDTO.getId(), "未获取到发票id");

            /*if("".equals(invoiceDTO.getDept()) || null ==invoiceDTO.getDept()){
                invoiceDTO.setDept(null);
                *//*result.addErrorMessage("部门未填");
                return result;*//*
            }
            if("".equals(invoiceDTO.getCostType()) || null ==invoiceDTO.getCostType()){
                invoiceDTO.setCostType(null);
                *//*result.addErrorMessage("费用类型未填");
                return result;*//*
            }*/
            Invoice waitEditInv = invoiceService.queryById(invoiceDTO.getId());
            if (null == waitEditInv) {
                result.addErrorMessage("发票可能已被删除, 请刷新后重试!");
                return result;
            }
            //查询 发票明细的 实际报销金额和实际报销税额 的总和 是否 与 发票行信息中实际报销金额和实际报销税额 相等
            InvoiceItem invoiceItem = invoiceService.queryByInvId_sum(invoiceDTO.getId());
          // if(a.compareTo(b)==0) 结果是true
           // 比较大小可以用 a.compareTo(b)
          //  返回值 -1 小于 0 等于 1 大于

            if (!BeanUtil.isEmpty(invoiceItem)) { // 发票明细不为空的时候才走进这个判断
                if ((invoiceItem.getExtField3() != null ) && (invoiceItem.getExtField5() != null )){
                    BigDecimal extField3_select=new BigDecimal(invoiceItem.getExtField3());
                    BigDecimal extField5_select=new BigDecimal(invoiceItem.getExtField5());
                    if(invoiceDTO.getExtField3() !=null){
                        BigDecimal extField3=new BigDecimal(invoiceDTO.getExtField3());
                        if( extField3_select.compareTo(extField3)==-1 || extField3_select.compareTo(extField3)==1){
                            result.addErrorMessage("请在发票明细修改实际报销金额和实际报销税额!");
                            return result;
                        }
                    }
                    if (invoiceDTO.getExtField5() != null){
                        BigDecimal extField5=new BigDecimal(invoiceDTO.getExtField5());
                        if( extField5_select.compareTo(extField5)==-1 || extField5_select.compareTo(extField5)==1){
                            result.addErrorMessage("请在发票明细修改实际报销金额和实际报销税额!");
                            return result;
                        }
                    }

                }
            }


            waitEditInv.setExtField3(invoiceDTO.getExtField3()); //实际报销金额
            waitEditInv.setExtField5(invoiceDTO.getExtField5());//实际报销税额
            waitEditInv.setCostType(invoiceDTO.getCostType());
            waitEditInv.setDept(invoiceDTO.getDept());
            invoiceService.updateByIdSelective(waitEditInv);
            result.setSuccessMessage("保存成功!");
        } catch (Exception e) {
            INVOICE_LOGGER.error("修改发票部门和费用类型失败, 原因:{}", e);
            result.addErrorMessage("保存失败!");
        }
        return result;
    }


    @Override
    public ExecuteResult<List<InvoiceDTO>> queryInvsByImgId(long edocImageId) {
        ExecuteResult<List<InvoiceDTO>> result = new ExecuteResult<>();
        try {
            Assert.notNull(edocImageId, "查询id不能为空");
            Invoice queryCondition = new Invoice();
            queryCondition.setEdocImageId(edocImageId);
            List<Invoice> invs = invoiceService.query(queryCondition);
            if (BeanUtil.isEmpty(invs)) {
                result.addErrorMessage("未查找到对应发票信息");
                return result;
            }
            List<InvoiceDTO> invoiceDTOS = DozerUtil.mapList(invs, InvoiceDTO.class);
            result.setResult(invoiceDTOS);
            result.setSuccessMessage("查询成功!");
        } catch (Exception e) {
            INVOICE_LOGGER.error("[根据影像id查询发票信息]失败, 原因:{}", e);
            result.addErrorMessage("查询失败!");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> deleteInvAndImagByEdocHeaderId(Long edocHeaderId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(edocHeaderId, "任务id不能为空");
            List<EdocImage> edocImages = edocImageService.selectAllImagesByEdocHeaderId(edocHeaderId);
            if (null == edocImages || edocImages.size() <= 0) {
                result.addErrorMessage("任务id:" + edocHeaderId + "下没有影像信息");
                return result;
            }
            for (EdocImage edocImage : edocImages) {
                // 循环删除影像
                edocImageService.remove(edocImage);

                // 查询影像对应发票信息,如果没有对应发票, 执行下一条
                ExecuteResult<List<InvoiceDTO>> invResult = this.queryInvsByImgId(edocImage.getId());
                if (invResult == null || invResult.getResult() == null || invResult.getResult().size() <= 0 ) {
                    continue;
                }

                // 循环删除影像对应的发票信息
                List<InvoiceDTO> invs = invResult.getResult();
                for (InvoiceDTO inv : invs) {
                    invoiceService.remove(DozerUtil.map(inv, Invoice.class));
                }
            }
            result.setSuccessMessage("根据任务id删除发票及影像信息成功");
        } catch (Exception e) {
            INVOICE_LOGGER.error("[根据任务id: {}删除发票和影像查询发票信息]失败, 原因:{}", e);
            result.addErrorMessage("查询失败!");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<InvoiceItemDTO>> editInvItemByInvItemId(InvoiceItemDTO invoiceItemDTO) {
        ExecuteResult<PageInfo<InvoiceItemDTO>> result = new ExecuteResult<>();
        try {
            Page<InvoiceItem> page = invoiceItemService.editInvItemByInvItemId(DozerUtil.map(invoiceItemDTO, InvoiceItemModel.class));
            PageInfo<InvoiceItemDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), InvoiceItemDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询发票明细信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            INVOICE_LOGGER.error("分页查询发票明细信息失败", e);
            result.addErrorMessage("分页查询发票明细信息失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<InvoiceItemDTO> editInvItemByInvItemId_one(InvoiceItemDTO invoiceItemDTO) {
        ExecuteResult<InvoiceItemDTO> result = new ExecuteResult<>();
        try {
           InvoiceItem invoiceItem = invoiceItemService.queryById(invoiceItemDTO.getId());
            InvoiceItemDTO dto = DozerUtil.map(invoiceItem, InvoiceItemDTO.class);
            result.setResult(dto);
            result.setSuccessMessage("查询发票明细信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            INVOICE_LOGGER.error("查询发票明细信息失败", e);
            result.addErrorMessage("查询发票明细信息失败");
        }
        return result;
    }

    @Override
    public  ExecuteResult<PageInfo<InvoiceDTO>> exportWaitCertInv_Part(InvoiceDTO invoiceDTO) {
        ExecuteResult<PageInfo<InvoiceDTO>> result = new ExecuteResult<>();
        try {
            Page<InvoiceModel> page = invoiceService.exportWaitCertInv_Part(DozerUtil.map(invoiceDTO,InvoiceModel.class));
            PageInfo<InvoiceDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),InvoiceDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("待认证列表分页查询成功");
        }catch (Exception e) {
            INVOICE_LOGGER.error("待认证列表分页查询异常{}", e);
            result.setErrorMessages(Arrays.asList("待认证列表分页查询异常"));
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<InvoiceDTO>> exportWaitCertInv_All(InvoiceDTO invoiceDTO) {
        ExecuteResult<PageInfo<InvoiceDTO>> result = new ExecuteResult<>();
        Date checkTimeEnd=invoiceDTO.getCheckTimeEnd();
        if(!BeanUtil.isEmpty(checkTimeEnd)) {
            invoiceDTO.setCheckTimeEnd((DateUtil.getNextDay(checkTimeEnd,1)));
        }
        if (!BeanUtil.isEmpty(invoiceDTO.getCreateTimeEnd())) {
            invoiceDTO.setCreateTimeEnd(DateUtil.getNextDay(invoiceDTO.getCreateTimeEnd(), 1));
        }
        try {
            InvoiceModel invoiceModel = DozerUtil.map(invoiceDTO, InvoiceModel.class);
            Page<InvoiceModel> page = invoiceService.exportWaitCertInv_All(invoiceModel);
            PageInfo<InvoiceDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), InvoiceDTO.class);
            result.setResult(pageInfo);
        } catch (Exception e) {
            INVOICE_LOGGER.error("待认证列表数据失败", e);
            result.addErrorMessage("待认证列表数据失败");
        }
        return result;
    }

    @Override
    public int deleteInvoiceByImageId(Long imageId) {
        int m = 0;
        try {
            m = invoiceService.deleteInvoiceByImageId(imageId);
        } catch (Exception e) {
            INVOICE_LOGGER.error("删除发票数据失败", e);
        }
        return m;
    }


    @Override
    public ExecuteResult<InvoiceDTO> checkStatusByInvId(Long invId) {
        ExecuteResult<InvoiceDTO> result = new ExecuteResult<>();
        try {
            Invoice invoiceInDB = invoiceService.queryByInvId(invId);
            if (invoiceInDB.getCheckStatus().equals(Constant.InvoiceCheckStatus.PASS)) { // 不冲突的发票不允许手工验重
                result.addErrorMessage("该发票已经校验通过，无法再次校验！");
                return result;
            }
            Invoice queryCondition = new Invoice();
            queryCondition.setInvCode(invoiceInDB.getInvCode());
            queryCondition.setInvNo(invoiceInDB.getInvNo());
            List<Invoice> repeatInvList = invoiceService.queryRepeatInvListNew(queryCondition);
            if (repeatInvList.size()<=1) { // 发票不冲突
                invoiceInDB.setCheckStatus(Constant.InvoiceCheckStatus.PASS);
                invoiceInDB.setExtField1(null);
                invoiceService.updateById(invoiceInDB);
            }else { // 冲突
                invoiceInDB.setCheckStatus(Constant.InvoiceCheckStatus.CONFLICT);
                List<String> edocNos = new ArrayList<>();
                for (Invoice i:repeatInvList) {
                    EdocImage edocImage = edocImageService.queryById(i.getEdocImageId());
                    if (null != edocImage) {
                        EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
                        if (null != edocHeader&&!edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.INVALID)) {
                            edocNos.add(edocHeader.getEdocNo());
                        }
                    }
                }
                invoiceInDB.setExtField1(edocNos.toString());
                invoiceService.updateById(invoiceInDB);
            }
            result.setSuccessMessage("手工验重成功");
            result.setResult(DozerUtil.map(invoiceInDB,InvoiceDTO.class));
        }catch (Exception e) {
            INVOICE_LOGGER.error("手工验重失败{}", e);
            result.addErrorMessage("手工验重失败");
        }
        return result;
    }
    /**
     * //查询冲突单据中包含该影像件的单据编号
     * @param edocNo
     * @return
     */
    @Override
    public ExecuteResult<List<InvoiceDTO>> queryInvsByEdocHeaderId(String edocNo) {
        ExecuteResult<List<InvoiceDTO>> result = new ExecuteResult<>();
        try {

            List<Invoice> invs = invoiceService.queryInvsByEdocHeaderId(edocNo);
            if (BeanUtil.isEmpty(invs)) {
                result.addErrorMessage("未查找到对应发票信息");
                return result;
            }
            List<InvoiceDTO> invoiceDTOS = DozerUtil.mapList(invs, InvoiceDTO.class);
            result.setResult(invoiceDTOS);
            result.setSuccessMessage("查询成功!");
        } catch (Exception e) {
            INVOICE_LOGGER.error("[根据影像单据编号查询发票信息]失败, 原因:{}", e);
            result.addErrorMessage("查询失败!");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> timeCheckReal() {
        ExecuteResult<String> result = new ExecuteResult<>();
        // 查找所有规则校验通过, 状态为待验真的发票
        List<Invoice> invs = invoiceService.queryWaitCheckRealInv();

        if (BeanUtil.isEmpty(invs)) {
            INVOICE_LOGGER.info(">>>>>>>>>>>>>>>>>>>当前不存在待验真发票");
            result.addErrorMessage("当前不存在待验真发票");
            return result;
        }

        // 验真
        for (Invoice inv : invs) {
            try {
                checkReal(inv.getId());
            } catch (Exception e) {
                result.addErrorMessage("验真失败");
                INVOICE_LOGGER.error("定时任务验真失败, 原因:{}", e);
            }
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<InvoiceDTO>> queryInvoicesByEdocNoForCheck_inv(InvoiceDTO invoiceDTO) {
        ExecuteResult<PageInfo<InvoiceDTO>> result = new ExecuteResult<>();
        try {
            InvoiceModel invoiceModel = DozerUtil.map(invoiceDTO, InvoiceModel.class);
            Page<InvoiceModel> page = invoiceService.queryInvoicesByEdocNoForCheck(invoiceModel);
            PageInfo<InvoiceDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), InvoiceDTO.class);
            List<InvoiceDTO> invoiceDTOList = pageInfo.getList();

            pageInfo.setList(invoiceDTOList);
            result.setResult(pageInfo);
        }catch (Exception e) {
            INVOICE_LOGGER.error("查询发票分页数据失败", e);
            result.addErrorMessage("查询发票分页数据失败");
        }
        return result;
    }

}
