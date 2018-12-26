package com.zynsun.platform.edoc.facade.scanTaskManage;

import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-22 11:15
 **/
public interface ReviewInfoFacade {


    /**
     * 人人了评价列表页
     * @param map
     * @return
     */
    Map<String, Object> reviewInfoDataGrid(Map<String, Object> map);


    /**
     * 人人乐评价下拉框
     * @param map
     * @return
     */
    Map<String,Object> reviewInfoCombobox(Map<String,Object> map);


    /**
     * 人人乐 评价人工放行
     * @param map
     * @return
     */
    Map<String,Object> reviewInfoPass(Map<String,Object> map);


}
