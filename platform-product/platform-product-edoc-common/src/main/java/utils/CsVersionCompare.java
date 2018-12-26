package utils;


import tk.mybatis.mapper.util.StringUtil;

public class CsVersionCompare {

	public static boolean compareCsVersion(String clientVersion,String dataVersion){

		boolean flag = false;
		if(StringUtil.isNotEmpty(clientVersion) && StringUtil.isNotEmpty(dataVersion)){
			String[] cv = clientVersion.split("\\.");
			String[] dv = dataVersion.split("\\.");
			if(cv.length==dv.length){
				for(int i=0;i<cv.length;i++){
					flag = dataBigClient(cv[i],dv[i]);
					if(flag){
						return flag;
					}
				}
			}
		}
		return flag;
	}
	
	public static boolean dataBigClient(String clientV,String dataV){
		boolean flag = false;
		if(Integer.parseInt(clientV)<Integer.parseInt(dataV)){
			flag = true;
		}
		return flag;
	}
}
