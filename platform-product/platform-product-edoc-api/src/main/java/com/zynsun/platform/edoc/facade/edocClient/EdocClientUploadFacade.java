package com.zynsun.platform.edoc.facade.edocClient;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.edocClient.beforeUpload.request.BeforeUploadRequest;
import com.zynsun.platform.edoc.dto.edocClient.beforeUpload.response.BeforeUploadResponse;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.request.DataUploadRequest;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.response.DataUploadResponse;
import com.zynsun.platform.edoc.dto.edocClient.endUpload.request.EndUploadRequest;
import com.zynsun.platform.edoc.dto.edocClient.endUpload.response.EndUploadResponse;

/**
 * 采集客户端上传影像接口
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/05 12:56
 */
public interface EdocClientUploadFacade {

    /**
     * 客户端开始上传影像
     *
     * @param beforeUploadRequest
     * @return
     */
    public ExecuteResult<BeforeUploadResponse> beforeUpload(BeforeUploadRequest beforeUploadRequest);

    /**
     * 客户端上传影像及发票等信息
     *
     * @param dataUploadRequest
     * @return
     */
    public ExecuteResult<DataUploadResponse> dataUpload(DataUploadRequest dataUploadRequest);

    /**
     * 客户端结束上传影像
     *
     * @param endUploadRequest
     * @return
     */
    public ExecuteResult<EndUploadResponse> endUpload(EndUploadRequest endUploadRequest);

}
