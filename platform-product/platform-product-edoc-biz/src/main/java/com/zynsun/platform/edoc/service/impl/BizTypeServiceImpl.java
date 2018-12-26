package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.BizType;
import com.zynsun.platform.edoc.domain.BizTypeStyle;
import com.zynsun.platform.edoc.mapper.BizTypeMapper;
import com.zynsun.platform.edoc.mapper.BizTypeStyleMapper;
import com.zynsun.platform.edoc.model.BizTypeModel;
import com.zynsun.platform.edoc.model.BizTypeModelDTO;
import com.zynsun.platform.edoc.service.BizTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-05-31 .
 */
@Service
public class BizTypeServiceImpl extends BaseServiceImpl<BizType> implements BizTypeService {

    private static final Logger BIZ_TYPE_LOGGER = LoggerFactory.getLogger(BizTypeServiceImpl.class);

    @Autowired
    private BizTypeMapper bizTypeMapper;
    @Autowired
    private BizTypeStyleMapper bizTypeStyleMapper;

    @Override
    protected IMapper<BizType> getBaseMapper() {
        return bizTypeMapper;
    }

    /**
     * @Description：查询所有有效的业务类型信息
     * @Author：FeiyueYang
     * @Param：bizType
     * @Date：2017/6/2 0002 下午 2:32
     */
    @Override
    public List<BizType> findBizTypes(BizType bizType) {
        return bizTypeMapper.select(bizType);
    }

    @Override
    public BizType queryByCode(String bizTypeCode) {
        return bizTypeMapper.selectByBizTypeCode(bizTypeCode);
    }

    @Override
    public int addBizAndBills(BizType bizType, List<BizTypeStyle> bizTypeStyleList) {
        int resultNum = bizTypeMapper.insert(bizType);
        if (1 == resultNum) {
            for (BizTypeStyle bizTypeStyle : bizTypeStyleList) {
                bizTypeStyle.setBizTypeId(bizType.getId());
                resultNum = bizTypeStyleMapper.insert(bizTypeStyle);
                if (1 != resultNum) {
                    BIZ_TYPE_LOGGER.error("[Service]业务类型关联的单证类型插入失败");
                    return 0;
                }
            }
            return 1;
        } else {
            BIZ_TYPE_LOGGER.error("[Service]业务类型对象插入失败");
            return 0;
        }
    }

    /**
     * @Description：根据ids查询对应的bizTypes
     * @Author：FeiyueYang
     * @Param：roleIds
     * @Date：2017/6/2 0002 下午 2:33
     */
    @Override
    public List<BizType> findBizTypeByRoleId(List<Long> roleIds) {

        List<BizType> bizTypes = new ArrayList<BizType>();
        try {
            bizTypes = bizTypeMapper.findBizsByRoleIds(roleIds);
        } catch (Exception e) {
            BIZ_TYPE_LOGGER.error("==根据ids查询对应的bizTypes===",e);
        }
        return bizTypes;
    }

    /**
     * 删除业务类型及关联的单证类型
     * @param bizTypeId
     * @return
     */
    @Override
    public int deleteBizAndBills(Long bizTypeId){
        BizType bizType = bizTypeMapper.selectByPrimaryKey(bizTypeId);
        bizType.setDeleted(1);
        int resultNum =bizTypeMapper.updateCASByPrimaryKey(bizType);
        if (1 == resultNum) {
            BizTypeStyle bizTypeStyle = new BizTypeStyle();
            bizTypeStyle.setBizTypeId(bizTypeId);
            List<BizTypeStyle> bizTypeStyleList = bizTypeStyleMapper.select(bizTypeStyle);
            for (BizTypeStyle vo : bizTypeStyleList) {
                vo.setDeleted(1);
                resultNum = bizTypeStyleMapper.updateCASByPrimaryKey(vo);
                if (resultNum != 1) {
                    BIZ_TYPE_LOGGER.error("[Service]业务类型关联的单证类型解绑失败");
                    return 0;
                }
            }
            return 1;
        } else{
            BIZ_TYPE_LOGGER.error("[Service]业务类型对象删除失败");
            return 0;
        }
    }

    /**
     * 根据id查询业务类型及其关联的单证类型
     * @param id
     * @return
     */
    @Override
    public BizTypeModel queryBizTypeModelById(Long id){
        return bizTypeMapper.selectBizTypeModel(id);
    }

    /**
     * 修改业务类型
     * @param bizType
     * @param bizTypeStyleList
     * @return
     */
    @Override
    public int modifiedBizType(BizType bizType, List<BizTypeStyle> bizTypeStyleList) {
        int resultNum = bizTypeMapper.updateCASByPrimaryKeySelective(bizType);
        if (1 == resultNum) {
            //删除关联的所有单证类型
            bizTypeStyleMapper.deleteByBizTypeId(bizType.getId());
            for (BizTypeStyle bizTypeStyle : bizTypeStyleList) {
                bizTypeStyle.setBizTypeId(bizType.getId());
                resultNum = bizTypeStyleMapper.insert(bizTypeStyle);
                if (1 != resultNum) {
                    BIZ_TYPE_LOGGER.error("[Service]业务类型关联的单证类型修改失败");
                    return 0;
                }
            }
            return 1;
        } else {
            BIZ_TYPE_LOGGER.error("[Service]业务类型对象修改失败");
            return 0;
        }
    }

    @Override
    public BizTypeModelDTO selectFromTepByBizTypeCode(String code, Integer deleted) {
        BizTypeModelDTO bizTypeModelDTO = bizTypeMapper.selectFromTepByBizTypeCode(code, deleted);
        return bizTypeModelDTO;
    }
}
