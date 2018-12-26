package utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 根据Url下载文件
 */
public class DownPdfUtil {
    /**
     *
     * @param urlStr   网络pdf文件路径
     * @param filePreName 文件名
     * @param savePath pdf保存路径
     * @param imgPath  影像保存路径
     * @throws IOException
     */
    public static String downLoadByUrl(String urlStr, String filePreName, String savePath, String imgPath) {
        String finalPdfPath = null;
        URL url = null;
        FileOutputStream fos = null;
        InputStream inputStream = null;
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
            byte[] getData = readInputStream(inputStream);

            // 保存pdf影像图片
            String imgFinalPath = imgPath  + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()) + File.separator + filePreName;
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
                ImageIO.write(image, "JPG", new File(imgFinalPath + File.separator + imgName));
            }

            //pdf文件保存
            File saveDir = new File(savePath + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
            if (!(saveDir.isDirectory())) {
                saveDir.mkdirs();
            }
            finalPdfPath = saveDir + File.separator + filePreName + ".pdf";
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
        }
        System.out.println("info:" + finalPdfPath);
        return finalPdfPath;
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        String savePath = "F:\\ebs";
        try {
//            downLoadByUrl("http://ebsdev.newhope.cn:8010/OA_HTML/OA.jsp?OAFunc=CUX_XMLREPROT_GL2&gl_batch_id=98889",
//                    "98889", savePath, "F:\\ebs\\image");
            downLoadByUrl("http://ebsdev.newhope.cn:8010/OA_HTML/OA.jsp?OAFunc=CUX_XMLREPROT_GL2&gl_deoc_value=600917110036&gl_leadger_id=2024",
                    "2014", savePath, "F:\\ebs\\image\\autohome_image");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
