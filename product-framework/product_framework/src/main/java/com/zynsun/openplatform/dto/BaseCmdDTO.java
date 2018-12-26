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
public class BaseCmdDTO implements Serializable {

    private static final long serialVersionUID = -1524194532303522643L;

    private Long id;
    
    /** 版本号（乐观锁）  严禁设置默认值 */
    private Long version;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

    
}
