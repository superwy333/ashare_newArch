package com.zynsun.platform.edoc.model;

import com.google.common.collect.Lists;
import com.zynsun.openplatform.domain.PageModel;

import java.util.List;

public class ArchiveCompanyModel extends PageModel {

	private static final long serialVersionUID = 1L;
	private String archiveId;
	private String archiveCode;
	private String companyId;//
	private String companyCode;//
	private String companyName;//
	private String parentId;//
	private List<ArchiveBizModel> archiveBizModelList = Lists.newArrayList();
	private List<String> bizIdList = Lists.newArrayList();
	private String archiveBizString;//公司下的所有的权限拼接的字符串

	//setter and getter


	public String getArchiveBizString() {
		return archiveBizString;
	}

	public void setArchiveBizString(String archiveBizString) {
		this.archiveBizString = archiveBizString;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode(){
		return companyCode;
	}
	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}

	public String getCompanyName(){
		return companyName;
	}
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getParentId(){
		return parentId;
	}
	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public List<ArchiveBizModel> getArchiveBizModelList() {
		return archiveBizModelList;
	}

	public void setArchiveBizModelList(List<ArchiveBizModel> archiveBizModelList) {
		this.archiveBizModelList = archiveBizModelList;
	}

	public List<String> getBizIdList() {
		List<String> bizIdList = Lists.newArrayList();
		for (ArchiveBizModel biz : archiveBizModelList) {
			bizIdList.add(biz.getId().toString());
		}
		return bizIdList;
	}

	public void setBizIdList(List<String> bizIdList) {
		archiveBizModelList = Lists.newArrayList();
		for (String bizId : bizIdList) {
			ArchiveBizModel biz = new ArchiveBizModel();
			biz.setId(Long.parseLong(bizId));
			archiveBizModelList.add(biz);
		}
	}

	public String getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
	}

	public String getArchiveCode() {
		return archiveCode;
	}

	public void setArchiveCode(String archiveCode) {
		this.archiveCode = archiveCode;
	}
}
