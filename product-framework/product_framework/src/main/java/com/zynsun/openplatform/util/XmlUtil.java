package com.zynsun.openplatform.util;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import com.sun.xml.bind.marshaller.DataWriter;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * xml 工具类
 * 
 * @author gongdaowen
 */
public class XmlUtil {
	
	static Logger log = LoggerFactory.getLogger(XmlUtil.class);

	public static class BigDecimalXmlAdapter extends XmlAdapter<String, BigDecimal>{
		public BigDecimal unmarshal(String v) throws Exception {
			if (BeanUtil.isEmpty(v)) {
				v = "";
			}
			v = v.trim();
			if(v.endsWith("-")){
//				log.info("INPUT [{}]  ---------->  OUTPUT[{}]", v, new BigDecimal("-" + v.substring(0, v.length() - 1)));
				return new BigDecimal("-" + v.substring(0, v.length() - 1));
			}
//			log.info("INPUT [{}]  ---------->  OUTPUT[{}]", v, new BigDecimal(v));
			return new BigDecimal(v);
		}
		public String marshal(BigDecimal v) throws Exception {
			if(BigDecimal.ZERO.compareTo(v) > 0){
				return v.abs().toString() + "-";
			}
			return v.toString();
		}
	}
	
	public static class CDATAXmlAdapter extends XmlAdapter<String, String>{
		public String unmarshal(String v) throws Exception {
			return v.replace("<![CDATA[", "").replace("]]>", "").replace("\n", "");
		}
		public String marshal(String v) throws Exception {
			return "<![CDATA[" + v + "]]>".replace("\n", "");
		}
	}
	
	private static JAXBContext jaxbContext;
	static {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		String[] scanPackages = {"com.zynsun.platform.ap.model.chouQuHttpInf.xml","com.zynsun.platform.ap.model.authHttpInf.xml"};
		jaxb2Marshaller.setPackagesToScan(scanPackages);
		jaxbContext = jaxb2Marshaller.getJaxbContext();
	}
	
	public static NamespacePrefixMapper createNamepacePrefix(final String prefix) {
		return new NamespacePrefixMapper() {
			public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
				return prefix;
			}
		};
	}
	
	public static Marshaller getMarshaller(final String prefix){
		Marshaller marshaller = null;
		try {
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // 编码
			
			if(prefix != null){
				marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", createNamepacePrefix(prefix));
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return marshaller;
	}
	
	public static Unmarshaller getUnmarshaller(final String prefix){
		Unmarshaller unmarshaller = null;
		try {
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return unmarshaller;
	}
	
	public static Marshaller getMarshaller(){
		return getMarshaller(null);
	}
	
	public static Unmarshaller getUnmarshaller(){
		return getUnmarshaller(null);
	}
	
	public static ResultBuilder marshal(Object obj){
		return marshal(getMarshaller(), obj);
	}
	
	public static ResultBuilder marshal(Object obj, String encoding){
		return marshal(getMarshaller(), obj, encoding);
	}
	
	public static ResultBuilder marshal(String prefix, Object obj){
		return marshal(getMarshaller(prefix), obj);
	}
	
	public static SourceBuilder unmarshal(){
		return unmarshal(getUnmarshaller());
	}
	
	public static SourceBuilder unmarshal(String prefix){
		return unmarshal(getUnmarshaller(prefix));
	}

	public static ResultBuilder marshal(Marshaller marshaller, Object obj){
		return new ResultBuilder(marshaller, obj);
	}
	
	public static ResultBuilder marshal(Marshaller marshaller, Object obj, String encoding){
		return new ResultBuilder(marshaller, obj, encoding);
	}
	
	public static SourceBuilder unmarshal(Unmarshaller unmarshaller){
		return new SourceBuilder(unmarshaller);
	}
	
	public static class ResultBuilder {
		private String encoding;
		private Marshaller marshaller;
		private Object obj;

		public ResultBuilder(Marshaller marshaller, Object obj) {
			try {
				this.encoding = (String) marshaller.getProperty(Marshaller.JAXB_ENCODING);
			} catch (PropertyException e) {
				e.printStackTrace();
			}
			this.marshaller = marshaller;
			this.obj = obj;
		}
		
		public ResultBuilder(Marshaller marshaller, Object obj, String encoding) {
			this.encoding = encoding;
			this.marshaller = marshaller;
			this.obj = obj;
		}

		/**
		 * 输出成字符串
		 */
		public String toString() {
			Result result = new StringResult();
			try {
				marshaller.marshal(obj, result);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return result.toString();
		}
		/**
		 * 输出成文件
		 */
		public File toFile(String filePath) {
			File file = new File(filePath);
			StreamResult result = new StreamResult(file);
			try {
				marshaller.marshal(obj, result);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return file;
		}
		
		public DataWriter getDataWriter(PrintWriter printWriter){
			try {
				return new DataWriter(printWriter, encoding);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 输出成文件(不过滤特殊字符串)
		 */
		public String toCDATAFile(String filePath) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = null;
			try {
				printWriter = new PrintWriter(new File(filePath));
				marshaller.marshal(obj, getDataWriter(printWriter));
				return stringWriter.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(printWriter != null){
					printWriter.close();
				}
			}
			return null;
		}
		/**
		 * 输出成字符串(不过滤特殊字符串)
		 */
		public String toCDATAString() {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = null;
			try {
				printWriter = new PrintWriter(stringWriter); 
				marshaller.marshal(obj, getDataWriter(printWriter));
				return stringWriter.toString();
			} catch (JAXBException e) {
				e.printStackTrace();
			} finally {
				if(printWriter != null){
					printWriter.close();
				}
			}
			return null;
		}
	}	
	public static class SourceBuilder {
		private Unmarshaller unmarshaller;

		public SourceBuilder(Unmarshaller unmarshaller) {
			this.unmarshaller = unmarshaller;
		}

		public Object fromString(String xml) {
			try {
				return unmarshaller.unmarshal(new StringSource(xml));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		public Object fromFile(File xmlFile) {
			try {
				return unmarshaller.unmarshal(xmlFile);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		public Object fromInputStream(InputStream is) {
			try {
				return unmarshaller.unmarshal(is);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		public <T> T fromString(String xml, Class<T> declaredType) {
			try{
				return unmarshaller.unmarshal(new StreamSource(xml), declaredType).getValue();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		public <T> T fromFile(File xmlFile, Class<T> declaredType) {
			try{
				return unmarshaller.unmarshal(new StreamSource(xmlFile), declaredType).getValue();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		public <T> T fromInputStream(InputStream is, Class<T> declaredType) {
			try {
				return unmarshaller.unmarshal(new StreamSource(is), declaredType).getValue();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 将字符串中的XML关键字进行转义
	 * @param input
	 * @return
	 */
	public static String encodeSpecailCharacters(String input) {
		if (BeanUtil.isEmpty(input)) {
			return "";
		}
		if (input.contains("<")) {
			input = input.replace("<", "&lt;");
		}
		if (input.contains(">")) {
			input = input.replace(">", "&gt;");
		}
		if (input.contains("\"")) {
			input = input.replace("\"", "&quot;");
		}
		if (input.contains("'")) {
			input = input.replace("'", "&apos;");
		}
		if (input.contains("&") && !input.contains("&amp;")) {
			input = input.replace("&", "&amp;");
		}
		return input;
	}
	
}
