package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.EdocCredentials;
import com.zynsun.platform.edoc.model.EdocCredentialsModel;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-25 下午 2:56
 * @Modified By:
 */
public interface EdocCredentialsService extends BaseService<EdocCredentials>{

    Page<EdocCredentialsModel> findCredentialsToBeSectionList(EdocCredentialsModel edocCredentialsModel);

    /**
     * 查询未获取pdf的凭证
     * @return
     */
    List<EdocCredentials> queryNoPdfCredByEBS();

    /**
     * 查询未获取pdf的凭证
     * @return
     */
    List<EdocCredentials> queryNoPdfCredByJDE();

    /**
     * 根据报账单id获取凭证
     */
    List<EdocCredentials> queryByEdocId(Long edocId);


    Integer updateVoucherStatus(Long edocId, String voucherStatus);

    List<EdocCredentials> queryVoucher(String credentialsNum);


    Page<EdocCredentialsModel> edocCredentialsPage(EdocCredentialsModel edocCredentialsModel);

    List<EdocCredentialsModel> edocCredentialsList(EdocCredentialsModel edocCredentialsModel);





}
