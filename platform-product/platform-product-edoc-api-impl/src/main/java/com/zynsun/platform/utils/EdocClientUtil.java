package com.zynsun.platform.utils;

import com.github.pagehelper.StringUtil;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.dto.edocClient.AbstractResponse;
import com.zynsun.platform.edoc.dto.edocClient.RequestHeader;
import com.zynsun.platform.edoc.dto.edocClient.ResponseHeader;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.request.DataUploadRequestBodyItem;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.response.DataUploadResponseBodyItem;
import constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 客户端相关工具类
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/05 15:19
 */
public class EdocClientUtil {

    private static final Logger EDOC_ECLIENT_UTIL_LOGGER = LoggerFactory.getLogger(EdocClientUtil.class);

    /**
     * 判断对象是否为空
     *
     * @param element
     * @return
     */
    public static boolean isEmpty(Object element) {
        if (null == element) {
            return true;
        }
        if (element instanceof CharSequence) {
            return ((CharSequence) element).length() == 0;
        }
        if (element instanceof Collection) {
            return ((Collection) element).isEmpty();
        }
        if (element instanceof Map) {
            return ((Map) element).isEmpty();
        }
        if (element.getClass().isArray()) {
            if (element instanceof Object[]) {
                return ((Object[]) element).length == 0;
            }
            if (element instanceof boolean[]) {
                return ((boolean[]) element).length == 0;
            }
            if (element instanceof byte[]) {
                return ((byte[]) element).length == 0;
            }
            if (element instanceof char[]) {
                return ((char[]) element).length == 0;
            }
            if (element instanceof double[]) {
                return ((double[]) element).length == 0;
            }
            if (element instanceof float[]) {
                return ((float[]) element).length == 0;
            }
            if (element instanceof int[]) {
                return ((int[]) element).length == 0;
            }
            if (element instanceof long[]) {
                return ((long[]) element).length == 0;
            }
            if (element instanceof short[]) {
                return ((short[]) element).length == 0;
            }
        }
        return false;
    }

    /**
     * 判断对象是否非空
     *
     * @param element
     * @return
     */
    public static boolean isNotEmpty(Object element) {
        return !isEmpty(element);
    }

    public static Long stringToLong(String element, boolean catchException) {
        Long rtnLong = 0L;
        try {
            rtnLong = Long.parseLong(element);
        } catch (Exception e) {
            if (catchException) {
                throw e;
            }
        }
        return rtnLong;
    }

    /**
     * 设置返回responseCode
     *
     * @param status
     * @param message
     * @param cls
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static <T extends AbstractResponse> T setResult(String status, String message, Class<T> cls) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T t = cls.newInstance();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResponseStatus(status);
        responseHeader.setResponseMessage(message);
        t.setResponseHeader(responseHeader);
        return t;
    }


    public static <T> T getRequestBean(String reqStr, Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] Methods = cls.getDeclaredMethods();
        if (isEmpty(reqStr)) {
            return null;
        }
        T t = cls.newInstance();
        reqStr = reqStr.replace("_", "").toUpperCase();
        for (int i = 0; i < Methods.length; i++) {
            if (Methods[i].getName().startsWith("get")) {
                continue;
            }
            String strFile = Methods[i].getName().substring(3).toUpperCase();
            if (reqStr.indexOf(strFile) != -1) {
                String value = reqStr.substring(reqStr.indexOf("<" + strFile + ">") + strFile.length() + 2, reqStr.indexOf("</" + strFile + ">"));
                Methods[i].invoke(t, value);
            }
        }
        return t;
    }

    public static ResponseHeader getResponseIsClientHeaderNull(RequestHeader header, boolean needMethod) {
        if (isEmpty(header)) {
            return new ResponseHeader("请求体不能为空.", false);
        }
        if (isEmpty(header.getClientMac())) {
            return new ResponseHeader("客户端MAC不能为空.", false);
        }
        if (isEmpty(header.getClientIp())) {
            return new ResponseHeader("客户端IP不能为空.", false);
        }
        if (isEmpty(header.getClientPc())) {
            return new ResponseHeader("计算机名称不能为空.", false);
        }
        if (needMethod && isEmpty(header.getMethod())) {
            return new ResponseHeader("获取配置的METHOD不能为空.", false);
        }
        return null;
    }

    /**
     * 单证条目信息校验
     *
     * @param item
     * @return
     */
    public static String validateRequetItemEmpty(DataUploadRequestBodyItem item) {
        if (StringUtil.isEmpty(item.getGuid())) {
            return "单证条目属性值 GUID 不能为空";
        }
        if (StringUtil.isEmpty(item.getEdocHeaderId())) {
            return "单证条目属性值 EdocHeaderId 不能为空";
        }
        if (StringUtil.isEmpty(item.getScanTime())) {
            return "单证条目属性值 ScanTime 不能为空";
        }
        if (StringUtil.isEmpty(item.getPageNumber())) {
            return "单证条目属性值 PageNumber 不能为空";
        }
        if (StringUtil.isEmpty(item.getDocBytedata())) {
            return "单证条目属性值 DocBytedata 不能为空";
        }
        if (StringUtil.isEmpty(item.getDocFormat())) {
            return "单证条目属性值 DocFormat 不能为空";
        } else if ((!Constant.PictureType.JPG.equals(item.getDocFormat())
                && (!Constant.PictureType.PNG.equals(item.getDocFormat()))
                && (!Constant.PictureType.TIF.equals(item.getDocFormat())))) {
            return "单证条目属性值 DocFormat 错误";
        }
        return null;
    }

