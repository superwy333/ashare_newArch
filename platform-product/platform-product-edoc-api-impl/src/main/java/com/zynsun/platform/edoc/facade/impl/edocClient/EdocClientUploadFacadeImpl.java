package com.zynsun.platform.edoc.facade.impl.edocClient;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.github.pagehelper.StringUtil;
import com.zynsun.openplatform.util.*;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.EdocImageDTO;
import com.zynsun.platform.edoc.dto.edocClient.ResponseHeader;
import com.zynsun.platform.edoc.dto.edocClient.beforeUpload.request.BeforeUploadRequest;
import com.zynsun.platform.edoc.dto.edocClient.beforeUpload.request.BeforeUploadRequestBody;
import com.zynsun.platform.edoc.dto.edocClient.beforeUpload.response.BeforeUploadResponse;
import com.zynsun.platform.edoc.dto.edocClient.beforeUpload.response.BeforeUploadResponseBody;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.request.*;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.response.DataUploadResponse;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.response.DataUploadResponseBody;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.response.DataUploadResponseBodyDataItem;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.response.DataUploadResponseBodyItem;
import com.zynsun.platform.edoc.dto.edocClient.endUpload.request.EndUploadRequest;
import com.zynsun.platform.edoc.dto.edocClient.endUpload.request.EndUploadRequestBodyItem;
import com.zynsun.platform.edoc.dto.edocClient.endUpload.response.EndUploadResponse;
import com.zynsun.platform.edoc.facade.edocClient.EdocClientUploadFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocImageFacade;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import com.zynsun.platform.edoc.service.EdocImageService;
import com.zynsun.platform.edoc.service.InvoiceService;
import com.zynsun.platform.utils.EdocClientUtil;
//import com.zynsun.platform.workflow.dto.WfTaskDTO;
//import com.zynsun.platform.workflow.facade.WfTaskFacade;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.util.*;

/**
 * 客户端上传影像数据相关接口实现类
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/05 14:39
 */
@Service(value = "edocClientUploadFacade")
public class EdocClientUploadFacadeImpl implements EdocClientUploadFacade {

    private static final Logger EDOC_CLIENT_LOGGER = LoggerFactory.getLogger(EdocClientUploadFacadeImpl.class);

    @Autowired
    private EdocHeaderService edocHeaderService;
    @Autowired
    private EdocHeaderFacade edocHeaderFacade;
    @Autowired
    private EdocImageService edocImageService;
    @Autowired
    private EdocImageFacade edocImageFacade;
    /*@Autowired
    private CompanyCliFacade companyCliFacade;
    @Autowired
    private VendorCliFacade vendorCliFacade;*/
//    @Autowired
//    private WfTaskFacade wfTaskFacade;
    /*@Autowired
    private DataDictCliFacade dataDictCliFacade;*/
    @Autowired
    private InvoiceService invoiceService;

    @Override
    public ExecuteResult<BeforeUploadResponse> beforeUpload(BeforeUploadRequest beforeUploadRequest) {
        EDOC_CLIENT_LOGGER.info("beforeUpload request: {}", JSON.toJSONString(beforeUploadRequest));
        ExecuteResult<BeforeUploadResponse> result = new ExecuteResult<>();
        BeforeUploadResponse response = new BeforeUploadResponse();
        BeforeUploadResponseBody responseBody = new BeforeUploadResponseBody();
        BeforeUploadRequestBody requestBody = beforeUploadRequest.getBody();
        try {
            // 验证客户端请求头信息
            response.setHeader(EdocClientUtil.getResponseIsClientHeaderNull(beforeUploadRequest.getHeader(), false));
            if (!EdocClientUtil.isEmpty(response.getHeader())) {
                result.setResult(response);
                return result;
            }
            // 单证编号
            String edocNo = requestBody.getEdocNo();
            // 是否有封面
            boolean isCover = EdocClientUtil.isEmpty(edocNo) ? false : true;
            EdocHeaderDTO currentBillheader = null;
            // 无封面根据规则生成单证编号
            if (!isCover) {
                // TODO 单证编号规则
                edocNo = Constant.BizTypeCodeEnum.text(requestBody.getCategoryCode()) + String.valueOf(edocHeaderService.createEdocFlowNum());
            }
            //String scanFlag = requestBody.getScanFlag();
            String scanFlag = "1";
            // 加入异常处理流程
            //boolean isException = false;
            ExecuteResult<EdocHeaderDTO> edocHeaderCheck = edocHeaderFacade.beforeUploadCheck(edocNo, scanFlag, requestBody.getCategoryCode());
            currentBillheader = edocHeaderCheck.getResult();
            /*if (currentBillheader.getEdocNo().contains(Constant.EDOC_NO_FOOTER_EXCEPTION)) {
                isException = true;
            }*/
            // 创建影像任务
            if (EdocClientUtil.isEmpty(currentBillheader) || EdocClientUtil.isEmpty(currentBillheader.getId())) {
                EdocHeaderDTO edocHeaderDTO = new EdocHeaderDTO();
                edocHeaderDTO.setEdocNo(currentBillheader.getEdocNo());
                edocHeaderDTO.setEdocTaskStatus(Constant.BizStatus.ON_UP_DATA);
                edocHeaderDTO.setEdocType(requestBody.getCategoryCode());
                edocHeaderDTO.setParentId(EdocClientUtil.stringToLong(requestBody.getParentId(), false));
                edocHeaderDTO.setDeleted(Constant.DeleteFlag.DEL_NO);
                edocHeaderDTO.setCreateTime(new Date());
                edocHeaderDTO.setCreateBy(requestBody.getUserCode());
                edocHeaderDTO.setTotalNum(requestBody.getTotalCount());
                edocHeaderDTO.setEdocIsMatched("0");
                edocHeaderDTO.setPublicType("0");
                edocHeaderDTO.setUploadType("-1");
                edocHeaderDTO.setSeal("1");
                edocHeaderDTO.setReview("1");
                edocHeaderDTO.setIsAbnormal("0");
                // 银行回单不设置实物签收状态
                if (!Constant.BizTypeCode.BANK_TICKET_TYPE_CODE.equals(requestBody.getCategoryCode())) {
                    edocHeaderDTO.setEdocPhysicalStatus(Constant.EdocPhysicalStatus.TO_SEND);
                }
                if (!BeanUtil.isEmpty(currentBillheader.getScanFlag())) {
                    edocHeaderDTO.setScanFlag(currentBillheader.getScanFlag());
                }

                // 新建任务(有封面)需要启动流程
                if (!edocNo.equals(currentBillheader.getEdocNo())) {
                    isCover = false;
                }
                edocHeaderDTO.setStartProcessFlag(isCover);
                edocHeaderDTO.setJsonData("{}");

                ExecuteResult<EdocHeaderDTO> executeResult = edocHeaderFacade.edocClientSaveEdocHeader(edocHeaderDTO);
                // TODO 无封面发票的流程信息
                // 创建影像任务成功
                if (executeResult.isSuccess()) {
                    currentBillheader = executeResult.getResult();
                }
            }
            // 设置返回参数, 成功返回当前影像任务ID
            if (EdocClientUtil.isEmpty(currentBillheader) || EdocClientUtil.isEmpty(currentBillheader.getId())) {
                response.setHeader(new ResponseHeader("创建影像任务失败", false));
            } else {
                responseBody.setEdocHeaderId(String.valueOf(currentBillheader.getId()));
                response.setHeader(new ResponseHeader("成功", true));
            }
            response.setBody(responseBody);
        } catch (Exception e) {
            response.setHeader(new ResponseHeader("系统异常", false));
            EDOC_CLIENT_LOGGER.error("BEFORE_UPLOAD_EXCEPTION --->, {}", e);
        }
        EDOC_CLIENT_LOGGER.info("beforeUpload response: {}", JSON.toJSONString(response));
        result.setResult(response);
        return result;
    }

