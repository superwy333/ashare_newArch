package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.zynsun.openplatform.dto.TreeDTO;
//import com.zynsun.openplatform.dubboRestLoginFilter.annotation.Login;
import com.zynsun.openplatform.dubboRestLoginFilter.annotation.Login;
import com.zynsun.openplatform.model.TreeModel;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.*;

import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.EdocImageDTO;
import com.zynsun.platform.edoc.dto.EdocReportDTO;
import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingArchivesBorrowDTO;
import com.zynsun.platform.edoc.dto.archive.ArchivedAccountDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocImageFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.InvoiceFacade;
import com.zynsun.platform.edoc.model.*;
import com.zynsun.platform.edoc.service.*;
//import com.zynsun.platform.quartz.dto.KafkaMessageDTO;
import com.zynsun.platform.utils.EdocClientUtil;
import com.zynsun.platform.utils.EnumUtil;
import constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import utils.DownPdfUtil;
import webservice.fssc.dto.F_BODY;
import webservice.fssc.dto.F_CXPZ_LIST;
import webservice.fssc.dto.F_LIST;
import webservice.fssc.fsscutil.FsscUtil;
import webservice.jde.jdeutil.JdeUtil;
import webservice.oa.oautil.OAUtil;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.rpc.ServiceException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:06 2018/1/2
 * @Modified By:
 */
@Path("edocHeaderFacade")
@Service("edocHeaderFacade")
public class EdocHeaderFacadeImpl implements EdocHeaderFacade {

    private static final Logger BILLHEADER_LOGGER = LoggerFactory.getLogger(EdocHeaderFacadeImpl.class);

    @Autowired
    private EdocHeaderService edocHeaderService;
//    @Autowired
//    private KafkaMessageFacade kafkaMessageFacade;
//    @Autowired
//    private WfProcessFacade wProcessFacade;
 /*   @Autowired
    private DataDictCliFacade dataDictCliFacade;*/
    @Autowired
    private InvoiceFacade invoiceFacade;
    @Autowired
    private EdocImageFacade edocImageFacade;
//    @Autowired
//    private WfTaskFacade wfTaskFacade;
    @Autowired
    private AccountingSectionService accountingSectionService;
    @Autowired
    private AccountingSectionItemService accoutningSectionItemService;
//    @Autowired
//    private ExpDetailFacade expDetailFacade;
    @Autowired
    private InvoiceItemService invoiceItemService;
    @Autowired
    private EdocCredentialsService edocCredentialsService;
    @Autowired
    private EdocImageService edocImageService;
    @Autowired
    private CostTypeService costTypeService;
/*    @Autowired
    private FitCheckTaskFacade fitCheckTaskFacade;*/
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ReviewInfoService reviewInfoService;

    @Autowired
    private BizTypeService bizTypeService;
    @Autowired
    private BillTypeService billTypeService;


