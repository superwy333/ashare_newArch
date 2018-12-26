package com.zynsun.platform.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jary on 2018/11/23/023.
 */
public class FileUtil {
    /**
     * 上传文件具体操作
     *
     * @param fileName 文件名
     * @param filePath 文件上传路径
     * @param inStream 文件流
     * @return 上传是否成功
     */
    private static boolean upload4Stream(String fileName, String filePath, InputStream inStream) {
        boolean result = false;
        if ((filePath == null) || (filePath.trim().length() == 0)) {
            return result;
        }
        OutputStream outStream = null;
        try {
            String wholeFilePath = filePath + "\\" + fileName;
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File outputFile = new File(wholeFilePath);
            boolean isFileExist = outputFile.exists();
            boolean canUpload = true;
            if (isFileExist) {
                canUpload = outputFile.delete();
            }
            if (canUpload) {
                int available = 0;
                outStream = new BufferedOutputStream(new FileOutputStream(
                        outputFile), 2048);
                byte[] buffer = new byte[2048];
                while ((available = inStream.read(buffer)) > 0) {
                    if (available < 2048)
                        outStream.write(buffer, 0, available);
                    else {
                        outStream.write(buffer, 0, 2048);
                    }
                }
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (outStream != null)
                    outStream.close();
            } catch (Exception ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (outStream != null)
                    outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static Map<String, Object> analysisFormData(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        String fileFormat = "";
        String fileName = "";
        List<Map<String, Object>> fileList = new ArrayList<>();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            // 构造一个文件上传处理对象
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");  // 支持中文文件名
            List<FileItem> list = new ArrayList<FileItem>();
            list = upload.parseRequest(request);
            for (int i = 0; i < list.size(); i++) {
                FileItem item = list.get(i);
                if (item.isFormField()) {    //普通表单值
                    resultMap.put(item.getFieldName(), item.getString("UTF-8"));
                } else {
                    String name = item.getName(); //获得上传的文件名（IE上是文件全路径，火狐等浏览器仅文件名）
                    fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());
                    fileFormat = fileName.substring(fileName.lastIndexOf(".")+1);   //文件扩展名
                    resultMap.put("bytes", item.get());
                    resultMap.put("enclosureName", item.getName());
                    resultMap.put("fileFormat", fileFormat);
                }
            }
        }
        resultMap.put("fileList", fileList);
        return resultMap;
    }


        /**
         * @author cjy
         * @date 2018/6/5 14:35
         * @param file
         * @return
         */
        // 判断文件夹是否存在
        public static void judeDirExists(File file) {

            if (file.exists()) {
                if (file.isDirectory()) {
                    System.out.println("dir exists");
                } else {
                    System.out.println("the same name file exists, can not create dir");
                }
            } else {
                System.out.println("dir not exists, create it ...");
                file.mkdir();
            }


    }
}