    @Override
    public ExecuteResult<DataUploadResponse> dataUpload(DataUploadRequest dataUploadRequest) {
        EDOC_CLIENT_LOGGER.info("dataUpload request: {}", JSON.toJSONString(dataUploadRequest));

        ExecuteResult<DataUploadResponse> result = new ExecuteResult<>();
        DataUploadResponse response = new DataUploadResponse();
        try {
            // 验证客户端请求头信息
            response.setHeader(EdocClientUtil.getResponseIsClientHeaderNull(dataUploadRequest.getHeader(), false));

            while (EdocClientUtil.isEmpty(response.getHeader())) {

                // 接收客户端请求参数
                List<DataUploadRequestBodyItem> requestBodyItems = dataUploadRequest.getBody().getDataItem().getItems();
                if (EdocClientUtil.isNotEmpty(requestBodyItems)) {
                    // 获取影像任务
                    String edocHeaderId = requestBodyItems.get(0).getEdocHeaderId();
                    EdocHeader edocHeader = edocHeaderService.queryById(EdocClientUtil.stringToLong(edocHeaderId, false));
                    if (EdocClientUtil.isEmpty(edocHeader)) {
                        response.setHeader(new ResponseHeader("影像任务不存在.", false));
                        break;
                    }
                    // 只允许"上传中" 或者 "重扫" 或者 "补扫" 的任务上传影像以及发票信息
                    if (!edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.ON_UP_DATA)
                            && !edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.AGAIN_SCAN)
                            && !edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.SINGLE_AGAIN_SCAN)
                            && !edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.WAIT_ADD_DATA)
                            && !edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.ARCHIVE_WAIT_ADD_DATA)) {
                        response.setHeader(new ResponseHeader("当前不允许追加上传影像", false));
                        break;
                    }

                    // 保存影像以及发票信息
                    //boolean flag = this.imgIsReplace(edocHeader);
                    int flag = this.checkScanModel(edocHeader);
                    if(flag==0){ // 全部重扫
                        response.setBody(listDataUploadItems(requestBodyItems, dataUploadRequest.getBody().getUserCode()));
                    }else if (flag==1){ // 单票重扫
                        response.setBody(listDataUploadItemsBySingle(requestBodyItems, dataUploadRequest.getBody().getUserCode()));
                    }else if (flag==2){ //补扫
                        response.setBody(listDataUploadItemsAddData(requestBodyItems, dataUploadRequest.getBody().getUserCode()));
                    }
                    // 判断当前任务是否以及上传完成：已上传数量 == 影像总张数
                    EdocImage queryImages = new EdocImage();
                    queryImages.setEdocHeaderId(EdocClientUtil.stringToLong(edocHeaderId, false));
                    queryImages.setDeleted(Constant.DeleteFlag.DEL_NO);
