package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.EdocImageDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:05 2018/1/2
 * @Modified By:
 */
public interface EdocImageFacade extends BaseFacade {

    /**
     * 通过影像Id删除附件影像
     * @author Jaryzhang
     * @param map
     * @return
     */
    Map<String,Object> billImagesDetails(Map<String,Object> map);

    /**
     * 删除附件
     * @author Jary
     * @param map
     * @return
     */
    Map<String,Object> deleteBillImageById(Map<String,Object> map);

    /**
     * 异常单据新增评价信息
     * @author Jary
     * @param map
     * @return
     */
    Map<String,Object> scrapByTaskId(Map<String,Object> map);

    /**
     * 上传附件
     * @author Jary
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> uploadFile( HttpServletRequest request,HttpServletResponse response) ;

    /**
     * 下载附件
     * @author Jary
     * @param map
     * @return
     */
    Response downloadFile(Map<String, Object> map);

    /**
     * 批量下载
     * @author Jary
     * @param map
     * @return
     */
    Response downloadFiles(Map<String, Object> map);

    Map<String, Object> uploadElcInvoicePDF(HttpServletRequest request,HttpServletResponse response);

    Map<String, Object> uploadBankTicket(HttpServletRequest request,HttpServletResponse response);

    /**
     * 追加或替换影像
     * @param bytes
     * @param imgId
     * @param edocHeader
     * @param optType
     * @param fileName
     * @return
     */
    ExecuteResult<String> addOrUpdateImages(byte[] bytes, Long imgId, EdocHeaderDTO edocHeader, String optType, String fileName);

    /**
     * 根据条件查询图像和文件
     * @param edocImage
     * @return
     */
    ExecuteResult<List<EdocImageDTO>> selectImages(EdocImageDTO edocImage);

    /**
     * 根据id查询图像信息
     * @param id
     * @return
     */
    ExecuteResult<EdocImageDTO> selectImageById(Long id);


    /**
     * 根据影像任务id查询图片页码
     * @param edocHeaderId
     * @return
     */
    ExecuteResult<Integer> queryMaxPagNum(Long edocHeaderId);

    /**
     * 上传文件
     * @param fullPath
     * @param fileName
     * @param fileExtName
     * @param pageNo
     * @param imageUrl
     * @param edocHeaderId
     * @return
     */
    ExecuteResult<String> uploadFiles(String fullPath, String fileName,String fileExtName,String pageNo,String imageUrl, Long edocHeaderId);


    /**
     * 上传文件
     * @param fullPath
     * @param fileName
     * @param fileExtName
     * @param pageNo
     * @param imageUrl
     * @param edocHeaderId
     * @return
     */
    ExecuteResult<String> uploadFiles(String fullPath, String fileName,String fileExtName,String pageNo,String imageUrl, Long edocHeaderId, String edocImageType);

    ExecuteResult<String> uploadFiles(String fullPath, String fileName,String fileExtName,String pageNo,String imageUrl, Long edocHeaderId, String edocImageType,String createBy);


    /**
     * 上传文件，用于附件上传转图片场景
     * 把附件Id存放到扩展字段5中
     * @param fullPath
     * @param fileName
     * @param fileExtName
     * @param pageNo
     * @param imageUrl
     * @param edocHeaderId
     * @param edocImageType
     * @return
     */
    ExecuteResult<String> uploadFiles(String fullPath, String fileName,String fileExtName,String pageNo,String imageUrl, Long edocHeaderId, String edocImageType, Long edocAttachId);



    /**
     * 根据docType 为1查询图像文件 为2查询非图像附件
     * @param edocHeaderId
     * @param docType
     * @return
     */
    ExecuteResult<List<EdocImageDTO>> selectImagesByEdocHeaderId(Long edocHeaderId, int docType);

    /**
     * 影像上传时图片校验流程
     *
     * @param uploadImageas
     * @return
     */
    ExecuteResult<EdocImageDTO> checkExistImages(EdocImageDTO uploadImageas);

    ExecuteResult<Integer> deleteImage(Long id);

    ExecuteResult<List<EdocImageDTO>> queryEdocImageByIds(List<Long> ids);

    /**
     * 根据条件查询edocImage
     *
     * @param edocImageDTO
     * @return
     */
    ExecuteResult<List<EdocImageDTO>> findEdocImage(EdocImageDTO edocImageDTO);

    ExecuteResult<EdocImageDTO> findOneEdocImage(EdocImageDTO imagesDTO);

    ExecuteResult<List<EdocImageDTO>> findEdocImageByParentId(Long id);

    ExecuteResult<String> updateImages(byte[] bytes, Long imgId, EdocHeaderDTO edocHeader, String fileName);

    /**
     * 单张发票标记是否重扫
     * @param edocImageDTO
     * @return
     */
    ExecuteResult<Integer> againScan(EdocImageDTO edocImageDTO);

    /**
     * 更新img
     * @param edocImageDTO
     * @return
     */
    ExecuteResult<Integer> updateImagesByDTO(EdocImageDTO edocImageDTO);

    ExecuteResult<Integer> updateImages(EdocImageDTO edocImageDTO);

    /**
     * 根据凭证id查询凭证影像
     * @param id 凭证id
     * @return
     */
    ExecuteResult<List<EdocImageDTO>> queryVoucherImage(Long id);


    /**
     * 根据Id删除影像，如果是发票，则还需要删除发票和发票明细
     * @param edocImageId
     * @return
     */
    ExecuteResult<String> deleteImageAndInvoiceByImageId(Long edocImageId);




}
