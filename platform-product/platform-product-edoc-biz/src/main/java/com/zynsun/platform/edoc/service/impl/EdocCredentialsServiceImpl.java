package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.EdocCredentials;
import com.zynsun.platform.edoc.mapper.EdocCredentialsMapper;
import com.zynsun.platform.edoc.model.EdocCredentialsModel;
import com.zynsun.platform.edoc.service.EdocCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-25 下午 3:09
 * @Modified By:
 */
@Service
public class EdocCredentialsServiceImpl extends BaseServiceImpl<EdocCredentials> implements EdocCredentialsService {

    @Autowired
    private EdocCredentialsMapper edocCredentialsMapper;

    @Override
    protected IMapper<EdocCredentials> getBaseMapper() {
        return edocCredentialsMapper;
    }

    @Override
    public Page<EdocCredentialsModel> findCredentialsToBeSectionList(EdocCredentialsModel edocCredentialsModel) {
        return edocCredentialsMapper.selectEdocCredentialsToBeSection(edocCredentialsModel);
    }

    @Override
    public List<EdocCredentials> queryNoPdfCredByEBS() {
        return edocCredentialsMapper.queryNoPdfCredByEBS();
    }

    @Override
    public List<EdocCredentials> queryNoPdfCredByJDE() {
        return edocCredentialsMapper.queryNoPdfCredByJDE();
    }


    @Override
    public   List<EdocCredentials> queryByEdocId(Long edocId){
        return edocCredentialsMapper.queryByEdocId(edocId);
    }
    @Override
    public Integer updateVoucherStatus(Long edocId, String voucherStatus){
        return edocCredentialsMapper.updateVoucherStatus(edocId, voucherStatus);
    }

    @Override
    public List<EdocCredentials> queryVoucher(String credentialsNum){
        return edocCredentialsMapper.queryVoucher(credentialsNum);
    }


    @Override
    public Page<EdocCredentialsModel> edocCredentialsPage(EdocCredentialsModel edocCredentialsModel) {
        return edocCredentialsMapper.edocCredentialsPage(edocCredentialsModel);
    }

    @Override
    public List<EdocCredentialsModel> edocCredentialsList(EdocCredentialsModel edocCredentialsModel) {
        return edocCredentialsMapper.edocCredentialsList(edocCredentialsModel);
    }
}