//                    int uploadedNum = edocImageService.queryCount(queryImages);
//                    if (EdocClientUtil.isNotEmpty(edocHeader.getTotalNum()) && edocHeader.getTotalNum() == uploadedNum) {
//                        endDataUpload(edocHeader, edocHeader.getTotalNum());
//                    }
                }
                response.setHeader(new ResponseHeader("接收成功", true));
                break;
            }
        } catch (Exception e) {
            response.setHeader(new ResponseHeader("系统异常", false));
            EDOC_CLIENT_LOGGER.error("DATA_UPLOAD_EXCEPTION --->, {}", e);
        }
        EDOC_CLIENT_LOGGER.info("dataUpload response: {}", JSON.toJSONString(response));
        result.setResult(response);
        return result;
    }

    /**
     * 遍历单证条目, 保存影像以及发票信息
     *
     * @param requestBodyItems
     * @param userCode
     * @return
     */
    private DataUploadResponseBody listDataUploadItems(List<DataUploadRequestBodyItem> requestBodyItems, String userCode) {
        DataUploadResponseBody responseBody = new DataUploadResponseBody();
        DataUploadResponseBodyDataItem dataItem = new DataUploadResponseBodyDataItem();
        dataItem.setItems(new ArrayList<>());
        responseBody.setDataItem(dataItem);
        // 接收影像信息
        for (DataUploadRequestBodyItem requestBodyItem : requestBodyItems) {
            DataUploadResponseBodyItem responseBodyItem = new DataUploadResponseBodyItem();
            String edocHeaderId = requestBodyItem.getEdocHeaderId();
            // 客户端唯一标识
            String guid = (StringUtil.isEmpty(requestBodyItem.getGuid())) ? "null" : requestBodyItem.getGuid();
            // 验证当前影像条目
            String validateMsg = EdocClientUtil.validateRequetItemEmpty(requestBodyItem);
            if (EdocClientUtil.isNotEmpty(validateMsg)) {
                dataItem.getItems().add(EdocClientUtil.addResponseBodyItem(false, validateMsg, guid, "null", responseBodyItem));
                continue;
            }
            // 保存图片到本地
            EdocImageDTO uploadImageas = new EdocImageDTO();
            // 发票附件
            String invSvrId = requestBodyItem.getInvSvrId();
            uploadImageas.setImageParentId(StringUtils.isEmpty(invSvrId) ? 0 : EdocClientUtil.stringToLong(invSvrId, false));
//            uploadImageas.setPageNo(EdocClientUtil.stringToLong(requestBodyItem.getPageNumber(), false));
            uploadImageas.setEdocHeaderId(EdocClientUtil.stringToLong(edocHeaderId, false));
            uploadImageas.setCreateBy(userCode);

            // pageNo特殊处理一下，因为待采集状态可多次重新追加扫描
            long pageNoFromClient = EdocClientUtil.stringToLong(requestBodyItem.getPageNumber(), false);
            int maxPageNo = edocImageFacade.queryMaxPagNum(EdocClientUtil.stringToLong(edocHeaderId, false)).getResult();
            uploadImageas.setPageNo(pageNoFromClient + maxPageNo);
            // 增加图片逻辑校验流程
            /*ExecuteResult<EdocImageDTO> executeResult = edocImageFacade.checkExistImages(uploadImageas);
            if (!executeResult.isSuccess()) {
                EdocClientUtil.addResponseBodyItem(false, executeResult.getErrorMessages().get(0), guid, String.valueOf(uploadImageas.getId()), responseBodyItem);
                continue;
            }
            EdocImage edocImage = DozerUtil.map(executeResult.getResult(), EdocImage.class);*/
            EdocImage edocImage = DozerUtil.map(uploadImageas,EdocImage.class);
            // 保存图片至硬盘
            edocImage = this.addPictureInfo(requestBodyItem, edocImage, guid, responseBodyItem);

            // 设置发票信息
            DataUploadRequestBodyItemInvoice bodyItemInvoice = requestBodyItem.getInvoice();
            Invoice invoice = null;
            List<InvoiceItem> invoiceItems = null;

            if (EdocClientUtil.isNotEmpty(bodyItemInvoice)
                    &&!"FT_COVER".equals(edocImage.getEdocImageType())
                    &&!"HT_COVER".equals(edocImage.getEdocImageType())
                    &&!"CG_COVER".equals(edocImage.getEdocImageType())
                    &&!"ZL_COVER".equals(edocImage.getEdocImageType())
                    &&!"1001".equals(edocImage.getEdocImageType())) { // 不是封面才保存
                // 保存发票
                invoice = this.saveInvoice(bodyItemInvoice, edocImage, userCode);
                // 发票明细信息
                DataUploadRequestBodyItemInvoiceDetails invoiceDetails = bodyItemInvoice.getDetails();
                List<DataUploadRequestBodyItemInvoiceDetailsItem> detailsItem = EdocClientUtil.isNotEmpty(invoiceDetails) ? invoiceDetails.getItem() : null;
                if (EdocClientUtil.isNotEmpty(invoiceDetails) && EdocClientUtil.isNotEmpty(detailsItem)) {
                    invoiceItems = saveInvoiceDetail(detailsItem, invoice, edocImage);
                }
            }
            // 保存影像、发票以及发票明细
            try {
                // 使用最新封面替换原来封面
                replaceAcctCover(edocImage);

                ExecuteResult<EdocImage> result = edocImageService.saveEdocImageAndInvoice(edocImage, invoice, invoiceItems);
                if (result.isSuccess()) {
                    edocImage = result.getResult();
                    EdocClientUtil.addResponseBodyItem(true, "保存成功", guid, String.valueOf(edocImage.getId()), responseBodyItem);
                } else {
                    EdocClientUtil.addResponseBodyItem(false, "保存失败", guid, String.valueOf(edocImage.getId()), responseBodyItem);
                }
            } catch (Exception e) {
                EDOC_CLIENT_LOGGER.error("DATA_UPLOAD_EXCEPTION --->, {}", e);
                EdocClientUtil.addResponseBodyItem(false, "保存失败", guid, String.valueOf(edocImage.getId()), responseBodyItem);
            }
            dataItem.getItems().add(responseBodyItem);
        }
        return responseBody;
    }

    /**
     * 标记扫描单票，保存影像以及发票信息
     *
     * @param requestBodyItems
     * @param userCode
     * @return
     */

    private DataUploadResponseBody listDataUploadItemsBySingle(List<DataUploadRequestBodyItem> requestBodyItems, String userCode) {
        DataUploadResponseBody responseBody = new DataUploadResponseBody();
        DataUploadResponseBodyDataItem dataItem = new DataUploadResponseBodyDataItem();
        dataItem.setItems(new ArrayList<>());
        responseBody.setDataItem(dataItem);
        Queue<Long>  queue = new LinkedList<>(); // 存放被扫描的所有img 的id
        // 接收影像信息
        for (DataUploadRequestBodyItem requestBodyItem : requestBodyItems) {
            DataUploadResponseBodyItem responseBodyItem = new DataUploadResponseBodyItem();
            String edocHeaderId = requestBodyItem.getEdocHeaderId();
            // 客户端唯一标识
            String guid = (StringUtil.isEmpty(requestBodyItem.getGuid())) ? "null" : requestBodyItem.getGuid();
            // 验证当前影像条目
            String validateMsg = EdocClientUtil.validateRequetItemEmpty(requestBodyItem);
            if (EdocClientUtil.isNotEmpty(validateMsg)) {
                dataItem.getItems().add(EdocClientUtil.addResponseBodyItem(false, validateMsg, guid, "null", responseBodyItem));
                continue;
            }
            if("ACCT_COVER".equals(requestBodyItem.getDocTypeCode())){ // 排除封面
                continue;
            }
            // 被标记的img
            List<EdocImageDTO> edocImageDelDTO = this.selectBydete(Long.valueOf(edocHeaderId));
            // 保存图片到本地
            EdocImageDTO uploadImageas = new EdocImageDTO();
            Long oldImgId = edocImageDelDTO.get(0).getId() ; // 取出img id
            //edocImageDelDTO.get(0).setDeleted(1);      // 逻辑删除
            edocImageFacade.deleteImageAndInvoiceByImageId(oldImgId); // 删除被标记的影像、发票和发票明细
            //edocImageFacade.updateImages(edocImageDelDTO.get(0)); // 更新
            // 发票附件
            uploadImageas.setExtField1(oldImgId == null ? null : oldImgId.toString()); // 被标记的图片ID
            String invSvrId = requestBodyItem.getInvSvrId();
            uploadImageas.setImageParentId(StringUtils.isEmpty(invSvrId) ? 0 : EdocClientUtil.stringToLong(invSvrId, false));
            uploadImageas.setPageNo(edocImageDelDTO.get(0).getPageNo());
            uploadImageas.setEdocHeaderId(EdocClientUtil.stringToLong(edocHeaderId, false));
            uploadImageas.setCreateBy(userCode);
            uploadImageas.setIsReplace("0");
            // 增加图片逻辑校验流程
            //ExecuteResult<EdocImageDTO> executeResult = edocImageFacade.checkExistImages(uploadImageas);
        /*    if (!executeResult.isSuccess()) {
                EdocClientUtil.addResponseBodyItem(false, executeResult.getErrorMessages().get(0), guid, String.valueOf(uploadImageas.getId()), responseBodyItem);
                continue;
            }*/
            EdocImage edocImage = DozerUtil.map(uploadImageas , EdocImage.class);
            // 保存图片至硬盘
            edocImage = this.addPictureInfo(requestBodyItem, edocImage, guid, responseBodyItem);

            // 设置发票信息
            DataUploadRequestBodyItemInvoice bodyItemInvoice = requestBodyItem.getInvoice();
            Invoice invoice = null;
            List<InvoiceItem> invoiceItems = null;

            if (EdocClientUtil.isNotEmpty(bodyItemInvoice)) {
                // 保存发票
                invoice = this.saveInvoice(bodyItemInvoice, edocImage, userCode);
                // 发票明细信息
                DataUploadRequestBodyItemInvoiceDetails invoiceDetails = bodyItemInvoice.getDetails();
                List<DataUploadRequestBodyItemInvoiceDetailsItem> detailsItem = EdocClientUtil.isNotEmpty(invoiceDetails) ? invoiceDetails.getItem() : null;
                if (EdocClientUtil.isNotEmpty(invoiceDetails) && EdocClientUtil.isNotEmpty(detailsItem)) {
                    invoiceItems = saveInvoiceDetail(detailsItem, invoice, edocImage);
                }
            }
            // 保存影像、发票以及发票明细
            try {
                ExecuteResult<EdocImage> result = edocImageService.saveEdocImageAndInvoice(edocImage, invoice, invoiceItems);
                if (result.isSuccess()) {
                    edocImage = result.getResult();
                    EdocClientUtil.addResponseBodyItem(true, "保存成功", guid, String.valueOf(edocImage.getId()), responseBodyItem);
                } else {
                    EdocClientUtil.addResponseBodyItem(false, "保存失败", guid, String.valueOf(edocImage.getId()), responseBodyItem);
                }
            } catch (Exception e) {
                EDOC_CLIENT_LOGGER.error("DATA_UPLOAD_EXCEPTION --->, {}", e);
                EdocClientUtil.addResponseBodyItem(false, "保存失败", guid, String.valueOf(edocImage.getId()), responseBodyItem);
            }
            dataItem.getItems().add(responseBodyItem);
        }
        return responseBody;
    }


    /**
     * 补扫，保存影像以及发票信息
     * @param requestBodyItems
     * @param userCode
     * @return
     */
    private DataUploadResponseBody listDataUploadItemsAddData(List<DataUploadRequestBodyItem> requestBodyItems, String userCode) {
        DataUploadResponseBody responseBody = new DataUploadResponseBody();
        DataUploadResponseBodyDataItem dataItem = new DataUploadResponseBodyDataItem();
        dataItem.setItems(new ArrayList<>());
        responseBody.setDataItem(dataItem);
        // 接收影像信息
        for (DataUploadRequestBodyItem requestBodyItem : requestBodyItems) {
            DataUploadResponseBodyItem responseBodyItem = new DataUploadResponseBodyItem();
            String edocHeaderId = requestBodyItem.getEdocHeaderId();
            // 客户端唯一标识
            String guid = (StringUtil.isEmpty(requestBodyItem.getGuid())) ? "null" : requestBodyItem.getGuid();
            // 验证当前影像条目
            String validateMsg = EdocClientUtil.validateRequetItemEmpty(requestBodyItem);
            if (EdocClientUtil.isNotEmpty(validateMsg)) {
                dataItem.getItems().add(EdocClientUtil.addResponseBodyItem(false, validateMsg, guid, "null", responseBodyItem));
                continue;
            }
            /**
             *  补扫需要依次替换需要标记重扫的图片
             *      1.如果补扫的图片少于重扫的图片,多余的重扫图片不作处理, image表id不变化
             *      2.如果补扫的图片多于重扫的图片,多余的重扫图片追加至单据后
             */
            // 保存图片到本地
            EdocImageDTO uploadImageas = new EdocImageDTO();
            // 被标记重扫的img
            Long reScanImgId = null;
            EdocImage queryImg = new EdocImage();
            queryImg.setEdocHeaderId(Long.valueOf(edocHeaderId));
            queryImg.setIsReplace("1");
            List<EdocImage> edocImages = edocImageService.queryEdocImage(queryImg);
            if (!BeanUtil.isEmpty(edocImages)) {
                reScanImgId = edocImages.get(0).getId();
                uploadImageas = DozerUtil.map(edocImages.get(0), EdocImageDTO.class);
            }

            // 发票附件
//            uploadImageas.setExtField1(reScanImgId == null ? null : reScanImgId.toString()); // 被标记的图片ID
            // 是否是被替换
            if (reScanImgId == null) {
                // 没有替换的图片, 只补扫
                uploadImageas.setIsReplace("0");
                String invSvrId = requestBodyItem.getInvSvrId();
                uploadImageas.setImageParentId(StringUtils.isEmpty(invSvrId) ? 0 : EdocClientUtil.stringToLong(invSvrId, false));
                uploadImageas.setEdocHeaderId(EdocClientUtil.stringToLong(edocHeaderId, false));
                uploadImageas.setCreateBy(userCode);

                //补扫场景下pageNo特殊处理一下
                long pageNoFromClient = EdocClientUtil.stringToLong(requestBodyItem.getPageNumber(), false);
                int maxPageNo = edocImageFacade.queryMaxPagNum(EdocClientUtil.stringToLong(edocHeaderId, false)).getResult();
                uploadImageas.setPageNo(pageNoFromClient + maxPageNo);
            } else {
                uploadImageas.setIsReplace(Constant.isReplace.REPLACE);
                uploadImageas.setExtField1(reScanImgId.toString());
                // 重扫过后把图片名字替换
                uploadImageas.setImageName("is_replaced");
            }

            //补扫影像打上补扫标识
            uploadImageas.setAddDataFlag("1");

            // 增加图片逻辑校验流程
            /*ExecuteResult<EdocImageDTO> executeResult = edocImageFacade.checkExistImages(uploadImageas);
            if (!executeResult.isSuccess()) {
                EdocClientUtil.addResponseBodyItem(false, executeResult.getErrorMessages().get(0), guid, String.valueOf(uploadImageas.getId()), responseBodyItem);
                continue;
            }
            EdocImage edocImage = DozerUtil.map(executeResult.getResult(), EdocImage.class);*/
            EdocImage edocImage = DozerUtil.map(uploadImageas,EdocImage.class);
            // 保存图片至硬盘
            edocImage = this.addPictureInfo(requestBodyItem, edocImage, guid, responseBodyItem);

            // 设置发票信息
            DataUploadRequestBodyItemInvoice bodyItemInvoice = requestBodyItem.getInvoice();
            Invoice invoice = null;
            List<InvoiceItem> invoiceItems = null;

            if (EdocClientUtil.isNotEmpty(bodyItemInvoice)) {
                // 保存发票
                invoice = this.saveInvoice(bodyItemInvoice, edocImage, userCode);
                // 发票明细信息
                DataUploadRequestBodyItemInvoiceDetails invoiceDetails = bodyItemInvoice.getDetails();
                List<DataUploadRequestBodyItemInvoiceDetailsItem> detailsItem = EdocClientUtil.isNotEmpty(invoiceDetails) ? invoiceDetails.getItem() : null;
                if (EdocClientUtil.isNotEmpty(invoiceDetails) && EdocClientUtil.isNotEmpty(detailsItem)) {
                    invoiceItems = saveInvoiceDetail(detailsItem, invoice, edocImage);
                }
            }
            // 保存影像、发票以及发票明细
            try {
                //补扫场景下需要过滤封面   ==> 补扫需要替换封面(即每次扫描用最新的封面)
                replaceAcctCover(edocImage);

                ExecuteResult<EdocImage> result = edocImageService.saveEdocImageAndInvoice(edocImage, invoice, invoiceItems);
                if (result.isSuccess()) {
                    edocImage = result.getResult();
                    EdocClientUtil.addResponseBodyItem(true, "保存成功", guid, String.valueOf(edocImage.getId()), responseBodyItem);
                } else {
                    EdocClientUtil.addResponseBodyItem(false, "保存失败", guid, String.valueOf(edocImage.getId()), responseBodyItem);
                }
            } catch (Exception e) {
                EDOC_CLIENT_LOGGER.error("DATA_UPLOAD_EXCEPTION --->, {}", e);
                EdocClientUtil.addResponseBodyItem(false, "保存失败", guid, String.valueOf(edocImage.getId()), responseBodyItem);
            }
            dataItem.getItems().add(responseBodyItem);
        }
        return responseBody;
    }

    /**
     * 替换报账单封面, 即每次扫描后, 都用最新封面替换原来封面
     *
     * @param edocImage 新增封面信息
     */
    private void replaceAcctCover(EdocImage edocImage) {
        if (edocImage != null && !BeanUtil.isEmpty(edocImage.getEdocHeaderId())) {

            if (Constant.BillTypeCode.NORMAL_COVER.equals(edocImage.getEdocImageType())) {
                // 删除原来的封面, 使用最新封面
                EdocImage queryCover = new EdocImage();
                queryCover.setEdocHeaderId(edocImage.getEdocHeaderId());
                queryCover.setEdocImageType(Constant.BillTypeCode.NORMAL_COVER);
                List<EdocImage> edocImageCoverList = edocImageService.query(queryCover);
                if (!BeanUtil.isEmpty(edocImageCoverList) && edocImageCoverList.size() > 0) {
                    // 为安全起见, 只删除查询到的第一张封面
                    EdocImage canDeleteCover = edocImageCoverList.get(0);
                    edocImageService.remove(canDeleteCover);
                }

                // 新增封面页码设为第一页
                edocImage.setPageNo(0L);
            }
        }
    }


    public  List<EdocImageDTO>  selectBydete(Long edocHeaderId){
        EdocImageDTO edocImageDTO = new EdocImageDTO();
        edocImageDTO.setEdocHeaderId(edocHeaderId);
        edocImageDTO.setIsReplace("1");
       // edocImageDTO.setDeleted(1);
        ExecuteResult<List<EdocImageDTO>> listExecuteResult = edocImageFacade.selectImages(edocImageDTO);
        return listExecuteResult.getResult();
    }
    /**
     * 保存影像对应发票信息
     *
     * @param bodyItemInvoice
     * @param edocImage
     * @param userCode
     * @return
     */
    private Invoice saveInvoice(DataUploadRequestBodyItemInvoice bodyItemInvoice, EdocImage edocImage, String userCode) {
        Invoice invoice = new Invoice();
        try {
            invoice.setInvSource(Constant.rrInvSource.CLIENT);

            // 判断发票是否已存在
            if (null != edocImage.getId()) {
                Invoice queryInv = new Invoice();
                queryInv.setEdocImageId(edocImage.getId());

                List<Invoice> existInvs = invoiceService.query(queryInv);
                if (!BeanUtil.isEmpty(existInvs)) {
                    invoice = existInvs.get(0);
                }
            }
            // 发票信息存储
            BeanUtils.copyProperties(bodyItemInvoice, invoice, new String[]{});
            // 校验码属性名不一样，单独处理一下
            String invCheckCode = bodyItemInvoice.getInvCheckCode();
            // 校验码截取后6位处理
            if (null != invCheckCode && invCheckCode.length() >= 6) {
                invoice.setCheckCode(invCheckCode.substring(invCheckCode.length() - 6, invCheckCode.length()));
            } else {
                invoice.setCheckCode(bodyItemInvoice.getInvCheckCode());
            }

            invoice.setEdocHeaderId(edocImage.getEdocHeaderId());
            invoice.setInvType(bodyItemInvoice.getInvType());
            invoice.setRemarks(StringUtil.isEmpty(bodyItemInvoice.getInvRemark()) ? "" : new String(Base64Util.decode(bodyItemInvoice.getInvRemark())));
            invoice.setBuyerName(StringUtil.isEmpty(bodyItemInvoice.getBuyerName()) ? "" : new String(Base64Util.decode(bodyItemInvoice.getBuyerName())));
            invoice.setSalerName(StringUtil.isEmpty(bodyItemInvoice.getSalerName()) ? "" : new String(Base64Util.decode(bodyItemInvoice.getSalerName())));

            // 发票密文区
            String taxbox = StringUtil.isEmpty(bodyItemInvoice.getInvTaxbox()) ? "" : bodyItemInvoice.getInvTaxbox();
            invoice.setCiphertextArea(taxbox);
            // 密文类型：1二维码, 0非二维码
            invoice.setCiphertextType(taxbox.length() > 120 ? "1" : "0");
            invoice.setEdocImageId(edocImage.getId());
            invoice.setCreateBy(userCode);
            String date = invoice.getInvDate();
            try {
                if (!BeanUtil.isEmpty(date)) {
                    date = DateUtil.stringToString(invoice.getInvDate(), "yyyy-MM-dd");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            invoice.setInvDate(date);
            // 购方税号(主/辅)
            /*if (StringUtil.isNotEmpty(bodyItemInvoice.getBuyerTaxCode()) || StringUtil.isNotEmpty(bodyItemInvoice.getBuyerTaxCodeB())) {
                ExecuteResult<CompanyResultDTO> resultComp = companyCliFacade.queryCompanyByTaxCode(bodyItemInvoice.getBuyerTaxCode());
                if (!resultComp.isSuccess() || BeanUtil.isEmpty(resultComp.getResult())) {
                    ExecuteResult<CompanyResultDTO> resultCompB = companyCliFacade.queryCompanyByTaxCode(bodyItemInvoice.getBuyerTaxCodeB());
                    if (resultCompB.isSuccess() && !BeanUtil.isEmpty(resultCompB.getResult())) {
                        invoice.setBuyerName(resultCompB.getResult().getCompanyName());
                    }
                } else {
                    invoice.setBuyerName(resultComp.getResult().getCompanyName());
                }
            }*/

            // 供应商税号(主/辅)
            /*if (StringUtil.isNotEmpty(bodyItemInvoice.getSalerTaxCode()) || StringUtil.isNotEmpty(bodyItemInvoice.getSalerTaxCodeB())) {
                ExecuteResult<List<VendorResultDTO>> resultVen = vendorCliFacade.queryVendorByTaxCode(bodyItemInvoice.getSalerTaxCode());
                if (!resultVen.isSuccess() || BeanUtil.isEmpty(resultVen.getResult())) {
                    ExecuteResult<List<VendorResultDTO>> resultVenB = vendorCliFacade.queryVendorByTaxCode(bodyItemInvoice.getSalerTaxCode());
                    if (resultVenB.isSuccess() && !BeanUtil.isEmpty(resultVenB.getResult())) {
                        invoice.setSalerName(resultVenB.getResult().get(0).getVcOrgNameZh());
                        invoice.setSalerTaxCode(bodyItemInvoice.getSalerTaxCode());
                    }
                } else {
                    invoice.setSalerName(resultVen.getResult().get(0).getVcOrgNameZh());
                    invoice.setSalerTaxCode(bodyItemInvoice.getSalerTaxCode());
                }
            }*/

            // 发票位运算校验结果
            invoice.setBitOperationResults(bodyItemInvoice.getOcrFlag());
            EDOC_CLIENT_LOGGER.info("[当前存储的发票信息：{}", JSON.toJSONString(invoice));
        } catch (Exception e) {
            EDOC_CLIENT_LOGGER.error("INVOICE_SAVE_EXCEPTION --->, {}", e);
        }
        return invoice;
    }

    /**
     * 保存发票明细信息
     * @param detailsItem
     * @param invoice
     * @param edocImage
     * @return
     */
    private List<InvoiceItem> saveInvoiceDetail(List<DataUploadRequestBodyItemInvoiceDetailsItem> detailsItem, Invoice invoice, EdocImage edocImage) {
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        for (DataUploadRequestBodyItemInvoiceDetailsItem invoiceDetailsItem : detailsItem) {
            InvoiceItem modelItem = new InvoiceItem();
            modelItem.setEdocHeaderId(edocImage.getEdocHeaderId());
            modelItem.setEdocImageId(edocImage.getId());
            modelItem.setInvId(invoice.getId());
            modelItem.setLineNo(invoiceDetailsItem.getLineNo());
            modelItem.setItemAmount(invoiceDetailsItem.getGoodsAmount());
            modelItem.setItemTax(invoiceDetailsItem.getGoodsTax());
            modelItem.setItemTaxRate(invoiceDetailsItem.getGoodsTaxRate());
            modelItem.setItemName(new String(Base64Util.decode(invoiceDetailsItem.getGoodsName())));
            modelItem.setItemQuantity(new String(Base64Util.decode(invoiceDetailsItem.getGoodsQuantity())));
            modelItem.setItemSpec(new String(Base64Util.decode(invoiceDetailsItem.getGoodsSpec())));
            modelItem.setItemPrice(new String(Base64Util.decode(invoiceDetailsItem.getGoodsPrice())));
            modelItem.setUnit(new String(Base64Util.decode(invoiceDetailsItem.getGoodsUnit())));
            invoiceItems.add(modelItem);
        }
        return invoiceItems;
    }

    /**
     * 保存图片信息
     *
     * @param requestBodyItem
     * @param edocImage
     * @param guid
     * @param responseBodyItem
     * @return
     */
    private EdocImage addPictureInfo(DataUploadRequestBodyItem requestBodyItem, EdocImage edocImage, String guid, DataUploadResponseBodyItem responseBodyItem) {
        try {
            EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
            //String rootPath = Constant.ImagePath.ROOT_PATH;
            String rootPath = LoadConfig.get("ROOT_PATH");
            if (BeanUtil.isEmpty(rootPath)) {
                EDOC_CLIENT_LOGGER.error("没有读取到ROOT_PATH，使用默认地址:{}",Constant.ImagePath.ROOT_PATH);
                rootPath = Constant.ImagePath.ROOT_PATH;
            }
            /*ExecuteResult<SysConfDictItemDTO> resultPath = dataDictCliFacade.findByEnNameAndItemCode("IMAGE_CONFIG", "ROOT_PATH");
            if(resultPath.isSuccess() && !BeanUtil.isEmpty(resultPath.getResult())){
                rootPath = resultPath.getResult().getDicItemValue();
            }*/
            // 添加图片,上传图片,保存图片信息
            String imgUrl = EdocClientUtil.getImagePath(requestBodyItem.getDocFormat(), edocHeader, String.valueOf(edocImage.getPageNo()), rootPath);
            // 图片全路径
            String fullPath = rootPath + imgUrl;
            EDOC_CLIENT_LOGGER.info("开始保存图片：" + requestBodyItem.getGuid() + "_" + requestBodyItem.getPageNumber() + "." + requestBodyItem.getDocFormat());
            // 上传图片
            BASE64Decoder decoder = new BASE64Decoder();
            if (EdocClientUtil.getPicture(decoder.decodeBuffer(requestBodyItem.getDocBytedata()), fullPath)) {
                EDOC_CLIENT_LOGGER.info("保存图片成功, 全路径为：" + fullPath);
//                edocImage.setScanner(requestBodyItem.getScanCode());
//                edocImage.setScannerCode(requestBodyItem.getScannerModel());
                edocImage.setCreateTime(new Date());
                edocImage.setImageUrl(imgUrl);
                edocImage.setImageRootPath(rootPath);
                edocImage.setEdocImageType(requestBodyItem.getDocTypeCode());
                // 附件相关信息存储
                if (StringUtil.isNotEmpty(requestBodyItem.getInvSvrId())) {
                    edocImage.setImageParentId(EdocClientUtil.stringToLong(requestBodyItem.getInvSvrId(), false));
                    edocImage.setEdocImageType(Constant.BillTypeCode.NORMAL_BILL);
                }
                EDOC_CLIENT_LOGGER.info("图片信息成功保存到数据库");
            }
        } catch (Exception e) {
            EDOC_CLIENT_LOGGER.error("PICTURE_SAVE_EXCEPTION --->, {}", e);
        }
        return edocImage;
    }

    @Override
    public ExecuteResult<EndUploadResponse> endUpload(EndUploadRequest endUploadRequest) {
        EDOC_CLIENT_LOGGER.info("endUpload request: {}", JSON.toJSONString(endUploadRequest));

        ExecuteResult<EndUploadResponse> result = new ExecuteResult<>();
        EndUploadResponse response = new EndUploadResponse();
        try {
            // 客户端请求头信息
            response.setHeader(EdocClientUtil.getResponseIsClientHeaderNull(endUploadRequest.getHeader(), false));
            if (!EdocClientUtil.isEmpty(response.getHeader())) {
                result.setResult(response);
                return result;
            }
            // 用户代码
            String userCode = endUploadRequest.getBody().getUserCode();
            // 需要结束上传任务信息
            List<EndUploadRequestBodyItem> requestBodyItems = endUploadRequest.getBody().getNodes().getItems();
            for (EndUploadRequestBodyItem requestBodyItem : requestBodyItems) {
                EdocHeader edocHeader = edocHeaderService.queryById(EdocClientUtil.stringToLong(requestBodyItem.getTaskId(), false));
                endDataUpload(edocHeader, Integer.parseInt(requestBodyItem.getPageNum())); // 结束影像任务上传
            }
            response.setHeader(new ResponseHeader("成功", true));
        } catch (Exception e) {
            response.setHeader(new ResponseHeader("系统异常", false));
            EDOC_CLIENT_LOGGER.error("END_UPLOAD_EXCEPTION --->, {}", e);
        }
        result.setResult(response);
        EDOC_CLIENT_LOGGER.info("endUpload response: {}", JSON.toJSONString(response));
        return result;
    }

    /**
     * 结束影像任务上传
     *
     * @param edocHeader    当前影像任务
     * @param pageNum       图片张数
     */
    private void endDataUpload(EdocHeader edocHeader, int pageNum) {
        try {
            if (Constant.BizStatus.ON_UP_DATA.equals(edocHeader.getEdocTaskStatus())
                    || Constant.BizStatus.AGAIN_SCAN.equals(edocHeader.getEdocTaskStatus())
                    || Constant.BizStatus.WAIT_ADD_DATA.equals(edocHeader.getEdocTaskStatus())
                    || Constant.BizStatus.WAIT_SCAN.equals(edocHeader.getEdocTaskStatus())
                    || Constant.BizStatus.ARCHIVE_WAIT_ADD_DATA.equals(edocHeader.getEdocTaskStatus())) {
                edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM);
                edocHeaderService.updateByIdSelective(edocHeader);
                //TODO 调用费报接口，获取单据税号，并与发票税号做校验
                // 新开个线程做异常检查
                new Thread(() -> {
                    //这里是线程需要做的事情
                    try {
                        edocHeaderFacade.edocAbnormalCheck(edocHeader.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }else if(Constant.BizStatus.SINGLE_AGAIN_SCAN.equals(edocHeader.getEdocTaskStatus())){
                edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM);
                edocHeaderService.updateByIdSelective(edocHeader);
            }
        } catch (Exception e) {
            EDOC_CLIENT_LOGGER.error("END_UPLOAD_EXCEPTION --->, {}", e);
        }
    }

    private void checkAbnormal(EdocHeader edocHeader) throws Exception {
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
                        break;
                    }
                }else {
                    invCheckRealPass = false;
                    abnormalType = "5";
                    break;
                }
            }

        }
        if (!invCheckRealPass||!invCheckStatusPass) {
            edocHeader.setIsAbnormal("1");
            edocHeader.setAbnormalType(abnormalType);
        }
    }

    // 是单票重扫 还是全部重扫
    public boolean imgIsReplace(EdocHeader edocHeader){
         /*
         //TODO
            如果是重扫分2中情况：1、全部重扫，那就逻辑删除所有img和发票，2、单票重扫。只逻辑删除isRpleace为1的单票
            先查询所有单票，看是否  isRpleace 都为0 如果为0 就全部重扫，否则就是单票重扫，此处逻辑代码还未加
         */
        boolean flag = false;
        /*EdocImageDTO edocImageDTO = new EdocImageDTO();
        edocImageDTO.setEdocHeaderId(edocHeader.getId());
        edocImageDTO.setIsReplace("1");
        ExecuteResult<List<EdocImageDTO>> listExecuteResult = edocImageFacade.selectImages(edocImageDTO);*/
        if(Constant.BizStatus.SINGLE_AGAIN_SCAN.equals(edocHeader.getEdocTaskStatus())){
            flag = false;
        }else{
            // 就是全部扫描
            flag = true;
        }
        return flag;
    }

    //是单票重扫还是
    public Integer checkScanModel(EdocHeader edocHeader){
        int flag = 0; //0:全部重扫 1：单票重扫 2：补扫
        if(Constant.BizStatus.SINGLE_AGAIN_SCAN.equals(edocHeader.getEdocTaskStatus())){
            flag = 1;
        }else if (Constant.BizStatus.WAIT_ADD_DATA.equals(edocHeader.getEdocTaskStatus()) || Constant.BizStatus.ARCHIVE_WAIT_ADD_DATA.equals(edocHeader.getEdocTaskStatus())){
            flag = 2;
        }
        return flag;
    }
}
