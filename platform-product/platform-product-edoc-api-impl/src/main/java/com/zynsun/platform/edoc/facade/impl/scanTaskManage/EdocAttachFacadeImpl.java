package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.alibaba.dubbo.common.utils.Assert;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocAttach;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.dto.EdocAttachDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocAttachFacade;
import com.zynsun.platform.edoc.model.EdocAttachModel;
import com.zynsun.platform.edoc.service.EdocAttachService;
import com.zynsun.platform.edoc.service.EdocImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Liangjiawei
 * @date 2018/4/26 13:41
 */
@Service("edocAttachFacade")
public class EdocAttachFacadeImpl implements EdocAttachFacade{

    private static final Logger EDOCATTACH_LOGGER = LoggerFactory.getLogger(EdocAttachFacadeImpl.class);

    private static final String ROOT_PATH = App.getConfig("imageRootPath");

    private static final String IMAGE_URL = App.getConfig("imageprefUrl");

    @Autowired
    private EdocAttachService edocAttachService;


    @Autowired
    private EdocImageService edocImageService;



    @Override
    public ExecuteResult<Integer> delAttachment(Long id) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            EdocAttach edocAttach = edocAttachService.queryById(id);
            if(BeanUtil.isEmpty(edocAttach)){
                result.addErrorMessage("没有对应文件记录");
                return result;
            }
           int num = edocAttachService.remove(edocAttach);
            if (num > 0 && ( "JPG".equals(edocAttach.getAttachFormat())||"PNG".equals(edocAttach.getAttachFormat())||"jpg".equals(edocAttach.getAttachFormat())||"png".equals(edocAttach.getAttachFormat())   ) ){
                //根据附件信息查询影像件图片信息
                List<EdocImage> edocImage = edocAttachService.queryByEdocAttach(edocAttach);
                for (EdocImage edocImag: edocImage) {
                    edocImageService.remove(edocImag);
                }

            }
            result.setResult(1);
            result.setSuccessMessage("删除成功");
        } catch (Exception e) {
            result.addErrorMessage("删除失败");
            EDOCATTACH_LOGGER.error("删除失败", e.toString());
        }
        return result;
    }

    /**
     * 根据条件查询图像和文件
     * @param edocAttachDTO
     * @return
     */
    @Override
    public ExecuteResult<List<EdocAttachDTO>> selectImages(EdocAttachDTO edocAttachDTO) {
        ExecuteResult<List<EdocAttachDTO>> result = new ExecuteResult<>();
        try{
            List<EdocAttachModel> edocAttachList = edocAttachService.selectImages(DozerUtil.map(edocAttachDTO, EdocAttachModel.class));
            if (edocAttachList != null){
                result.setResult(DozerUtil.mapList(edocAttachList,EdocAttachDTO.class));
            }else {
                result.setResult(null);
            }
        }catch (Exception e){
            result.addErrorMessage("没有对应文件");
            EDOCATTACH_LOGGER.error("没有对应文件",e.toString());
        }
        return result;
    }

    /**
     * 根据影像任务id查询图片页码
     * @param billHeaderId
     * @return
     */
    @Override
    public ExecuteResult<Integer> queryMaxPagNum(Long billHeaderId) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try{
            result.setResult(edocAttachService.queryMaxPagNum(billHeaderId));
        }catch (Exception e){
            result.addErrorMessage("查询图片页面错误");
            EDOCATTACH_LOGGER.error("查询图片页面错误",e.toString());
        }
        return result;
    }

    /**
     * 上传文件
     * @param fullPath
     * @param fileName
     * @param fileExtName
     * @param pageNo
     * @param imageUrl
     * @param billHeaderId
     * @return
     */
    @Override
    public ExecuteResult<String> uploadFiles(String fullPath, String fileName, String fileExtName, String pageNo, String imageUrl, Long billHeaderId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        String[] fileExt = {"jpg", "jpeg", "png", "bmp", "JPEG", "JPG", "PNG", "BMP"};
        try{
            EDOCATTACH_LOGGER.info("保存图片成功，全路径为：{}", fullPath);
            EdocAttach edocAttach = new EdocAttach();
            edocAttach.setEdocHeaderId(billHeaderId);
            edocAttach.setAttachName(fileName);
            edocAttach.setAttachUrl(imageUrl);
            edocAttach.setAttachRootPath(ROOT_PATH);
            if (Arrays.asList(fileExt).contains(fileExtName)) {
                edocAttach.setAttachFormat("jpg");
            } else if ("PDF".equals(fileExtName.toUpperCase())) {
                edocAttach.setAttachFormat(fileExtName.toUpperCase());
            } else {
                edocAttach.setAttachFormat(fileExtName);
            }
            edocAttach.setCreateBy(SubjectUtil.getUser().getLoginName());
            edocAttach.setCreateTime(new Date());
            edocAttachService.add(edocAttach);
            result.setResult(edocAttach.getId().toString());
            result.setSuccessMessage("上传文件成功");
        }catch (Exception e){
            EDOCATTACH_LOGGER.error("上传文件失败", e.toString());
            result.addErrorMessage("上传文件失败");
        }
        return result;
    }

    /**
     * 根据条件查询图像和文件
     * @param edocAttachDTO
     * @return
     */
    @Override
    public ExecuteResult<EdocAttachDTO> findOneEdcoAttach(EdocAttachDTO edocAttachDTO) {
        ExecuteResult<EdocAttachDTO> result = new ExecuteResult<>();
        EdocAttachDTO edocAttachTypeDTO = new EdocAttachDTO();
        try{
            Assert.notNull(edocAttachDTO,"查询条件对象为空");
            EdocAttach edocAttach = DozerUtil.map(edocAttachDTO,EdocAttach.class);
            List<EdocAttach> edocAttachList = edocAttachService.queryEdcoAttach(edocAttach);
            if (edocAttachList != null && !edocAttachList.isEmpty()){
                edocAttachTypeDTO = DozerUtil.map(edocAttachList.get(0), EdocAttachDTO.class);
            }
            result.setResult(edocAttachTypeDTO);
        }catch (Exception e){
            EDOCATTACH_LOGGER.error("对象转换失败,失败原因<{}>",e);
            result.addErrorMessage("系统异常,请联系管理员!");
            return result;
        }
        return result;
    }

    /**
     * 根据id查找文件
     * @param id
     * @return
     */
    @Override
    public ExecuteResult<List<EdocAttachDTO>> findEdocAttachByParentId(Long id) {
        ExecuteResult<List<EdocAttachDTO>> result = new ExecuteResult<>();
        List<EdocAttachDTO> edocAttachDTOList = new ArrayList<>();
        try{
            if (BeanUtil.isEmpty(id)){
                result.addErrorMessage("输入参数不能为空");
                return result;
            }
            List<EdocAttach> edocAttachList = edocAttachService.queryEdocAttachByParentId(id);
            if (!BeanUtil.isEmpty(edocAttachList)){
                edocAttachDTOList = DozerUtil.mapList(edocAttachList,EdocAttachDTO.class);
                result.setResult(edocAttachDTOList);
            }else {
                result.addErrorMessage("该发票下没有附件");
            }
        }catch (Exception e){
            EDOCATTACH_LOGGER.error("查询发票附件出现异常，异常信息为:{}", e);
            result.addErrorMessage("查询发票附件出现异常");
        }
        return result;
    }

    /**
     * 根据edcoHeaderId查找文件
     * @param edcoHeaderId
     * @return
     */
    @Override
    public ExecuteResult<List<EdocAttachDTO>> selectImagesByEdocHeardId(Long edcoHeaderId) {
        ExecuteResult<List<EdocAttachDTO>> result = new ExecuteResult<>();
        try{
            List<EdocAttach> edocAttachList = edocAttachService.selectImagesByEdocHeardId(edcoHeaderId);
            if (!BeanUtil.isEmpty(edocAttachList)){
                result.setResult(DozerUtil.mapList(edocAttachList,EdocAttachDTO.class));
                result.setSuccessMessage("查询成功");
            }else {
                result.setSuccessMessage("没有对应文件列表");
            }
        }catch (Exception e){
            EDOCATTACH_LOGGER.error("查询失败", e.toString());
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocAttachDTO>> findByBillHeader(Long id) {
        ExecuteResult<List<EdocAttachDTO>> result = new ExecuteResult<>();
        try{
            List<EdocAttach> edocAttachList = edocAttachService.selectImagesByEdocHeardId(id);
            if (!BeanUtil.isEmpty(edocAttachList)){
                List<EdocAttachDTO> edocAttachDTOList = DozerUtil.mapList(edocAttachList,EdocAttachDTO.class);
                result.setResult(edocAttachDTOList);
                result.setSuccessMessage("查询成功");
                EDOCATTACH_LOGGER.info("查询成功");
            }else {
                result.addErrorMessage("查询失败");
            }
        }catch (Exception e){
            EDOCATTACH_LOGGER.error("查询失败", e.toString());
            result.addErrorMessage("查询失败");
        }
        return result;
    }
}