    /**
     * @Description：图片字节流存储
     */
    public static boolean getPicture(byte[] b, String imgFilePath) {
        boolean flag = false;
        try {
            if (b != null) { // 图像数据为空
                // Base64解码
                EDOC_ECLIENT_UTIL_LOGGER.debug("======图片的字符串大小为" + b.length + "===========");
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
            EDOC_ECLIENT_UTIL_LOGGER.error("==解密图片字节码异常==", e);
        }
        return flag;
    }

    /**
     * @param status       上传结果 Y：成功；N：失败
     * @param message      返回信息
     * @param guid         本地唯一标识
     * @param imgId        服务器端标识
     * @param responseItem 单证影像条目
     * @return ImageDataResponseBodyItem
     */
    public static DataUploadResponseBodyItem addResponseBodyItem(boolean status,
                                                                 String message,
                                                                 String guid,
                                                                 String imgId,
                                                                 DataUploadResponseBodyItem responseItem) {
        responseItem.setGuid(guid);
        responseItem.setSvrGuid(imgId);
        responseItem.setResult(status ? "Y" : "N");
        responseItem.setMessage(message);
        return responseItem;
    }

    /**
     * @Description：获取影像的图片存储路径
     */
    public static String getImagePath(String fileNameEnd, EdocHeader edocHeader, String imageNum, String rootPath) {
        String edocNo = edocHeader.getEdocNo();
        if (edocHeader.getEdocNo().contains("/")) {
            edocNo = edocNo.replaceAll("/", "_");
        }
        if (fileNameEnd.contains(".")) {
            String[] split = fileNameEnd.split("[.]");
            fileNameEnd = split[split.length - 1];
        }
        String fileName = edocNo + "_" + StringUtils.leftPad(imageNum, 4, "0") + "." + fileNameEnd;
        //补扫场景加上特殊后缀
        if (edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.WAIT_ADD_DATA)) {
            fileName = edocNo + "_" + StringUtils.leftPad(imageNum, 4, "0") + "_add"+ (int)(Math.random()*9000+1000) +"." + fileNameEnd;
        }
        Date date = edocHeader.getCreateTime() == null ? new Date() : edocHeader.getCreateTime();

        String directionName = "/" + DateUtils.formatDate(date, "yyyyMMdd") + "/" + edocNo;// 部分路径名称
        String imgUrl = directionName + "/" + fileName;
        EDOC_ECLIENT_UTIL_LOGGER.debug("[edocNo " + edocHeader.getEdocNo() + " 第 " + imageNum + " 张图片路径是" + imgUrl + "]");
        File folder = new File(rootPath + directionName);
        if (!(folder.isDirectory())) {
            folder.mkdirs();
        }
        return imgUrl;
    }


    public static void main(String[] args) {
        List<Long> lists = new ArrayList<>();
        lists.add(1L);
        lists.add(2L);
        lists.add(3L);
        lists.add(4L);

        boolean isAllHasPdf = false;
        for (Long edocCredentials : lists) {
            if (edocCredentials > 3) {
                break ;
            }
            isAllHasPdf = true;
        }

        System.out.println(isAllHasPdf);
    }

}
