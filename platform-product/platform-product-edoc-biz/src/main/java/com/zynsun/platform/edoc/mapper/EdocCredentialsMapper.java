package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.EdocCredentials;
import com.zynsun.platform.edoc.model.EdocCredentialsModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-25 下午 3:10
 * @Modified By:
 */
public interface EdocCredentialsMapper extends IMapper<EdocCredentials> {


    Page<EdocCredentialsModel> selectEdocCredentialsToBeSection(@Param(value = "edocCredentialsModel") EdocCredentialsModel edocCredentialsModel);

    Page<EdocCredentialsModel> edocCredentialsPage(@Param(value = "edocCredentialsModel") EdocCredentialsModel edocCredentialsModel);

    List<EdocCredentialsModel> edocCredentialsList(@Param(value = "edocCredentialsModel") EdocCredentialsModel edocCredentialsModel);
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
     * 根据报账单的id获取凭证
     * @param edocId
     * @return
     */
    List<EdocCredentials> queryByEdocId(Long edocId);

    List<EdocCredentials> queryVoucher(String credentialsNum);


    Integer updateVoucherStatus(@Param(value = "edocId") Long edocId, @Param(value = "voucherStatus") String voucherStatus);
}
