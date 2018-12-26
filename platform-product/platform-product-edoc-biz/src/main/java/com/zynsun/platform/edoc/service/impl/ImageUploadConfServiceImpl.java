package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.platform.edoc.domain.ImageUploadConf;
import com.zynsun.platform.edoc.mapper.ImageUploadConfMapper;
import com.zynsun.platform.edoc.model.ImageUploadConfModel;
import com.zynsun.platform.edoc.service.ImageUploadConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 11:05 2018/1/11
 * @Modified By:
 */
@Service
public class ImageUploadConfServiceImpl extends BaseServiceImpl<ImageUploadConf> implements ImageUploadConfService {

    @Autowired
    private ImageUploadConfMapper imageUploadConfMapper;

    @Override
    protected IMapper<ImageUploadConf> getBaseMapper() {
        return imageUploadConfMapper;
    }

    @Override
    public void addImageUploadConf(ImageUploadConfModel uploadConfModel) {
        if(uploadConfModel.getId()!=null){
            updateByIdSelective(DozerUtil.map(uploadConfModel,ImageUploadConf.class));
        }else{
            add(DozerUtil.map(uploadConfModel,ImageUploadConf.class));
        }
    }

    @Override
    public ImageUploadConfModel findConf() {
        List<ImageUploadConf> list = queryAll();
        if(BeanUtil.isEmpty(list)){
            return null;
        }
        return DozerUtil.mapList(list,ImageUploadConfModel.class).get(0);
    }
}
