package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.model.EdocImageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EdocImageMapper extends IMapper<EdocImage> {

    void delImagesByEdocHeaderId(@Param("edocHeaderId") long edocHeaderId, @Param("deleteFlag") int deleteFlag);

    /**
     * @Description：查询当前最大页码
     * @Author：TanZhang
     * @Param：edocHeaderId
     * @Date：2017-08-10  下午 10:40
     */
    int queryMaxPageNoByEdocHeaderId(@Param(value = "edocHeaderId") Long edocHeaderId);

    /**
     * 根据docType 为1查询图像文件 为2查询非图像附件
     * @param edocHeaderId
     * @param docType
     * @return
     */
    List<EdocImageModel> selectImagesByEdocHeaderId(@Param(value = "edocHeaderId") Long edocHeaderId, @Param(value="docType") int docType);

    /**
     * 根据edocHeaderId查询该任务下的所有图片
     * @param edocHeaderId
     * @return
     */
    List<EdocImage> selectAllImagesByEdocHeaderId(Long edocHeaderId);
    /**
     * 根据条件查询edocImage
     *
     * @param edocImage
     * @return
     */
    List<EdocImage> selectEdocImage(@Param(value = "edocImage") EdocImage edocImage);

    Page<EdocImageModel> selectEdocImageByPage(@Param(value = "edocImage") EdocImageModel edocImageModel);


    List<EdocImage> selectAllImages(Long parentId);



    /**
     * 根据parentId查询
     * @param id
     * @return
     */
    List<EdocImage> selectEdocImageByParentId(Long id);
    public void updateEdocImage(EdocImage edocImage);
    public  void insertEdocImage(EdocImage edocImage);


    List<EdocImage> selectVouncherById(String id);
}