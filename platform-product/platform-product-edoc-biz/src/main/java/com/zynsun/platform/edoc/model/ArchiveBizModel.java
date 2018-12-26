package com.zynsun.platform.edoc.model;


import com.zynsun.openplatform.domain.PageModel;

public class ArchiveBizModel  extends PageModel {

	private static final long serialVersionUID = 1L;
	private String archiveCompanyId;//
	private String archiveType;//档案类型code
	private String archiveTypeName;//档案类型名称
	
	//setter and getter

	public String getArchiveCompanyId(){
		return archiveCompanyId;
	}
	public void setArchiveCompanyId(String archiveCompanyId){
		this.archiveCompanyId = archiveCompanyId;
	}

	public String getArchiveType(){
		return archiveType;
	}
	public void setArchiveType(String archiveType){
		this.archiveType = archiveType;
	}

	public String getArchiveTypeName() {
		return archiveTypeName;
	}

	public void setArchiveTypeName(String archiveTypeName) {
		this.archiveTypeName = archiveTypeName;
	}
}