    @Login
    @POST
    @Path("edocAbnormalDetails")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocAbnormalDetails(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            String taskId = (String)map.get("taskId");
            EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(taskId));
            if (BeanUtil.isEmpty(edocHeader.getIsAbnormal())||"0".equals(edocHeader.getIsAbnormal())) {
                executeResult.put("code","001");
                executeResult.put("msg","此单据不是异常单据");
                executeResult.put("success",false);
                return executeResult;
            }
            String abnormalType = edocHeader.getAbnormalType();
            /**
             * 异常类型：
             * 1-单据重复
             * 2-不同单据发票重复
             * 3-单据法人税号与发票税号不一致
             * 4-发票数据识别不完整或缺失
             * 5-发票验真失败
             */
            // 目前只做1和2
            switch (abnormalType) {
                case "1":
                    String edocNo = edocHeader.getEdocNo();
                    if (edocNo.contains(Constant.EDOC_NO_FOOTER_EXCEPTION)) {
                        String[] edocNoArrays = edocNo.split("_");
                        edocNo = edocNoArrays[0];
                    }
                    EdocHeaderModel queryCondition = new EdocHeaderModel();
                    queryCondition.setEdocNo(edocNo);
                    List<EdocHeaderModel> edocHeaderList = edocHeaderService.queryEdocHeaders(queryCondition);
                    executeResult.put("code","000");
                    executeResult.put("msg","单据重复");
                    executeResult.put("success",true);
                    executeResult.put("result",edocHeaderList);
                    break;
                case "2":
                    List<Invoice> invoiceList = invoiceService.queryInvByEdocHeader(Long.valueOf(taskId));
                    List<EdocHeader> invRepeatEdocHeaderList = new ArrayList<>();
                    for (Invoice i:invoiceList) {
                        if (!BeanUtil.isEmpty(i.getCheckStatus())&&Constant.InvoiceCheckStatus.CONFLICT.equals(i.getCheckStatus())) {
                            //String[] edocNos = i.getExtField1().split(",");
                            Gson gson = new Gson();
                            List<String> edocNos = gson.fromJson(i.getExtField1(),List.class);
                            for (String e:edocNos) {
                                EdocHeader edocHeaderQueryConditon = new EdocHeader();
                                edocHeaderQueryConditon.setEdocNo(e);
                                List<EdocHeader> edocHeaderListRepeat = edocHeaderService.query(edocHeaderQueryConditon);
                                invRepeatEdocHeaderList.addAll(edocHeaderListRepeat);
                                /*if (!e.equals(edocHeader.getEdocNo())) {
                                    List<EdocHeader> edocHeaderListRepeat = edocHeaderService.query(edocHeaderQueryConditon);
                                    invRepeatEdocHeaderList.addAll(edocHeaderListRepeat);
                                }*/

                            }
                        }
                    }
                    executeResult.put("code","000");
                    executeResult.put("msg","不同单据发票重复");
                    executeResult.put("success",true);
                    executeResult.put("result",invRepeatEdocHeaderList);
                    break;
                default:
                    executeResult.put("code","001");
                    executeResult.put("msg","此单据异常类型不支持查询");
                    executeResult.put("success",false);
                    return executeResult;
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("获取异常详情失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","获取异常详情失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @Login
    @POST
    @Path("edocAbnormalCheck")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocAbnormalCheck(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        boolean checkResult = false;
        String abnormalType = null;
        try {
            String taskId = (String)map.get("taskId");
            checkResult = edocAbnormalCheck(Long.valueOf(taskId));
            if (checkResult) {
                EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(taskId));
                abnormalType = EdocHeaderDTO.AbnormalTypeEnum.parse(edocHeader.getAbnormalType()).getName();
            }
            executeResult.put("checkResult",checkResult);
            executeResult.put("abnormalType",abnormalType);
            executeResult.put("code","000");
            executeResult.put("msg","异常检查操作成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("异常检查操作失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","异常检查操作失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }


    @Override
    public boolean edocAbnormalCheck(Long edocHeaderId) {
        boolean isAbnormal = false;
        String abnormalType = null;
        try {
            /**
             * 单据异常检查：
             * 1-单据重复
             * 2-不同单据发票重复
             * 3-单据法人税号与发票税号不一致
             * 4-发票数据识别不完整或缺失
             * 5-发票验真失败
             */
            EdocHeader edocHeader = edocHeaderService.queryById(edocHeaderId);
            if (edocHeader.getEdocNo().contains(Constant.EDOC_NO_FOOTER_EXCEPTION)) { // 单据重复
                isAbnormal = true;
                abnormalType = "1";
                edocHeader.setIsAbnormal("1");
                edocHeader.setAbnormalType(abnormalType);
                edocHeaderService.updateByIdSelective(edocHeader);
                return isAbnormal;
            }else { // 其他异常
                return checkAbnormal(edocHeader);
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("单据异常检查失败{}",e);
        }
        return isAbnormal;
    }

    private void checkAbnormalDetail(EdocHeader edocHeader) throws Exception {
        List<Invoice> invoiceList = invoiceService.queryInvByEdocHeader(edocHeader.getId()); // 单据下所有发票
        if (!BeanUtil.isEmpty(invoiceList)) { //如果单据下有发票，则先进行发票方面的检查
            for (Invoice i:invoiceList) {
                // 发票规则检查
                invoiceService.invoiceCheckRule(i);
                // 发票验真
                invoiceFacade.checkReal(i.getId());
            }
        }
    }

    private boolean checkAbnormal(EdocHeader edocHeader) throws Exception {
        this.checkAbnormalDetail(edocHeader);
        List<Invoice> invoiceList = invoiceService.queryInvByEdocHeader(edocHeader.getId());
        boolean invCheckStatusPass = true; // 发票规则校验通过
        String abnormalType = null; // 1-单据重复 2-不同单据发票重复 3-单据法人税号与发票税号不一致 4-发票数据识别不完整或缺失 5-发票验真失败
        boolean invCheckRealPass = true; // 发票验真通过
        for (Invoice i:invoiceList) {
            // 检查发票规则校验结果，如果有一条发票的规则校验不通过，则break
            if (!BeanUtil.isEmpty(i.getCheckStatus())) {
                if (Constant.InvoiceCheckStatus.CONFLICT.equals(i.getCheckStatus())) {
                    invCheckStatusPass = false;
                    abnormalType = "2";
                    break;
                }
                if (Constant.InvoiceCheckStatus.NOT_PASS.equals(i.getCheckStatus())) {
                    invCheckStatusPass = false;
                    abnormalType = "4";
                    break;
                }
            }else {
                invCheckStatusPass = false;
                abnormalType = "4";
                break;
            }
        }
        if (invCheckStatusPass) { // 所有规则校验都通过则检查验真结果
            for (Invoice i:invoiceList) {
                if (!BeanUtil.isEmpty(i.getCheckDetails())) {
                    if (!"11".equals(i.getCheckDetails())) {
                        invCheckRealPass = false;
                        abnormalType = "5";
                    }
                }else {
                    invCheckRealPass = false;
                    abnormalType = "5";
                }
            }
        }
        //TODO 税号校验
        if (!invCheckRealPass||!invCheckStatusPass) {
            edocHeader.setIsAbnormal("1");
            edocHeader.setAbnormalType(abnormalType);
        }else {
            edocHeader.setIsAbnormal("0");
            edocHeader.setAbnormalType(null);
        }
        edocHeaderService.updateById(edocHeader);
        return !invCheckRealPass||!invCheckStatusPass;
    }

    @Login
    @POST
    @Path("completeAbnormal")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> completeAbnormal(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            String taskId = (String)map.get("taskId");
            EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(taskId));
            edocHeader.setIsAbnormal("0");
            edocHeader.setAbnormalType(null);
            edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM);
            edocHeaderService.updateByIdSelective(edocHeader);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("完成处理操作失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","完成处理操作失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @Login
    @POST
    @Path("edocHeaderSeal")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocHeaderSeal(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            List<String> taskIdList = (List<String>)map.get("taskId");
            String seal = (String)map.get("seal");
            for (String taskId:taskIdList) {
                EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(taskId));
                edocHeader.setSeal(seal);
                edocHeaderService.updateByIdSelective(edocHeader);
            }
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("手工封单/接单操作失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","手工封单/接单操作失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    private Map<String,Object> getBillTypeComboboxByBizCode(String BizCode) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> dataMapList = new ArrayList<>();
        List<BillType> billTypeList = billTypeService.queryBillTypesByBizCode(BizCode);
        for (BillType billType:billTypeList) {
            Map<String, String> data = new HashMap<>();
            data.put("id",billType.getCode());
            data.put("value",billType.getName());
            dataMapList.add(data);
        }
        result.put("combobox", dataMapList);
        return result;
    }

    @Login
    @POST
    @Path("edocHeaderCombobox")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocHeaderCombobox(Map<String, Object> map) {
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
                case 1: result = EnumUtil.IntEnumToMap(EdocHeaderDTO.EdocTypeEnum.class); break;
                case 2: result = EnumUtil.IntEnumToMap(EdocHeaderDTO.EdocSealEnum.class); break;
                case 3: result = EnumUtil.IntEnumToMap(EdocHeaderDTO.EdocReviewEnum.class); break;
                case 4: result = EnumUtil.IntEnumToMap(EdocHeaderDTO.EdocTaskStatusEnum.class); break;
                case 5: result = EnumUtil.IntEnumToMap(EdocHeaderDTO.EdocImageTypeEnum.class); break;
                case 6: result = EnumUtil.IntEnumToMap(EdocHeaderDTO.AbnormalTypeEnum.class); break;
                case 7: result = getBillTypeComboboxByBizCode("FY"); break;// 7-费用报销下的文件类型
                case 8: result = getBillTypeComboboxByBizCode("CG"); break; // 8-采购结算下的文件类型
                case 9: result = getBillTypeComboboxByBizCode("ZL"); break; // 9-资料存档下的文件类型
                case 10: result = getBillTypeComboboxByBizCode("HT"); break; // 10-合同存档下的业务类型
            }
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("获取下拉框数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","获取下拉框数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    //原来的接口
    @Login
    @POST
    @Path("edocHeaderDetails")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocHeaderDetails(Map<String,Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            String edocHeaderId = (String)map.get("taskId");
            Map<String,Object> result = new HashMap<>();
            List<Map<String,Object>> datagrid = new ArrayList<>();
            EdocImage queryCondition = new EdocImage();
            queryCondition.setEdocHeaderId(Long.parseLong(edocHeaderId));
            List<EdocImage> edocImageList = edocImageService.queryEdocImage(queryCondition);
            // 影像分页查看
            String imageViewByPage = LoadConfig.get("IMAGE_VIEW_BY_PAGE");
            String imageViewByPageThreshold = BeanUtil.isEmpty(LoadConfig.get("IMAGE_VIEW_BY_PAGE_THRESHOLD"))?"30":LoadConfig.get("IMAGE_VIEW_BY_PAGE_THRESHOLD");
            if (BeanUtil.isEmpty(imageViewByPage)||!"1".equals(imageViewByPage)||edocImageList.size()<Integer.valueOf(imageViewByPageThreshold)) { // 影像分页查看功能关闭
                if (!BeanUtil.isEmpty(edocImageList)) {
                    for (EdocImage e:edocImageList) {
                        //List<Invoice> json = new ArrayList<>();
                        Map<String,Object> datagridItem = new HashMap<>();
                        String imageNginx = LoadConfig.get("EDOC_IMAGE_NGINX_URL");
                        datagridItem.put("path",imageNginx + e.getImageUrl());
                        datagridItem.put("id",e.getId());
                        datagridItem.put("gatheringTime",BeanUtil.isEmpty(e.getCreateTime())?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreateTime()));
                        datagridItem.put("status","已采集");
                        datagridItem.put("docId",e.getEdocImageType());
                        datagridItem.put("docName",EdocHeaderDTO.EdocImageTypeEnum.parse(e.getEdocImageType()).getName());
                        datagridItem.put("imageFormat",e.getImageFormat());
                        if (!BeanUtil.isEmpty(e.getImageSource())) {
                            EdocImageDTO.EdocImageSourceEnum edocImageSourceEnum = EdocImageDTO.EdocImageSourceEnum.parse(e.getImageSource());
                            if (!BeanUtil.isEmpty(edocImageSourceEnum)) {
                                datagridItem.put("imageSource",edocImageSourceEnum.getName());
                            }
                        }
                        datagridItem.put("createBy",e.getCreateBy());
                        InvoiceModel invoiceQueryCondition = new InvoiceModel();
                        invoiceQueryCondition.setEdocImageId(e.getId());
                        List<InvoiceModel> invoiceList = invoiceService.queryInvoicesNoPage(invoiceQueryCondition);
                        if (!BeanUtil.isEmpty(invoiceList)) {
                            //List<InvoiceModel> invoiceModelList = DozerUtil.mapList(invoiceList,InvoiceModel.class);
                            for (InvoiceModel i:invoiceList) {
                                if (!BeanUtil.isEmpty(i.getEdocType())) {
                                    EdocHeaderDTO.EdocTypeEnum edocTypeEnum = EdocHeaderDTO.EdocTypeEnum.parse(i.getEdocType());
                                    if (!BeanUtil.isEmpty(edocTypeEnum)) {
                                        i.setEdocType(edocTypeEnum.getName());
                                    }
                                }
                                if (!BeanUtil.isEmpty(i.getCheckStatus())) {
                                    InvoiceDTO.InvoiceCheckStatusEnum invoiceCheckStatusEnum = InvoiceDTO.InvoiceCheckStatusEnum.parse(i.getCheckStatus());
                                    if (!BeanUtil.isEmpty(invoiceCheckStatusEnum)) {
                                        i.setCheckStatus(invoiceCheckStatusEnum.getName());
                                    }
                                }
                                if (!BeanUtil.isEmpty(i.getCheckDetails())) {
                                    InvoiceDTO.InvCheckDetailsEnum invCheckDetailsEnum = InvoiceDTO.InvCheckDetailsEnum.parse(i.getCheckDetails());
                                    if (!BeanUtil.isEmpty(invCheckDetailsEnum)) {
                                        i.setCheckDetails(invCheckDetailsEnum.getName());
                                    }
                                }
                                InvoiceItem invoiceItem = new InvoiceItem();
                                invoiceItem.setInvId(i.getId());
                                List<InvoiceItem> invoiceItemList = invoiceItemService.query(invoiceItem);
                                if (!BeanUtil.isEmpty(invoiceItemList)) {
                                    i.setInvoiceItemList(invoiceItemList);
                                }
                            }
                            datagridItem.put("json",invoiceList);
                        }
                        datagrid.add(datagridItem);
                    }
                }
                result.put("viewByPage",false);
                result.put("viewImagePage",null);
                result.put("viewImageLimit",null);
            }else { // 影像分页查看功能打开
                Integer viewImagePage = (Integer) map.get("viewImagePage");
                Integer viewImageLimit = (Integer) map.get("viewImageLimit");
                if (BeanUtil.isEmpty(viewImagePage)) {
                    viewImagePage = 1;
                }
                if (BeanUtil.isEmpty(viewImageLimit)) {
                    viewImageLimit = 30;

                }
                EdocImageModel edocImageModel = new EdocImageModel();
                edocImageModel.setEdocHeaderId(Long.parseLong(edocHeaderId));
                edocImageModel.setPage(viewImagePage);
                edocImageModel.setPageSize(viewImageLimit);
                Page<EdocImageModel> page = edocImageService.queryEdocImageByPage(edocImageModel);
                List<EdocImageModel> edocImageModelList = page.getResult();
                if (!BeanUtil.isEmpty(edocImageModelList)) {
                    for (EdocImageModel model:edocImageModelList) {
                        //List<Invoice> json = new ArrayList<>();
                        Map<String,Object> datagridItem = new HashMap<>();
                        String imageNginx = LoadConfig.get("EDOC_IMAGE_NGINX_URL");
                        datagridItem.put("path",imageNginx + model.getImageUrl());
                        datagridItem.put("id",model.getId());
                        datagridItem.put("gatheringTime",BeanUtil.isEmpty(model.getCreateTime())?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(model.getCreateTime()));
                        datagridItem.put("status","已采集");
                        datagridItem.put("docId",model.getEdocImageType());
                        datagridItem.put("docName",EdocHeaderDTO.EdocImageTypeEnum.parse(model.getEdocImageType()).getName());
                        datagridItem.put("imageFormat",model.getImageFormat());
                        if (!BeanUtil.isEmpty(model.getImageSource())) {
                            EdocImageDTO.EdocImageSourceEnum edocImageSourceEnum = EdocImageDTO.EdocImageSourceEnum.parse(model.getImageSource());
                            if (!BeanUtil.isEmpty(edocImageSourceEnum)) {
                                datagridItem.put("imageSource",edocImageSourceEnum.getName());
                            }
                        }
                        datagridItem.put("createBy",model.getCreateBy());
                        InvoiceModel invoiceQueryCondition = new InvoiceModel();
                        invoiceQueryCondition.setEdocImageId(model.getId());
                        List<InvoiceModel> invoiceList = invoiceService.queryInvoicesNoPage(invoiceQueryCondition);
                        if (!BeanUtil.isEmpty(invoiceList)) {
                            //List<InvoiceModel> invoiceModelList = DozerUtil.mapList(invoiceList,InvoiceModel.class);
                            for (InvoiceModel i:invoiceList) {
                                if (!BeanUtil.isEmpty(i.getEdocType())) {
                                    EdocHeaderDTO.EdocTypeEnum edocTypeEnum = EdocHeaderDTO.EdocTypeEnum.parse(i.getEdocType());
                                    if (!BeanUtil.isEmpty(edocTypeEnum)) {
                                        i.setEdocType(edocTypeEnum.getName());
                                    }
                                }
                                if (!BeanUtil.isEmpty(i.getCheckStatus())) {
                                    InvoiceDTO.InvoiceCheckStatusEnum invoiceCheckStatusEnum = InvoiceDTO.InvoiceCheckStatusEnum.parse(i.getCheckStatus());
                                    if (!BeanUtil.isEmpty(invoiceCheckStatusEnum)) {
                                        i.setCheckStatus(invoiceCheckStatusEnum.getName());
                                    }
                                }
                                if (!BeanUtil.isEmpty(i.getCheckDetails())) {
                                    InvoiceDTO.InvCheckDetailsEnum invCheckDetailsEnum = InvoiceDTO.InvCheckDetailsEnum.parse(i.getCheckDetails());
                                    if (!BeanUtil.isEmpty(invCheckDetailsEnum)) {
                                        i.setCheckDetails(invCheckDetailsEnum.getName());
                                    }
                                }
                                InvoiceItem invoiceItem = new InvoiceItem();
                                invoiceItem.setInvId(i.getId());
                                List<InvoiceItem> invoiceItemList = invoiceItemService.query(invoiceItem);
                                if (!BeanUtil.isEmpty(invoiceItemList)) {
                                    i.setInvoiceItemList(invoiceItemList);
                                }
                            }
                            datagridItem.put("json",invoiceList);
                        }
                        datagrid.add(datagridItem);
                    }
                }
                result.put("viewByPage",true);
                result.put("viewImagePage",viewImagePage);
                result.put("viewImageLimit",viewImageLimit);
            }
            result.put("datagrid",datagrid);
            result.put("totalElements",edocImageList.size());
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功");
            executeResult.put("success",true);
        }catch (Exception e) {
            executeResult.put("code","001");
            executeResult.put("msg","资料处理失败");
            executeResult.put("success",false);
            BILLHEADER_LOGGER.error("查询异常,原因{}", e);
            return executeResult;
        }
        return executeResult;
    }

    //只展示JPG格式影像
    @Login
    @POST
    @Path("edocHeaderDetails1")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocHeaderDetails1(Map<String,Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            String edocHeaderId = (String)map.get("taskId");
            Map<String,Object> result = new HashMap<>();
            List<Map<String,Object>> datagrid = new ArrayList<>();
            EdocImage queryCondition = new EdocImage();
            queryCondition.setEdocHeaderId(Long.parseLong(edocHeaderId));
            queryCondition.setImageFormat("jpg");
            List<EdocImage> edocImageList = edocImageService.queryEdocImage(queryCondition);
            // 影像分页查看
            String imageViewByPage = LoadConfig.get("IMAGE_VIEW_BY_PAGE");
            String imageViewByPageThreshold = BeanUtil.isEmpty(LoadConfig.get("IMAGE_VIEW_BY_PAGE_THRESHOLD"))?"30":LoadConfig.get("IMAGE_VIEW_BY_PAGE_THRESHOLD");
            if (BeanUtil.isEmpty(imageViewByPage)||!"1".equals(imageViewByPage)||edocImageList.size()<Integer.valueOf(imageViewByPageThreshold)) { // 影像分页查看功能关闭
                if (!BeanUtil.isEmpty(edocImageList)) {
                    for (EdocImage e:edocImageList) {
                        //List<Invoice> json = new ArrayList<>();
                        Map<String,Object> datagridItem = new HashMap<>();
                        String imageNginx = LoadConfig.get("EDOC_IMAGE_NGINX_URL");
                        datagridItem.put("path",imageNginx + e.getImageUrl());
                        datagridItem.put("id",e.getId());
                        datagridItem.put("gatheringTime",BeanUtil.isEmpty(e.getCreateTime())?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreateTime()));
                        datagridItem.put("status","已采集");
                        datagridItem.put("docId",e.getEdocImageType());
                        datagridItem.put("docName",EdocHeaderDTO.EdocImageTypeEnum.parse(e.getEdocImageType()).getName());
                        datagridItem.put("imageFormat",e.getImageFormat());
                        if (!BeanUtil.isEmpty(e.getImageSource())) {
                            EdocImageDTO.EdocImageSourceEnum edocImageSourceEnum = EdocImageDTO.EdocImageSourceEnum.parse(e.getImageSource());
                            if (!BeanUtil.isEmpty(edocImageSourceEnum)) {
                                datagridItem.put("imageSource",edocImageSourceEnum.getName());
                            }
                        }
                        datagridItem.put("createBy",e.getCreateBy());
                        InvoiceModel invoiceQueryCondition = new InvoiceModel();
                        invoiceQueryCondition.setEdocImageId(e.getId());
                        List<InvoiceModel> invoiceList = invoiceService.queryInvoicesNoPage(invoiceQueryCondition);
                        if (!BeanUtil.isEmpty(invoiceList)) {
                            //List<InvoiceModel> invoiceModelList = DozerUtil.mapList(invoiceList,InvoiceModel.class);
                            for (InvoiceModel i:invoiceList) {
                                if (!BeanUtil.isEmpty(i.getEdocType())) {
                                    EdocHeaderDTO.EdocTypeEnum edocTypeEnum = EdocHeaderDTO.EdocTypeEnum.parse(i.getEdocType());
                                    if (!BeanUtil.isEmpty(edocTypeEnum)) {
                                        i.setEdocType(edocTypeEnum.getName());
                                    }
                                }
                                if (!BeanUtil.isEmpty(i.getCheckStatus())) {
                                    InvoiceDTO.InvoiceCheckStatusEnum invoiceCheckStatusEnum = InvoiceDTO.InvoiceCheckStatusEnum.parse(i.getCheckStatus());
                                    if (!BeanUtil.isEmpty(invoiceCheckStatusEnum)) {
                                        i.setCheckStatus(invoiceCheckStatusEnum.getName());
                                    }
                                }
                                if (!BeanUtil.isEmpty(i.getCheckDetails())) {
                                    InvoiceDTO.InvCheckDetailsEnum invCheckDetailsEnum = InvoiceDTO.InvCheckDetailsEnum.parse(i.getCheckDetails());
                                    if (!BeanUtil.isEmpty(invCheckDetailsEnum)) {
                                        i.setCheckDetails(invCheckDetailsEnum.getName());
                                    }
                                }
                                InvoiceItem invoiceItem = new InvoiceItem();
                                invoiceItem.setInvId(i.getId());
                                List<InvoiceItem> invoiceItemList = invoiceItemService.query(invoiceItem);
                                if (!BeanUtil.isEmpty(invoiceItemList)) {
                                    i.setInvoiceItemList(invoiceItemList);
                                }
                            }
                            datagridItem.put("json",invoiceList);
                        }
                        datagrid.add(datagridItem);
                    }
                }
                result.put("viewByPage",false);
                result.put("viewImagePage",null);
                result.put("viewImageLimit",null);
            }else { // 影像分页查看功能打开
                Integer viewImagePage = (Integer) map.get("viewImagePage");
                Integer viewImageLimit = (Integer) map.get("viewImageLimit");
                if (BeanUtil.isEmpty(viewImagePage)) {
                    viewImagePage = 1;
                }
                if (BeanUtil.isEmpty(viewImageLimit)) {
                    viewImageLimit = 30;

                }
                EdocImageModel edocImageModel = new EdocImageModel();
                edocImageModel.setEdocHeaderId(Long.parseLong(edocHeaderId));
                edocImageModel.setPage(viewImagePage);
                edocImageModel.setPageSize(viewImageLimit);
                queryCondition.setImageFormat("jpg");
                Page<EdocImageModel> page = edocImageService.queryEdocImageByPage(edocImageModel);
                List<EdocImageModel> edocImageModelList = page.getResult();
                if (!BeanUtil.isEmpty(edocImageModelList)) {
                    for (EdocImageModel model:edocImageModelList) {
                        //List<Invoice> json = new ArrayList<>();
                        Map<String,Object> datagridItem = new HashMap<>();
                        String imageNginx = LoadConfig.get("EDOC_IMAGE_NGINX_URL");
                        datagridItem.put("path",imageNginx + model.getImageUrl());
                        datagridItem.put("id",model.getId());
                        datagridItem.put("gatheringTime",BeanUtil.isEmpty(model.getCreateTime())?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(model.getCreateTime()));
                        datagridItem.put("status","已采集");
                        datagridItem.put("docId",model.getEdocImageType());
                        datagridItem.put("docName",EdocHeaderDTO.EdocImageTypeEnum.parse(model.getEdocImageType()).getName());
                        datagridItem.put("imageFormat",model.getImageFormat());
                        if (!BeanUtil.isEmpty(model.getImageSource())) {
                            EdocImageDTO.EdocImageSourceEnum edocImageSourceEnum = EdocImageDTO.EdocImageSourceEnum.parse(model.getImageSource());
                            if (!BeanUtil.isEmpty(edocImageSourceEnum)) {
                                datagridItem.put("imageSource",edocImageSourceEnum.getName());
                            }
                        }
                        datagridItem.put("createBy",model.getCreateBy());
                        InvoiceModel invoiceQueryCondition = new InvoiceModel();
                        invoiceQueryCondition.setEdocImageId(model.getId());
                        List<InvoiceModel> invoiceList = invoiceService.queryInvoicesNoPage(invoiceQueryCondition);
                        if (!BeanUtil.isEmpty(invoiceList)) {
                            //List<InvoiceModel> invoiceModelList = DozerUtil.mapList(invoiceList,InvoiceModel.class);
                            for (InvoiceModel i:invoiceList) {
                                if (!BeanUtil.isEmpty(i.getEdocType())) {
                                    EdocHeaderDTO.EdocTypeEnum edocTypeEnum = EdocHeaderDTO.EdocTypeEnum.parse(i.getEdocType());
                                    if (!BeanUtil.isEmpty(edocTypeEnum)) {
                                        i.setEdocType(edocTypeEnum.getName());
                                    }
                                }
                                if (!BeanUtil.isEmpty(i.getCheckStatus())) {
                                    InvoiceDTO.InvoiceCheckStatusEnum invoiceCheckStatusEnum = InvoiceDTO.InvoiceCheckStatusEnum.parse(i.getCheckStatus());
                                    if (!BeanUtil.isEmpty(invoiceCheckStatusEnum)) {
                                        i.setCheckStatus(invoiceCheckStatusEnum.getName());
                                    }
                                }
                                if (!BeanUtil.isEmpty(i.getCheckDetails())) {
                                    InvoiceDTO.InvCheckDetailsEnum invCheckDetailsEnum = InvoiceDTO.InvCheckDetailsEnum.parse(i.getCheckDetails());
                                    if (!BeanUtil.isEmpty(invCheckDetailsEnum)) {
                                        i.setCheckDetails(invCheckDetailsEnum.getName());
                                    }
                                }
                                InvoiceItem invoiceItem = new InvoiceItem();
                                invoiceItem.setInvId(i.getId());
                                List<InvoiceItem> invoiceItemList = invoiceItemService.query(invoiceItem);
                                if (!BeanUtil.isEmpty(invoiceItemList)) {
                                    i.setInvoiceItemList(invoiceItemList);
                                }
                            }
                            datagridItem.put("json",invoiceList);
                        }
                        datagrid.add(datagridItem);
                    }
                }
                result.put("viewByPage",true);
                result.put("viewImagePage",viewImagePage);
                result.put("viewImageLimit",viewImageLimit);
            }
            result.put("datagrid",datagrid);
            result.put("totalElements",edocImageList.size());
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功");
            executeResult.put("success",true);
        }catch (Exception e) {
            executeResult.put("code","001");
            executeResult.put("msg","资料处理失败");
            executeResult.put("success",false);
            BILLHEADER_LOGGER.error("查询异常,原因{}", e);
            return executeResult;
        }
        return executeResult;
    }


    @Login
    @POST
    @Path("removeEdocHeader")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    @Transactional
    public Map<String, Object> removeEdocHeader(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            String edocHeaderIdStr = (String)map.get("taskId");
            EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(edocHeaderIdStr));
            if (Constant.Seal.SEALED.equals(edocHeader.getSeal())) {
                executeResult.put("code","001");
                executeResult.put("msg","任务已封单！");
                executeResult.put("success",false);
                return executeResult;
            }
            EdocImage edocImage = new EdocImage();
            edocImage.setEdocHeaderId(edocHeader.getId());
            List<EdocImage> edocImageList = edocImageService.queryEdocImage(edocImage);
            if (!BeanUtil.isEmpty(edocImageList)) {
                for (EdocImage e:edocImageList) {
                    edocImageService.remove(e); // 删除影像
                    Invoice invoice = new Invoice();
                    invoice.setEdocImageId(e.getId());
                    List<Invoice> invoiceList = invoiceService.query(invoice);
                    if (!BeanUtil.isEmpty(invoiceList)) {
                        for (Invoice i:invoiceList) {
                            invoiceService.remove(i); // 删除发票
                        }
                    }
                }
            }
            edocHeaderService.remove(edocHeader); // 删除单据
            //executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("删除任务失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","删除任务失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @Login
    @POST
    @Path("edocHeaderDataGrid")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocHeaderDataGrid(Map<String, Object> map) {
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

            EdocHeaderModel edocHeaderModel = new EdocHeaderModel();
            edocHeaderModel.setPage(inputPage);
            edocHeaderModel.setPageSize(rows);
            edocHeaderModel.setEdocNo(BeanUtil.isEmpty(map.get("edocNo"))?null:(String) map.get("edocNo"));
            edocHeaderModel.setCompanyName(BeanUtil.isEmpty(map.get("companyName"))?null:(String) map.get("companyName"));
            edocHeaderModel.setCreateBy(BeanUtil.isEmpty(map.get("createBy"))?null:(String) map.get("createBy"));
            edocHeaderModel.setEdocType(BeanUtil.isEmpty(map.get("edocType"))?null:(String) map.get("edocType"));
            edocHeaderModel.setEdocTaskStatus(BeanUtil.isEmpty(map.get("edocTaskStatus"))?null:(String) map.get("edocTaskStatus"));
            edocHeaderModel.setSeal(BeanUtil.isEmpty(map.get("seal"))?null:(String) map.get("seal"));
            edocHeaderModel.setReview(BeanUtil.isEmpty(map.get("review"))?null:(String) map.get("review"));
            edocHeaderModel.setEdocNo(BeanUtil.isEmpty(map.get("edocNo"))?null:(String) map.get("edocNo"));
            edocHeaderModel.setIsAbnormal(BeanUtil.isEmpty(map.get("isAbnormal"))?null:(String) map.get("isAbnormal"));

            List<String> createTime = (List<String>)map.get("createTime");
            if (!BeanUtil.isEmpty(createTime)) {
                edocHeaderModel.setQueryTimeStart(BeanUtil.isEmpty(createTime.get(0))?null:createTime.get(0));
                edocHeaderModel.setQueryTimeEnd(BeanUtil.isEmpty(createTime.get(1))?null:createTime.get(1));
            }

            Page<EdocHeaderModel> page = edocHeaderService.queryEdocHeaderPage(edocHeaderModel);
            if (!BeanUtil.isEmpty(page.getResult())) {
                List<EdocHeaderModel> edocHeaderModelList = page.getResult();
                for (EdocHeaderModel e:edocHeaderModelList) {
                    if (!BeanUtil.isEmpty(e.getEdocType())) {
                        EdocHeaderDTO.EdocTypeEnum edocTypeEnum = EdocHeaderDTO.EdocTypeEnum.parse(e.getEdocType());
                        if (!BeanUtil.isEmpty(edocTypeEnum)) {
                            e.setEdocType(edocTypeEnum.getName());
                        }
                    }
                    if (!BeanUtil.isEmpty(e.getEdocTaskStatus())) {
                        EdocHeaderDTO.EdocTaskStatusEnum edocTaskStatusEnum = EdocHeaderDTO.EdocTaskStatusEnum.parse(e.getEdocTaskStatus());
                        if (!BeanUtil.isEmpty(edocTaskStatusEnum)) {
                            e.setEdocTaskStatus(edocTaskStatusEnum.getName());
                        }
                    }
                    if (!BeanUtil.isEmpty(e.getSeal())) {
                        EdocHeaderDTO.EdocSealEnum edocSealEnum = EdocHeaderDTO.EdocSealEnum.parse(e.getSeal());
                        if (!BeanUtil.isEmpty(edocSealEnum)) {
                            e.setSeal(edocSealEnum.getName());
                        }
                    }
                    if (!BeanUtil.isEmpty(e.getReview())) {
                        EdocHeaderDTO.EdocReviewEnum edocReviewEnum = EdocHeaderDTO.EdocReviewEnum.parse(e.getReview());
                        if (!BeanUtil.isEmpty(edocReviewEnum)) {
                            e.setReview(edocReviewEnum.getName());
                        }
                    }
                    if (!BeanUtil.isEmpty(e.getCreateTime())) {
                        edocHeaderModel.setCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreateTime()));
                    }
                    if (!BeanUtil.isEmpty(e.getAbnormalType())) {
                        EdocHeaderDTO.AbnormalTypeEnum abnormalTypeEnum = EdocHeaderDTO.AbnormalTypeEnum.parse(e.getAbnormalType());
                        if (!BeanUtil.isEmpty(abnormalTypeEnum)) {
                            e.setAbnormalType(abnormalTypeEnum.getName());
                        }
                    }
                }
            }
            Map<String,Object> result = new HashMap<>();
            result.put("dataGrid",page.getResult());
            edocHeaderModel.setPageSize(null);
            edocHeaderModel.setPage(null);
            List<EdocHeaderModel> edocHeaderListTotal = edocHeaderService.queryEdocHeaders(edocHeaderModel);
            result.put("totalRows",BeanUtil.isEmpty(edocHeaderListTotal)?0:edocHeaderListTotal.size());
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("查询任务分页数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","查询发票分页数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> save(EdocHeaderDTO edocHeaderDTO) {
        return null;
    }

    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> dataGrid(EdocHeaderDTO headerDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
            Date createTimeEnd=headerDTO.getCreateTimeEnd();
            if(!BeanUtil.isEmpty(createTimeEnd)) {
                headerDTO.setCreateTimeEnd((DateUtil.getNextDay(createTimeEnd,1)));
            }

            EdocHeaderModel headerModel = DozerUtil.map(headerDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> page = edocHeaderService.queryEdocHeaderPage(headerModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), EdocHeaderDTO.class);
            result.setSuccessMessage("分页查询成功");
            result.setResult(pageInfo);
        } catch (Exception e) {
            result.addErrorMessage("分页查询失败");
            BILLHEADER_LOGGER.error("查询异常,原因{}", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> selectEdocHeaderById(Long id) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        try {
            EdocHeader edocHeader = edocHeaderService.queryById(id);
            if (edocHeader != null) {
                result.setResult(DozerUtil.map(edocHeader, EdocHeaderDTO.class));
                result.setSuccessMessage("查询成功");
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> updateEdocPhysicalStatus(String billNo,Integer billStatus) {
        ExecuteResult<String> result = new ExecuteResult<>();
        int i=0;
        EdocHeader edocHeader = new EdocHeader();
        edocHeader.setEdocNo(billNo);
        EdocHeader edocHeaders =edocHeaderService.queryOne(edocHeader);
        i = edocHeaderService.updatePhysicalStatus(edocHeaders,billStatus);
        if(i>0){
            result.setResult("同步邮包状态成功");
            result.setSuccessMessage("同步邮包状态成功");
        }else {
            result.setResult("同步邮包状态失败");
            result.addErrorMessage("同步邮包状态失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> queryEdocHeaderByCondition(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        try {
            if (BeanUtil.isEmpty(edocHeaderDTO)) {
                result.addErrorMessage("查询条件不能为空");
                return result;
            }
            EdocHeader edocHeader = edocHeaderService.queryOne(DozerUtil.map(edocHeaderDTO, EdocHeader.class));
            if (edocHeader != null) {
                result.setResult(DozerUtil.map(edocHeader, EdocHeaderDTO.class));
                result.setSuccessMessage("查询成功");
            } else {
                result.addErrorMessage("没有相关记录");
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> delEdocHeaderByCondition(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            if (BeanUtil.isEmpty(edocHeaderDTO)) {
                result.addErrorMessage("删除条件不能为空");
                return result;
            }

            result.setResult(edocHeaderService.remove(DozerUtil.map(edocHeaderDTO,EdocHeader.class)));
        }catch (Exception e){
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> selectEdocHeaderByBusinessKey(String businessKey) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        try{
            EdocHeader edocHeader =  edocHeaderService.queryEdocHeaderByBusinessKey(businessKey);
            if(edocHeader!=null){
                result.setResult(DozerUtil.map(edocHeader,EdocHeaderDTO.class));
                result.setSuccessMessage("查询成功");
            }
        }catch (Exception e){
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> queryDoneWorkList(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try{
            /*edocHeaderDTO.setTaskAssingee(SubjectUtil.getUser().getLoginName());*/
            edocHeaderDTO.setTenantId("AP");
            EdocHeaderModel edocHeaderModel = DozerUtil.map(edocHeaderDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> headerModelPage = edocHeaderService.queryDoneWorkList(edocHeaderModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(headerModelPage.toPageInfo(),EdocHeaderDTO.class);
            result.setResult(pageInfo);
        }catch (Exception e){
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> queryToBeWorkList(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try{
            /*if(StringUtils.isBlank(edocHeaderDTO.getTaskAssingee())){
                edocHeaderDTO.setTaskAssingee(SubjectUtil.getUser().getLoginName());
            }*/
            edocHeaderDTO.setTenantId("AP");
            EdocHeaderModel edocHeaderModel = DozerUtil.map(edocHeaderDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> headerModelPage = edocHeaderService.queryToBeWorkList(edocHeaderModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(headerModelPage.toPageInfo(),EdocHeaderDTO.class);
            result.setResult(pageInfo);
        }catch (Exception e){
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> queryAllWorkList(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try{
            edocHeaderDTO.setTenantId("AP");
            EdocHeaderModel edocHeaderModel = DozerUtil.map(edocHeaderDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> headerModelPage = edocHeaderService.queryAllWorkList(edocHeaderModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(headerModelPage.toPageInfo(),EdocHeaderDTO.class);
            result.setResult(pageInfo);
        }catch (Exception e){
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    //@Transactional(rollbackFor = Exception.class)
    @Override
    public ExecuteResult<EdocHeaderDTO> edocClientSaveEdocHeader(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        if(BeanUtil.isEmpty(edocHeaderDTO)){
            result.addErrorMessage("影像任务不能为空");
            return result;
        }
        EdocHeader edocHeader = DozerUtil.map(edocHeaderDTO,EdocHeader.class);
        int count=0;
        //判断是否启动流程
        if(edocHeaderDTO.isStartProcessFlag()){
            //创建影像任务并发送启动流程的消息到消息队列（适用高并发启动流程场景）,基于可靠消息最终一致性实现
            //0.启动方法事务注解,确保消息确认不成功导致业务数据保存成功，消息确认失败造成不一致性
            //1.调用消息facade增加待确认消息到数据库
            //生成msgId
            String msgId= UUID.randomUUID().toString();
//            KafkaMessageDTO kafkaMessageDTO = new KafkaMessageDTO();
//            //设置msgId
//            kafkaMessageDTO.setMsgId(msgId);
//            kafkaMessageDTO.setMessageTopic("edoc-workflow-topic-123456");
//            kafkaMessageDTO.setMessageBody(JSON.toJSONString(edocHeader));
            try{
                /*kafkaMessageDTO = kafkaMessageFacade.saveKafkaMessage(kafkaMessageDTO).getResult();*/
                //2.保存业务数据
                count = edocHeaderService.add(edocHeader);

                /*if (edocHeader.getEdocType().equals(Constant.BizTypeCode.ACCOUNT_TYPE_CODE)) {  //只有报账单才启动流程
                    ExecuteResult<SysConfDictItemDTO> wfAccount = dataDictCliFacade.findByEnNameAndItemCode("EDOC_WORKFLOW", edocHeader.getEdocType());
                    try {
                        wProcessFacade.startProcess(wfAccount.getResult().getDicItemValue(), String.valueOf(edocHeader.getId()));
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/

            }catch (Exception e){
                BILLHEADER_LOGGER.error("客户端创建影像任务失败-{}", e);
                result.addErrorMessage("客户端创建影像任务失败");
            }
            //3.修改传递信息状态和主体并推送信息（不能放在try catch中，防止消息发送失败造成业务处理数据不能回滚）
            //先注释掉kafak消息发送，解决上传问题
            /*kafkaMessageDTO.setMessageBody(JSON.toJSONString(edocHeader));
            kafkaMessageFacade.updateAndSendKafkaMessage(kafkaMessageDTO);*/

        }else{
            try{
                count = edocHeaderService.add(edocHeader);
            }catch (Exception e){
                BILLHEADER_LOGGER.error("客户端创建影像任务失败", e);
                result.addErrorMessage("客户端创建影像任务失败");
            }

        }

        if(count<=0){
            result.addErrorMessage("客户端创建影像任务失败");
            return result;
        }
        result.setResult(DozerUtil.map(edocHeader,EdocHeaderDTO.class));
        return result;
    }

//    /**
//     * 确认报账单创建方法
//     * @param edocHeaderDTO
//     */
//    public void confirmClientSaveEdocHeader(EdocHeaderDTO edocHeaderDTO){
//        System.out.println("客户端创建影像任务成功");
//    }
//
//    /**
//     * 确认报账单删除方法
//     * @param edocHeaderDTO
//     */
//    public void cancelClientSaveEdocHeader(EdocHeaderDTO edocHeaderDTO){
//        EdocHeader edocHeader = edocHeaderService.queryEdocHeaderByEdocNo(edocHeaderDTO.getEdocNo(),edocHeaderDTO.getEdocType());
//        if(BeanUtil.isEmpty(edocHeader)){
//            edocHeaderService.remove(edocHeader);
//        }
//    }

    private void completeReviewInfo(Long edocHeaderId) {
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setEdocHeaderId(edocHeaderId.toString());
        reviewInfo.setIsProcessed("0");
        List<ReviewInfo> reviewInfoList = reviewInfoService.query(reviewInfo);
        if (!BeanUtil.isEmpty(reviewInfoList)) {
            ReviewInfo reviewInfoInDB = reviewInfoList.get(0);
            reviewInfoInDB.setIsProcessed("1");
            reviewInfoService.updateByIdSelective(reviewInfoInDB);
        }
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> beforeUploadCheck(String edocNo, String scanFlag, String categoryCode) {
        ExecuteResult<EdocHeaderDTO> executeResult = new ExecuteResult<>(); // 结果对象
        EdocHeader edocHeader = new EdocHeader();
        edocHeader.setEdocType(categoryCode);
        //edocHeader.setScanFlag(scanFlag);
        // 根据单证编号以及业务代码查找影像任务
        List<EdocHeader> headers = edocHeaderService.findDataListByEdocNoAndCategoryCode(edocNo, categoryCode);
        // 如果存在影像任务, 对任务状态进行校验
        if (null != headers && headers.size() > 0) {
            edocHeader = headers.get(0); // 赋值
            switch (edocHeader.getEdocTaskStatus()) {
                case Constant.BizStatus.ON_UP_DATA :        // 上传中不需要创建影像任务
                    break;
                case Constant.BizStatus.AGAIN_SCAN :        // 重扫不需要创建影像任务
                    this.imgIsReplace(edocHeader);     // 是单票重扫 还是全部重扫
                    //this.completeRunTask(edocHeader);  // 重扫说明已经调用E7接口，并启动了流程，所以要完成流程任务
                    completeReviewInfo(edocHeader.getId());
                    break;
                case Constant.BizStatus.SINGLE_AGAIN_SCAN :        // 重扫不需要创建影像任务
                    this.imgIsReplace(edocHeader);     // 是单票重扫 还是全部重扫
                    //this.completeRunTask(edocHeader);  // 重扫说明已经调用E7接口，并启动了流程，所以要完成流程任务
                    break;
                case Constant.BizStatus.INVALID :           // 作废, 创建新影像任务, 标记为上传中
                    edocHeader = new EdocHeader();
                    edocHeader.setEdocNo(edocNo);
                    break;
                case Constant.BizStatus.WAIT_ADD_DATA :           //补扫，不需要创建影像任务
                    //edocHeader.setScanFlag(scanFlag);
                    edocHeaderService.updateByIdSelective(edocHeader);
                    completeReviewInfo(edocHeader.getId());
                    break;
                case Constant.BizStatus.ARCHIVE_WAIT_ADD_DATA:           //档案岗补扫，不需要创建影像任务
                    //edocHeader.setScanFlag(scanFlag);
                    edocHeaderService.updateByIdSelective(edocHeader);
                    break;
                case Constant.BizStatus.WAIT_SCAN:                //待采集状态为接口建单
                    //edocHeader.setScanFlag(scanFlag);
                    edocHeader.setEdocTaskStatus(Constant.BizStatus.ON_UP_DATA);
                    edocHeaderService.updateByIdSelective(edocHeader);
                    break;



                //  TODO 其他情况根据具体业务增加分支
                default:                                     // 说明重复上传了, 标记为异常任务
                    edocHeader = new EdocHeader();
                    String exceptionEdocNo = edocNo + "_" + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + "_" + Constant.EDOC_NO_FOOTER_EXCEPTION;
                    edocHeader.setEdocNo(exceptionEdocNo);
                    //edocHeader.setScanFlag(scanFlag);
            }
        } else {
            // 不存在影像任务, 新建
            edocHeader.setEdocNo(edocNo);
        }
        executeResult.setResult(DozerUtil.map(edocHeader, EdocHeaderDTO.class));
        return executeResult;
    }

    // 是单票重扫 还是全部重扫
    public void imgIsReplace(EdocHeader edocHeader){
         /*
         //TODO
            如果是重扫分2中情况：1、全部重扫，那就逻辑删除所有img和发票，2、单票重扫。只逻辑删除isRpleace为1的单票
            先查询所有单票，看是否  isRpleace 都为0 如果为0 就全部重扫，否则就是单票重扫，此处逻辑代码还未加
         */
 /*       EdocImageDTO edocImageDTO = new EdocImageDTO();
        edocImageDTO.setEdocHeaderId(edocHeader.getId());
        edocImageDTO.setIsReplace("1");
        edocImageDTO.setDeleted(0);
        ExecuteResult<List<EdocImageDTO>> listExecuteResult = edocImageFacade.selectImages(edocImageDTO);*/
        if(Constant.BizStatus.SINGLE_AGAIN_SCAN.equals(edocHeader.getEdocTaskStatus())){
            // this.deleteSingImg(listExecuteResult.getResult());
        }else if(Constant.BizStatus.AGAIN_SCAN.equals(edocHeader.getEdocTaskStatus())){
            // 就是全部扫描
            this.deleteAllImg(edocHeader.getEdocNo());
        }
    }

    //删除单据下的影像和发票
    public void deleteAllImg(String edocNo){
        EdocHeader edocHeader1 = new EdocHeader();
        edocHeader1.setEdocNo(edocNo);
        EdocHeader edocHeaderInDB = edocHeaderService.findValidEdocHeader(edocHeader1).get(0);
        List<EdocImageDTO> edocImageDTOList = edocImageFacade.selectImagesByEdocHeaderId(edocHeaderInDB.getId(),0).getResult();
        if (edocImageDTOList.size() >= 1) {
            for (EdocImageDTO e : edocImageDTOList) {
                edocImageFacade.deleteImage(e.getId());
                InvoiceDTO invoiceIndb = invoiceFacade.findInvoiceByImgId(e.getId()).getResult();
                if (!BeanUtil.isEmpty(invoiceIndb)&&!BeanUtil.isEmpty(invoiceIndb.getId())) {
                    invoiceFacade.removeInv(invoiceIndb.getId());
                    InvoiceItem queryCondition = new InvoiceItem();
                    queryCondition.setInvId(invoiceIndb.getId());
                    List<InvoiceItem> invoiceItemList = invoiceItemService.query(queryCondition);
                    if (!BeanUtil.isEmpty(invoiceItemList)) {
                        for (InvoiceItem i:invoiceItemList) {
                            invoiceFacade.removeInvItem(i.getId());
                        }
                    }
                }
            }
        }
    }

    //只删除标记重扫的img 和 发票
    public void deleteSingImg(List<EdocImageDTO> edocImageDTOs){
        for(EdocImageDTO imageDTO : edocImageDTOs ){
            imageDTO.setDeleted(1); // 逻辑删除
            edocImageFacade.updateImages(imageDTO);
        }
    }

    // 重扫说明已经调用E7接口，并启动了流程，所以要完成流程任务
    public void completeRunTask(EdocHeader edocHeader){
        // 清空备注
        if(StringUtils.isNotBlank(edocHeader.getRemarks())){
            edocHeader.setRemarks("");
            this.updateEdocHeader(DozerUtil.map(edocHeader , EdocHeaderDTO.class ));
        }
        EdocHeaderModel edocHeaderModel = edocHeaderService.selectTaskByEdocId(edocHeader);
//        wfTaskFacade.completeTask(edocHeaderModel.getId()+"" , edocHeaderModel.getTaskId() , edocHeaderModel.getTaskKey() , null);
    }

    @Override
    public List<EdocHeaderDTO> findEdocHeaderList(EdocHeaderDTO edocHeaderDTO){
        List<EdocHeader> query = edocHeaderService.query(DozerUtil.map(edocHeaderDTO, EdocHeader.class));
        return DozerUtil.mapList(query,EdocHeaderDTO.class);
    }

    @Override
    public List<EdocHeaderDTO> selectEdocHeaderList(EdocHeaderDTO edocHeaderDTO){
        List<EdocHeaderDTO> edocHeaderDTOS = edocHeaderService.selectEdocHeaderList(edocHeaderDTO);
        return edocHeaderDTOS;
    }
    @Override
    public EdocHeaderDTO queryById1(Long billHeadId){
        EdocHeader edocHeader = edocHeaderService.queryById(billHeadId);
        return DozerUtil.map(edocHeader,EdocHeaderDTO.class);
    }

    /**
     * @Description：根据id查询单证类型信息
     * @Author：FeiyueYang
     * @Param：id
     * @Date：2017/6/3 0003 下午 8:10
     */
    @Override
    public ExecuteResult<EdocHeaderDTO> findEdocHeaderById(Long id) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<EdocHeaderDTO>();
        try {
            Assert.notNull(id, "id不能为空");
            EdocHeader edocHeader = edocHeaderService.queryById(id);
            // 根据EdocheaderID 查询taskID
            EdocHeaderDTO edocHeaderDTO = DozerUtil.map(edocHeader, EdocHeaderDTO.class);
            EdocHeaderModel edocHeaderModel = edocHeaderService.selectTaskByEdocId(edocHeader);
            edocHeaderDTO.setTaskId(edocHeaderModel.getTaskId()); // 工作流任务ID
            result.setSuccessMessage("查询成功");
            BILLHEADER_LOGGER.info("查询成功");
            result.setResult(edocHeaderDTO);
            return result;
        }catch (Exception e){
            result.addErrorMessage("查询出错");
            BILLHEADER_LOGGER.error("查询出错", e);
            return result;
        }
    }

    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> findEdocHeaderPage(EdocHeaderDTO headerDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
            Date createTimeEnd=headerDTO.getCreateTimeEnd();
            if(!BeanUtil.isEmpty(createTimeEnd)) {
                headerDTO.setCreateTimeEnd((DateUtil.getNextDay(createTimeEnd,1)));
            }

            EdocHeaderModel headerModel = DozerUtil.map(headerDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> page = edocHeaderService.queryEdocHeaderPage(headerModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), EdocHeaderDTO.class);
            result.setSuccessMessage("分页查询成功");
            result.setResult(pageInfo);
        } catch (Exception e) {
            result.addErrorMessage("分页查询失败");
            BILLHEADER_LOGGER.error("查询异常,原因{}", e);
            return result;
        }
        return result;
    }




    @Override
    public String findEdocHeaderPageTw(EdocHeaderDTO headerDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        JSONObject resultObject = null;
        Date createTimeEnd=headerDTO.getCreateTimeEnd();
        if(!BeanUtil.isEmpty(createTimeEnd)) {
            headerDTO.setCreateTimeEnd((DateUtil.getNextDay(createTimeEnd,1)));
        }
        try {
            EdocHeaderModel headerModel = DozerUtil.map(headerDTO, EdocHeaderModel.class);
            //Page<EdocHeaderModel> page = edocHeaderService.queryEdocHeaderPage(headerModel);
            Page<EdocHeaderModel> page = edocHeaderService.queryEdocHeaderPageTw(headerModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), EdocHeaderDTO.class);
            result.setSuccessMessage("分页查询成功");
            result.setResult(pageInfo);
            resultObject = this.jsonFactory(result, pageInfo.getList().get(0));
        } catch (Exception e) {
            result.addErrorMessage("分页查询失败");
            BILLHEADER_LOGGER.error("查询异常,原因{}", e);
            return JSON.toJSONString(result);
        }
        return resultObject == null ? "" : resultObject.toString();
    }

    /**
     * 最终json 格式：
     * {"result":{"lastPage":1,"navigatepageNums":[1],"startRow":1,"hasNextPage":false,"prePage":0,"nextPage":0,"endRow":1,"pageSize":10,
     * "list":[{"lastModifyBy":"test001","edocType":"01","edocTaskStatus":"00","appli_money":"100","pageSize":10,"appli_user":"李四","rows":10,
     * "version":1,"edocIsMatched":"0","startProcessFlag":false,"appli_company ":"立刻","createBy":"test001","deleted":0,"jsonData":"
     * {\"appli_user\":\"李四\",\"appli_money\":100,\"appli_company \":\"立刻\"}","createTime":"2018-05-29","lastModifyTime":"2018-05-28","edocNo":
     * "2018/5/28","id":7,"page":1}],"pageNum":1,"navigatePages":8,"total":1,"pages":1,"firstPage":1,"size":1,"isLastPage":true,"hasPreviousPage":false,"
     * isFirstPage":true},"errorMessages":[],"warningMessages":[],"fieldErrors":{},"e:true,"successMessage":"分页查询成功"}
     *
     */
    public JSONObject jsonFactory( ExecuteResult<PageInfo<EdocHeaderDTO>> result , EdocHeaderDTO headerDTO ){
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject resultObject = null;
        try {
            String resultStr = objectMapper.writeValueAsString(result);
            resultObject = JSON.parseObject(resultStr);
            JSONObject result1 = resultObject.getJSONObject("result");
            JSONArray list = result1.getJSONArray("rows");
            // 循环处理row 并给所有row 添加字段属性 以便页面动态展示
            Iterator<Object> iterator = list.iterator();
            while(iterator.hasNext()){
                JSONObject edocHeaderDTO = (JSONObject)iterator.next();
                JSONObject jsonDataObj = JSON.parseObject(headerDTO.getJsonData());
                Set<String> set = jsonDataObj.keySet();
                for(String jsonKey : set){
                    edocHeaderDTO.put(jsonKey , jsonDataObj.getString(jsonKey));
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    @Override
    public String findFiledJsonData(Long edocHeaderId) {
        try{
            EdocHeader edocHeader = edocHeaderService.queryById(edocHeaderId);
            if(StringUtils.isNotBlank(edocHeader.getJsonData())){
                return edocHeader.getJsonData();
            }
        }catch (Exception e){
            BILLHEADER_LOGGER.error("查询jsonData异常,原因{}", e);
        }
        return null;
    }

    @Override
    public String initMenu(String edocType) {
        return edocHeaderService.initMenu(edocType);
    }

    @Override
    public ExecuteResult<Boolean> reviewEdocHeader(EdocHeaderDTO edocHeaderDTO, String reviewState) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        try {
            //EdocHeader edocHeader = DozerUtil.map(edocHeaderDTO,EdocHeader.class);
            EdocHeader edocHeader = new EdocHeader();
            edocHeader.setEdocNo(edocHeaderDTO.getEdocNo());
            edocHeader.setDeleted(0);
            List<EdocHeader> edocHeaderList = edocHeaderService.findValidEdocHeader(edocHeader);
            EdocHeader edocHeaderInDB = null;
            if (edocHeaderList.size() < 1) {
                BILLHEADER_LOGGER.error("单据不存在{}", edocHeader.getEdocNo());
                result.setResult(false);
                result.setErrorMessages(Arrays.asList("单据不存在:"+edocHeader.getEdocNo()));
                return result;

            }else if (edocHeaderList.size() > 1) {
                BILLHEADER_LOGGER.error("数据异常，请联系管理员{}", edocHeader.getEdocNo());
                result.setResult(false);
                result.setErrorMessages(Arrays.asList("数据异常，请联系管理员:"+edocHeader.getEdocNo()));
                return result;
            }else {
                edocHeaderInDB = edocHeaderList.get(0);
            }
            String reScanFlag = LoadConfig.get("reviewStateConfig_rescan");
            String invalidFlag = LoadConfig.get("reviewStateConfig_invalid");

            if (reviewState.equals(invalidFlag)) { //作废
                edocHeaderInDB.setEdocTaskStatus(Constant.BizStatus.INVALID);
                edocHeaderInDB.setRemarks(edocHeaderDTO.getRemarks()); // 作废原因
                this.handlerTask(edocHeaderInDB , 0); // 处理
            }else if (reviewState.equals(reScanFlag)) { // 重扫
                edocHeaderInDB.setEdocTaskStatus(Constant.BizStatus.WAIT_REPUSH_VSS);
                edocHeaderInDB.setRemarks(edocHeaderDTO.getRemarks()); // 重扫原因
                this.handlerTask(edocHeaderInDB , 1);  // 处理
                // 启动流程
                startProcess(edocHeaderInDB);
            }else {
                BILLHEADER_LOGGER.error("非法的reviewState{}", reviewState);
                result.setResult(false);
                result.setErrorMessages(Arrays.asList("非法的reviewState:"+reviewState));
                return result;
            }
            edocHeaderService.updateByIdSelective(edocHeaderInDB);
            result.setResult(true);
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("reviewEdocHeader方法执行异常{}",e);
            result.setResult(false);
            result.setErrorMessages(Arrays.asList("系统异常，请联系管理员"));
            return result;
        }
        return result;
    }

    public void startProcess( EdocHeader edocHeader){
        //ExecuteResult<SysConfDictItemDTO> wfAccount = dataDictCliFacade.findByEnNameAndItemCode("EDOC_WORKFLOW", edocHeader.getEdocType());
//        wProcessFacade.startProcess(wfAccount.getResult().getDicItemValue(), String.valueOf(edocHeader.getId()));
    }

    public void  handlerTask( EdocHeader edocHeaderInDB , Integer msg){
        // 根据edochead id 找到工作流任务
        EdocHeaderModel edocHeaderModel = edocHeaderService.selectTaskByEdocId(edocHeaderInDB);
        String taskId = edocHeaderModel.getTaskId(); // 工作流任务ID
        Map<String , Object> variable = new HashMap<>();
        variable.put("msg" , msg); // 1代表重扫 , 0 代表重扫
//        wfTaskFacade.completeTask(edocHeaderInDB.getId()+"" , taskId , edocHeaderModel.getTaskKey() , variable);
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> findValidEdocHeader(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        //EdocHeader edocHeader = new EdocHeader();
        EdocHeader edocHeaderInDB = null;
        try {
            //edocHeader.setEdocNo(edocHeaderDTO.getEdocNo());
            //edocHeader.setDeleted(MdConstant.DELETE.NO);
            EdocHeader edocHeader = DozerUtil.map(edocHeaderDTO,EdocHeader.class);
            List<EdocHeader> edocHeaderList = edocHeaderService.findValidEdocHeader(edocHeader);
            if (edocHeaderList.size() < 1) {
                BILLHEADER_LOGGER.error("单据不存在{}", edocHeader.getEdocNo());
                result.setErrorMessages(Arrays.asList("单据不存在:"+edocHeader.getEdocNo()));
                return result;
            }else if (edocHeaderList.size() > 1) {
                BILLHEADER_LOGGER.error("数据异常，请联系管理员{}", edocHeader.getEdocNo());
                result.setErrorMessages(Arrays.asList("数据异常，请联系管理员:"+edocHeader.getEdocNo()));
                return result;
            }else {
                edocHeaderInDB = edocHeaderList.get(0);
                EdocHeaderDTO edocHeaderDTOInDB = DozerUtil.map(edocHeaderInDB,EdocHeaderDTO.class);
                result.setResult(edocHeaderDTOInDB);
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("findValidEdocHeader方法执行异常{}",e);
            result.setErrorMessages(Arrays.asList("系统异常，请联系管理员"));
            return result;
        }
        return result;
    }


    /*@Override
    public String sendBill(Long edocHeaderId) {
        System.out.println("始调用E7影像通开知接口");
        JSONObject request = new JSONObject(32,true);
        JSONArray invoices = new JSONArray();
        try {
            String url = LoadConfig.get("EdocMatchApi");
            BILLHEADER_LOGGER.info("始调用E7影像通开知接口，接口url:{}",url);
            EdocHeader edocHeader = edocHeaderService.queryById(edocHeaderId);
            //List<EdocImageDTO> edocImageDTOList= edocImageFacade.selectImagesByEdocHeaderId(edocHeader.getId(),docType).getResult();
            EdocImageDTO edocImageDTO = new EdocImageDTO();
            edocImageDTO.setEdocHeaderId(edocHeaderId);
            edocImageDTO.setDeleted(MdConstant.DELETE.NO);
            List<EdocImageDTO> edocImageDTOList= edocImageFacade.selectImages(edocImageDTO).getResult();
            List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
            for (EdocImageDTO e:edocImageDTOList) {
                if (!e.getEdocImageType().equals("ACCT_COVER")) {
                    InvoiceDTO invoice = invoiceFacade.findInvoiceByImgId(e.getId()).getResult();
                    Map<String,Object> invoiceMap = new HashMap<>();
                    invoiceMap.put("invNo",invoice.getInvNo());
                    invoiceMap.put("invCode",invoice.getInvCode());
                    invoiceMap.put("invDate",new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(invoice.getInvDate())));
                    invoiceMap.put("invType",e.getEdocImageType());
                    invoiceMap.put("buyerTaxCode",invoice.getBuyerTaxCode());
                    invoiceMap.put("salerTaskCode",invoice.getSalerTaxCode());
                    invoiceMap.put("invAmount",invoice.getInvAmount());
                    invoiceMap.put("invTax",invoice.getInvTax());
                    invoiceMap.put("invTotal",invoice.getInvTotal());
                    invoices.add(invoiceMap);
                }
            }
            request.put("edocNo", edocHeader.getEdocNo());
            request.put("scannerCode", edocHeader.getCreateBy());
            request.put("scanTime", new SimpleDateFormat("yyyy-MM-dd").format(edocHeader.getCreateTime()));
            request.put("extField1", "");
            request.put("extField2", "");
            request.put("extField3", "");
            request.put("extField4", "");
            request.put("extField5", "");
            request.put("invoices", invoices);
            BILLHEADER_LOGGER.info("开始发送，发送报文：{}", request.toString());
            String response = HttpClientUtil.httpPostWithJSON(url, request.toString());
            BILLHEADER_LOGGER.info("结束发送，返回报文：{}", response);
            return response;
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("E7影像通知接口异常", e);
        }
        return null;
    }*/

    @Override
    public ExecuteResult<Boolean> completeEdocTask(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        try {
            EdocHeader edocHeader = DozerUtil.map(edocHeaderDTO, EdocHeader.class);
            this.handlerTask(edocHeader, 0);
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
            BILLHEADER_LOGGER.error("completeEdocTask 方法异 >>>>>> {}", e);
            result.setErrorMessages(Arrays.asList("系统异常，请联系管理员"));
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> updateEdocHeader(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            EdocHeader edocHeader = DozerUtil.map(edocHeaderDTO, EdocHeader.class);
            int i = edocHeaderService.updateByIdSelective(edocHeader);
            result.setResult(i);
        } catch (Exception e) {
            result.setResult(0);
            BILLHEADER_LOGGER.error("updateEdocHeader 方法异 >>>>>> {}", e);
            result.setErrorMessages(Arrays.asList("系统异常，请联系管理员"));
        }
        return result;
    }


    @Override
    public ExecuteResult<String> saveElecInvoice(String originalFilename, Long edocId, InvoiceDTO invoiceDTO, String filePath,String createBy) {
        BILLHEADER_LOGGER.info("电子发票开始存入数据库{},{}", invoiceDTO.getInvNo(), invoiceDTO.getInvDate());
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //查询单据
            EdocHeaderDTO queryCondition = new EdocHeaderDTO();
            queryCondition.setId(edocId);
            EdocHeaderDTO edocHeaderDTO = this.findValidEdocHeader(queryCondition).getResult();

            //新增edocImage
            int maxPageNo = edocImageService.queryMaxPagNum(edocId);
            //List<EdocImageDTO> edocImageDTOList = edocImageFacade.selectImagesByEdocHeaderId(edocId, 0).getResult();
            int pageNo = 1;
            if (!BeanUtil.isEmpty(maxPageNo)) {
                pageNo = maxPageNo + 1;
            }
            String edocImageId = edocImageFacade.uploadFiles("", originalFilename, "", "" + pageNo, filePath, edocId, "EVAT_N_E_INV",createBy).getResult();
            EdocImageDTO queryCondition2 = new EdocImageDTO();
            queryCondition2.setEdocHeaderId(edocId);
            //queryCondition2.setPageNo(new Long((long) pageNo));
            queryCondition2.setImageName(originalFilename);
            EdocImageDTO edocImageDTO = edocImageFacade.findEdocImage(queryCondition2).getResult().get(0);


            //edocImageDTO.setEdocImageType("1001");

            //发票数据规整
            invoiceDTO.setInvAmount(invoiceDTO.getInvAmount().replaceAll("¥", ""));
            invoiceDTO.setInvTax(BeanUtil.isEmpty(invoiceDTO.getInvTax())?"":invoiceDTO.getInvTax().replaceAll("¥", ""));
            invoiceDTO.setBuyerName("");
            String invDate = invoiceDTO.getInvDate().replaceAll(" ", "");
            invoiceDTO.setInvDate(DateUtil.stringToString(invoiceDTO.getInvDate(),"yyyy-MM-dd"));
            //String yyyy=invDate.substring(invDate.length()-11,invDate.length()-7);
            //String mm=invDate.substring(invDate.length()-6,invDate.length()-4);
            //String dd=invDate.substring(invDate.length()-3,invDate.length()-1);
            /*Date d1 = new SimpleDateFormat("yyyy年MM月dd日").parse(invDate);//定义起始日期
            SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            SimpleDateFormat sdf2= new SimpleDateFormat("dd");
            String yyyy = sdf0.format(d1);
            String mm = sdf1.format(d1);
            String dd = sdf2.format(d1);
            invoiceDTO.setInvDate(yyyy+mm+dd);*/
            //新增发票
            invoiceDTO.setEdocImageId(Long.valueOf(edocImageId));
            invoiceDTO.setInvType("4");
            invoiceDTO.setCheckStatus("01");
            Invoice invoice = DozerUtil.map(invoiceDTO,Invoice.class);
            invoiceService.add(invoice);
            //invoiceFacade.addInvoice(invoiceDTO);

            //InvoiceDTO invoiceDTOInDB = invoiceFacade.findInvoiceByImgId(edocImageDTO.getId()).getResult();
            //发票规则校验
            invoiceFacade.invoiceCheckRule(invoice.getId());
            //发票验真，补充其余的发票信息
            invoiceFacade.checkReal(invoice.getId());
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("saveElecInvoice 方法异常 >>>>>> {}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> saveImage(String originalFilename, Long edocId, String filePath) {
        BILLHEADER_LOGGER.info("影像文件开始存入数据库-文件名{},-edocHeaderId{}", originalFilename, edocId);
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //查询单据
            EdocHeaderDTO queryCondition = new EdocHeaderDTO();
            queryCondition.setId(edocId);
            EdocHeaderDTO edocHeaderDTO = this.findValidEdocHeader(queryCondition).getResult();

            //新增edocImage
            List<EdocImageDTO> edocImageDTOList = edocImageFacade.selectImagesByEdocHeaderId(edocId, 0).getResult();
            int pageNo = 1;
            if (!BeanUtil.isEmpty(edocImageDTOList)) {
                pageNo = edocImageDTOList.size() + 1;
            }
            edocImageFacade.uploadFiles("", originalFilename, "", "" + pageNo, filePath, edocId, "EVAT_N_E_INV");

        }catch (Exception e) {
            BILLHEADER_LOGGER.error("saveImage 方法异常 >>>>>> {}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> saveImage(String originalFilename, Long edocId, String filePath,String edocImageType,String createBy) {
        BILLHEADER_LOGGER.info("影像文件开始存入数据库-文件名{},-edocHeaderId{}", originalFilename, edocId);
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //查询单据
            EdocHeaderDTO queryCondition = new EdocHeaderDTO();
            queryCondition.setId(edocId);
            EdocHeaderDTO edocHeaderDTO = this.findValidEdocHeader(queryCondition).getResult();

            //新增edocImage
            List<EdocImageDTO> edocImageDTOList = edocImageFacade.selectImagesByEdocHeaderId(edocId, 0).getResult();
            /*int pageNo = 1;
            if (!BeanUtil.isEmpty(edocImageDTOList)) {
                pageNo = edocImageDTOList.size() + 1;
            }*/
            int pageNo = edocImageService.queryMaxPagNum(edocId);
            pageNo = pageNo + 1;
            edocImageFacade.uploadFiles("", originalFilename, "", "" + pageNo, filePath, edocId, edocImageType,createBy);

        }catch (Exception e) {
            BILLHEADER_LOGGER.error("saveImage 方法异常 >>>>>> {}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> replaceElecInvoice(EdocImageDTO edocImageDTO, InvoiceDTO invInfoDTO) {
        BILLHEADER_LOGGER.info("电子发票开始存入数据库{},{}", invInfoDTO.getInvNo(), invInfoDTO.getInvDate());
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 更新图片信息
            EdocImage edocImage = DozerUtil.map(edocImageDTO, EdocImage.class);
            edocImageService.updateByIdSelective(edocImage);

            //发票数据规整
            invInfoDTO.setInvAmount(invInfoDTO.getInvAmount().replaceAll("¥", ""));
            invInfoDTO.setInvTax(BeanUtil.isEmpty(invInfoDTO.getInvTax())?"":invInfoDTO.getInvTax().replaceAll("¥", ""));
            String invDate = invInfoDTO.getInvDate().replaceAll(" ", "");
            invInfoDTO.setInvDate(DateUtil.stringToString(invDate, "yyyy-MM-dd"));
            //新增或更新发票
            invInfoDTO.setEdocImageId(edocImageDTO.getId());
            invInfoDTO.setInvType("4");
            invInfoDTO.setCheckStatus("01");
            invoiceFacade.addInvoice(invInfoDTO);
            ExecuteResult<List<InvoiceDTO>> result1 = invoiceFacade.queryInvsByImgId(edocImageDTO.getId());
            if (!BeanUtil.isEmpty(result1) && !BeanUtil.isEmpty(result1.getResult())) {
                InvoiceDTO invoiceDTO = result1.getResult().get(0);
                try {
                    //发票规则校验
                    invoiceFacade.invoiceCheckRule(invoiceDTO.getId());
                    //发票验真，补充其余的发票信息
                    invoiceFacade.checkReal(invoiceDTO.getId());
                } catch (Exception e) {
                    BILLHEADER_LOGGER.error("发票规则校验或验真异常 >>>>>> {}", e);
                }
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("replaceElecInvoice 方法异常 >>>>>> {}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> saveImage(EdocImageDTO edocImageDTO) {
        BILLHEADER_LOGGER.info("影像文件开始存入数据库-文件名{},-edocHeaderId{}", edocImageDTO.getImageName(),edocImageDTO.getEdocHeaderId());
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 更新图片信息
            EdocImage edocImage = DozerUtil.map(edocImageDTO, EdocImage.class);
            edocImageService.updateByIdSelective(edocImage);
            // 针对电子发票替换场景下识别不出发票信息的特殊处理，删除这张影像对应的发票数据
            List<InvoiceDTO> invoiceDTOList = invoiceFacade.queryInvsByImgId(edocImage.getId()).getResult();
            if (!BeanUtil.isEmpty(invoiceDTOList)) {
                for (InvoiceDTO i:invoiceDTOList) {
                    invoiceFacade.removeInv(i.getId());
                }
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("saveImage 方法异常 >>>>>> {}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> findBillHeaderToBeSection(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
            edocHeaderDTO.setEdocTaskStatus(Constant.BizStatus.WAIT_ARCHIVED);
            EdocHeader edocHeader = DozerUtil.map(edocHeaderDTO, EdocHeader.class);
            Page<EdocHeader> edocHeaderPage = edocHeaderService.findBillHeaderToBeSection(edocHeader);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(edocHeaderPage.toPageInfo(), EdocHeaderDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询成功");
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> findSectionByEdocNo(String edocNo) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        try {
            EdocHeader edocHeader = edocHeaderService.findSectionByEdocNo(edocNo);
            if (edocHeader == null) {
                result.setSuccessMessage("该单证编号不存在");
                return result;
            }
            //全宗校验暂时先去掉
            /*if (edocHeader.getCompanyName() != null && !"".equals(edocHeader.getCompanyName())) {
                CompanyModel companyModel = archiveCompanyService.selectCompanyByName(edocHeader.getCompanyName());
                if (companyModel != null) {
                    edocHeader.setFondsCode(companyModel.getArchiveCode());
                } else {
                    result.setSuccessMessage("该单证所属公司没有对应全宗");
                    return result;
                }
            }*/
            EdocHeaderDTO edocHeaderDTO = DozerUtil.map(edocHeader, EdocHeaderDTO.class);
            result.setResult(edocHeaderDTO);
            result.setSuccessMessage("查询成功");
        } catch (Exception e) {
            result.addErrorMessage("查询失败");
            BILLHEADER_LOGGER.error("查询失败", e);
        }
        return result;
    }



    @Override
    public ExecuteResult<PageInfo<ArchivedAccountDTO>> findArchivedAccount(ArchivedAccountDTO edocHeaderDTO) {
        ExecuteResult<PageInfo<ArchivedAccountDTO>> result = new ExecuteResult<>();
        try {
            ArchivedAccountModel model = DozerUtil.map(edocHeaderDTO, ArchivedAccountModel.class);
            Page<ArchivedAccountModel> pages = edocHeaderService.findArchivedAccounts(model);
            PageInfo<ArchivedAccountDTO> pageInfo = DozerUtil.mapPage(pages.toPageInfo(), ArchivedAccountDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("查询已归档报账单成功");
        } catch (Exception e) {
            System.out.println(e);
            result.addErrorMessage("查询已归档报账单失败");
            return result;
        }
        return result;
    }
    /**
     * 报账单档案明细列表
     */
    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> queryArchivedAccount(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try{
            EdocHeaderModel expDetail = DozerUtil.map(edocHeaderDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> expressDetailList = edocHeaderService.querArchiveds(expDetail);
            PageInfo<EdocHeaderDTO> expDetailModelDTOs = DozerUtil.mapPage(expressDetailList.toPageInfo(), EdocHeaderDTO.class);
            result.setResult(expDetailModelDTOs);
            result.setSuccessMessage("查询单据信息成功.");
        }catch (Exception e){
            result.addErrorMessage("查询失败");
            return result;
        }
        return result;
    }


    @Override
    public ExecuteResult<List<EdocHeaderDTO>> findBillHeaderBySectionId(Long id) {
        ExecuteResult<List<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
            List<EdocHeader> models = edocHeaderService.findBillHeaderBySectionId(id);
            if (models != null && !models.isEmpty()) {
                List<EdocHeaderDTO> dtos = DozerUtil.mapList(models, EdocHeaderDTO.class);
                result.setResult(dtos);
                return result;
            } else {
                result.setSuccessMessage("没有查到对应影像任务数据");
                return result;
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
            return result;
        }
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> editSectionByEdocNo(String edocNo, Long sectionId) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        try {
            EdocHeader edocHeader = edocHeaderService.findSectionByEdocNo(edocNo);
            if (edocHeader == null) {
                result.setSuccessMessage("没有查到对应单据");
                return result;
            }
            AccountingSection accountingSection = accountingSectionService.queryById(sectionId);
            if (accountingSection == null) {
                result.setSuccessMessage("没有对应分册数据");
                return result;
            }

            if (!edocHeader.getCompanyName().equals(accountingSection.getOfficeName())) {
                result.setSuccessMessage("非同一公司下单据无法分册");
                return result;
            }

            if (!edocHeader.getEdocType().equals(accountingSection.getBizType())) {
                result.setSuccessMessage("非同一业务类型单据无法分册");
                return result;
            }

            List<Long> ids = new ArrayList<>();
            ids.add(edocHeader.getId());
            String message = accoutningSectionItemService.editAccountingSectionItems(ids, accountingSection);
            if (message != null && !"".equals(message)) {
                result.setResult(DozerUtil.map(edocHeader, EdocHeaderDTO.class));
                result.setSuccessMessage(message);
                return result;
            } else {
                result.setSuccessMessage(message);
                return result;
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("添加失败", e);
            result.addErrorMessage("添加失败");
            return result;
        }
    }


    @Override
    public ExecuteResult<Integer> deleteSectionItem(Long billHeaderId, Long sectionId) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            Integer deleteResult = accoutningSectionItemService.deleteItemByBillHeaderIdAndSectionId(billHeaderId, sectionId);
            if (deleteResult == 1) {
                result.setResult(deleteResult);
                result.setSuccessMessage("删除成功");
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("删除分册数据失败", e);
            result.addErrorMessage("删除分册数据失败");
            return result;
        }
        return result;
    }


    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> queryExmaineToBeWorkList(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try{
            if(StringUtils.isBlank(edocHeaderDTO.getTaskAssingee())){
                edocHeaderDTO.setTaskAssingee(SubjectUtil.getUser().getLoginName());
            }
            EdocHeaderModel edocHeaderModel = DozerUtil.map(edocHeaderDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> headerModelPage = edocHeaderService.queryExmaineToBeWorkList(edocHeaderModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(headerModelPage.toPageInfo(),EdocHeaderDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("查询成功");
        }catch (Exception e){
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<AccountingArchivesBorrowDTO>> queryArchBorrow(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO) {
        ExecuteResult<PageInfo<AccountingArchivesBorrowDTO>> result = new ExecuteResult<>();
        try{
            AccountingArchivesBorrowModel accountingArchivesBorrowModel = DozerUtil.map(accountingArchivesBorrowDTO,AccountingArchivesBorrowModel.class);
            //SubjectUtil.getUser().getLoginName()
            Page<AccountingArchivesBorrowModel> headerModelPage = edocHeaderService.queryArchBorrow(accountingArchivesBorrowModel);
            PageInfo<AccountingArchivesBorrowDTO> pageInfo = DozerUtil.mapPage(headerModelPage.toPageInfo(),AccountingArchivesBorrowDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("查询成功");
        }catch (Exception e){
            BILLHEADER_LOGGER.error("查询失败{}", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> saveCendentialsImages(EdocHeaderDTO edocHeaderDTO, List<String> fileNameList) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            List<EdocImageDTO> edocImageDTOList = edocImageFacade.selectImagesByEdocHeaderId(edocHeaderDTO.getId(),0).getResult();
            Integer maxPageNo = edocImageFacade.queryMaxPagNum(edocHeaderDTO.getId()).getResult();
            for (int i=0;i<fileNameList.size();i++) {
                edocImageFacade.uploadFiles("","","",""+maxPageNo+i+1,fileNameList.get(i),edocHeaderDTO.getId(),"FEE_CENDENTIALS");
            }
            result.setSuccessMessage("新增成功");
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("新增失败{}", e);
            result.addErrorMessage("新增失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> createHeader(Map<String, String> input) {
        ExecuteResult<EdocHeaderDTO> executeResult = new ExecuteResult<>();
        try {
            // 单证编号
            String edocNo = input.get("bill_num");
            boolean isCover=true;
            // 是否有封面
            //  boolean isCover = EdocClientUtil.isEmpty(edocNo) ? false : true;
            EdocHeaderDTO currentBillheader = null;
            // 无封面根据规则生成单证编号
//            if (!isCover) {
//                // TODO 单证编号规则
//                edocNo = Constant.BizTypeCodeEnum.text(input.get("categoryCode")) + String.valueOf(edocHeaderService.createEdocFlowNum());
//            }
            // 加入异常处理流程
            ExecuteResult<EdocHeaderDTO> edocHeaderCheck = beforeUploadCheck(edocNo, null, input.get("bill_type"));
            currentBillheader = edocHeaderCheck.getResult();
            // 创建影像任务
            if (EdocClientUtil.isEmpty(currentBillheader) || EdocClientUtil.isEmpty(currentBillheader.getId())) {
                EdocHeaderDTO edocHeaderDTO = new EdocHeaderDTO();
                edocHeaderDTO.setEdocNo(currentBillheader.getEdocNo());
                edocHeaderDTO.setEdocType(input.get("bill_type"));
//                edocHeaderDTO.setParentId(EdocClientUtil.stringToLong(input.get("parentId"), false));
                edocHeaderDTO.setDeleted(Constant.DeleteFlag.DEL_NO);
                edocHeaderDTO.setCreateTime(new Date());
                edocHeaderDTO.setCreateBy(input.get("create_by"));
//                edocHeaderDTO.setTotalNum(Integer.parseInt(input.get("totalCount")));
                edocHeaderDTO.setEdocIsMatched("1");
                edocHeaderDTO.setPublicType(input.get("public_type") == null ? "0" : input.get("public_type"));
                edocHeaderDTO.setCompanyCode(input.get("com_id"));
                edocHeaderDTO.setCompanyName(input.get("com_name"));
                edocHeaderDTO.setRemarks(input.get("remarks"));
                edocHeaderDTO.setUploadType(input.get("upload_type"));
                edocHeaderDTO.setEdocPhysicalStatus(Constant.EdocPhysicalStatus.TO_SEND);
                String uploadType = input.get("upload_type");
                if ("3".equals(uploadType)) { //如果upload_type字段是3，表示这个单子既没有扫描任务也没有上传任务，此时直接把状态设置为已采集
                    edocHeaderDTO.setEdocTaskStatus(Constant.BizStatus.INFORMED);
                    // 签收状态改为[未操作-正常签收] 20180814 确认 - 陈勇 无影像(不操作)的后续可归档
                    edocHeaderDTO.setEdocPhysicalStatus(Constant.EdocPhysicalStatus.UNHANDEL_NORMAL_RECEIVED);
                }else {
                    edocHeaderDTO.setEdocTaskStatus(Constant.BizStatus.WAIT_SCAN);
                }

                //新增字段
                edocHeaderDTO.setProcessId(input.get("processId"));
                edocHeaderDTO.setFdModelName(input.get("fdModelName"));
                edocHeaderDTO.setSysFlag(input.get("sys_flag"));

                // 新建任务(有封面)需要启动流程
                if (!edocNo.equals(currentBillheader.getEdocNo())) {
                    isCover = false;
                }
                edocHeaderDTO.setStartProcessFlag(isCover);
                edocHeaderDTO.setJsonData("{}");
                executeResult = edocClientSaveEdocHeader(edocHeaderDTO);

                // 对公的无影像的需要创建一致性任务
                boolean createFitCheckTask = "3".equals(uploadType)
                        && "0".equals(edocHeaderDTO.getPublicType())
                        && !BeanUtil.isEmpty(executeResult)
                        && !BeanUtil.isEmpty(executeResult.getResult())
                        && !BeanUtil.isEmpty(executeResult.getResult().getId());

                /*if (createFitCheckTask) {
                    FitCheckTaskDTO fitCheckTaskDTO = new FitCheckTaskDTO();
                    fitCheckTaskDTO.setEdocNo(executeResult.getResult().getEdocNo());
                    fitCheckTaskDTO.setEdocType(Constant.BizTypeCode.ACCOUNT_TYPE_CODE);
                    fitCheckTaskDTO.setFitType("0");
                    fitCheckTaskDTO.setEdocHeaderId(executeResult.getResult().getId());
                    fitCheckTaskDTO.setTaskStatus(Constant.FitCheckTaskStatus.WAITING_DEAL);
                    fitCheckTaskFacade.saveFitCheckTask(fitCheckTaskDTO);
                }*/
            } else {
                executeResult.addErrorMessage("任务已存在");
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("创建影像任务失败-{}", e);
            executeResult.addErrorMessage("客户端创建影像任务失败");
            e.printStackTrace();
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> rejectHeader(Map<String, String> input) {
        //判断是否快递已寄出
        ExecuteResult<EdocHeaderDTO> executeResult = new ExecuteResult<>();
//        ExpDetailModelDTO expDetailModelDTO = new ExpDetailModelDTO();
//        expDetailModelDTO.setBillId(input.get("edocNo"));
//        ExecuteResult<List<ExpDetailModelDTO>> listExecuteResult = expDetailFacade.queryExpList(expDetailModelDTO);
//        //快递寄出后返回无法退单
//        if (listExecuteResult.isSuccess()) {
//            List<ExpDetailModelDTO> result1 = listExecuteResult.getResult();
//            if (result1.get(0).getBillStatus() == 1 || result1.get(0).getBillStatus() == 2) {
//                BILLHEADER_LOGGER.error("该单据已寄出，无法退单");
//                executeResult.addErrorMessage("该单据已寄出，无法退单");
//                return executeResult;
//            }
//        }
//        //查询邮包明细信息发生错误，返回查询失败
//        if ("查询失败".equals(listExecuteResult.getErrorMessages().get(0))) {
//            executeResult.addErrorMessage("查询失败");
//            return executeResult;
//        }
//        //没有相关快递或者快递还未寄出时退单
//        EdocHeaderDTO edocHeaderDTO = new EdocHeaderDTO();
//        edocHeaderDTO.setEdocNo(input.get("edocNo"));
//        edocHeaderDTO.setRemarks(input.get("reviewRemark")); // 原因
//        ExecuteResult<Boolean> executeResult2 = this.reviewEdocHeader(edocHeaderDTO, input.get("reviewState"));
//        if (!executeResult2.isSuccess()) {
//            executeResult.setErrorMessages(executeResult2.getErrorMessages());
//            return executeResult;
//        }
//        executeResult.setResult(edocHeaderDTO);
        return executeResult;
    }

    public ExecuteResult<EdocHeaderDTO> beforeCreateCheck(String edocNo, String categoryCode) {
        ExecuteResult<EdocHeaderDTO> executeResult = new ExecuteResult<>(); // 结果对象
        EdocHeader edocHeader = new EdocHeader();
        edocHeader.setEdocType(categoryCode);
        // 根据单证编号以及业务代码查找影像任务
        List<EdocHeader> headers = edocHeaderService.findDataListByEdocNoAndCategoryCode(edocNo, categoryCode);
        // 如果存在影像任务, 对任务状态进行校验
        if (null != headers && headers.size() > 0) {
            edocHeader = headers.get(0); // 赋值
            switch (edocHeader.getEdocTaskStatus()) {
                case Constant.BizStatus.ON_UP_DATA :        // 上传中不需要创建影像任务
                    break;
                case Constant.BizStatus.AGAIN_SCAN :        // 重扫不需要创建影像任务
//                    this.imgIsReplace(edocHeader);     // 是单票重扫 还是全部重扫
//                    this.completeRunTask(edocHeader);  // 重扫说明已经调用E7接口，并启动了流程，所以要完成流程任务
                    break;
                case Constant.BizStatus.SINGLE_AGAIN_SCAN :        // 重扫不需要创建影像任务
//                    this.imgIsReplace(edocHeader);     // 是单票重扫 还是全部重扫
                    //this.completeRunTask(edocHeader);  // 重扫说明已经调用E7接口，并启动了流程，所以要完成流程任务
                    break;
                case Constant.BizStatus.INVALID :           // 作废, 创建新影像任务, 标记为上传中
                    edocHeader = new EdocHeader();
                    edocHeader.setEdocNo(edocNo);
                    break;
                case Constant.BizStatus.WAIT_SCAN:
                    break;
//                //  TODO 其他情况根据具体业务增加分支
//                case Constant.BizStatus.WAIT_MATCH:
//                    break;
                default:                                     // 说明重复上传了, 标记为异常任务
                    edocHeader = new EdocHeader();
                    String exceptionEdocNo = edocNo + "_" + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + "_" + Constant.EDOC_NO_FOOTER_EXCEPTION;
                    edocHeader.setEdocNo(exceptionEdocNo);
            }
        } else {
            // 不存在影像任务, 新建
            edocHeader.setEdocNo(edocNo);
        }
        executeResult.setResult(DozerUtil.map(edocHeader, EdocHeaderDTO.class));
        return executeResult;
    }

    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> queryWaitEdoc(EdocHeaderDTO edocHeaderDTO) {
//        List<EdocHeader> edocHeaders =  edocHeaderService.selectWaitEdocByCrete(creater);
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
            EdocHeaderModel headerModel = DozerUtil.map(edocHeaderDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> headerModelPage = edocHeaderService.selectWaitEdocByCrete(headerModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(headerModelPage.toPageInfo(), EdocHeaderDTO.class);
            result.setResult(pageInfo);
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("queryWaitEdoc方法执行异常{}",e);
            result.addErrorMessage("系统异常，请联系管理员");
        }
        return result;
//        EdocHeaderDTO edocHeaderDTO = new EdocHeaderDTO();
//        edocHeaderDTO.setCreateBy(creater);
//        String edocHeaderPageTw = this.findEdocHeaderPageTw(edocHeaderDTO);

    }

    /*@Override
    public ExecuteResult<EdocHeaderDTO> qryExpStatus(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        EdocHeader edocHeader = new EdocHeader();
        EdocHeader edocHeaderInDB = null;
        try {
            edocHeader.setEdocNo(edocHeaderDTO.getEdocNo());
            edocHeader.setDeleted(MdConstant.DELETE.NO);
            List<EdocHeader> edocHeaderList = edocHeaderService.findValidEdocHeader(edocHeader);
            if (edocHeaderList.size() < 1) {
                BILLHEADER_LOGGER.error("单据不存在{}", edocHeader.getEdocNo());
                result.setErrorMessages(Arrays.asList("单据不存在:"+edocHeader.getEdocNo()));
                return result;
            }else if (edocHeaderList.size() > 1) {
                BILLHEADER_LOGGER.error("数据异常，请联系管理员{}", edocHeader.getEdocNo());
                result.setErrorMessages(Arrays.asList("数据异常，请联系管理员:"+edocHeader.getEdocNo()));
                return result;
            }else {
                edocHeaderInDB = edocHeaderList.get(0);
                EdocHeaderDTO edocHeaderDTOInDB = DozerUtil.map(edocHeaderInDB,EdocHeaderDTO.class);
                result.setResult(edocHeaderDTOInDB);
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("findValidEdocHeader方法执行异常{}",e);
            result.setErrorMessages(Arrays.asList("系统异常，请联系管理员"));
            return result;
        }
        return result;
    }*/

    /*##################################S FSSC webservice ##########################################**/
    /**
     * F_DJBH：请求参数-影像单据编号
     * F_BILL_ID：返回参数-影像单据编号
     * F_DJLX：影像单据类型
     *
     * F_LYXT 系统来源字段必须传 "OA"
     *
     * 请求参数包含关系： Input > F_BODY > F_LIST > F_DJBH
     *
     * 接收参数包含关系：Output > F_BODY > F_LIST > F_DJBH
     *
     *
     */
    @Override
    public ExecuteResult<String> isCanArchive() {
        ExecuteResult<String> result = new ExecuteResult<>();
        long startTime = System.currentTimeMillis();
        try {
            List<EdocHeader> edocHeaders = edocHeaderService.queryWaitArchive();
            if (BeanUtil.isEmpty(edocHeaders)) {
                BILLHEADER_LOGGER.info("【FSSC-是否可归档接口】未查询到需要确认是否可归档的单据");
                result.addErrorMessage("【FSSC-是否可归档接口】未查询到需要确认是否可归档的单据");
                return result;
            }
            BILLHEADER_LOGGER.info(">>>>>>>>>>>>>>>>>>>>>分批处理开始");
            //限制条数
            int limit = 200;
            Integer size = edocHeaders.size();
            if (limit < size) {
                //分批数
                int part = size / limit;
                BILLHEADER_LOGGER.info("本次共有 ：{}条，！" + " 分为 ：{}批处理", size, part);
                for (int i = 0; i < part; i++) {
                    List<EdocHeader> listPage = edocHeaders.subList(0, limit);
                    BILLHEADER_LOGGER.info("处理数据: {}条", listPage.size());
                    // 分批处理支付凭证
                    try {
                        //partHandelCanArchive(listPage);
                    } catch (Exception e) {
                        BILLHEADER_LOGGER.error("处理异常,异常原因: {}", e);
                    }
                    // 剔除已处理的
                    edocHeaders.subList(0, limit).clear();
                }
                // 如果有剩余, 处理剩余条数
                if (!edocHeaders.isEmpty()) {
                    BILLHEADER_LOGGER.info("剩余数据条数: {}", edocHeaders.size());//表示最后剩下的数据
                    try {
                        //partHandelCanArchive(edocHeaders);
                    } catch (Exception e) {
                        BILLHEADER_LOGGER.error("处理异常,异常原因: {}", e);
                    }
                }
            } else {
                // 小于限制条数直接处理
                BILLHEADER_LOGGER.info("数据小于限制条数, 数据条数: {}", edocHeaders.size());
                try {
                    //partHandelCanArchive(edocHeaders);
                } catch (Exception e) {
                    BILLHEADER_LOGGER.error("处理异常,异常原因: {}", e);
                }
            }

        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【FSSC-是否可归档接口】异常，{}", e);
            result.addErrorMessage("【FSSC-是否可归档接口】异常，请联系管理员");
        }

        long endTims = System.currentTimeMillis();
        BILLHEADER_LOGGER.info(">>>>>>>>>>>>>>>>>>>>>分批处理结束. 处理耗时: {}", (endTims - startTime));

        return result;
    }

    /**
     * 分批处理是否可归档
     * @param edocHeaders
     */
    /*public void partHandelCanArchive(List<EdocHeader> edocHeaders) {
        // 将单据组装到F_LIST
        List<F_LIST> fListList = getFLists(edocHeaders);

        // 将F_LIST组装到F_BODY
        F_BODY fBody = new F_BODY();
        fBody.setF_LIST(fListList);
        fBody.setF_LYXT(Constant.OA_ID);
        String fbodyReq = JSON.toJSONString(fBody);
        // 获取数据集
        String url = null;
        ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("FSSC_CONFIG","FSSC_INTERFACE_URL");
        if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
            url = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
        }
        F_BODY fbodyRes = null;
        try {
            fbodyRes = FsscUtil.getBody(fbodyReq, Constant.FsscServiceId.IS_CAN_ARCHIVE, url);
        } catch (Exception e) {
            BILLHEADER_LOGGER.info("【FSSC-是否可归档接口】调用远程方法异常: {}", e);
            BILLHEADER_LOGGER.info("【FSSC-是否可归档接口】异常请求参数: {}", fbodyReq);
            return;
        }
        if (fbodyRes == null) {
            BILLHEADER_LOGGER.info("【FSSC-是否可归档接口】未接收到F_BODY结果");
            return ;
        }
        List<F_LIST> flistRes = fbodyRes.getF_LIST();
        if (BeanUtil.isEmpty(flistRes)) {
            BILLHEADER_LOGGER.info("【FSSC-是否可归档接口】返回结果F_BODY中F_LIST为空");
            return ;
        }
        for (F_LIST fList : flistRes) {
            String f_djbh = fList.getF_DJBH();
            String f_bill_id = fList.getF_BILL_ID();
            String f_djlx = fList.getF_DJLX();
            String f_sta_value = fList.getF_STA_VALUE();
            if (f_sta_value != null && "1".equals(f_sta_value)) {
                // 修改单据是否可归档状态为 可归档
                for (EdocHeader edocHeader : edocHeaders) {
                    if (!edocHeader.getEdocNo().equals(f_bill_id)) {
                        continue;
                    }
                    edocHeader.setEdocArchiveStatus(Constant.FsscEdocStatus.CAN_ARCHIVE);
                    edocHeaderService.updateByIdSelective(edocHeader);
                }

                BILLHEADER_LOGGER.info(f_djbh + " " + f_bill_id + " " + f_djlx + " " + f_sta_value);
            }
        }
    }*/

    /*@Override
    public ExecuteResult<String> isWaitCert() {
        ExecuteResult<String> result = new ExecuteResult<>();
        // 根据id降序查询已采集单据，限制条数
        try {
            List<EdocHeader> edocHeaders = edocHeaderService.queryWaitCertEdoc();
            if (BeanUtil.isEmpty(edocHeaders)) {
                BILLHEADER_LOGGER.info("【FSSC-是否待认证接口】未查询到需要确认是否可认证的单据");
                result.addErrorMessage("【FSSC-是否待认证接口】未查询到需要确认是否可认证的单据");
                return result;
            }
            // 将单据组装到F_LIST
            List<F_LIST> fListList = getFLists(edocHeaders);

            // 将F_LIST组装到F_BODY
            F_BODY fBody = new F_BODY();
            fBody.setF_LIST(fListList);
            fBody.setF_LYXT(Constant.OA_ID);
            String fbodyReq = JSON.toJSONString(fBody);
            // 获取数据集
            String url = null;
            ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("FSSC_CONFIG","FSSC_INTERFACE_URL");
            if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
                url = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
            }
            F_BODY fbodyRes = FsscUtil.getBody(fbodyReq, Constant.FsscServiceId.IS_WAIT_CERT, url);
            if (fbodyRes == null) {
                BILLHEADER_LOGGER.info("【FSSC-是否待认证接口】未接收到F_BODY结果");
                result.addErrorMessage("【FSSC-是否待认证接口】未接收到F_BODY结果");
                return result;
            }
            List<F_LIST> flistRes = fbodyRes.getF_LIST();
            if (BeanUtil.isEmpty(flistRes)) {
                BILLHEADER_LOGGER.info("【FSSC-是否待认证接口】返回结果F_BODY中F_LIST为空");
                result.addErrorMessage("【FSSC-是否待认证接口】返回结果F_BODY中F_LIST为空");
                return result;
            }
            for (F_LIST fList : flistRes) {
                String f_djbh = fList.getF_DJBH();
                String f_bill_id = fList.getF_BILL_ID();
                String f_djlx = fList.getF_DJLX();
                String f_sta_value = fList.getF_STA_VALUE();
                // 更新单据下发票认证状态  f_sta_value: 0 不可认证 1 待认证
                if (f_sta_value != null && "1".equals(f_sta_value)) {
                    // 修改单据是否可归档状态为 可归档
                    for (EdocHeader edocHeader : edocHeaders) {
                        if (!edocHeader.getEdocNo().equals(f_bill_id)) {
                            continue;
                        }
                        // 更新发票认证状态
                        ExecuteResult<String> updateInvCertResult = invoiceFacade.updateCertStatus(edocHeader.getId());

                        // 更新单据认证状态
                        edocHeader.setEdocCertStatus(Constant.FsscEdocStatus.CAN_CERT);
                        edocHeaderService.updateByIdSelective(edocHeader);
                    }

                    BILLHEADER_LOGGER.info(f_djbh + " " + f_bill_id + " " + f_djlx + " " + f_sta_value);
                }
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【FSSC-是否待认证接口】异常，{}", e);
            result.addErrorMessage("【FSSC-是否待认证接口】异常，请联系管理员");
        }
        return result;
    }*/

    /**
     * 20180807-共享确认,
     * EBS：单据最多只会冲销一次. 未冲销单据的费用凭证有1条, 冲销的单据凭证有2条,
     *      且一个单据最多对应2条费用凭证(被冲销凭证, 冲销凭证)
     *
     * JDE：JDE凭证号只有一个，如果冲销了，新传的正常/历史/冲销凭证号都是相同的，只需要去重新获取pdf。故jde凭证只保存一个
     *
     * @return
     */
   /* @Override
    public ExecuteResult<String> getExpenseVoucher() {
        ExecuteResult<String> result = new ExecuteResult<>();
        // 根据id降序查询已采集单据，限制条数
        try {
            List<EdocHeader> edocHeaders = edocHeaderService.queryNoExpenseVoucher();
            if (BeanUtil.isEmpty(edocHeaders)) {
                BILLHEADER_LOGGER.info("【FSSC-获取费用凭证接口】未查询到需要获取费用凭证的单据");
                result.addErrorMessage("【FSSC-获取费用凭证接口】未查询到需要获取费用凭证的单据");
                return result;
            }

            // 将单据组装到F_LIST
            List<F_LIST> fListList = getFLists(edocHeaders);

            // 将F_LIST组装到F_BODY
            F_BODY fBody = new F_BODY();
            fBody.setF_LIST(fListList);
            fBody.setF_LYXT(Constant.OA_ID);
            // 凭证类型：1、费用凭证 2、付款凭证
            fBody.setF_PZ_TYPE(Constant.CredentialsType.EXPEN_CRED);
            String fbodyReq = JSON.toJSONString(fBody);
            // 获取数据集
            String url = null;
            ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("FSSC_CONFIG","FSSC_INTERFACE_URL");
            if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
                url = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
            }
            F_BODY fbodyRes = FsscUtil.getBody(fbodyReq, Constant.FsscServiceId.GET_VOUCHER, url);
            if (fbodyRes == null) {
                BILLHEADER_LOGGER.info("【FSSC-获取费用凭证接口】未接收到F_BODY结果");
                result.addErrorMessage("【FSSC-获取费用凭证接口】未接收到F_BODY结果");
                return result;
            }
            List<F_LIST> flistRes = fbodyRes.getF_LIST();
            if (BeanUtil.isEmpty(flistRes)) {
                BILLHEADER_LOGGER.info("【FSSC-获取费用凭证接口】返回结果F_BODY中F_LIST为空");
                result.addErrorMessage("【FSSC-获取费用凭证接口】返回结果F_BODY中F_LIST为空");
                return result;
            }

            // 保存从接口获取的费用凭证信息
            saveExpenseVoucher(edocHeaders, flistRes);
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【FSSC-获取费用凭证接口】异常，{}", e);
            result.addErrorMessage("【FSSC-是获取费用凭证接口】异常，请联系管理员");
        }
        return result;
    }*/

    /**
     * 保存费用凭证
     * @param edocHeaders
     * @param flistRes
     */
    private void saveExpenseVoucher(List<EdocHeader> edocHeaders, List<F_LIST> flistRes) {
        for (F_LIST fList : flistRes) {
            String f_djbh = fList.getF_DJBH();
            String f_bill_id = fList.getF_BILL_ID();
            // 核算系统
            String f_hsxt = fList.getF_HSXT();
            // 核算账套
            String f_hszt = fList.getF_HSZT();
            // 凭证编号
            String f_pzbh = fList.getF_PZBH();
            // 保存费用凭证
            for (EdocHeader edocHeader : edocHeaders) {
                if (!edocHeader.getEdocNo().equals(f_bill_id)) {
                    continue;
                }

                // 获取费用凭证时更新发票认证状态
                ExecuteResult<String> updateInvCertResult = invoiceFacade.updateCertStatus(edocHeader.getId());
                BILLHEADER_LOGGER.info("更新单据:<{}>认证状态结果:{}", edocHeader.getEdocNo(), JSON.toJSONString(updateInvCertResult));

                // 凭证状态：1.正常凭证 2. 历史凭证 3. 冲销凭证, 备用字段1保存

                // 保存正常费用凭证
                EdocCredentials normalCred = new EdocCredentials();
                normalCred.setCredentialsNum(f_pzbh);
                normalCred.setCredentialsType(Constant.CredentialsType.EXPEN_CRED);
                normalCred.setEdocHeaderId(edocHeader.getId());
                normalCred.setExtField1("1");
                normalCred.setExtField2(f_hszt);
                normalCred.setExtField3(f_hsxt);
                normalCred.setVoucherStatus("4");
                List<EdocCredentials> existNormalCreds = edocCredentialsService.query(normalCred);
                // 若凭证不存在，新增凭证； 存在不做处理
                if (existNormalCreds == null || existNormalCreds.size() == 0) {
                    normalCred.setPrintCount(0L);
                    edocCredentialsService.add(normalCred);
                } else {
                    // JDE 凭证，把凭证pdf路径置空
                    if(Constant.JDE_ID.equals(f_hsxt)) {
                        EdocCredentials waitUpdateJDECred = existNormalCreds.get(0);
                        waitUpdateJDECred.setPdfFileUrl(null);
                        edocCredentialsService.updateById(waitUpdateJDECred);
                    }
                }

                // 更新单据状态为已获取费用凭证
                edocHeader.setEdocVoucherStatus(Constant.FsscEdocStatus.HAS_EXPENSE_VOUCHER);
                edocHeaderService.updateByIdSelective(edocHeader);

                // 只有EBS才有历史和冲销凭证， JDE因为历史和冲销凭证号相同，故只保存正常凭证
                if (Constant.EBS_ID.equals(f_hsxt)) {
                    // 处理历史冲销凭证
                    List<F_CXPZ_LIST> f_cxpz_list = fList.getF_CXPZ_LIST();
                    if (BeanUtil.isEmpty(f_cxpz_list)) {
                        continue;
                    }
                    for (F_CXPZ_LIST fCxpzList : f_cxpz_list) {
                        // 历史凭证
                        String f_lspzbh = fCxpzList.getF_PZBH();
                        EdocCredentials     lscred = new EdocCredentials();
                        lscred.setCredentialsNum(f_lspzbh);
                        lscred.setCredentialsType(Constant.CredentialsType.EXPEN_CRED);
                        lscred.setEdocHeaderId(edocHeader.getId());
//                        lscred.setExtField2(f_hszt);
//                        lscred.setVoucherStatus("4");
                        List<EdocCredentials> existLsCreds = edocCredentialsService.query(lscred);
                        // 不存在，新增
                        if (existLsCreds == null || existLsCreds.size() == 0) {
                            lscred.setExtField1("2");
                            lscred.setPrintCount(0L);
                            lscred.setExtField2(f_hszt);
                            lscred.setExtField3(f_hsxt);
                            lscred.setVoucherStatus("4");
                            edocCredentialsService.add(lscred);
                        } else {
                            // 存在，更新凭证状态
                            for (EdocCredentials existLsCred : existLsCreds) {
                                existLsCred.setExtField1("2");
                                lscred.setExtField2(f_hszt);
                                existLsCred.setVoucherStatus("4");
                                edocCredentialsService.updateByIdSelective(existLsCred);
                            }
                        }

                        // 冲销凭证
                        String f_cxpzbh = fCxpzList.getF_CXPZBH();
                        EdocCredentials cxcred = new EdocCredentials();
                        cxcred.setCredentialsNum(f_cxpzbh);
                        cxcred.setCredentialsType(Constant.CredentialsType.EXPEN_CRED);
                        cxcred.setEdocHeaderId(edocHeader.getId());

                        List<EdocCredentials> existCxCreds = edocCredentialsService.query(cxcred);
                        // 不存在，新增
                        if (existCxCreds == null || existCxCreds.size() == 0) {
                            cxcred.setExtField1("3");
                            cxcred.setPrintCount(0L);
                            cxcred.setVoucherStatus("4");
                            cxcred.setExtField2(f_hszt);
                            cxcred.setExtField3(f_hsxt);
                            edocCredentialsService.add(cxcred);
                        } else {
                            // 存在，更新凭证状态
                            for (EdocCredentials existCxCred : existCxCreds) {
                                existCxCred.setExtField1("3");
                                cxcred.setVoucherStatus("4");
                                cxcred.setExtField2(f_hszt);
                                edocCredentialsService.updateByIdSelective(existCxCred);
                            }

                        }
                    }
                }

            }

        }
    }

    @Override
    public ExecuteResult<String> getPayVoucher() {
        ExecuteResult<String> result = new ExecuteResult<>();
        long startTime = System.currentTimeMillis();
        // 根据id降序查询银行回单03，已采集状态
        try {
            List<EdocHeader> edocHeaders = edocHeaderService.queryNoPayVoucher();

            if (BeanUtil.isEmpty(edocHeaders)) {
                result.addErrorMessage("未查询到需要获取付款凭证的单据");
                BILLHEADER_LOGGER.info("【FSSC-获取支付凭证接口】未查询到需要获取付款凭证的单据");
                return result;
            }
            BILLHEADER_LOGGER.info(">>>>>>>>>>>>>>>>>>>>>分批处理开始");

            //限制条数
            int limit = 200;
            Integer size = edocHeaders.size();
            if (limit < size) {
                //分批数
                int part = size / limit;
                BILLHEADER_LOGGER.info("本次共有 ：{}条，！" + " 分为 ：{}批处理", size, part);
                for (int i = 0; i < part; i++) {
                    List<EdocHeader> listPage = edocHeaders.subList(0, limit);
                    BILLHEADER_LOGGER.info("处理数据: {}条", listPage.size());
                    // 分批处理支付凭证
                    try {
                        //partHandelPayVoucher(listPage);
                    } catch (Exception e) {
                        BILLHEADER_LOGGER.error("处理异常,异常原因: {}", e);
                    }
                    // 剔除已处理的
                    edocHeaders.subList(0, limit).clear();
                }
                // 如果有剩余, 处理剩余条数
                if (!edocHeaders.isEmpty()) {
                    BILLHEADER_LOGGER.info("剩余数据条数: {}", edocHeaders.size());//表示最后剩下的数据
                    try {
                        //partHandelPayVoucher(edocHeaders);
                    } catch (Exception e) {
                        BILLHEADER_LOGGER.error("处理异常,异常原因: {}", e);
                    }
                }
            } else {
                // 小于限制条数直接处理
                BILLHEADER_LOGGER.info("数据小于限制条数, 数据条数: {}", edocHeaders.size());
                try {
                    //partHandelPayVoucher(edocHeaders);
                } catch (Exception e) {
                    BILLHEADER_LOGGER.error("处理异常,异常原因: {}", e);
                }
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【FSSC-获取支付凭证接口】异常，{}", e);
            result.addErrorMessage("【FSSC-是获取支付凭证接口】异常，请联系管理员");
        }
        long endTims = System.currentTimeMillis();
        BILLHEADER_LOGGER.info(">>>>>>>>>>>>>>>>>>>>>分批处理结束. 处理耗时: {}", (endTims - startTime));

        return result;
    }

    /**
     * 分批处理支付凭证
     * @param edocHeaders
     *//*
    private void partHandelPayVoucher(List<EdocHeader> edocHeaders) {
        // 将单据组装到F_LIST
        List<F_LIST> fListList = new ArrayList<>();
        for (int i = 0; i < edocHeaders.size(); i++) {
            String edocNo_payNo = edocHeaders.get(i).getEdocNo();
            if (BeanUtil.isEmpty(edocNo_payNo)) {
                continue;
            }
            F_LIST fList = new F_LIST();
            fList.setF_ZFHH(edocNo_payNo);
            fListList.add(fList);
        }

        // 将F_LIST组装到F_BODY
        F_BODY fBody = new F_BODY();
        fBody.setF_LIST(fListList);
        fBody.setF_LYXT(Constant.OA_ID);
        // 凭证类型：1、费用凭证 2、付款凭证
        fBody.setF_PZ_TYPE(Constant.CredentialsType.PAY_CRED);
        String fbodyReq = JSON.toJSONString(fBody);
        // 获取数据集
        F_BODY fbodyRes = null;
        try {
            String url = null;
            ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("FSSC_CONFIG","FSSC_INTERFACE_URL");
            if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
                url = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
            }
            fbodyRes = FsscUtil.getBody(fbodyReq, Constant.FsscServiceId.GET_VOUCHER, url);
        } catch (Exception e) {
            BILLHEADER_LOGGER.info("【FSSC-获取支付凭证接口】调用远程方法异常: {}", e);
            BILLHEADER_LOGGER.info("【FSSC-获取支付凭证接口】异常请求参数: {}", fbodyReq);
            return;
        }
        if (fbodyRes == null) {
            BILLHEADER_LOGGER.info("【FSSC-获取支付凭证接口】未接收到F_BODY结果");
            return;
        }
        List<F_LIST> flistRes = fbodyRes.getF_LIST();
        if (BeanUtil.isEmpty(flistRes)) {
            BILLHEADER_LOGGER.info("【FSSC-获取支付凭证接口】返回结果F_BODY中F_LIST为空");
            return;
        }
        for (F_LIST fList : flistRes) {
            // 支付行号
            String f_zfhh = fList.getF_ZFHH();
            // 单据号
            String f_bill_id = fList.getF_BILL_ID();
            // 核算系统
            String f_hsxt = fList.getF_HSXT();
            // 核算账套
            String f_hszt = fList.getF_HSZT();
            // 凭证编号
            String f_pzbh = fList.getF_PZBH();
            // 保存支付凭证
            for (EdocHeader edocHeader : edocHeaders) {
                String edocNo = edocHeader.getEdocNo();
                if (!edocNo.equals(f_zfhh)) {
                    continue;
                }

                EdocCredentials cred = new EdocCredentials();
                cred.setCredentialsNum(f_pzbh);
                cred.setCredentialsType(Constant.CredentialsType.PAY_CRED);
                cred.setEdocHeaderId(edocHeader.getId());
                cred.setPrintCount(0L);
                cred.setVoucherStatus("4");
                cred.setExtField2(f_hszt);
                cred.setExtField3(f_hsxt);
                edocCredentialsService.add(cred);
                // 更新单据状态为已获取费用凭证
                edocHeader.setEdocVoucherStatus(Constant.FsscEdocStatus.HAS_PAY_VOUCHER);
                edocHeader.setEdocVoucherNo(f_bill_id);

                // 根据单号查询银行回单关联的报账单，添加银行回单公司信息
                EdocHeader queryRelatedEdocHeader = new EdocHeader();
                queryRelatedEdocHeader.setEdocNo(f_bill_id);
                List<EdocHeader> relatedEdocHeaders = edocHeaderService.findValidEdocHeader(queryRelatedEdocHeader);
                if(!BeanUtil.isEmpty(relatedEdocHeaders)) {
                    EdocHeader relatedEdocHeader = relatedEdocHeaders.get(0);
                    edocHeader.setCompanyCode(relatedEdocHeader.getCompanyCode());
                    edocHeader.setCompanyName(relatedEdocHeader.getCompanyName());
                }

                edocHeaderService.updateByIdSelective(edocHeader);
            }
        }
    }*/

    /**
     * 保存pdf文件及pdf转换过后的影像件
     * 通过JDE
     */
    /*@Override
    public ExecuteResult<String> getVoucherPDFByJDE() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 查找未获取pdf的凭证号
            List<EdocCredentials> noPdfCreds =  edocCredentialsService.queryNoPdfCredByJDE();
            if (noPdfCreds == null || noPdfCreds.size() <= 0) {
                result.addErrorMessage("暂时没有未获取pdf文件的凭证 JDE");
                BILLHEADER_LOGGER.info("【FSSC-获取凭证PDF接口】暂时没有未获取pdf文件的凭证 JDE");
                return result;
            }
            // pdf 保存路径
            String pdfRootPath = LoadConfig.get("elecInv.storePath");
            ExecuteResult<SysConfDictItemDTO> pdfSaveResult = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","JDE_PDF_SAVE_PATH");
            if (!BeanUtil.isEmpty(pdfSaveResult.getResult())&&!BeanUtil.isEmpty(pdfSaveResult.getResult().getDicItemValue())) {
                pdfRootPath = pdfSaveResult.getResult().getDicItemValue() ;
            }

            // pdf 影像保存路径
            String imgRootPath = Constant.ImagePath.ROOT_PATH;
            ExecuteResult<SysConfDictItemDTO> imgPathResult = dataDictCliFacade.findByEnNameAndItemCode("IMAGE_CONFIG","ROOT_PATH");
            if (!BeanUtil.isEmpty(imgPathResult.getResult())&&!BeanUtil.isEmpty(imgPathResult.getResult().getDicItemValue())) {
                imgRootPath = imgPathResult.getResult().getDicItemValue();
            }

            // jde 数据库 CRPDTA（测试）/PRODDTA（生产）
            String db = Constant.JDE_DBNSP;
            ExecuteResult<SysConfDictItemDTO> jdeDBResult = dataDictCliFacade.findByEnNameAndItemCode("JDE_DB", "DB");
            if (!BeanUtil.isEmpty(jdeDBResult.getResult())&&!BeanUtil.isEmpty(jdeDBResult.getResult().getDicItemValue())) {
                db = jdeDBResult.getResult().getDicItemValue() ;
            }

            // pdf接口路径
            String urlPath = "";
            ExecuteResult<SysConfDictItemDTO> PDFUrl = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","JDE_WEBSERVICE_URL");
            if (!BeanUtil.isEmpty(PDFUrl.getResult())&&!BeanUtil.isEmpty(PDFUrl.getResult().getDicItemValue())) {
                urlPath = PDFUrl.getResult().getDicItemValue();
            }

            for (EdocCredentials noPdfCred : noPdfCreds) {
                // 凭证编号
                String credentialsNum = noPdfCred.getCredentialsNum();
                // 1、凭证对象       2、文件名称      3、PDF保存路径       4、PDF对应影像保存路径 6、pdf接口路径
                String pdfFinalpath = this.downLoadByUrlByJDE(noPdfCred, credentialsNum, pdfRootPath, imgRootPath, db, urlPath);
                if (BeanUtil.isEmpty(pdfFinalpath)) {
                    continue;
                }
                noPdfCred.setPdfFileUrl(pdfFinalpath);
                edocCredentialsService.updateByIdSelective(noPdfCred);

                // 判断单据下对应得pdf是否已全部获取pdf
                EdocCredentials queryHasPdf = new EdocCredentials();
                queryHasPdf.setEdocHeaderId(noPdfCred.getEdocHeaderId());
                List<EdocCredentials> credsInEdoc = edocCredentialsService.query(queryHasPdf);
                if (credsInEdoc == null || credsInEdoc.size() == 0) {
                    continue;
                }
                boolean isAllHasPdf = true;
                for (EdocCredentials edocCredentials : credsInEdoc) {
                    // 存在未获取pdf的凭证
                    if (BeanUtil.isEmpty(edocCredentials.getPdfFileUrl())) {
                        isAllHasPdf = false;
                        break ;
                    }
                }
                // 修改单据状态为待归档
                if (isAllHasPdf) {
                    EdocHeader edocHeader = edocHeaderService.queryById(noPdfCred.getEdocHeaderId());
                    if (edocHeader != null) {
                        edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_ARCHIVED);
                        edocHeaderService.updateByIdSelective(edocHeader);
                    }
                }
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【FSSC-获取凭证PDF接口】 JDE异常，{}", e);
            result.addErrorMessage("【FSSC-获取凭证PDF接口】 JDE异常，请联系管理员");
        }
        return result;
    }*/

    /**
     * 保存费用pdf文件及pdf转换过后的影像件
     */
    /*@Override
    public ExecuteResult<String> getExpensePdf() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 查找未获取pdf的费用
            List<EdocHeader> edocHeaders = edocHeaderService.queryExpense();
            if (edocHeaders == null || edocHeaders.size() <= 0) {
                result.addErrorMessage("暂时没有 已获取费用凭证 未获取pdf文件的凭证");
                BILLHEADER_LOGGER.info("【OA-获取PDF接口】暂时没有 已获取费用凭证 未获取pdf文件的凭证");
                return result;
            }
            //获取当前系统，win 还是 linux
            String os = System.getProperty("os.name");
            // pdf 保存路径
            String pdfRootPath = LoadConfig.get("elecInv.storePath");
            //影像保存路径
            String imgRootPath=Constant.ImagePath.ROOT_PATH;
            //pdf接口路径
            String pdfResult="";
            //win就是本地测试，linux就是发版的
            if(os.toLowerCase().startsWith("win")){
                pdfRootPath="D:\\";
                imgRootPath="D:\\";
                ExecuteResult<SysConfDictItemDTO> pdfResultPath = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","INTER_PDF_URL");
                if(!BeanUtil.isEmpty(pdfResultPath.getResult()) && !BeanUtil.isEmpty(pdfResultPath.getResult().getDicItemValue())){
                    pdfResult=pdfResultPath.getResult().getDicItemValue();
                }
            }else{
                ExecuteResult<SysConfDictItemDTO> pdfSaveResult = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","OA_PDF_SAVE_PATH");
                if (!BeanUtil.isEmpty(pdfSaveResult.getResult())&&!BeanUtil.isEmpty(pdfSaveResult.getResult().getDicItemValue())) {
                    pdfRootPath = pdfSaveResult.getResult().getDicItemValue();
                }
                ExecuteResult<SysConfDictItemDTO> imgResultPath = dataDictCliFacade.findByEnNameAndItemCode("IMAGE_CONFIG","ROOT_PATH");
                if(!BeanUtil.isEmpty(imgResultPath.getResult()) && !BeanUtil.isEmpty(imgResultPath.getResult().getDicItemValue())){
                    imgRootPath=imgResultPath.getResult().getDicItemValue();
                }
                ExecuteResult<SysConfDictItemDTO> pdfResultPath =  dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","INTER_PDF_URL");
                if(!BeanUtil.isEmpty(pdfResultPath.getResult()) && !BeanUtil.isEmpty(pdfResultPath.getResult().getDicItemValue())){
                    pdfResult=pdfResultPath.getResult().getDicItemValue();
                }
            }
            BILLHEADER_LOGGER.info("--------------【OA-获取PDF接口】定时任务启动! -------------------");
            BILLHEADER_LOGGER.info("【OA-获取PDF接口】定时任务 url :"+pdfResult);

            for (EdocHeader edocHeader : edocHeaders) {
                StringBuilder sb = new StringBuilder();
                sb.append(pdfResult).append(edocHeader.getEdocNo()).append(".pdf");
                //20180920新需求：调用蓝凌接口并保存pdf
                //下载pdf
                String pdfFinalpath = this.downLoadByUrl(sb.toString(), edocHeader.getEdocNo(), pdfRootPath, imgRootPath, edocHeader.getId());
                if (BeanUtil.isEmpty(pdfFinalpath)) {
                    continue;
                }
                edocHeader.setPdfUrl(pdfFinalpath);
                edocHeaderService.updateByIdSelective(edocHeader);
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【OA-获取PDF接口】 异常，{}", e);
            result.addErrorMessage("【OA-获取PDF接口】 异常，请联系管理员");
        }
        return result;
    }*/

    /**
     * 下载pdf文件及保存pdf影像件
     */
    private String downLoadByUrlByJDE(EdocCredentials noPdfCred, String filePreName, String savePath, String imgPath, String db, String urlPath) {
        String finalPdfPath = null;
        FileOutputStream fos = null;
        try {
            String token = JdeUtil.getToken(db,urlPath);
            if (BeanUtil.isEmpty(token)) {
                BILLHEADER_LOGGER.error("【通过JDE获取TOKEN错误】，Token为空");
                return null;
            }
            EdocHeader edocHeader = edocHeaderService.queryEdocHeaderByBusinessKey(noPdfCred.getEdocHeaderId().toString());
            if (edocHeader == null || BeanUtil.isEmpty(edocHeader.getCompanyCode())) {
                BILLHEADER_LOGGER.error("工单[" + noPdfCred.getEdocHeaderId() + "]无公司信息，无法通过JDE获取凭证");
                return null;
            }
            Date createTime = noPdfCred.getCreateTime();
            //获取自己数组
//            byte[] getData = JdeUtil.getPDFBytes(db, token, edocHeader.getCompanyCode(), createTime.getYear() - 100, createTime.getMonth() + 1, noPdfCred.getCredentialsNum());
            byte[] getData = JdeUtil.getPDFBytes(db, token, noPdfCred.getCredentialsNum(),urlPath);
            if (getData==null || getData.length==0){
                BILLHEADER_LOGGER.error("工单[" + noPdfCred.getEdocHeaderId() + "]，通过JDE获取凭证失败");
                return null;
            }

            // 保存pdf影像图片
            String imgFinalPath = imgPath  + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + filePreName;
            File filepath = new File(imgFinalPath);
            if (!filepath.exists()) {
                filepath.mkdirs();//如果目录不存在，创建目录
            }
            PDDocument doc = PDDocument.load(getData);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                String imgName = filePreName + "_" + StringUtils.leftPad(String.valueOf(i + 1), 4, "0") + ".jpg";
                ImageIO.write(image, "JPG", new File(imgFinalPath + "/" + imgName));
                EdocImage edocImage = new EdocImage();
                edocImage.setEdocHeaderId(0L);
                edocImage.setImageRootPath(imgPath);
                edocImage.setImageUrl("/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + filePreName +  "/" + imgName);
                edocImage.setExtField1(String.valueOf(noPdfCred.getId()));
                edocImageService.add(edocImage);
            }

            //pdf文件保存
            File saveDir = new File(savePath + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()));
            if (!(saveDir.isDirectory())) {
                saveDir.mkdirs();
            }
            finalPdfPath = saveDir + "/" + filePreName + ".pdf";
            File file = new File(finalPdfPath);
            fos = new FileOutputStream(file);
            fos.write(getData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        BILLHEADER_LOGGER.error("pdf save path: " + finalPdfPath);
        return finalPdfPath;
    }

    /*
    获取jde法人公司
     */
    /*@Override
    public ExecuteResult<String> syncJdeCompany(){
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // jde 数据库 CRPDTA（测试）/PRODDTA（生产）
            String db = Constant.JDE_DBNSP;;
            ExecuteResult<SysConfDictItemDTO> jdeDBResult = dataDictCliFacade.findByEnNameAndItemCode("JDE_DB", "DB");
            if (!BeanUtil.isEmpty(jdeDBResult.getResult())&&!BeanUtil.isEmpty(jdeDBResult.getResult().getDicItemValue())) {
                db = jdeDBResult.getResult().getDicItemValue() ;
            }
            // pdf接口路径
            String urlPath = "";
            ExecuteResult<SysConfDictItemDTO> PDFUrl = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","JDE_WEBSERVICE_URL");
            if (!BeanUtil.isEmpty(PDFUrl.getResult())&&!BeanUtil.isEmpty(PDFUrl.getResult().getDicItemValue())) {
                urlPath = PDFUrl.getResult().getDicItemValue();
            }
            String token = JdeUtil.getToken(db,urlPath);
            if (BeanUtil.isEmpty(token)) {
                BILLHEADER_LOGGER.error("【通过JDE获取TOKEN错误】，Token为空");
                result.addErrorMessage("【通过JDE获取TOKEN错误】，Token为空");
                return result;
            }
            String copanyFacde = JdeUtil.getCopanyFacde(db,token,urlPath);
            if(copanyFacde != null && copanyFacde != ""){
                JSONArray jsonArray=JSON.parseArray(copanyFacde);
                *//*mdLegalCompanyFacade.addOrUpdateCompany(jsonArray);*//*
            }

        }catch (Exception e){
            e.printStackTrace();
            BILLHEADER_LOGGER.error("【同步JDE法人公司信息】异常，原因：{}", e);
            result.addErrorMessage("同步JDE法人公司信息失败");
        }
        return result;
    }*/

    /**
     * 保存pdf文件及pdf转换过后的影像件
     * 通过EBS
     */
    /*@Override
    public ExecuteResult<String> getVoucherPDF() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 查找未获取pdf的凭证号
            List<EdocCredentials> noPdfCreds =  edocCredentialsService.queryNoPdfCredByEBS();
            if (noPdfCreds == null || noPdfCreds.size() <= 0) {
                result.addErrorMessage("暂时没有未获取pdf文件的凭证 EBS");
                BILLHEADER_LOGGER.info("【FSSC-获取凭证PDF接口】暂时没有未获取pdf文件的凭证 EBS");
                return result;
            }
            // pdf 保存路径
            String pdfRootPath = LoadConfig.get("elecInv.storePath");
            ExecuteResult<SysConfDictItemDTO> pdfSaveResult = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","EBS_PDF_SAVE_PATH");
            if (!BeanUtil.isEmpty(pdfSaveResult.getResult())&&!BeanUtil.isEmpty(pdfSaveResult.getResult().getDicItemValue())) {
                pdfRootPath = pdfSaveResult.getResult().getDicItemValue() ;
            }
            // pdf 影像保存路径
            String imgRootPath = Constant.ImagePath.ROOT_PATH;
            ExecuteResult<SysConfDictItemDTO> imgPathResult = dataDictCliFacade.findByEnNameAndItemCode("IMAGE_CONFIG","ROOT_PATH");
            if (!BeanUtil.isEmpty(imgPathResult.getResult())&&!BeanUtil.isEmpty(imgPathResult.getResult().getDicItemValue())) {
                imgRootPath = imgPathResult.getResult().getDicItemValue();
            }
            // pdf接口路径
            String urlPath = "";
            ExecuteResult<SysConfDictItemDTO> PDFUrl = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","VOUCHER_PDF_URL");
            if (!BeanUtil.isEmpty(PDFUrl.getResult())&&!BeanUtil.isEmpty(PDFUrl.getResult().getDicItemValue())) {
                urlPath = PDFUrl.getResult().getDicItemValue();
            }
            for (EdocCredentials noPdfCred : noPdfCreds) {
                // 凭证编号
                String credentialsNum = noPdfCred.getCredentialsNum();
                // 核算账套
                String hszt = noPdfCred.getExtField2();
                StringBuilder sb = new StringBuilder();
                sb.append(urlPath).append("?OAFunc=CUX_XMLREPROT_GL2&gl_deoc_value=").append(credentialsNum).append("&gl_leadger_id=").append(hszt);
                String pdfFinalpath = this.downLoadByUrl(sb.toString(), credentialsNum, pdfRootPath, imgRootPath, noPdfCred.getId());
                if (BeanUtil.isEmpty(pdfFinalpath)) {
                    continue;
                }
                noPdfCred.setPdfFileUrl(pdfFinalpath);
                edocCredentialsService.updateByIdSelective(noPdfCred);

                // 判断单据下对应得pdf是否已全部获取pdfgetPayVoucher
                EdocCredentials queryHasPdf = new EdocCredentials();
                queryHasPdf.setEdocHeaderId(noPdfCred.getEdocHeaderId());
                List<EdocCredentials> credsInEdoc = edocCredentialsService.query(queryHasPdf);
                if (credsInEdoc == null || credsInEdoc.size() == 0) {
                    continue;
                }
                boolean isAllHasPdf = true;
                for (EdocCredentials edocCredentials : credsInEdoc) {
                    // 存在未获取pdf的凭证
                    if (BeanUtil.isEmpty(edocCredentials.getPdfFileUrl())) {
                        isAllHasPdf = false;
                        break ;
                    }
                }
                // 修改单据状态为待归档
                if (isAllHasPdf) {
                    EdocHeader edocHeader = edocHeaderService.queryById(noPdfCred.getEdocHeaderId());
                    if (edocHeader != null) {
                        edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_ARCHIVED);
                        edocHeaderService.updateByIdSelective(edocHeader);
                    }
                }
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【FSSC-获取凭证PDF接口】异常 EBS，{}", e);
            result.addErrorMessage("【FSSC-获取凭证PDF接口】异常 EBS，请联系管理员");
        }
        return result;
    }*/

    /**
     * 下载pdf文件及保存pdf影像件
     * @param urlStr
     * @param filePreName
     * @param savePath
     * @param imgPath
     * @return
     */
    private String downLoadByUrl(String urlStr, String filePreName, String savePath, String imgPath, long id) {
        String finalPdfPath = null;
        URL url = null;
        FileOutputStream fos = null;
        InputStream inputStream = null;
        PDDocument doc = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为10秒
            conn.setConnectTimeout(10 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = DownPdfUtil.readInputStream(inputStream);

            // 保存pdf影像图片
            String imgFinalPath = imgPath  + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + filePreName;
            File filepath = new File(imgFinalPath);
            if (!filepath.exists()) {
                filepath.mkdirs();//如果目录不存在，创建目录
            }
            doc = PDDocument.load(getData);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                String imgName = filePreName + "_" + StringUtils.leftPad(String.valueOf(i + 1), 4, "0") + ".jpg";
                ImageIO.write(image, "JPG", new File(imgFinalPath + "/" + imgName));
                EdocImage edocImage = new EdocImage();
                edocImage.setEdocHeaderId(0L);
                edocImage.setImageRootPath(imgPath);
                edocImage.setImageUrl("/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + filePreName +  "/" + imgName);
                edocImage.setExtField1(String.valueOf(id));
                edocImageService.add(edocImage);
            }
            //pdf文件保存
            File saveDir = new File(savePath + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()));
            if (!(saveDir.isDirectory())) {
                saveDir.mkdirs();
            }
            finalPdfPath = saveDir + "/" + filePreName + ".pdf";
            File file = new File(finalPdfPath);
            fos = new FileOutputStream(file);
            fos.write(getData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(doc != null){
                try {
                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        BILLHEADER_LOGGER.error("pdf save path: " + finalPdfPath);
        return finalPdfPath;
    }

    /**
     * 获取费用类型
     * @return
     */
    /*@Override
    public ExecuteResult<String> getCostType() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            String url = null;
            ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("FSSC_CONFIG","FSSC_INTERFACE_URL");
            if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
                url = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
            }
            List<F_BODY> costTypeBody = FsscUtil.getCostTypeBody(Constant.FsscServiceId.GET_COST_TYPE, url);
            if (costTypeBody == null || costTypeBody.size() == 0) {
                result.addErrorMessage("【FSSC-获取费用类型接口】获取费用类型为空");
                return result;
            }
            for (F_BODY f_body : costTypeBody) {
                // 只取明细，过滤条件：F_LEAF=1 and F_ENABLE=1
                if (!"1".equals(f_body.getF_LEAF()) || !"1".equals(f_body.getF_ENABLE())) {
                    continue;
                }
                CostType costType = new CostType();
                costType.setfId(f_body.getF_ID());
                List<CostType> existCostTypes = costTypeService.query(costType);
                // 不存在，新增
                if (existCostTypes == null || existCostTypes.size() == 0) {
                    costType.setfName(f_body.getF_NAME());
                    costType.setfLevel(f_body.getF_LEVEL());
                    costType.setfLeaf(f_body.getF_LEAF());
                    costType.setfParent(f_body.getF_PARENT());
                    costType.setfEnable(f_body.getF_ENABLE());
                    costTypeService.add(costType);
                } else {
                    // 已存在，更新
                    CostType existCostType = existCostTypes.get(0);
                    existCostType.setfName(f_body.getF_NAME());
                    existCostType.setfLevel(f_body.getF_LEVEL());
                    existCostType.setfLeaf(f_body.getF_LEAF());
                    existCostType.setfParent(f_body.getF_PARENT());
                    existCostType.setfEnable(f_body.getF_ENABLE());
                    costTypeService.updateByIdSelective(existCostType);
                }
            }

            result.setSuccessMessage("【FSSC-获取费用类型接口】获取费用类型成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            BILLHEADER_LOGGER.error("【FSSC-获取费用类型接口】异常，{}", e);
            result.addErrorMessage("【FSSC-获取费用类型接口】异常，请联系管理员");
        }

        return result;
    }*/

    /**
     * 组装fList
     * @param edocHeaders
     * @return
     */
    private List<F_LIST> getFLists(List<EdocHeader> edocHeaders) {
        List<F_LIST> fListList = new ArrayList<>();
        for (int i = 0; i < edocHeaders.size(); i++) {
            F_LIST fList = new F_LIST();
            fList.setF_DJBH(edocHeaders.get(i).getEdocNo());
            fList.setF_DJLX("");
            fListList.add(fList);
        }
        return fListList;
    }

    /*************************************E FSSC webservice*************************************************/

    /**
     * 将待采集与待补录的单据任务更正为已采集状态
     * 并发起流程通知
     *
     * 2018年9月5日09:50:36修改：
     * 增加已采集状态的单据直接发起流程，跳过状态更新
     */
    @Override
    public ExecuteResult<String> completeUpload(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            EdocHeader edocHeader = edocHeaderService.queryById(id);
            // 待采集或补扫状态单据可以发起流程
            if (Constant.BizStatus.WAIT_SCAN.equals(edocHeader.getEdocTaskStatus()) || Constant.BizStatus.WAIT_ADD_DATA.equals(edocHeader.getEdocTaskStatus())) {
                // 对于新希望项目既有上传任务又有扫描任务的单据
                // 在结束扫描时判断是否已经结束了扫描任务
                // 如果结束了扫描任务，就把状态修改为已采集
                // 否则返回完成提交失败
//                if (edocHeader.getUploadType().equals(Constant.UploadType.SCAN_ADN_UPLOAD)) {
//                    // 完成上传任务
//                    edocHeader.setUploadFinished("1");
//                    edocHeaderService.updateByIdSelective(edocHeader);
//                    if ("1".equals(BeanUtil.isEmpty(edocHeader.getScanFinished())?"-1":edocHeader.getScanFinished())) { // 扫描已完成
//                        edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM);
//                        edocHeaderService.updateByIdSelective(edocHeader);
//                        this.inform(id);// 通知
//                    }else {
//                        result.addErrorMessage("该单据没有结束扫描！");
//                        return result;
//                    }
//                }else { //正常流程
                edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM);
                edocHeaderService.updateByIdSelective(edocHeader);
                //this.inform(id);// 通知
//                }
                result.setSuccessMessage("修改影像任务状态成功");
            }else if (Constant.BizStatus.WAIT_CONFIRM.equals(edocHeader.getEdocTaskStatus())) {
                //this.inform(id);// 通知
                result.setSuccessMessage(String.format("工单'%s'已发起通知", id));
            }else {
                result.addErrorMessage("该工单已在流程中，发起流程失败。");
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("修改影像任务状态异常{}",e);
            result.setErrorMessages(Arrays.asList("系统异常，请联系管理员"));
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> matchEdoc(EdocHeaderDTO waitMatch_Edoc, EdocHeaderDTO waitScan_edoc) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<EdocHeaderDTO>();
        try {
            ExecuteResult<EdocHeaderDTO> waitMatchEdoc = this.queryEdocHeaderByCondition(waitMatch_Edoc);
            if (!waitMatchEdoc.isSuccess()) {
                result.addErrorMessage(waitMatchEdoc.getErrorMessages().get(0));
                return result;
            }
            ExecuteResult<EdocHeaderDTO> waitScanEdoc = this.queryEdocHeaderByCondition(waitScan_edoc);
            if (!waitScanEdoc.isSuccess()) {
                result.addErrorMessage(waitScanEdoc.getErrorMessages().get(0));
                return result;
            }

            EdocImageDTO edocImageDTO = new EdocImageDTO();
            edocImageDTO.setEdocHeaderId(waitMatchEdoc.getResult().getId());
            ExecuteResult<List<EdocImageDTO>> edocImagesResult = edocImageFacade.findEdocImage(edocImageDTO);
            if (!edocImagesResult.isSuccess()) {
                result.addErrorMessage(edocImagesResult.getErrorMessages().get(0));
                return result;
            }
            for (EdocImageDTO eIDTO : edocImagesResult.getResult()) {
                eIDTO.setEdocHeaderId(waitScanEdoc.getResult().getId());
                edocImageFacade.updateImages(eIDTO);
            }

            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setEdocHeaderId(waitMatchEdoc.getResult().getId());
            ExecuteResult<PageInfo<InvoiceDTO>> invoicesResult = invoiceFacade.queryInvoices(invoiceDTO);
            if (!invoicesResult.isSuccess()) {
                result.addErrorMessage(invoicesResult.getErrorMessages().get(0));
                return result;
            }
            for (InvoiceDTO ivDTO : invoicesResult.getResult().getList()) {
                ivDTO.setEdocHeaderId(waitScanEdoc.getResult().getId());
//                invoiceFacade.updateInv(ivDTO);
            }

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setEdocHeaderId(waitMatchEdoc.getResult().getId());
            List<InvoiceItem> invoiceItems = invoiceItemService.query(invoiceItem);
            for (InvoiceItem ii : invoiceItems) {
                ii.setEdocHeaderId(waitScanEdoc.getResult().getId());
                invoiceItemService.updateById(ii);
            }
            waitMatchEdoc.getResult().setDeleted(1);
            ExecuteResult<Integer> integerExecuteResult = this.updateEdocHeader(waitMatchEdoc.getResult());
            if (!integerExecuteResult.isSuccess()) {
                result.addErrorMessage(integerExecuteResult.getErrorMessages().get(0));
                return result;
            }
            result.setResult(waitScan_edoc);
        } catch (Exception e) {
            result.addErrorMessage("matchEdoc调用异常");

        }
        return result;

    }

    @Override
    public ExecuteResult<PageInfo<EdocHeaderDTO>> findExcEdoc(EdocHeaderDTO edocHeaderDTO) {


        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
            EdocHeaderModel edocHeaderModel = DozerUtil.map(edocHeaderDTO, EdocHeaderModel.class);
            Page<EdocHeaderModel> edocHeaderModelPage = edocHeaderService.selectExcEdoc(edocHeaderModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(edocHeaderModelPage.toPageInfo(), EdocHeaderDTO.class);
            result.setResult(pageInfo);

        } catch (Exception e) {
            result.addErrorMessage("findExcEdoc接口调用失败");
        }
        return result;
    }

    /*@Override
    public ExecuteResult<String> inform() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //找到所有状态为已采集的单据
            EdocHeader edocHeader = new EdocHeader();
            edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM);
            edocHeader.setEdocType(Constant.BizTypeCode.ACCOUNT_TYPE_CODE);
            edocHeader.setDeleted(Constant.DeleteFlag.DEL_NO);
            List<EdocHeader> edocHeaderList = edocHeaderService.query(edocHeader);

            if (BeanUtil.isEmpty(edocHeaderList)) {
                BILLHEADER_LOGGER.error("不存在需要通知的单据");
                result.addErrorMessage("不存在需要通知的单据");
                return result;
            }

            for (EdocHeader e:edocHeaderList) {
                Map<String,String> requestMap = new HashMap<>();
                String processId = e.getProcessId();
                String fdModelName = e.getFdModelName();
                requestMap.put("processId", BeanUtil.isEmpty(processId)?"":processId);
                requestMap.put("fdModelName", BeanUtil.isEmpty(fdModelName)?"":fdModelName);
                requestMap.put("operationType", "handler_pass");
                requestMap.put("auditNote", "已上传");
                requestMap.put("handler", "imgyingxiang");
                BILLHEADER_LOGGER.info("通知费控："+requestMap.toString());

                String url = null;
                ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("SYNC_FROM_OA","OA_INFORM_URL");
                if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
                    url = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
                }
                Map<String, String> responseMap = OAUtil.approveProcess(requestMap, url);
                BILLHEADER_LOGGER.info("接收返回："+responseMap.toString());
                //System.out.println("code: " + responseMap.get("code"));
                //System.out.println("msg: " + responseMap.get("msg"));
                String code = responseMap.get("code");
                String msg = responseMap.get("msg");
                if ("T".equals(code) || "P".equals(code)) {
                    //费空返回成功，修改影像任务状态为已通知
                    e.setEdocTaskStatus(Constant.BizStatus.INFORMED);
                    edocHeaderService.updateByIdSelective(e);
                }
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("通知异常{}",e);
            result.setErrorMessages(Arrays.asList("通知异常"));
        }
        return result;
    }*/

    /*@Override
    public ExecuteResult<String> inform(Long edocHeaderId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            EdocHeader edocHeader = edocHeaderService.queryById(edocHeaderId);
            if (BeanUtil.isEmpty(edocHeader)) {
                result.addErrorMessage("[发起流程]失败, 没有找到对应单据");
                return result;
            }
            if (!Constant.BizStatus.WAIT_CONFIRM.equals(edocHeader.getEdocTaskStatus()) && !Constant.BizStatus.WAIT_ADD_DATA.equals(edocHeader.getEdocTaskStatus())) {
                BILLHEADER_LOGGER.info("[发起流程]失败, 只有已采集或补扫状态单据可以发起流程");
                result.addErrorMessage("只有已采集或补扫状态单据可以发起流程");
                return result;
            }
            Map<String,String> requestMap = new HashMap<>();

            String processId = edocHeader.getProcessId();
            String fdModelName = edocHeader.getFdModelName();
            requestMap.put("processId", BeanUtil.isEmpty(processId)?"":processId);
            requestMap.put("fdModelName", BeanUtil.isEmpty(fdModelName)?"":fdModelName);
            requestMap.put("operationType", "handler_pass");
            requestMap.put("auditNote", "已上传");
            requestMap.put("handler", "imgyingxiang");
            BILLHEADER_LOGGER.info("通知费控："+requestMap.toString());

            String url = null;
            ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("SYNC_FROM_OA","OA_INFORM_URL");
            if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
                url = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
            }
            Map<String, String> responseMap = OAUtil.approveProcess(requestMap, url);
            BILLHEADER_LOGGER.info("接收返回："+responseMap.toString());
            //System.out.println("code: " + responseMap.get("code"));
            //System.out.println("msg: " + responseMap.get("msg"));
            String code = responseMap.get("code");
            String msg = responseMap.get("msg");
            if ("T".equals(code) || "P".equals(code)) {
                //费空返回成功，修改影像任务状态为已通知
                edocHeader.setEdocTaskStatus(Constant.BizStatus.INFORMED);
                //去除有标记重扫记录标识
                edocHeader.setExtField5("0");
                edocHeaderService.updateByIdSelective(edocHeader);
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("通知异常{}",e);
            result.setErrorMessages(Arrays.asList("通知异常"));
        }
        return result;
    }*/

    @Override
    public ExecuteResult<EdocHeaderDTO> findEdocHeaderByCredentialsNo(String credentialsNo) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        try {
            EdocCredentials edocCredentials = new EdocCredentials();
            edocCredentials.setCredentialsNum(credentialsNo.trim());
            List<EdocCredentials> edocCredentialsList = edocCredentialsService.query(edocCredentials);
            if (!BeanUtil.isEmpty(edocCredentialsList)&&edocCredentialsList.size()==1) {
                edocCredentialsList.get(0).getVoucherStatus();
                EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(edocCredentialsList.get(0).getEdocHeaderId()));
                EdocHeaderDTO edocHeaderDTO=DozerUtil.map(edocHeader,EdocHeaderDTO.class);
                edocHeaderDTO.setVoucherStatus(edocCredentialsList.get(0).getVoucherStatus());
                result.setResult(edocHeaderDTO);
                result.setSuccessMessage("查询成功");
            }else {
                result.addErrorMessage("凭证数据异常");
            }
        }catch (Exception e) {
            BILLHEADER_LOGGER.error("查询异常{}",e);
            result.setErrorMessages(Arrays.asList("查询异常"));
        }
        return result;
    }

    @Override
    public String findEdocHeaderPageTw_noMatch(EdocHeaderDTO headerDTO) {
        ExecuteResult<PageInfo<EdocHeaderDTO>> result = new ExecuteResult<>();
        JSONObject resultObject = null;
        Date createTimeEnd=headerDTO.getCreateTimeEnd();
        if(!BeanUtil.isEmpty(createTimeEnd)) {
            headerDTO.setCreateTimeEnd((DateUtil.getNextDay(createTimeEnd,1)));
        }
        try {
            EdocHeaderModel headerModel = DozerUtil.map(headerDTO, EdocHeaderModel.class);

            Page<EdocHeaderModel> page = edocHeaderService.queryEdocHeaderPageTw_noMatch(headerModel);
            PageInfo<EdocHeaderDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), EdocHeaderDTO.class);
            result.setSuccessMessage("分页查询成功");
            result.setResult(pageInfo);
            resultObject = this.jsonFactory(result, pageInfo.getList().get(0));
        } catch (Exception e) {
            result.addErrorMessage("分页查询失败");
            BILLHEADER_LOGGER.error("查询异常,原因{}", e);
            return JSON.toJSONString(result);
        }
        return resultObject == null ? "" : resultObject.toString();
    }

    @Override
    public ExecuteResult<List<TreeDTO>> queryCostTypeToTree(Integer pageNum, Integer pageSize, String fNameOrFid) {

        ExecuteResult<List<TreeDTO>> result = new ExecuteResult<List<TreeDTO>>();
        try {
            List<TreeModel> treeModels = costTypeService.queryCostTypeToTree(pageNum,pageSize, fNameOrFid);
            if (treeModels.size() == 0) {
                result.addErrorMessage("查询数据为空！");
                return result;
            }
            result.setResult(PlatformBeanUtils.copyProperties(TreeDTO.class, treeModels));
            for (TreeDTO treeDTO : result.getResult()){
                if (org.springframework.util.StringUtils.isEmpty(treeDTO.getCode())) {
                    treeDTO.setText(treeDTO.getText());
                } else {
                    treeDTO.setText(treeDTO.getCode() + "_" + treeDTO.getText());
                }
            }
            result.setSuccessMessage("查询费用类型成功！");
        } catch (Exception e) {
            result.addErrorMessage("查询费用类型失败！");
            BILLHEADER_LOGGER.error("查询所有费用类型失败！", e);
        }
        return result;
    }


    @Override
    public ExecuteResult<List<EdocReportDTO>> parseEdocReport(Map<String,Object> queryTime) {
        ExecuteResult<List<EdocReportDTO>> result = new ExecuteResult<>();
        try {
            List<EdocReportModel> edocReportModelList = edocHeaderService.selectEdocReport(queryTime);
//            if ("queryDistinct".equals(queryTime.get("flag"))) {
//                for (int i=0;i<edocReportModelList.size();i++) {
//                    if (BeanUtil.isEmpty(edocReportModelList.get(i).getOperator())) {
//                        edocReportModelList.remove(i);
//                    }
//                }
//            }
            List<EdocReportDTO> edocReportDTOList = DozerUtil.mapList(edocReportModelList,EdocReportDTO.class);
            result.setResult(edocReportDTOList);
            result.setSuccessMessage("生成成功");
        }catch (Exception e) {
            result.addErrorMessage("生成失败！");
            BILLHEADER_LOGGER.error("生成失败！", e);
        }
        return result;
    }


    @Override
    public ExecuteResult<Long> createHeader(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<Long> result = new ExecuteResult<>();
        try {
            // 没有单号需要自动生成单号
            if (BeanUtil.isEmpty(edocHeaderDTO.getEdocNo())) {

                String edocNo = "BK" + new SimpleDateFormat("yyyymmdd").format(new Date()).toString()+(int)(Math.random()*9000+1000);
                //String edocNo = "BK"+String.valueOf(edocHeaderService.createEdocFlowNum());
                edocHeaderDTO.setEdocNo(edocNo);
            }
            edocHeaderDTO.setJsonData("{}");
            EdocHeader queryCondition = new EdocHeader();
            queryCondition.setEdocNo(edocHeaderDTO.getEdocNo());
            List<EdocHeader> edocHeaderList = edocHeaderService.findValidEdocHeader(queryCondition);
            if (BeanUtil.isEmpty(edocHeaderList)) {
                EdocHeader edocHeader = DozerUtil.map(edocHeaderDTO,EdocHeader.class);
                edocHeaderService.add(edocHeader);
                List<EdocHeader> edocHeaderListInDB = edocHeaderService.findValidEdocHeader(queryCondition);
                result.setResult(edocHeaderListInDB.get(0).getId());
                result.setSuccessMessage("创建edocHeader成功,edocNo:"+edocHeaderDTO.getEdocNo());
            }else {
                result.addErrorMessage("该单据号已存在");
            }
        }catch (Exception e) {
            result.addErrorMessage("创建edocHeader执行失败！");
            BILLHEADER_LOGGER.error("创建edocHeader执行失败！{}", e);
        }
        return result;
    }

    @Transactional
    @Override
    public ExecuteResult<Long> createHeader(EdocHeaderDTO edocHeaderDTO, List<EdocImageDTO> edocImageDTOList) {
        ExecuteResult<Long> result = new ExecuteResult<>();
        try {
            result = this.createHeader(edocHeaderDTO);
            Long edocHeaderId = result.getResult();
            if (BeanUtil.isEmpty(edocHeaderId)) {// 创建单据失败
                result.setErrorMessages(Arrays.asList("创建edocHeader执行失败！"));
                return result;
            }else {// 单据创建成功，继续存入影像
                int pageNo = 0;
                for (EdocImageDTO e:edocImageDTOList) {
                    edocImageFacade.uploadFiles("", "", "", "" + (++pageNo), e.getImageUrl(), edocHeaderId, e.getEdocImageType());
                }
            }
        }catch (Exception e) {
            result.addErrorMessage("创建edocHeader执行失败！");
            BILLHEADER_LOGGER.error("创建edocHeader执行失败！{}", e);
        }
        return result;
    }


    /*@Transactional
    @Override
    public ExecuteResult<Long> createHeader(EdocHeaderDTO edocHeaderDTO, String pdfFilePath, String edocImageType) {
        ExecuteResult<Long> result = new ExecuteResult<>();
        try {
            ////TODO 解析银行回单，获取单号


            result = this.createHeader(edocHeaderDTO);
            Long edocHeaderId = result.getResult();
            EdocHeader edocHeaderInDB = edocHeaderService.queryById(edocHeaderId);
            // 组装文件保存路径并创建目录
            String rootPath = LoadConfig.get("elecInv.storePath");
            ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","UPLOAD_PATH");
            if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
                rootPath = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
            }
            String parentPath = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"+edocHeaderInDB.getEdocNo();
            String finalPath = rootPath+"/"+parentPath;
            String fileName = "BK"+new SimpleDateFormat("yyyyMMdd").format(new Date())+(int)(Math.random()*9000+1000)+".png";
            File filepath = new File(finalPath, fileName);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();//如果目录不存在，创建目录
            }
            // 生成图片
            this.pdfToImage(pdfFilePath,finalPath+"/"+fileName);
            //为目录赋权限
            if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
                String command = "chmod -R" + " 755" + " " + rootPath;
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(command);
                BILLHEADER_LOGGER.info("修改权限{}", command);
            }
            BILLHEADER_LOGGER.info("生成图片文件成功:"+finalPath+"/"+fileName);
            //创建影像
            EdocImageDTO edocImageDTO = new EdocImageDTO();
            edocImageDTO.setEdocImageType(edocImageType);
            edocImageDTO.setImageUrl("/"+parentPath+"/"+fileName);
            //List<EdocImageDTO> edocImageDTOList = new ArrayList<>();
            //edocImageDTOList.add(edocImageDTO);
            Integer maxPageNum = edocImageFacade.queryMaxPagNum(edocHeaderId).getResult();
            if (BeanUtil.isEmpty(maxPageNum)) {
                maxPageNum = 1;
            }
            edocImageFacade.uploadFiles("", "", "", "" + (maxPageNum++), edocImageDTO.getImageUrl(), edocHeaderId, edocImageDTO.getEdocImageType());
        }catch (Exception e) {
            result.addErrorMessage("创建edocHeader执行失败！");
            BILLHEADER_LOGGER.error("创建edocHeader执行失败！{}", e);
        }
        return result;
    }*/


    private void pdfToImage(String pdfFilePath,String desPath) {
        try {
            File pdf = new File(pdfFilePath);

            PDDocument doc = PDDocument.load(pdf);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                ImageIO.write(image, "PNG", new File(desPath)); //生成电子发票图片
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public ExecuteResult<String> updateEdocNo(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            EdocHeader edocHeader = edocHeaderService.queryById(edocHeaderDTO.getId());
            edocHeader.setEdocNo(edocHeaderDTO.getEdocNo());
            edocHeaderService.updateByIdSelective(edocHeader);

            //修改图片的单证类型
            EdocImage edocImage = edocImageService.queryById(edocHeaderDTO.getImgId());
            if ( !edocHeaderDTO.getEdocImageType().equals(edocImage.getEdocImageType())) {
                edocImage.setEdocImageType(edocHeaderDTO.getEdocImageType());
                edocImageService.updateByIdSelective(edocImage);
            }

            result.setSuccessMessage("更新成功");
        }catch (Exception e) {
            result.addErrorMessage("更新失败！");
            BILLHEADER_LOGGER.error("更新失败！{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocHeaderDTO> changeNormal(EdocHeaderDTO headerDTO) {
        ExecuteResult<EdocHeaderDTO> result = new ExecuteResult<>();
        try {

            EdocHeaderModel edocHeader = DozerUtil.map(headerDTO, EdocHeaderModel.class);
            //根据edocNoc查询单据是否存在
            List<EdocHeaderModel> edocHeaderModelList = edocHeaderService.changeNormal(edocHeader);
            if(edocHeaderModelList.size()==0 ) {
                result.setSuccessMessage("该单据不存在,无法置为正常");
            } else if (edocHeaderModelList.size() == 1){
                EdocHeaderModel edocHeaderModel = edocHeaderModelList.get(0);

                if (!edocHeaderModel.getId().equals(edocHeader.getId())){//异常id和正常id
                    //待采集-07 正在上传影像-00 待补录-02 重扫-32 单票重扫-31 呆匹配33 待归档-27
                    // 只有目标单据为待采集状态
                    if (Constant.BizStatus.WAIT_SCAN.equals(edocHeaderModel.getEdocTaskStatus())) {
                     /*if ("07".equals(edocHeaderModel.getEdocTaskStatus()) || "00".equals(edocHeaderModel.getEdocTaskStatus()) ||
                             "02".equals(edocHeaderModel.getEdocTaskStatus()) ||"32".equals(edocHeaderModel.getEdocTaskStatus()) ||
                             "31".equals(edocHeaderModel.getEdocTaskStatus()) ||"33".equals(edocHeaderModel.getEdocTaskStatus()) ||
                             "27".equals(edocHeaderModel.getEdocTaskStatus())  ){*/
                        ////需要把该单据下所有发票信息和影像关联的数据--重新绑定
                        int num = edocHeaderService.updateEdocNoByEdoc(edocHeader);
                        //原待匹配的影像件进行逻辑删除--前端将待匹配影像件id
                        int num1 = edocHeaderService.deleteEdocNoByEdoc(edocHeader.getId());
                        //edoc_header 将正常单据下影像状态改为已采集和scan_finished由0改为1
                        int num2 = edocHeaderService.updateEdocNo_HeaderByEdoc(edocHeader);
                        result.setSuccessMessage("置为正常成功");
                    }else{
                        result.setSuccessMessage("目标单据为待采集,才允许置为正常");
                    }

                }else if (edocHeaderModel.getId().equals(edocHeader.getId())){//要置为正常的是本身
                    edocHeaderModel.setEdocIsMatched("1");
                    EdocHeader edocHead = DozerUtil.map(edocHeaderModel, EdocHeader.class);
                    edocHeaderService.updateById(edocHead);
                }else{
                    result.setSuccessMessage("异常单据,无法置为正常");
                }
            }else if (edocHeaderModelList.size() > 1){
                result.setSuccessMessage("异常单据,无法置为正常");
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("查询失败", e);
            result.addErrorMessage("置为正常失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocHeaderDTO>> queryEdocHeaderListByCondition(EdocHeaderDTO queryEdocHeader) {
        ExecuteResult<List<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
            Assert.notNull(queryEdocHeader, "查询条件不能为空");
            List<EdocHeader> edocHeaderList = edocHeaderService.query(DozerUtil.map(queryEdocHeader, EdocHeader.class));
            if (BeanUtil.isEmpty(edocHeaderList)) {
                result.addErrorMessage("未查找到对应单据");
                return result;
            }
            List<EdocHeaderDTO> edocHeaderDTOS = DozerUtil.mapList(edocHeaderList, EdocHeaderDTO.class);
            result.setResult(edocHeaderDTOS);
            result.setSuccessMessage("查询成功");
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("[根据条件查询单据]查询失败");
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    //删除异常影像件
    @Override
    public ExecuteResult<String> deleteExpEdoc(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //删除异常影像件 同步1.单据 2.图片 3.发票
            EdocHeader edocHeader = edocHeaderService.queryById(id);
            int i = edocHeaderService.deleteEdocNoByEdoc(id);
            if(i > 0){
                List<EdocImage> edocImages = edocImageService.selectAllImagesByEdocHeaderId(id);
                edocImageService.delImagesByEdocHeaderId(id);//删除图片
                if (!BeanUtil.isEmpty(edocImages)&&edocImages.size() > 0){
                    for (EdocImage edocImage:edocImages){

                        //再根据invoice的id删除明细
                        ExecuteResult<List<InvoiceDTO>> listExecuteResult = invoiceFacade.queryInvsByImgId(edocImage.getId());//根据图片id查询发票信息
                        List<InvoiceDTO> invoiceDTOList = listExecuteResult.getResult();
                        //根据图片删除invoice
                        // invoiceFacade.deleteImage(edocImage.getId());
                        int m = invoiceFacade.deleteInvoiceByImageId(edocImage.getId());
                        if (!BeanUtil.isEmpty(invoiceDTOList)&&invoiceDTOList.size() > 0){
                            for (InvoiceDTO invoiceDTO:invoiceDTOList) {
                                invoiceItemService.deleteInvoiceItemByInvoiceId(invoiceDTO.getId());
                            }
                        }

                    }
                }
                //查询冲突单据中包含该影像件的单据编号
                ExecuteResult<List<InvoiceDTO>> listResult = invoiceFacade.queryInvsByEdocHeaderId(edocHeader.getEdocNo());
                List<InvoiceDTO> invoiceList = listResult.getResult();
                if(!BeanUtil.isEmpty(invoiceList) && invoiceList.size() > 0){
                    for (InvoiceDTO invoiceDTO:invoiceList) {
                        if (!BeanUtil.isEmpty(invoiceDTO.getExtField1()) &&!BeanUtil.isEmpty(edocHeader.getEdocNo())
                                &&invoiceDTO.getExtField1().indexOf(edocHeader.getEdocNo()) != -1 ){//包含单据号
                            int y = invoiceDTO.getExtField1().indexOf(edocHeader.getEdocNo());
                            String beforeZF = invoiceDTO.getExtField1().substring(y-1, y);//单据号前一个字符
                            String afterZF = invoiceDTO.getExtField1().substring(y+edocHeader.getEdocNo().length(), y+edocHeader.getEdocNo().length()+1);//单据号后一个字符
                            if (beforeZF.equals("[") && afterZF.equals("]")){//仅此一个单据冲突
                                //仅有该单据冲突
                                invoiceDTO.setCheckStatus("11");
                                invoiceDTO.setExtField1(null);
                                InvoiceModel invoiceModel = DozerUtil.map(invoiceDTO, InvoiceModel.class);
                                invoiceService.updateInvoice(invoiceModel);
                                //  invoiceFacade.updateInv(invoiceDTO);
                            }else if(beforeZF.equals("[") && afterZF.equals(",")){
                                String extField1 = beforeZF.concat(invoiceDTO.getExtField1().substring(y+edocHeader.getEdocNo().length()+1));
                                invoiceDTO.setExtField1(extField1);
                                InvoiceModel invoiceModel = DozerUtil.map(invoiceDTO, InvoiceModel.class);
                                invoiceService.updateInvoice(invoiceModel);
                                // invoiceFacade.updateInv(invoiceDTO);
                            }else if(beforeZF.equals(" ") && afterZF.equals("]")){
                                String extField1 = invoiceDTO.getExtField1().substring(0, y-1).concat(afterZF);
                                invoiceDTO.setExtField1(extField1);
                                InvoiceModel invoiceModel = DozerUtil.map(invoiceDTO, InvoiceModel.class);
                                invoiceService.updateInvoice(invoiceModel);
                                // invoiceFacade.updateInv(invoiceDTO);
                            }else if(beforeZF.equals(" ") && afterZF.equals(",")){
                                String extField1 = invoiceDTO.getExtField1().substring(0, y).concat(invoiceDTO.getExtField1().substring(y+edocHeader.getEdocNo().length()+1));
                                invoiceDTO.setExtField1(extField1);
                                InvoiceModel invoiceModel = DozerUtil.map(invoiceDTO, InvoiceModel.class);
                                invoiceService.updateInvoice(invoiceModel);
                                //  invoiceFacade.updateInv(invoiceDTO);
                            }
                        }
                    }
                }

                result.setSuccessMessage("删除成功");
            }else {
                result.addErrorMessage("删除失败,存在异常数据，请联系管理员！");
                return result;
            }

        }catch (Exception e) {
            result.addErrorMessage("删除失败,存在异常数据，请联系管理员！");
            BILLHEADER_LOGGER.error("删除失败！{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocHeaderDTO>> queryEdocNoCheckRealResult(String edocNo) {
        ExecuteResult<List<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
            List<EdocHeaderModel> edocHeaderModelList = edocHeaderService.queryEdocNoCheckRealResult(edocNo);
            if (BeanUtil.isEmpty(edocHeaderModelList)) {
                result.addErrorMessage("查询失败");
                BILLHEADER_LOGGER.error("查询失败");
            }
            List<EdocHeaderDTO> edocHeaderDTOS = DozerUtil.mapList(edocHeaderModelList, EdocHeaderDTO.class);
            result.setResult(edocHeaderDTOS);
        } catch (Exception e) {
            result.addErrorMessage("查询失败");
            BILLHEADER_LOGGER.error("查询异常！原因:{}", e);
        }
        return result;
    }

    /**
     *  只有单据下存在未验真成功的发票才返回false
     *   1.单据下没有发票, 返回:成功
     *   2.单据下发票全部验真通过,返回成功
     *   3.单据下存在不是验真成功的发票,返回失败
     *
     *      11: 系统验真真票
     "      12: 不通过
     "      18: 人工验真真票
     *
     * @param id
     * @return 1 通过， 2 不通过 3 待验真
     */
    @Override
    public String queryRealResultByEdoc(Long id) {
        String result = "1";
        try {
            Assert.notNull(id, "单据id不能为空");
            List<EdocImage> edocImages = edocImageService.selectAllImagesByEdocHeaderId(id);
            //单据下没有图片,返回成功
            if (BeanUtil.isEmpty(edocImages)) {
                return result;
            }
            // 存在验真失败发票，返回验真失败
            for (EdocImage edocImage : edocImages) {
                ExecuteResult<List<InvoiceDTO>> invListResults = invoiceFacade.queryInvsByImgId(edocImage.getId());
                if (!BeanUtil.isEmpty(invListResults) && !BeanUtil.isEmpty(invListResults.getResult())) {
                    List<InvoiceDTO> invList = invListResults.getResult();
                    for (InvoiceDTO invoiceDTO : invList) {
                        if ("12".equals(invoiceDTO.getCheckDetails())) {
                            result = "2";
                            return result;
                        }
                    }
                }
            }
            // 不存在验真失败发票情况下， 存在未验真成功发票，返回待验真
            for (EdocImage edocImage : edocImages) {
                ExecuteResult<List<InvoiceDTO>> invListResults = invoiceFacade.queryInvsByImgId(edocImage.getId());
                if (!BeanUtil.isEmpty(invListResults) && !BeanUtil.isEmpty(invListResults.getResult())) {
                    List<InvoiceDTO> invList = invListResults.getResult();
                    for (InvoiceDTO invoiceDTO : invList) {
                        if (!"11".equals(invoiceDTO.getCheckDetails()) && !"18".equals(invoiceDTO.getCheckDetails())) {
                            result = "3";
                            return result;
                        }
                    }
                }
            }
        } catch (Exception e) {
            result = "3";
            e.printStackTrace();
        }


        return result;
    }

    /**
     * 查询单据下发票是否存在：法人公司不匹配,发票数据异常
     *      1.如果存在返回：法人公司不匹配,发票数据异常
     *      2.不存在返回：验真通过
     * @param id
     * @return
     */
    @Override
    public String getCheckRealRemark(Long id) {
        String result = "验真通过";

        try {
            Assert.notNull(id, "单据id不能为空");
            List<EdocImage> edocImages = edocImageService.selectAllImagesByEdocHeaderId(id);
            //单据下没有图片,返回通过
            if (BeanUtil.isEmpty(edocImages)) {
                return result;
            }

            for (EdocImage edocImage : edocImages) {
                ExecuteResult<List<InvoiceDTO>> invListResults = invoiceFacade.queryInvsByImgId(edocImage.getId());
                if (!BeanUtil.isEmpty(invListResults) && !BeanUtil.isEmpty(invListResults.getResult())) {
                    List<InvoiceDTO> invList = invListResults.getResult();
                    for (InvoiceDTO invoiceDTO : invList) {
                        if ("法人公司不匹配,发票数据异常".equals(invoiceDTO.getExtField2())) {
                            result = "法人公司不匹配,发票数据异常";
                            return result;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public ExecuteResult<String> archiveRescan(Long edocHeaderId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(edocHeaderId, "获取单据id失败");
            EdocHeader header = edocHeaderService.queryById(edocHeaderId);
            if (null == header) {
                result.addErrorMessage("单据可能已被删除, 请刷新后重试");
                return result;
            }
            if (Arrays.asList(Constant.BizStatus.WAIT_ARCHIVED,
                    Constant.BizStatus.WAIT_BOXING,
                    Constant.BizStatus.TOBEARCHIVED,
                    Constant.BizStatus.ARCHIVED).contains(header.getEdocTaskStatus())) {

                result.addErrorMessage("归档中的单据不允许补扫!");
                return result;
            }
            // 更新单据状态为档案岗补扫
            header.setEdocTaskStatus(Constant.BizStatus.ARCHIVE_WAIT_ADD_DATA);
            edocHeaderService.updateByIdSelective(header);

            result.setSuccessMessage("操作成功");
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("档案岗标记补扫异常，原因:{}", e);
            result.addErrorMessage("操作失败");
        }
        return result;
    }

    /*************************************S 资金pdf*************************************************/
    /**
     * 1.遍历资金前一天pdf目录保存至影像当天目录
     *      如: 今天是201808月17号, 我们回去获取前一天资金20180816目录下的文件,然后copy到我们自己的文件夹20180817下
     *
     *      ！！！20181029 调整为处理资金当天文件，因为月末过帐，不能等到下一天再处理
     * 2.获取pdf文件名
     * 3.复制pdf到影像指定目录
     */
    /*@Override
    public ExecuteResult<String> copyZjPdf() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 资金存储pdf路径
            String zjRootPath = LoadConfig.get("ZJ_ROOT_PATH");
            ExecuteResult<SysConfDictItemDTO> zjRootPathExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("NEWHOPE_ZJ","ZJ_ROOT_PATH");
            if (!BeanUtil.isEmpty(zjRootPathExecuteResult.getResult())&&!BeanUtil.isEmpty(zjRootPathExecuteResult.getResult().getDicItemValue())) {
                zjRootPath = zjRootPathExecuteResult.getResult().getDicItemValue();
            }
            // 影像保存资金pdf路径
            String edocRootPath = LoadConfig.get("EDOC_ZJ_ROOT_PATH");
            ExecuteResult<SysConfDictItemDTO> edocRootPathExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("NEWHOPE_ZJ","EDOC_ZJ_ROOT_PATH");
            if (!BeanUtil.isEmpty(edocRootPathExecuteResult.getResult())&&!BeanUtil.isEmpty(edocRootPathExecuteResult.getResult().getDicItemValue())) {
                edocRootPath = edocRootPathExecuteResult.getResult().getDicItemValue();
            }
            //String ZjDatePath = DateUtil.dateToString(DateUtil.getNextDay(new Date(), -1), "yyyyMMdd");
            // 由处理资金前一天目录改为处理资金当天目录
            String ZjDatePath = DateUtil.dateToString(new Date(),  "yyyyMMdd");
            // 当天获取资金前一天上传文件目录
            String zjPdfPath = zjRootPath + File.separator + ZjDatePath;
            File zjFile = new File(zjPdfPath);
            if (!zjFile.exists()) {
                BILLHEADER_LOGGER.error("【copy资金pdf至影像】，资金路径:{}不存在", zjPdfPath);
                result.addErrorMessage("资金文件目录" + zjPdfPath + "不存在");
                return result;
            }
            // 当天影像存储目录, 今日目录
            String edocDatePath = DateUtil.dateToString(new Date(), "yyyyMMdd");
            String edocPdfPath =  edocRootPath + "/" + edocDatePath;
            File edocFile = new File(edocPdfPath);
            if (!zjFile.exists()) {
                //如果目录不存在，创建目录
                edocFile.mkdirs();
            }
            // copy资金当天pdf目录至影像pdf保存路径
            String[] filePath = zjFile.list();
            for (int i = 0; i < filePath.length; i++) {
                if (new File(zjPdfPath  + "/" + filePath[i]).isFile()) {
                    File source = new File(zjPdfPath + File.separator + filePath[i]);
                    File dest = new File(edocPdfPath + File.separator + filePath[i]);
                    if (!dest.exists()) {
                        //如果目录不存在，创建目录
                        edocFile.mkdirs();
                    }
                    *//*FileUtil.copyFileUsingFileChannels(source, dest);*//*
                }
            }
            result.setSuccessMessage("【copy资金pdf至影像成功】");
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【copy资金pdf至影像】异常，{}", e);
            result.addErrorMessage("【copy资金pdf至影像】异常，请联系管理员");
        }
        return result;
    }*/

    /**
     * 处理资金pdf文件
     */
    /*@Override
    public ExecuteResult<String> handZjPdf() {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 获取pdf存储目录
            String edocRootPath = LoadConfig.get("EDOC_ZJ_ROOT_PATH");
            ExecuteResult<SysConfDictItemDTO> edocRootPathExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("NEWHOPE_ZJ","EDOC_ZJ_ROOT_PATH");
            if (!BeanUtil.isEmpty(edocRootPathExecuteResult.getResult())&&!BeanUtil.isEmpty(edocRootPathExecuteResult.getResult().getDicItemValue())) {
                edocRootPath = edocRootPathExecuteResult.getResult().getDicItemValue();
            }

            String date = DateUtil.dateToString(new Date(), "yyyyMMdd");
            String edocPdfPath =  edocRootPath + "/" + date;
            File edocFile = new File(edocPdfPath);
            if (!edocFile.exists()) {
                BILLHEADER_LOGGER.error("【处理资金pdf】,今日目录:{}不存在", edocPdfPath);
                result.addErrorMessage("【处理资金pdf】失败,今天的资金pdf目录还不存在");
                return result;
            }
            // 遍历目录下文件
            File fa[] = edocFile.listFiles();
            for (int i = 0; i < fa.length; i++) {
                File fs = fa[i];
                if (!fs.isDirectory()) {
                    // 文件名
                    String name = fs.getName();
                    // 文件前缀
                    String prifix  = name.substring(0, name.lastIndexOf("."));
                    // 文件后缀
                    String suffix = name.substring(name.lastIndexOf(".") + 1);
                    // 文件全路径
                    String fullPath = edocPdfPath + "/" + name;
                    EdocHeaderDTO edocHeaderDTO = new EdocHeaderDTO();
                    if (!"pdf".equals(suffix) && !"PDF".equals(suffix)) {
                        BILLHEADER_LOGGER.info("【处理资金pdf】，非pdf文件:{}不做处理", fullPath);
                        continue;
                    }
                    edocHeaderDTO.setEdocNo(prifix);
                    edocHeaderDTO.setEdocType(Constant.BizTypeCode.BANK_TICKET_TYPE_CODE);
                    edocHeaderDTO.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM);
                    edocHeaderDTO.setEdocIsMatched("1");
                    *//**
                     * 指定回单来源：上传
                     *//*
                    edocHeaderDTO.setEdocSource(Constant.EdocSource.UPLOAD);
                    createHeader(edocHeaderDTO, edocPdfPath + "/" + name,"BANK_TICKET");
                }
            }
            result.setSuccessMessage("【处理资金pdf】成功");
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【处理资金pdf】异常，{}", e);
            result.addErrorMessage("【处理资金pdf】异常，请联系管理员");
        }
        return result;
    }*/
    /*************************************E 资金pdf*************************************************/

    /**
     * 影像自动任务接口
     *   1.查询异常并且状态是已采集的报账单任务
     *   2.调用OA影像任务自动接口
     *   3.OA接口调用edoc创建影像任务接口
     *
     */
    /*@Override
    public void automaticTask() {
        try {
            // 查询异常已采集报账单
            List<EdocHeader> edocHeaderList = edocHeaderService.queryAbnormalEdocHeader();

            if (BeanUtil.isEmpty(edocHeaderList)) {
                BILLHEADER_LOGGER.error("【影像自动任务接口】: 暂无需要调用OA接口的数据");
                return;
            }

            String url = null;
            ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("SYNC_FROM_OA","OA_AUTOMATICTASK_URL");
            if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
                url = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
            }
            for (EdocHeader edocHeader : edocHeaderList) {
                try {
                    OAUtil.automaticTask(url, edocHeader.getEdocNo());
                    BILLHEADER_LOGGER.info("【影像自动任务接口】: 单号: {}调用成功", edocHeader.getEdocNo());
                } catch (Exception e) {
                    BILLHEADER_LOGGER.error("【影像自动任务接口】调用接口发生异常: {}", e);
                }
            }
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【影像自动任务接口】发生异常: {}", e);
        }

    }*/

    @Override
    public ExecuteResult<String> deleteEdocHeaderById(Long edocHeaderId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(edocHeaderId, "id不能为空");
            EdocHeader edocHeader = edocHeaderService.queryById(edocHeaderId);
            if (null == edocHeader) {
                result.addErrorMessage("单据不存在或以被其他人删除，请刷新后重试");
                return result;
            }
            // 删除影像图片
            edocImageService.delImagesByEdocHeaderId(edocHeaderId);

            // 删除单据
            edocHeader.setDeleted(Constant.DeleteFlag.DEL_YES);
            edocHeaderService.updateByIdSelective(edocHeader);

            result.setSuccessMessage("删除成功");
        } catch (Exception e) {
            BILLHEADER_LOGGER.error("【删除单据异常】，异常原因: {}", e);
            result.addErrorMessage("删除失败！");
        }
        return result;
    }

}