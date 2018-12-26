package com.zynsun.openplatform.dto;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 基础Vo
 * 
 * @ClassName: BaseVo
 * @author yechuan
 * @date 2016年5月22日 下午9:57:59
 * 
 */
public class BaseCmdResultDTO implements Serializable {

    private static final long serialVersionUID = -1524194532303522642L;

    private Long id;
    


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
