package webservice.jde.jdeutil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webservice.jde.service.WebService;
import webservice.jde.service.WebServiceSoap;
import webservice.jde.service.jdeService.WebServiceLocator;
import webservice.jde.service.jdeService.WebServiceSoap_PortType;

import javax.imageio.ImageIO;
import javax.xml.rpc.ServiceException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.regex.Pattern;


public class JdeUtil {

    private static final Logger logger = LoggerFactory.getLogger(JdeUtil.class);

    private static final String word = "^[A-Za-z]+$";




    public static String getToken(String db){
        String token = null;
        try {
            WebServiceSoap serviceSoap = new WebService().getWebServiceSoap();
            String dbnsp = db, stype = "JDE001";
            String where = "[{\"TOKEN_NAME\":\"getjdeinfo\", \"TOKEN_PASSWORD\":\"ed4fb33746d2475e82570c20fa6b1873\"}]";
            String result = serviceSoap.mainWebService(dbnsp, stype, where);
            if (!StringUtils.isBlank(result)){
                JSONArray array = JSONArray.parseArray(result);
                JSONObject object = array.getJSONObject(0);
                if (0 == object.getInteger("code")){
                    token = object.getString("result");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("通过JDE获取Token接口异常");
        }
        return token;
    }


    /**
     * 返回PDF byte数组值
     */
    public static byte[] getPDFBytes(String db, String token, String creNum){
        byte[] bytes = null;
        try {
            WebServiceSoap serviceSoap = new WebService().getWebServiceSoap();
            String dbnsp = db, stype = "JDE009";
            String where = "[{\"TOKEN\":\""+token+"\",\"CCICU\":\""+creNum+"\" }]";

            logger.info(String.format("获取JDE PDF凭证调用参数， DBNsp: %s, sType: %s, sWhere：%s", dbnsp, stype, where));

            bytes = serviceSoap.mainPdfWebService(dbnsp, stype, where);
            if (bytes!=null){
                if (bytes.length < 100) {//怀疑是错误信息，通过正则匹配
                    String res = new String(bytes, Charset.forName("UTF-8"));
                    if (Pattern.matches(word, res.replaceAll(" ", ""))){
                        logger.error(String.format("JDE获取PDF接口错误，JDE返回错误：%s", res));
                        bytes = null;//吧bytes置空
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("通过JDE获取凭证PDF接口异常");
        }
        return bytes;
    }

    /*
    返回法人公司数据
     */
    public static String getCopanyFacde(String db, String token){
        String companyFace = null;
        try {
            WebServiceSoap serviceSoap = new WebService().getWebServiceSoap();
            String dbnsp = db, stype = "JDE003";
            //目前就传了2个参数
            String where = "[{\"TOKEN\":\""+token+"\",\"CCCO\":\"\",\"CCMCU\":\"\",\"CCSTYL\":\"BS\",\"DATE_FROM\":\"\",\"DATE_TO\":\"\" }]";
            String result = serviceSoap.mainWebService(dbnsp, stype, where);
            if (!StringUtils.isBlank(result)){
                JSONArray array = JSONArray.parseArray(result);
                JSONObject object = array.getJSONObject(0);
                if (0 == object.getInteger("code")){
                    companyFace = object.getString("result");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("通过JDE获取法人公司接口异常");
        }
        return companyFace;
    }


    //下面的3个方法全是为了动态获取url改的
    public static String getToken(String db,String url){
        String token = null;
        try {
            webservice.jde.service.jdeService.WebService service = new WebServiceLocator();
            ((WebServiceLocator) service).setWebServiceSoapEndpointAddress(url);
            WebServiceSoap_PortType webServiceSoap = service.getWebServiceSoap();
            String dbnsp = db, stype = "JDE001";
            String where = "[{\"TOKEN_NAME\":\"getjdeinfo\", \"TOKEN_PASSWORD\":\"ed4fb33746d2475e82570c20fa6b1873\"}]";
            logger.info(String.format("获取JDE token参数， DBNsp: %s, sType: %s, sWhere：%s,url : %s", dbnsp, stype, where,url));
            String result = webServiceSoap.mainWebService(dbnsp, stype, where);
            logger.info("获取 token result：%s",result);
            if (!StringUtils.isBlank(result)){
                JSONArray array = JSONArray.parseArray(result);
                JSONObject object = array.getJSONObject(0);
                if (0 == object.getInteger("code")){
                    token = object.getString("result");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("通过JDE获取Token接口异常");
        }
        return token;
    }

    /**
     * 返回PDF byte数组值
     */
    public static byte[] getPDFBytes(String db, String token, String creNum, String url){
        byte[] bytes = null;
        try {
            webservice.jde.service.jdeService.WebService service = new WebServiceLocator();
            ((WebServiceLocator) service).setWebServiceSoapEndpointAddress(url);
            WebServiceSoap_PortType webServiceSoap = service.getWebServiceSoap();
            String dbnsp = db, stype = "JDE009";
            String where = "[{\"TOKEN\":\""+token+"\",\"CCICU\":\""+creNum+"\" }]";

            logger.info(String.format("获取JDE PDF凭证调用参数， DBNsp: %s, sType: %s, sWhere：%s, url : %s", dbnsp, stype, where,url));

            bytes = webServiceSoap.mainPdfWebService(dbnsp, stype, where);
            if (bytes!=null){
                if (bytes.length < 100) {//怀疑是错误信息，通过正则匹配
                    String res = new String(bytes, Charset.forName("UTF-8"));
                    if (Pattern.matches(word, res.replaceAll(" ", ""))){
                        logger.error(String.format("JDE获取PDF接口错误，JDE返回错误：%s", res));
                        bytes = null;//吧bytes置空
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("通过JDE获取凭证PDF接口异常");
        }
        return bytes;
    }

    /*
    返回法人公司数据
     */
    public static String getCopanyFacde(String db, String token, String url){
        String companyFace = null;
        try {
            webservice.jde.service.jdeService.WebService service = new WebServiceLocator();
            ((WebServiceLocator) service).setWebServiceSoapEndpointAddress(url);
            WebServiceSoap_PortType webServiceSoap = service.getWebServiceSoap();
            String dbnsp = db, stype = "JDE003";
            //目前就传了2个参数
            String where = "[{\"TOKEN\":\""+token+"\",\"CCCO\":\"\",\"CCMCU\":\"\",\"CCSTYL\":\"BS\",\"DATE_FROM\":\"\",\"DATE_TO\":\"\" }]";
            logger.info(String.format("获取JDE 调用参数， DBNsp: %s, sType: %s, sWhere：%s,url : %s", dbnsp, stype, where,url));
            String result = webServiceSoap.mainWebService(dbnsp, stype, where);
            if (!StringUtils.isBlank(result)){
                JSONArray array = JSONArray.parseArray(result);
                JSONObject object = array.getJSONObject(0);
                if (0 == object.getInteger("code")){
                    companyFace = object.getString("result");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("通过JDE获取法人公司接口异常");
        }
        return companyFace;
    }

    public static void main(String[] args) {
        try {
//            getPDFBytes(null, null, 18, 9, null);
            getPDFBytesTest(Constant.JDE_DBNSP, getToken(Constant.JDE_DBNSP), "00833", 15, 5, "71");
//            Date createTime = new Date();
//            System.out.println(createTime.getYear()-100);
//            System.out.println(createTime.getMonth()+1);
//            hhe();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 返回PDF byte数组值
     */
    private static void getPDFBytesTest(String db, String token, String companyName, int year, int month, String creNum){
        byte[] bytes;
        try {
            WebServiceSoap serviceSoap = new WebService().getWebServiceSoap();
            String stype = "JDE009";
            String where = "[{\"TOKEN\":\""+token+"\",\"CCCO\":\""+companyName+"\", \"CCFY\":"+year+", \"CCPN\":"+month+", \"CCREG\":\""+creNum+"\" }]";

            System.out.println(String.format("获取JDE PDF凭证调用参数， DBNsp: %s, sType: %s, sWhere：%s", db, stype, where));

            bytes = serviceSoap.mainPdfWebService(db, stype, where);
            System.out.println("bytes length: " + bytes.length);
            System.out.println(new String(bytes));
            if (bytes.length > 0){
                File file = new File("D://cre.pdf");
                OutputStream out = new FileOutputStream(file);
                out.write(bytes);
                out.flush();
                out.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("通过JDE获取凭证PDF接口异常");
        }
        System.out.println("done");
    }



    public static  void  hhe(){
        long current = System.currentTimeMillis();
        File file = new File("D:\\22.pdf");
        try {
            FileInputStream in = new FileInputStream(file);
            long length = file.length();
            byte[] bytes = new byte[(int) length];
            in.read(bytes, 0, (int) length);
            PDDocument doc = PDDocument.load(bytes);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                String imgName = "D:\\333.jpg";
                ImageIO.write(image, "JPG", new File(imgName));
                System.out.println("success");
                System.out.println(System.currentTimeMillis() - current);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
