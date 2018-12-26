package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.google.gson.Gson;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.xiaoleilu.hutool.lang.Assert;
import com.zynsun.openplatform.context.PlatformContext;
import com.zynsun.openplatform.dubboRestLoginFilter.annotation.Login;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.*;
import com.zynsun.platform.edoc.annotation.MapUtil;
import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.dto.BeanDTO;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.EdocImageDTO;
import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocImageFacade;
import com.zynsun.platform.edoc.model.EdocHeaderModel;
import com.zynsun.platform.edoc.model.EdocImageModel;
import com.zynsun.platform.edoc.model.ReviewInfoModel;
import com.zynsun.platform.edoc.service.*;
import com.zynsun.platform.edoc.webservice.BaseInter;
import com.zynsun.platform.utils.EdocClientUtil;
import com.zynsun.platform.utils.FileUtil;
import constant.Constant;
import jp.sourceforge.qrcode.QRCodeDecoder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utils.App;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static redis.clients.jedis.Protocol.CHARSET;


/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:06 2018/1/2
 * @Modified By:
 */
@Path("edocImageFacade")
@Service("edocImageFacade")
public class EdocImageFacadeImpl implements EdocImageFacade, BaseInter {

    private static final Logger IMAGES_LOGGER = LoggerFactory.getLogger(EdocImageFacadeImpl.class);

    private static final String ROOT_PATH = App.getConfig("imageRootPath");

    private static final String IMAGE_URL = App.getConfig("imageprefUrl");
    @Autowired
    ReviewInfoService reviewInfoService;
    @Autowired
    private EdocImageService edocImageService;
    @Autowired
    private EdocHeaderService edocHeaderService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceItemService invoiceItemService;
    @Autowired
    private EdocHeaderFacade edocHeaderFacade;

    /**
     * 生成图片路径
     *
     * @param fileNameEnd
     * @param edocHeader
     * @param imageNum
     * @return
     */
    public static String getImagePath(String fileNameEnd, EdocHeaderDTO edocHeader, String imageNum) {
        Map<String, String> map = new HashMap<>();
        String fileName = edocHeader.getEdocNo() + "_" + UUID.randomUUID().toString().replaceAll("\\-", "") + "_" + imageNum + "." + fileNameEnd;
        Date date = edocHeader.getCreateTime() == null ? new Date() : edocHeader.getCreateTime();
        String directionName = "/" + DateUtils.formatDate(date, "yyyyMMdd") + "/" + edocHeader.getEdocNo() + "/" + edocHeader.getId();//部分路径名称
        String imgUrl = directionName + "/" + fileName;
        IMAGES_LOGGER.debug("[edocNo " + edocHeader.getEdocNo() + " 第 " + imageNum + " 张图片路径是" + imgUrl + "]");
        File folder = new File(ROOT_PATH + directionName);
        if (!(folder.isDirectory())) {
            folder.mkdirs();
        }
        return imgUrl;
    }

    /**
     * 获取图片字节流
     *
     * @param b
     * @param imgFilePath
     * @return
     */
    public static boolean getPicture(byte[] b, String imgFilePath) {
        boolean flag = false;
        try {
            if (b != null) { // 图像数据为空
                IMAGES_LOGGER.debug("======图片的字符串大小为" + b.length + "===========");
                // 调整异常数据
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
                flag = true;
            }
        } catch (Exception e) {
            IMAGES_LOGGER.error("==解密图片字节码异常==", e);
        }
        return flag;
    }

