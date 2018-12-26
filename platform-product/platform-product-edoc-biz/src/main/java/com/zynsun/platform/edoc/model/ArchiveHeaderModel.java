package com.zynsun.platform.edoc.model;


import com.zynsun.openplatform.domain.PageModel;

public class ArchiveHeaderModel extends PageModel {

	private static final long serialVersionUID = 1L;
	private String headerName;//全宗名称
	private String headerCode;//全宗编号
	private String sectionFlag;//ABADON
	private String remarks;
	private String companyIds;
	private String companyCodes;
	private String companyNames;


	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	public String getSectionFlag() {
		return sectionFlag;
	}

	public void setSectionFlag(String sectionFlag) {
		this.sectionFlag = sectionFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(String companyIds) {
		this.companyIds = companyIds;
	}

	public String getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(String companyNames) {
		this.companyNames = companyNames;
	}

	public String getCompanyCodes() {
		return companyCodes;
	}

	public void setCompanyCodes(String companyCodes) {
		this.companyCodes = companyCodes;
	}
}