    public static void bytesToFile(byte[] buffer, final String filePath) {

        File file = new File(filePath);

        OutputStream output = null;
        BufferedOutputStream bufferedOutput = null;

        try {
            output = new FileOutputStream(file);

            bufferedOutput = new BufferedOutputStream(output);

            bufferedOutput.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedOutput) {
                try {
                    bufferedOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @POST
    @Path("billImagesDetails")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> billImagesDetails(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            BeanDTO beanDTO = new BeanDTO();
            MapUtil.map2Bean(map, beanDTO);
            if (StringUtils.isEmpty(beanDTO.getTaskId())) return MapUtil.getResultMap(map, "001", "任务标识为空", RESP_FAIL);
            EdocHeaderModel edocHeaderModel = new EdocHeaderModel();
            edocHeaderModel.setId(Long.valueOf(beanDTO.getTaskId()));
            List<EdocHeaderModel> edocHeaderModelList = edocHeaderService.getBillImagesByBillHeaderId(edocHeaderModel);
            executeResult = MapUtil.getResultMap(map, "000", "资料处理成功", RESP_SUCCESS, BeanUtil.isEmpty(edocHeaderModelList) ? null : edocHeaderModelList.get(0));
        } catch (Exception e) {
            IMAGES_LOGGER.error("查询异常,原因{}", e);
            return MapUtil.getResultMap(map, "001", "资料处理失败", RESP_FAIL);
        }
        return executeResult;
    }

    @Login
    @POST
    @Path("scrapByTaskId")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> scrapByTaskId(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            //参数验证
            BeanDTO beanDTO = new BeanDTO();
            Map<String, Object> validateResult = this.validateTaskInfo(map, beanDTO);
            if (!MapUtil.getBoolean(validateResult)) return validateResult;

            ReviewInfoModel reviewInfoReq = new ReviewInfoModel();
            MapUtil.map2Bean(map, reviewInfoReq);
            Map<String, Object> validateReviewInfo = this.validateReviewInfo(reviewInfoReq);
            if (!MapUtil.getBoolean(validateReviewInfo)) return validateReviewInfo;

            //更新
            executeResult = this.reviewInfoService.updateScrapByTaskId(reviewInfoReq);
        } catch (Exception e) {
            IMAGES_LOGGER.error("系统异常,原因{}", e);
            return MapUtil.getResultMap(map, "001", "系统异常", RESP_FAIL);
        }
        return executeResult;
    }

    private Map<String, Object> validateReviewInfo(ReviewInfoModel reviewInfoReq) {
        if (StringUtils.isEmpty(reviewInfoReq.getReviewStatus()))
            return com.zynsun.platform.edoc.utils.MapUtil.getResultMap("001", "评价标识为空", RESP_FAIL);
        if (StringUtils.isEmpty(reviewInfoReq.getReviewMessage()))
            return com.zynsun.platform.edoc.utils.MapUtil.getResultMap("001", "评价信息为空", RESP_FAIL);
        if (StringUtils.isEmpty(reviewInfoReq.getReviewSource()))
            return com.zynsun.platform.edoc.utils.MapUtil.getResultMap("001", "评价来源为空", RESP_FAIL);
        if (StringUtils.isEmpty(reviewInfoReq.getReviewMessage()))
            return com.zynsun.platform.edoc.utils.MapUtil.getResultMap("001", "评价时间为空", RESP_FAIL);
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setDeleted(0);
        reviewInfo.setIsProcessed("0");
        reviewInfo.setEdocHeaderId(reviewInfoReq.getTaskId());
        List<ReviewInfo> queryReviewInfoList = reviewInfoService.query(reviewInfo);
        if (!BeanUtil.isEmpty(queryReviewInfoList))
            return com.zynsun.platform.edoc.utils.MapUtil.getResultMap("001", "有未处理完的评价信息，操作失败", RESP_FAIL);
        return MapUtil.getResultMap("001", "参数验证成功", RESP_SUCCESS);
    }

    @POST
    @Path("uploadElcInvoicePDF")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> uploadElcInvoicePDF(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        List<String> msgList = new ArrayList<>();
        result.put("msg", msgList);
        String userName = null;
        try {
            File pdfTmpPath = new File(getRootPath() + "/ElcPdftmp");
            if (!pdfTmpPath.getParentFile().exists()) {
                pdfTmpPath.getParentFile().mkdirs();//如果目录不存在，创建目录
            }
            Map<String, Object> pdfInfo = getPDFFileForElcPDF(request, getRootPath() + "/ElcPdftmp" + "/tmpPdf.pdf");
            String token = (String) pdfInfo.get("token");
            if (BeanUtil.isEmpty(token)) {
                result.put("code","001");
                result.put("msg","token不能为空！");
                result.put("success",false);
                return result;
            }
            userName = checkToken(token);
            if (BeanUtil.isEmpty(userName)) {
                result.put("code","002");
                result.put("msg","token校验异常！");
                result.put("success",false);
                return result;
            }
            File normalFile = (File) pdfInfo.get("file");
            StringBuffer message = new StringBuffer("");
            com.alibaba.dubbo.common.utils.Assert.notNull(normalFile, "上传文件为空");
            String edocId = (String) pdfInfo.get("taskId");
            EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(edocId));
            if (BeanUtil.isEmpty(edocHeader)) {
                msgList.add("没有找到可用单据");
                return result;
            }
                /*if (!Constant.BizStatus.WAIT_SCAN.equals(edocHeader.getEdocTaskStatus())&&!Constant.BizStatus.WAIT_ADD_DATA.equals(edocHeader.getEdocTaskStatus())) {
                    msgList.add("只有待采集(或补扫)状态的单据才能上传电子发票");
                    return result;
                }*/
            // 相同名称电子发票不能重复上传
            String originalFilename = (String) pdfInfo.get("fileName");
                /*EdocImageDTO queryRepeatUpload = new EdocImageDTO();
                queryRepeatUpload.setEdocHeaderId(edocHeaderDTO.getId());
                queryRepeatUpload.setImageName(originalFilename);
                if (isRepeatUpload(message, queryRepeatUpload)) continue;*/
            //组装文件保存路径并创建目录
            String rootPath = getRootPath();
            String edocNo = edocHeader.getEdocNo();
            String parentPath = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + edocNo;
            String finalPath = rootPath + "/" + parentPath;
            String fileName = "EI" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + (int) (Math.random() * 9000 + 1000) + ".jpg";
            String fileQRname = "EI" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + (int) (Math.random() * 9000 + 1000) + "_QR.jpg";
            File filepath = new File(finalPath, fileName);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();//如果目录不存在，创建目录
            }
            String desFilePath = finalPath + "/" + fileName;
            String desQRpath = finalPath + "/" + fileQRname;
            String invoiceInfo = pdfToImage(normalFile, desFilePath, desQRpath); // todo 这里可能会出现解析不出发票信息的场景
            //为目录赋权限
            grantAuthorization(rootPath, finalPath, fileName);

            if (!BeanUtil.isEmpty(invoiceInfo)) { // invoiceInfo不为空，则解析成功，保存影像和发票
                IMAGES_LOGGER.info("{}-解析二维码成功{}", originalFilename, invoiceInfo);
                //解析发票信息串
                String[] invoiceInfoArr = invoiceInfo.split(",");
                String invCode = invoiceInfoArr[2];
                String invNo = invoiceInfoArr[3];
                String invAmount = invoiceInfoArr[4];
                String invDate = invoiceInfoArr[5];
                String checkCode = invoiceInfoArr[6];
                InvoiceDTO invoiceDTO = new InvoiceDTO();
                invoiceDTO.setInvNo(invNo);
                invoiceDTO.setInvCode(invCode);
                invoiceDTO.setInvAmount(invAmount);
                invoiceDTO.setInvDate(invDate);
                invoiceDTO.setInvSource("3");
                // 校验码截取后6位处理
                if (null != checkCode && checkCode.length() >= 6) {
                    invoiceDTO.setCheckCode(checkCode.substring(checkCode.length() - 6, checkCode.length()));
                } else {
                    invoiceDTO.setCheckCode(checkCode);
                }
                //保存数据,验真验重
                edocHeaderFacade.saveElecInvoice(originalFilename, Long.valueOf(edocId), invoiceDTO, "/" + parentPath + "/" + fileName,userName);
            } else { // 解析失败，只保存影像
                IMAGES_LOGGER.info("{}-解析二维码失败", originalFilename);
                edocHeaderFacade.saveImage(originalFilename, Long.valueOf(edocId), "/" + parentPath + "/" + fileName,"EVAT_N_E_INV",userName);
            }
            // 接着处理pdf的剩下几页
            int pdfPageNums = getPdfPageNums(normalFile);
            if (pdfPageNums > 1) { // 如果pdf不止一页，则需要把后面几页全部转换成图片
                for (int i=1;i<pdfPageNums;i++) { // i从1开始是因为第一页电子发票在上面已经处理过了
                    // 保存edocImage
                    String fileNameNew = "EI" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + (int) (Math.random() * 9000 + 1000) + ".jpg";
                    edocHeaderFacade.saveImage(originalFilename, Long.valueOf(edocId), "/" + parentPath + "/" + fileNameNew,"1001",userName);
                    // 转换图片
                    pdfToImage(normalFile,finalPath + "/" + fileNameNew,i);
                }
            }
            if ("".equals(message.toString())) {
                msgList.add("上传成功");
                result.put("success", true);
            } else {
                msgList.add("上传失败");
                result.put("success", false);
            }
        } catch (Exception e) {
            IMAGES_LOGGER.error("上传电子发票异常{}", e);
            msgList.add("上传电子发票异常");
        }
        return result;
    }

    //@Login
    @POST
    @Path("uploadBankTicket")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> uploadBankTicket(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> map = null;
        BeanDTO beanDTO = new BeanDTO();
        response.setCharacterEncoding("UTF-8");
        String userName = null;
        try {
            //解析获取前台传递的formData
            map = FileUtil.analysisFormData(request);
            MapUtil.map2Bean(map, beanDTO);
            // 手工处理token
            String token = (String) map.get("token");
            if (BeanUtil.isEmpty(token)) {
                res.put("code","001");
                res.put("msg","token不能为空！");
                res.put("success",false);
                return res;
            }
            userName = checkToken(token);
            if (BeanUtil.isEmpty(userName)) {
                res.put("code","002");
                res.put("msg","token校验异常！");
                res.put("success",false);
                return res;
            }

            //参数非空验证
            if (BeanUtil.isEmpty(beanDTO.getFileFormat())) return MapUtil.getResultMap(map, "001", "上传失败，请选择文件", false);

            //上传附件取消格式校验
//            if (!Arrays.asList(LoadConfig.get("APPENDIX_TYPE").split(",")).contains(beanDTO.getFileFormat()))
//                return MapUtil.getResultMap(map, "001", "上传失败，附件格式异常", false);

            if (BeanUtil.isEmpty(beanDTO.getEdocImageType()))
                return MapUtil.getResultMap(map, "001", "上传失败，单证类型为空", false);

            if (!beanDTO.getEdocImageType().trim().equals(Constant.BillTypeCode.BANK_TICKET))
                return MapUtil.getResultMap(map, "001", "上传失败，单证类型错误", false);

            if (BeanUtil.isEmpty(beanDTO.getEnclosureName()))
                return MapUtil.getResultMap(map, "001", "上传失败，影像名称为空", false);

            if (BeanUtil.isEmpty(beanDTO.getTaskId())) return MapUtil.getResultMap(map, "001", "上传失败，任务Id为空", false);

            //任务校验
            Map<String, Object> validateTaskMap = this.validateTaskInfo(map, beanDTO);
            if (!(boolean) validateTaskMap.get("success")) return validateTaskMap;


            //服务器上传图片
            EdocHeader edocHeader = this.edocHeaderService.queryById(Long.valueOf(beanDTO.getTaskId()));
            String rootPath = LoadConfig.get("ROOT_PATH");
            String imagePath = null;
            EdocImage edocImageParam = new EdocImage();
            edocImageParam.setEdocHeaderId(edocHeader.getId());
            int pageNo = this.edocImageService.queryMaxPagNum(edocHeader.getId());
            //Long pageNo = edocImages.get(0).getPageNo();
            if (BeanUtil.isEmpty(pageNo) || pageNo == 0) {
                //return MapUtil.getResultMap(map, "001", "上传失败，影像编号为空", false);
                pageNo = 1;

            }


            if ("jpg".equals(beanDTO.getFileFormat())
                    || "png".equals(beanDTO.getFileFormat())
                    || "JPG".equals(beanDTO.getFileFormat())
                    || "PNG".equals(beanDTO.getFileFormat())) {
                imagePath = EdocClientUtil.getImagePath(beanDTO.getFileFormat(), edocHeader, pageNo + 1 + "", rootPath);
                String serverImagePath = rootPath + imagePath;

                if (!getPicture(beanDTO.getBytes(), serverImagePath))
                    return MapUtil.getResultMap(map, "001", "上传失败，银行回单异常", false);

                //保存影像
                EdocImage edocImage = new EdocImage();
                edocImage.setEdocImageType(beanDTO.getEdocImageType());
                edocImage.setEdocHeaderId(Long.valueOf(beanDTO.getTaskId()));
                edocImage.setImageName(beanDTO.getEnclosureName());
                edocImage.setImageUrl(imagePath);
                edocImage.setPageNo(Long.valueOf(pageNo + 1));
                edocImage.setImageRootPath(rootPath);
                edocImage.setVersion(1L);
                edocImage.setImageFormat("jpg");
                edocImage.setDeleted(0);
                edocImage.setCreateBy(userName);
                edocImage.setImageSource("2");
                edocImageService.add(edocImage);

            } else if ("pdf".equals(beanDTO.getFileFormat())
                    || "PDF".equals(beanDTO.getFileFormat())) {
                // 生成PDF临时文件
                String tmpPath = getRootPath() + "/ElcPdftmp" + "/tmpPdf_bank_ticket.pdf";
                bytesToFile(beanDTO.getBytes(), tmpPath);
                int pdfPageNums = getPdfPageNums(new File(tmpPath));
                for (int i=0;i<pdfPageNums;i++) {
                    int pageNoforPdf = this.edocImageService.queryMaxPagNum(edocHeader.getId());
                    imagePath = EdocClientUtil.getImagePath("jpg", edocHeader, pageNoforPdf + 1 + "", rootPath);
                    String serverImagePath = rootPath + imagePath;
                    pdfToImage(new File(tmpPath), serverImagePath,i);
                    //保存影像
                    EdocImage edocImage = new EdocImage();
                    edocImage.setEdocImageType(beanDTO.getEdocImageType());
                    edocImage.setEdocHeaderId(Long.valueOf(beanDTO.getTaskId()));
                    edocImage.setImageName(beanDTO.getEnclosureName());
                    edocImage.setImageUrl(imagePath);
                    edocImage.setPageNo(Long.valueOf(pageNoforPdf + 1));
                    edocImage.setImageRootPath(rootPath);
                    edocImage.setVersion(1L);
                    edocImage.setImageFormat("jpg");
                    edocImage.setDeleted(0);
                    edocImage.setCreateBy(userName);
                    edocImage.setImageSource("2");
                    edocImageService.add(edocImage);
                }
            } else {
                return MapUtil.getResultMap(map, "001", "上传失败，只支持jpg、png或pdf文件", false);
            }
            res = MapUtil.getResultMap(map, "000", "银行回单上传成功", true);
        } catch (Exception e) {
            IMAGES_LOGGER.error("系统异常,原因{}", e);
            return MapUtil.getResultMap(map, "001", "系统异常", false);
        }
        return res;
    }

    @POST
    @Path("uploadFile")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> uploadFile(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> map = null;
        BeanDTO beanDTO = new BeanDTO();
        response.setCharacterEncoding("UTF-8");
        String userName = null;
        try {
            //解析获取前台传递的formData
            map = FileUtil.analysisFormData(request);
            MapUtil.map2Bean(map, beanDTO);
            // 手工处理token
            String token = (String) map.get("token");
            if (BeanUtil.isEmpty(token)) {
                res.put("code","001");
                res.put("msg","token不能为空！");
                res.put("success",false);
                return res;
            }
            userName = checkToken(token);
            if (BeanUtil.isEmpty(userName)) {
                res.put("code","002");
                res.put("msg","token校验异常！");
                res.put("success",false);
                return res;
            }
            //参数非空验证
            if (BeanUtil.isEmpty(beanDTO.getFileFormat()))
                return MapUtil.getResultMap(map, "001", "上传失败，请选择文件", RESP_FAIL);

            //上传附件取消格式校验
//            if (!Arrays.asList(LoadConfig.get("APPENDIX_TYPE").split(",")).contains(beanDTO.getFileFormat()))
//                return MapUtil.getResultMap(map, "001", "上传失败，附件格式异常", false);

            if (BeanUtil.isEmpty(beanDTO.getEdocImageType()))
                return MapUtil.getResultMap(map, "001", "上传失败，单证类型为空", RESP_FAIL);

            if (!beanDTO.getEdocImageType().trim().equals(Constant.BillTypeCode.NORMAL_BILL))
                return MapUtil.getResultMap(map, "001", "上传失败，单证类型错误", RESP_FAIL);

            if (BeanUtil.isEmpty(beanDTO.getEnclosureName()))
                return MapUtil.getResultMap(map, "001", "上传失败，影像名称为空", RESP_FAIL);

            if (BeanUtil.isEmpty(beanDTO.getTaskId()))
                return MapUtil.getResultMap(map, "001", "上传失败，任务Id为空", RESP_FAIL);

            //任务校验
            Map<String, Object> validateTaskMap = this.validateTaskInfo(map, beanDTO);
            if (!MapUtil.getBoolean(validateTaskMap)) return validateTaskMap;

            //服务器上传图片
            EdocHeader edocHeader = this.edocHeaderService.queryById(Long.valueOf(beanDTO.getTaskId()));
            String rootPath = Constant.ImagePath.ROOT_PATH;
            String imagePath = null;
            EdocImage edocImageParam = new EdocImage();
            edocImageParam.setEdocHeaderId(edocHeader.getId());
            List<EdocImage> edocImages = this.edocImageService.queryEdocImage(edocImageParam);
            Long pageNo = edocImages.get(0).getPageNo();
            if (BeanUtil.isEmpty(pageNo))
                return MapUtil.getResultMap(map, "001", "上传失败，影像编号为空", RESP_FAIL);
            imagePath = EdocClientUtil.getImagePath(beanDTO.getFileFormat(), edocHeader, pageNo + 1 + "", rootPath);
            String serverImagePath = rootPath + imagePath;
            if (!getPicture(beanDTO.getBytes(), serverImagePath))
                return MapUtil.getResultMap(map, "001", "上传失败，附件异常", RESP_FAIL);

            //保存影像
            EdocImage edocImage = new EdocImage();
            edocImage.setEdocImageType(beanDTO.getEdocImageType());
            edocImage.setEdocHeaderId(Long.valueOf(beanDTO.getTaskId()));
            edocImage.setImageName(beanDTO.getEnclosureName());
            edocImage.setImageUrl(imagePath);
            edocImage.setPageNo(pageNo + 1);
            edocImage.setImageRootPath(rootPath);
            edocImage.setVersion(1L);
            edocImage.setImageFormat(beanDTO.getFileFormat());
            edocImage.setDeleted(0);
            edocImage.setCreateBy(userName);
            edocImageService.add(edocImage);
            res = MapUtil.getResultMap(map, "000", "附件上传成功", RESP_SUCCESS);
        } catch (Exception e) {
            IMAGES_LOGGER.error("系统异常,原因{}", e);
            return MapUtil.getResultMap(map, "001", "系统异常", RESP_FAIL);
        }
        return res;
    }

    @GET
    @Path("download")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(Map<String, Object> map) {
        try {
            BeanDTO beanDTO = new BeanDTO();
            MapUtil.map2Bean(map, beanDTO);
            //参数非空验证
            if (BeanUtil.isEmpty(beanDTO.getImageId()))
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "下载失败，下载标识为空", RESP_FAIL))).build();

            EdocImage edocImage = edocImageService.queryById(Long.valueOf(beanDTO.getImageId()));
            if (BeanUtil.isEmpty(edocImage))
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "下载失败，未找到该文件", RESP_FAIL))).build();

            EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
            if (BeanUtil.isEmpty(edocHeader) || edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.INVALID))
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "下载失败，该任务已作废", RESP_FAIL))).build();

            File file = new File(edocImage.getImageRootPath() + edocImage.getImageUrl());
            if (!file.isFile() || !file.exists())
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "下载失败，未找到该文件", RESP_FAIL))).build();

            String mt = new MimetypesFileTypeMap().getContentType(file);
            String fileName = file.getName();
            return Response.ok(file, mt)
                    .header("Content-disposition", "attachment;filename=" + fileName)
                    .header("ragma", "No-cache")
                    .header("Cache-Control", "no-cache").build();
        } catch (Exception e) {
            IMAGES_LOGGER.error("系统异常,原因{}", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "系统异常", RESP_FAIL))).build();
        }
    }

    @GET
    @Path("downloadFiles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFiles(Map<String, Object> map) {
        try {
            BeanDTO beanDTO = new BeanDTO();
            MapUtil.map2Bean(map, beanDTO);
            //参数非空验证
            if (BeanUtil.isEmpty(beanDTO.getTaskId()))
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "下载失败，下载标识为空", RESP_FAIL))).build();

            EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(beanDTO.getTaskId()));
            if (BeanUtil.isEmpty(edocHeader) || edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.INVALID))
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "下载失败，该任务已作废", RESP_FAIL))).build();
            List<EdocImage> edocImages = edocImageService.selectAllImagesByEdocHeaderId(Long.valueOf(beanDTO.getTaskId()));

            if (BeanUtil.isEmpty(edocImages))
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "下载失败，未找到该文件", RESP_FAIL))).build();
            EdocImage edocImage = edocImages.get(0);
            String edocNoPath = edocImage.getImageUrl().substring(0, edocImage.getImageUrl().lastIndexOf("/") + 1);
            edocNoPath = edocImage.getImageRootPath() + edocNoPath;
            String zipPath = edocHeader.getEdocNo() + ".zip";
            ZipUtil.compress(edocNoPath, zipPath, true);
            byte[] bytes = ZipUtil.toByteArray(zipPath);
            return Response.ok(bytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-disposition", "attachment;filename=" + zipPath)
                    .header("ragma", "No-cache")
                    .header("Cache-Control", "no-cache").build();
        } catch (Exception e) {
            IMAGES_LOGGER.error("系统异常,原因{}", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new Gson().toJson(MapUtil.getResultMap(map, "001", "系统异常", RESP_FAIL))).build();
        }
    }

    /**
     * 任务信息校验
     *
     * @param map
     * @return
     * @author Jary
     */
    private Map<String, Object> validateTaskInfo(Map<String, Object> map, BeanDTO beanDTO) throws Exception {
        MapUtil.map2Bean(map, beanDTO);
        EdocHeader edocHeader;
        if (StringUtils.isEmpty(beanDTO.getTaskId())) return MapUtil.getResultMap(map, "001", "任务标识为空", RESP_FAIL);
        edocHeader = edocHeaderService.queryById(Long.valueOf(beanDTO.getTaskId()));
        if (BeanUtil.isEmpty(edocHeader)) return MapUtil.getResultMap(map, "001", "任务为空或已作废", RESP_FAIL);
        //if (StringUtils.isEmpty(edocHeader.getSeal())) return MapUtil.getResultMap(map,"001", "审核状态为空，操作失败", false);
//        if (edocHeader.getSeal().equals(EdocHeaderConstant.EdocTaskStatus.TASK_AUDIT))
//            return MapUtil.getResultMap(map,"001", "任务审核中，操作失败", false);
        /*if (edocHeader.getSeal().equals(EdocHeaderConstant.EdocTaskStatus.TASK_PASS))
            return MapUtil.getResultMap(map,"001", "任务审核已通过，操作失败", false);*/
        //if (StringUtils.isEmpty(edocHeader.getSeal())) return MapUtil.getResultMap(map,"001", "锁定状态为空，操作失败", false);
        if (edocHeader.getSeal().equals(Constant.Seal.SEALED))
            return MapUtil.getResultMap(map, "001", "任务审核已封单，操作失败", RESP_FAIL);
        return MapUtil.getResultMap(map, "000", "任务信息验证成功", RESP_SUCCESS);
    }

    /**
     * @param map
     * @return
     * @author Jary
     * 影像信息校验
     */
    private Map<String, Object> validateImageInfo(Map<String, Object> map, BeanDTO beanDTO) throws Exception {
        EdocImage edocImage = null;
        MapUtil.map2Bean(map, beanDTO);
        if (StringUtils.isEmpty(beanDTO.getImageId())) return MapUtil.getResultMap(map, "001", "影像标识为空", RESP_FAIL);
        edocImage = edocImageService.queryById(Long.valueOf(beanDTO.getImageId()));
        if (BeanUtil.isEmpty(edocImage)) return MapUtil.getResultMap(map, "001", "单据下无该影像", RESP_FAIL);
        beanDTO.setTaskId(String.valueOf(edocImage.getEdocHeaderId()));
        return MapUtil.getResultMap(map, "000", "影像信息验证成功", RESP_SUCCESS, edocImage.getEdocHeaderId());
    }

    @POST
    @Path("deleteBillImageById")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> deleteBillImageById(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            BeanDTO beanDTO = new BeanDTO();
            //参数验证
            Map<String, Object> validateImageInfo = this.validateImageInfo(map, beanDTO);
            if (!MapUtil.getBoolean(validateImageInfo)) return validateImageInfo;
            Map<String, Object> validateResult = this.validateTaskInfo(map, beanDTO);
            if (!MapUtil.getBoolean(validateResult)) return validateResult;

            //更新
            edocImageService.updateEdocImage(Long.valueOf(beanDTO.getImageId()));
            executeResult = MapUtil.getResultMap(map, "000", "资料处理成功", RESP_SUCCESS);
        } catch (Exception e) {
            IMAGES_LOGGER.error("系统异常,原因{}", e);
            return MapUtil.getResultMap(map, "001", "资料处理失败", RESP_FAIL);
        }
        return executeResult;
    }

    @Override
    public ExecuteResult save(Object o) {
        return null;
    }

    @Override
    public ExecuteResult<PageInfo> dataGrid(Object o) {
        return null;
    }

    @Override
    public ExecuteResult<String> addOrUpdateImages(byte[] bytes, Long imgId, EdocHeaderDTO edocHeader, String optType, String fileName) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //判断操作类型
            if ("add".equals(optType)) {
                //查询页码
                int pagNum = edocImageService.queryMaxPagNum(edocHeader.getId());
                String pagNo = BeanUtil.isEmpty(pagNum) ? (1 + "") : (pagNum + 1 + "");
                //组装imagePath
                String fileExtName = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
                String imageUrl = getImagePath(fileExtName, edocHeader, pagNo);
                String fullPath = ROOT_PATH + imageUrl;
                if (getPicture(bytes, fullPath)) {
                    IMAGES_LOGGER.info("保存图片成功，全路径为：{}", fullPath);
                    EdocImage edocImage = new EdocImage();
                    edocImage.setEdocHeaderId(edocHeader.getId());
                    edocImage.setImageName(fileName);
                    edocImage.setImageUrl(imageUrl);
                    edocImage.setImageRootPath(ROOT_PATH);
                    edocImage.setPageNo(Long.parseLong(pagNo));
                    edocImage.setImageFormat("jpg");
                    edocImage.setCreateBy(SubjectUtil.getUser().getUserCode());
                    edocImage.setCreateTime(new Date());
                    edocImage.setIsReplace(Constant.isReplace.NO);
                    //保存
                    edocImageService.add(edocImage);
                    result.setResult("追加图片成功");
                    result.setSuccessMessage("追加图片成功");
                } else {
                    result.addErrorMessage("图片追加失败");
                    IMAGES_LOGGER.error("图片追加失败");
                }
            } else {
                //查询当前image对象
                EdocImage edocImage = edocImageService.queryById(imgId);
                //拷贝信息到新imgae中
                EdocImage copyEdocImage = DozerUtil.map(edocImage, EdocImage.class);
                //设置创建时间
                copyEdocImage.setCreateTime(new Date());
                copyEdocImage.setCreateBy(SubjectUtil.getUser().getUserCode());
                //组装保存路径
                //组装imagePath
                String fileExtName = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
                //图片路径
                String fileRepalceName = edocHeader.getEdocNo() + "_" + UUID.randomUUID().toString().replaceAll("\\-", "") + "_" + copyEdocImage.getPageNo() + "." + fileExtName;
                Date date = edocHeader.getCreateTime() == null ? new Date() : edocHeader.getCreateTime();
                String directionName = "/" + DateUtils.formatDate(date, "yyyyMMdd") + "/" + edocHeader.getEdocNo() + "/" + edocHeader.getId();//部分路径名称
                String imgUrl = directionName + "/" + fileRepalceName;
                File folder = new File(ROOT_PATH + directionName);
                if (!(folder.isDirectory())) {
                    folder.mkdirs();
                }
                //全路径
                String fullPath = ROOT_PATH + imgUrl;
                if (getPicture(bytes, fullPath)) {
                    IMAGES_LOGGER.info("保存图片成功，全路径为：{}", fullPath);
                    //设置图片全路径
                    copyEdocImage.setImageRootPath(ROOT_PATH);
                    copyEdocImage.setImageUrl(imgUrl);
                    copyEdocImage.setImageName(fileName);
                    copyEdocImage.setIsReplace(Constant.isReplace.YES);
                    //设置ID为空
                    copyEdocImage.setId(null);
                    //替换图片
                    edocImageService.updateImages(edocImage, copyEdocImage);
                    //设置成功信息
                    result.setResult("替换图片成功");
                    result.setSuccessMessage("替换图片成功");
                } else {
                    result.addErrorMessage("图片替换失败");
                    IMAGES_LOGGER.error("图片替换失败");
                }
            }
        } catch (Exception e) {
            result.addErrorMessage("图片操作失败");
            IMAGES_LOGGER.error("图片操作失败", e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<String> updateImages(byte[] bytes, Long imgId, EdocHeaderDTO edocHeader, String fileName) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 默认路径
            String rootPath = LoadConfig.get("ROOT_PATH");
            /*ExecuteResult<SysConfDictItemDTO> resultPath = dataDictCliFacade.findByEnNameAndItemCode("IMAGE_CONFIG",
                    "ROOT_PATH");
            if(resultPath.isSuccess() && !BeanUtil.isEmpty(resultPath.getResult())){
                rootPath = resultPath.getResult().getDicItemValue();
            }*/

            //查询当前image对象
            EdocImage edocImage = edocImageService.queryById(imgId);
            //拷贝信息到新imgae中
            EdocImage copyEdocImage = DozerUtil.map(edocImage, EdocImage.class);

            //组装保存路径
            //组装imagePath
            String fileExtName = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
            // 任务下图片最大页数
            int maxPageNum = edocImageService.queryMaxPagNum(edocHeader.getId());
            // 新图片页数在此基础上加1
            int newPageNum = maxPageNum + 1;
            //图片路径
            String fileRepalceName = edocHeader.getEdocNo() + "_" + StringUtils.leftPad(String.valueOf(newPageNum), 4, "0") + "." + fileExtName;
            Date date = edocHeader.getCreateTime() == null ? new Date() : edocHeader.getCreateTime();
            //部分路径名称
            String directionName = "/" + DateUtils.formatDate(date, "yyyyMMdd") + "/" + edocHeader.getEdocNo();
            String imgUrl = directionName + "/" + fileRepalceName;

            File folder = new File(rootPath + directionName);
            if (!(folder.isDirectory())) {
                folder.mkdirs();
            }
            //全路径
            String fullPath = rootPath + imgUrl;
            if (getPicture(bytes, fullPath)) {
                IMAGES_LOGGER.info("保存图片成功，全路径为：{}", fullPath);
                //设置图片全路径
                copyEdocImage.setImageRootPath(rootPath);
                copyEdocImage.setImageUrl(imgUrl);
                copyEdocImage.setImageName(fileName);
                copyEdocImage.setIsReplace(Constant.isReplace.YES);
                //设置ID为空
                copyEdocImage.setId(null);
                //替换图片
                edocImageService.operateUpdateImages(edocImage, copyEdocImage);
                //设置成功信息
                result.setResult("替换图片成功");
                result.setSuccessMessage("替换图片成功");
            } else {
                result.addErrorMessage("图片替换失败");
                IMAGES_LOGGER.error("图片替换失败");
            }
        } catch (Exception e) {
            result.addErrorMessage("图片操作失败");
            IMAGES_LOGGER.error("图片操作失败, 失败原因:{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> againScan(EdocImageDTO edocImageDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            // 先查询edocheader 和 img
            EdocImage edocImage = edocImageService.queryById(edocImageDTO.getId());
            EdocHeader edocHeader = edocHeaderService.queryById(edocImageDTO.getId());
            edocHeader.setEdocType(Constant.BizStatus.AGAIN_SCAN); //重扫32
            edocImage.setIsReplace(edocImageDTO.getIsReplace()); // 标记img重扫
            edocHeaderService.updateByIdSelective(edocHeader);
            result.setResult(edocImageService.updateById(edocImage));
        } catch (Exception e) {
            result.addErrorMessage("服务器异常，请联系管理员");
            IMAGES_LOGGER.error("服务器异常，请联系管理员 >>>>>>>>{} ", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> updateImagesByDTO(EdocImageDTO edocImageDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            if (BeanUtil.isEmpty(edocImageDTO.getId())) {
                result.addErrorMessage("请先上传影像");
                return result;
            }

            EdocImage edocImage = edocImageService.queryById(edocImageDTO.getId());
            if (BeanUtil.isEmpty(edocImage)) {
                result.addErrorMessage("未找到对应影像");
                return result;
            }
            edocImage.setIsReplace(edocImageDTO.getIsReplace());
            edocImageService.updateByIdSelective(edocImage);
            result.setResult(1);
            result.setSuccessMessage("标记成功");
            /*// step 2 在更新edocheader
            EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
            if (edocImageDTO.getIsReplace().equals("1")) {
                edocHeader.setEdocTaskStatus(Constant.BizStatus.SINGLE_AGAIN_SCAN); // 标记31
            }else {
                edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM); // 01
            }

            result.setResult(edocHeaderService.updateById(edocHeader));*/
        } catch (Exception e) {
            result.addErrorMessage("服务器异常，请联系管理员");
            IMAGES_LOGGER.error("服务器异常，请联系管理员 >>>>>>>>{} ", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> updateImages(EdocImageDTO edocImageDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            result.setResult(edocImageService.updateById(DozerUtil.map(edocImageDTO, EdocImage.class)));
        } catch (Exception e) {
            result.addErrorMessage("服务器异常，请联系管理员");
            IMAGES_LOGGER.error("服务器异常，请联系管理员", e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocImageDTO>> selectImages(EdocImageDTO edocImage) {
        ExecuteResult<List<EdocImageDTO>> result = new ExecuteResult<>();
        try {
            List<EdocImageModel> edocImageList = edocImageService.selectImages(DozerUtil.map(edocImage, EdocImageModel.class));
            if (edocImageList != null) {
                result.setResult(DozerUtil.mapList(edocImageList, EdocImageDTO.class));
            } else {
                result.setResult(null);
            }
        } catch (Exception e) {
            result.addErrorMessage("没有对应文件");
            IMAGES_LOGGER.error("没有对应文件", e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocImageDTO> selectImageById(Long id) {
        ExecuteResult<EdocImageDTO> result = new ExecuteResult<>();
        try {
            EdocImage edocImage = edocImageService.queryById(id);
            if (!BeanUtil.isEmpty(edocImage)) {
                result.setResult(DozerUtil.map(edocImage, EdocImageDTO.class));
            }
        } catch (Exception e) {
            result.addErrorMessage("没有对应文件");
            IMAGES_LOGGER.error("没有对应文件", e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> queryMaxPagNum(Long edocHeaderId) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            result.setResult(edocImageService.queryMaxPagNum(edocHeaderId));
        } catch (Exception e) {
            result.addErrorMessage("查询图片页面错误");
            IMAGES_LOGGER.error("查询图片页面错误", e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<String> uploadFiles(String fullPath, String fileName, String fileExtName, String pageNo, String imageUrl, Long edocHeaderId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        String[] fileExt = {"jpg", "jpeg", "png", "bmp", "JPEG", "JPG", "PNG", "BMP"};
        try {
            IMAGES_LOGGER.info("保存图片成功，全路径为：{}", fullPath);
            EdocImage edocImage = new EdocImage();
            edocImage.setEdocHeaderId(edocHeaderId);
            edocImage.setImageName(fileName);
            edocImage.setImageUrl(imageUrl);
            edocImage.setImageRootPath(ROOT_PATH);
            edocImage.setPageNo(Long.parseLong(pageNo));
            if (Arrays.asList(fileExt).contains(fileExtName)) {
                edocImage.setImageFormat("jpg");
                edocImage.setIsReplace(Constant.isReplace.NO);
            } else if ("PDF".equals(fileExtName.toUpperCase())) {
                edocImage.setImageFormat(fileExtName.toUpperCase());
            } else {
                edocImage.setImageFormat(fileExtName);
            }
//            edocImage.setCreateBy(WebSubjectUtil.getUser().getUserCode());
            edocImage.setCreateTime(new Date());
            //保存
            edocImageService.add(edocImage);
            result.setSuccessMessage("上传文件成功");
        } catch (Exception e) {
            IMAGES_LOGGER.error("上传文件失败", e.toString());
            result.addErrorMessage("上传文件失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> uploadFiles(String fullPath, String fileName, String fileExtName, String pageNo, String imageUrl, Long edocHeaderId, String edocImageType, String createBy) {

        ExecuteResult<String> result = new ExecuteResult<>();
        String[] fileExt = {"jpg", "jpeg", "png", "bmp", "JPEG", "JPG", "PNG", "BMP"};
        try {
            IMAGES_LOGGER.info("保存图片成功，全路径为：{}", fullPath);
            EdocImage edocImage = new EdocImage();
            edocImage.setEdocHeaderId(edocHeaderId);
            edocImage.setImageName(fileName);
            edocImage.setImageUrl(imageUrl);
            edocImage.setImageRootPath(ROOT_PATH);
            edocImage.setEdocImageType(edocImageType);
            edocImage.setImageParentId(0L);
            edocImage.setIsReplace("0");
            edocImage.setImageSource("2");
            edocImage.setImageFormat("jpg");
            edocImage.setPageNo(Long.parseLong(pageNo));
            edocImage.setCreateTime(new Date());
            edocImage.setCreateBy(createBy);
            //保存
            edocImageService.add(edocImage);
            result.setResult(edocImage.getId().toString());
            result.setSuccessMessage("上传文件成功");
        } catch (Exception e) {
            IMAGES_LOGGER.error("上传文件失败", e.toString());
            result.addErrorMessage("上传文件失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> uploadFiles(String fullPath, String fileName, String fileExtName, String pageNo, String imageUrl, Long edocHeaderId, String edocImageType) {
        ExecuteResult<String> result = new ExecuteResult<>();
        String[] fileExt = {"jpg", "jpeg", "png", "bmp", "JPEG", "JPG", "PNG", "BMP"};
        try {
            IMAGES_LOGGER.info("保存图片成功，全路径为：{}", fullPath);
            EdocImage edocImage = new EdocImage();
            edocImage.setEdocHeaderId(edocHeaderId);
            edocImage.setImageName(fileName);
            edocImage.setImageUrl(imageUrl);
            edocImage.setImageRootPath(ROOT_PATH);
            edocImage.setEdocImageType(edocImageType);
            edocImage.setImageParentId(0L);
            edocImage.setIsReplace("0");
            edocImage.setImageSource("2");
            edocImage.setImageFormat("jpg");
            edocImage.setPageNo(Long.parseLong(pageNo));
           /* if (Arrays.asList(fileExt).contains(fileExtName)) {
                edocImage.setImageFormat("jpg");
                edocImage.setIsReplace(Constant.isReplace.NO);
            } else if ("PDF".equals(fileExtName.toUpperCase())) {
                edocImage.setImageFormat(fileExtName.toUpperCase());
            } else {
                edocImage.setImageFormat(fileExtName);
            }*/
//            edocImage.setCreateBy(WebSubjectUtil.getUser().getUserCode());
            edocImage.setCreateTime(new Date());
            //保存
            edocImageService.add(edocImage);
            result.setResult(edocImage.getId().toString());
            result.setSuccessMessage("上传文件成功");
        } catch (Exception e) {
            IMAGES_LOGGER.error("上传文件失败", e.toString());
            result.addErrorMessage("上传文件失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> uploadFiles(String fullPath, String fileName, String fileExtName, String pageNo, String imageUrl, Long edocHeaderId, String edocImageType, Long edocAttachId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        String[] fileExt = {"jpg", "jpeg", "png", "bmp", "JPEG", "JPG", "PNG", "BMP"};
        try {
            IMAGES_LOGGER.info("保存图片成功，全路径为：{}", fullPath);
            EdocImage edocImage = new EdocImage();
            edocImage.setExtField5(edocAttachId.toString());
            edocImage.setEdocHeaderId(edocHeaderId);
            edocImage.setImageName(fileName);
            edocImage.setImageUrl(imageUrl);
            edocImage.setImageRootPath(ROOT_PATH);
            edocImage.setEdocImageType(edocImageType);
            edocImage.setImageParentId(0L);
            edocImage.setIsReplace("0");
            edocImage.setPageNo(Long.parseLong(pageNo));
            if (Arrays.asList(fileExt).contains(fileExtName)) {
                edocImage.setImageFormat("jpg");
                edocImage.setIsReplace(Constant.isReplace.NO);
            } else if ("PDF".equals(fileExtName.toUpperCase())) {
                edocImage.setImageFormat(fileExtName.toUpperCase());
            } else {
                edocImage.setImageFormat(fileExtName);
            }
//            edocImage.setCreateBy(WebSubjectUtil.getUser().getUserCode());
            edocImage.setCreateTime(new Date());
            //保存
            edocImageService.add(edocImage);
            result.setSuccessMessage("上传文件成功");
        } catch (Exception e) {
            IMAGES_LOGGER.error("上传文件失败", e.toString());
            result.addErrorMessage("上传文件失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> deleteImage(Long id) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            EdocImage edocImage = edocImageService.queryById(id);
            if (BeanUtil.isEmpty(edocImage)) {
                result.addErrorMessage("没有对应文件记录");
                return result;
            }
            edocImageService.remove(edocImage);
            result.setResult(1);
            result.setSuccessMessage("删除成功");
        } catch (Exception e) {
            result.addErrorMessage("删除失败");
            IMAGES_LOGGER.error("删除失败", e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocImageDTO>> queryEdocImageByIds(List<Long> ids) {
        ExecuteResult<List<EdocImageDTO>> result = new ExecuteResult<>();
        try {
            List<EdocImageModel> models = edocImageService.queryEdocImageByIds(ids);
            if (BeanUtil.isEmpty(models)) {
                result.addErrorMessage("没有查询到文件数据");
                return result;
            }
            result.setResult(DozerUtil.mapList(models, EdocImageDTO.class));
        } catch (Exception e) {
            result.addErrorMessage("获取文件数据失败");
            IMAGES_LOGGER.error("获取文件数据失败", e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocImageDTO>> selectImagesByEdocHeaderId(Long edocHeaderId, int docType) {
        ExecuteResult<List<EdocImageDTO>> result = new ExecuteResult<>();
        try {
            List<EdocImageModel> models = edocImageService.selectImagesByEdocHeaderId(edocHeaderId, docType);
            if (models != null && !models.isEmpty()) {
                result.setResult(DozerUtil.mapList(models, EdocImageDTO.class));
                result.setSuccessMessage("查询成功");
            } else {
                result.setSuccessMessage("没有对应文件列表");
            }
        } catch (Exception e) {
            result.addErrorMessage("查询失败");
            IMAGES_LOGGER.error("查询失败", e.toString());
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocImageDTO> checkExistImages(EdocImageDTO uploadImageas) {
        ExecuteResult<EdocImageDTO> result = new ExecuteResult<>();
        try {
            // TODO  根据业务逻辑判断: 是否覆盖原始图片以及影像,或者新图片增加版本号
            edocImageService.delImagesByEdocHeaderIdAndPageNo(uploadImageas.getEdocHeaderId(), uploadImageas.getPageNo());
            uploadImageas.setIsReplace(Constant.isReplace.NO);
            uploadImageas.setImageName("");
        } catch (Exception e) {
            result.addErrorMessage("删除被覆盖的图片失败");
            IMAGES_LOGGER.error("删除被覆盖的图片失败", e);
        }
        result.setResult(uploadImageas);
        return result;
    }

    @Override
    public ExecuteResult<List<EdocImageDTO>> findEdocImage(EdocImageDTO edocImageDTO) {
        ExecuteResult<List<EdocImageDTO>> executeResult = new ExecuteResult<>();
        List<EdocImageDTO> billTypeDTOList = new ArrayList<>();
        try {
            Assert.notNull(edocImageDTO, "查询条件对象为空");
            EdocImage edocImage = DozerUtil.map(edocImageDTO, EdocImage.class);
            List<EdocImage> edocImageList = edocImageService.queryEdocImage(edocImage);
            if (edocImageList != null && !edocImageList.isEmpty()) {
                billTypeDTOList = DozerUtil.mapList(edocImageList, EdocImageDTO.class);
            }
            executeResult.setResult(billTypeDTOList);
            return executeResult;
        } catch (Exception e) {
            IMAGES_LOGGER.error("对象转换失败,失败原因<{}>", e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<EdocImageDTO> findOneEdocImage(EdocImageDTO edocImageDTO) {
        ExecuteResult<EdocImageDTO> executeResult = new ExecuteResult<>();
        EdocImageDTO billTypeDTO = new EdocImageDTO();
        try {
            Assert.notNull(edocImageDTO, "查询条件对象为空");
            EdocImage edocImage = DozerUtil.map(edocImageDTO, EdocImage.class);
            List<EdocImage> edocImageList = edocImageService.queryEdocImage(edocImage);
            if (edocImageList != null && !edocImageList.isEmpty()) {
                billTypeDTO = DozerUtil.map(edocImageList.get(0), EdocImageDTO.class);
            }
            executeResult.setResult(billTypeDTO);
            return executeResult;
        } catch (Exception e) {
            IMAGES_LOGGER.error("对象转换失败,失败原因<{}>", e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<List<EdocImageDTO>> findEdocImageByParentId(Long id) {
        ExecuteResult<List<EdocImageDTO>> result = new ExecuteResult<>();
        List<EdocImageDTO> edocImageDTOList = new ArrayList<>();
        try {
            if (BeanUtil.isEmpty(id)) {
                result.addErrorMessage("输入参数不可为空");
                return result;
            }
            List<EdocImage> edocImageList = edocImageService.queryEdocImageByParentId(id);
            if (!BeanUtil.isEmpty(edocImageList)) {
                edocImageDTOList = DozerUtil.mapList(edocImageList, EdocImageDTO.class);
                result.setResult(edocImageDTOList);
            } else {
                result.addErrorMessage("该发票下没有附件！");
            }
        } catch (Exception e) {
            result.addErrorMessage("查询发票附件出现异常");
            IMAGES_LOGGER.error("查询发票附件出现异常，异常信息为:{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocImageDTO>> queryVoucherImage(Long id) {
        ExecuteResult<List<EdocImageDTO>> result = new ExecuteResult<>();
        List<EdocImageDTO> imgDTOS = new ArrayList<>();
        try {
            Assert.notNull(id, "凭证id不能为空");
            List<EdocImage> images = edocImageService.queryVouncherById(id);
            if (null == images) {
                result.addErrorMessage("对应凭证影像不存在");
                return result;
            }
            imgDTOS = DozerUtil.mapList(images, EdocImageDTO.class);
            result.setResult(imgDTOS);
            result.setSuccessMessage("查询凭证号影像成功");
        } catch (Exception e) {
            result.addErrorMessage("查询凭证影像失败");
            IMAGES_LOGGER.error("查询凭证影像出现异常，异常信息为:{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> deleteImageAndInvoiceByImageId(Long edocImageId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            EdocImage edocImage = edocImageService.queryById(edocImageId);
            //删除影像
            edocImageService.remove(edocImage);
            //删除发票
            Invoice invoice = invoiceService.queryInvoiceByImgId(edocImageId);
            invoiceService.remove(invoice);
            //删除发票明细
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvId(invoice.getId());
            List<InvoiceItem> invoiceItemList = invoiceItemService.query(invoiceItem);
            for (InvoiceItem i : invoiceItemList) {
                invoiceItemService.remove(i);
            }
        } catch (Exception e) {
            result.addErrorMessage("删除影像和发票信息失败");
            IMAGES_LOGGER.error("删除影像和发票信息失败:{}", e);
        }
        return result;
    }

    /**
     * 目录授权
     *
     * @param rootPath
     * @param finalPath
     * @param fileName
     * @throws IOException
     */
    private void grantAuthorization(String rootPath, String finalPath, String fileName) throws IOException {
        String osFlag = System.getProperties().getProperty("os.name").toUpperCase();
        if (osFlag.indexOf("WINDOWS") == -1) {
            String command = "chmod -R" + " 755" + " " + rootPath;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
            IMAGES_LOGGER.info("修改权限{}", command);
        }
        IMAGES_LOGGER.info("生成图片文件成功:" + finalPath + "/" + fileName);
    }

    /**
     * pdf转图片
     * 只转第一页
     * @param file
     * @param desFilePath
     * @return
     * @throws Exception
     */
    private String pdfToImage(File file, String desFilePath) throws Exception {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            //int pageCount = doc.getNumberOfPages();
        /*for (int i = 0; i < pageCount; i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 296);
            BufferedImage Qr = image.getSubimage(150,0,250,400); //截图图片
            ImageIO.write(image, "PNG", new File(desFilePath)); //生成电子发票图片
            ImageIO.write(Qr, "PNG", new File(desQRpath)); //生成二维码图片
            //parseQRCode("F:\\pdfTest\\QR.jpg"); //解析二维码
        }*/
            BufferedImage image = renderer.renderImageWithDPI(0, 296);
            BufferedImage Qr = image.getSubimage(0, 0, 700, 700); //截图图片
            ImageIO.write(image, "jpg", new File(desFilePath)); //生成电子发票图片
            //ImageIO.write(Qr, "PNG", new File(desQRpath)); //生成二维码图片
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private int getPdfPageNums(File file) {
        int pageNums = 0;
        PDDocument doc = null;
        try {
            doc = PDDocument.load(file);
            pageNums = doc.getNumberOfPages();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pageNums;
    }


    /**
     *
     * pdf转图片
     * 按入參页数
     * @param file
     * @param desFilePath
     * @return
     * @throws Exception
     */
    private String pdfToImage(File file, String desFilePath, int pageIndex) throws Exception {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImageWithDPI(pageIndex, 296);
            ImageIO.write(image, "jpg", new File(desFilePath)); //生成电子发票图片
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 电子发票pdf转图片，截取pdf二维码图片
     *
     * @param file
     * @return
     */
    private String pdfToImage(File file, String desFilePath, String desQRpath) throws Exception {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            //int pageCount = doc.getNumberOfPages();
        /*for (int i = 0; i < pageCount; i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 296);
            BufferedImage Qr = image.getSubimage(150,0,250,400); //截图图片
            ImageIO.write(image, "PNG", new File(desFilePath)); //生成电子发票图片
            ImageIO.write(Qr, "PNG", new File(desQRpath)); //生成二维码图片
            //parseQRCode("F:\\pdfTest\\QR.jpg"); //解析二维码
        }*/
            // 第一页电子发票的处理
            BufferedImage image = renderer.renderImageWithDPI(0, 296);
            BufferedImage Qr = image.getSubimage(0, 0, 700, 700); //截图图片
            ImageIO.write(image, "jpg", new File(desFilePath)); //生成电子发票图片
            ImageIO.write(Qr, "jpg", new File(desQRpath)); //生成二维码图片
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this.parseQRCode(desQRpath);
    }

    /**
     * 解析电子发票二维码
     *
     * @param filePath
     * @return
     */
    private String parseQRCode(String filePath) {
        String content = "";
        Result result = null;
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            MultiFormatReader formatReader = new MultiFormatReader();
            try {
                result = formatReader.decode(binaryBitmap, hints);
            }catch (Exception e) {
                e.printStackTrace();
            }

            if (BeanUtil.isEmpty(result)||!result.getText().contains(",")) {
                QRCodeDecoder codeDecoder = new QRCodeDecoder();
                String result2 = new String(codeDecoder.decode(new MyQRCodeImage(image)), "utf-8");
                System.out.println(result2);
                if (!BeanUtil.isEmpty(result2)) {
                    return result2;
                }
            }

            System.out.println("result 为：" + result.toString());
            System.out.println("resultFormat 为：" + result.getBarcodeFormat());
            System.out.println("resultText 为：" + result.getText());
            //设置返回值
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 获取保存文件根目录
     *
     * @return
     */
    private String getRootPath() {
        String rootPath = LoadConfig.get("ELECINV_STOREPATH");
        /*ExecuteResult<SysConfDictItemDTO> sysConfDictItemDTOExecuteResult = dataDictCliFacade.findByEnNameAndItemCode("NEW_HOPE_CONFIG","UPLOAD_PATH");
        if (!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult())&&!BeanUtil.isEmpty(sysConfDictItemDTOExecuteResult.getResult().getDicItemValue())) {
            rootPath = sysConfDictItemDTOExecuteResult.getResult().getDicItemValue();
        }*/
        return rootPath;
    }

    private Map<String, Object> getPDFFileForElcPDF(HttpServletRequest request, String filePath) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // 构造一个文件上传处理对象
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");  // 支持中文文件名
        List list = new ArrayList<FileItem>();
        list = upload.parseRequest(request);
        for (int i = 0; i < list.size(); i++) {
            FileItem item = (FileItem) list.get(i);
            if (item.isFormField()) {    //普通表单值
                map.put(item.getFieldName(), item.getString("UTF-8"));
            } else {
                String name = item.getName(); //获得上传的文件名（IE上是文件全路径，火狐等浏览器仅文件名）
                String fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());
                String fileFormat = fileName.substring(fileName.lastIndexOf("."));   //文件扩展名
                byte[] bytes = item.get();
                bytesToFile(bytes, filePath);
                map.put("file", new File(filePath));
                map.put("fileName", name);
            }
        }
        return map;
    }




    private String checkToken(String token) {
        String userName = null;
        try {
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");
            headers.set("Authorization", new StringBuilder("Bearer ").append(token).toString());
            String verifyUrl = LoadConfig.get("VERIFY_LOGIN_URL");
            ResponseEntity<HashMap> responseLogin = template.exchange(verifyUrl, HttpMethod.GET, new HttpEntity<>(headers), HashMap.class);
            if(responseLogin != null && responseLogin.getBody() != null && responseLogin.getBody().get("userName") != null && !BeanUtil.isEmpty(responseLogin.getBody().get("userName").toString())) {
                userName = (String) responseLogin.getBody().get("userName");
            }
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return userName;
    }


}
