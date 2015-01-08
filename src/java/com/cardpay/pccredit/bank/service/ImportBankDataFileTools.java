package com.cardpay.pccredit.bank.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.cardpay.pccredit.bank.model.DataFileConf;
import com.cardpay.pccredit.base.InitServer;
import com.wicresoft.util.database.convert.SqlJavaNameUtil;
import com.wicresoft.util.date.DateHelper;

/**
 * @author chenzhifang
 *
 * 2014-12-5下午5:51:05
 */
public class ImportBankDataFileTools {
	// 分隔符
	public static final String SPLITE_CHARS = "@\\|@";
	
	public static final String DECIMAL = "DECIMAL";
	public static final String VARCHAR = "VARCHAR";
	public static final String DATE_STRING = "DATE_STRING";
	public static final String DATE_NOW = "DATE_NOW";
	
	public Logger log = Logger.getLogger(ImportBankDataFileTools.class);
	
	/**
	 *  解析数据文件配置
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<DataFileConf> parseDataFileConf(String fileName) throws Exception {
		List<DataFileConf> confList = new ArrayList<DataFileConf>();
		//创建一个读取工具
	    SAXReader xmlReader = new SAXReader();
	    //获取xml文档实例 
	    Document xmlDocument = (Document) xmlReader.read(new FileInputStream(fileName));
	    List<Element> elements = xmlDocument.getRootElement().elements();
	    String column, jdbcType, index, style;
	    for(Element element : elements){
	    	column = element.attributeValue("column");
	    	jdbcType = element.attributeValue("jdbcType");
	    	index = element.attributeValue("index");
	    	style = element.attributeValue("style");
	    	if(StringUtils.isNotEmpty(index)){
	    		confList.add(new DataFileConf(column, jdbcType, Integer.valueOf(index), style));
	    	}else{
	    		confList.add(new DataFileConf(column, jdbcType, -1, style));
	    	}
	    }
	    return confList;
	}
	
	/**
	 * 解析”存贷记卡台帐“数据文件
	 * @param fileName
	 * @param confMap
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, Object>> parseDataFile(String fileName, List<DataFileConf> confList) throws Exception{
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			fis=new FileInputStream(fileName);
		    isr=new InputStreamReader(fis, "gbk");
		    br = new BufferedReader(isr,10*1024*1024);// 用5M的缓冲读取文本文件  
		      
			String line="", value = null, type = null, column = null;
	        String[] dataArrs=null;
	        Map<String, Object> map = null;
	        int count = 0;
	        while ((line=br.readLine())!=null) {
	        	System.out.println(count);
	        	count++;
	        	dataArrs = StringUtils.splitPreserveAllTokens(line.replaceAll(SPLITE_CHARS, "±"),"±");
	            map = new HashMap<String, Object>();
	            boolean flag = true;
	            for(DataFileConf dataFileConf : confList){
	            	type = dataFileConf.getJdbcType();
	            	column = dataFileConf.getColumn();
	            	
	            	if(dataFileConf.getIndex() >= 1){
	            		value = dataArrs[dataFileConf.getIndex()-1].trim();
	            	}
	            	
	            	if(DECIMAL.equals(type) || VARCHAR.equals(type)){
						value = value.trim();
						
						if(DECIMAL.equals(type) || NumberUtils.isNumber(value)){
							value = NumberUtils.createBigDecimal(value).toString();
						}
											
						if(DECIMAL.equals(type) && StringUtils.isNotEmpty(value) && !NumberUtils.isNumber(value)){
							log.info(value + " is not number, line string : " + line);
							flag = false;
							break;
						}
	            	}else if(DATE_STRING.equals(type)){
	            		value = value.replaceAll("-", "");
	            	}else if(DATE_NOW.equals(type)){
	            		value = DateHelper.getDateFormat(new Date(), dataFileConf.getStyle());
	            	}
	            	
					map.put(SqlJavaNameUtil.getVariableName(column, false),value);
	            }
	            if(flag){
	            	datas.add(map);
	            }
	        }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br != null){
				 br.close();
			}
			if(isr != null){
				isr.close();
			}
			if(fis != null){
				fis.close();
			}
		}
        return datas;
	}
	
	/**
	 *  解析数据文件配置
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void parse(String fileName) throws Exception {
		URL url = XmAccCreditService.class.getResource("/datamapping/" + fileName);
		//创建一个读取工具
	    SAXReader xmlReader = new SAXReader();
	    //获取要校验xml文档实例 
	    Document xmlDocument = (Document) xmlReader.read(new FileInputStream(url.getPath()));
	    List<Element> elements = xmlDocument.getRootElement().elements();
	    int i = 0;
	    for(int k = 0; k < elements.size(); k++){
	    	Element element = elements.get(k);
	    	Attribute  attribute = element.attribute("index");
	    	if(StringUtils.isNotEmpty(attribute.getValue())){
	    		i = i + 1;
	    		element.attribute("index").setValue((i)+"");
	    	}else{
	    		element.attribute("index").setValue("");
	    	}
	    }
		XMLWriter xw = new XMLWriter();
		xw.write(xmlDocument);
		// 刷新
		xw.flush();
		xw.close();
	}
	
	public String getFileFullName(String fileName){
		return InitServer.getClassPath() + fileName;
	}
	
	public List<Map<String, Object>> largeFileIO(String fileName, List<DataFileConf> confList) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            BufferedReader in = new BufferedReader(new InputStreamReader(bis, "gbk"), 10 * 1024 * 1024);//10M缓存
            
            String line="", value = null, type = null, column = null;
	        String[] dataArrs=null;
	        Map<String, Object> map = null;
	        int count = 0;
            while (in.ready()) {
				line = in.readLine();
				System.out.println(count);
				count++;
				dataArrs = StringUtils.splitPreserveAllTokens(line.replaceAll(SPLITE_CHARS, "±"),"±");
				map = new HashMap<String, Object>();
				boolean flag = true;
				for(DataFileConf dataFileConf : confList){
					type = dataFileConf.getJdbcType();
					column = dataFileConf.getColumn();
					
					if(dataFileConf.getIndex() >= 1){
						value = dataArrs[dataFileConf.getIndex()-1].trim();
					}
					
					if(DECIMAL.equals(type) || VARCHAR.equals(type)){
						value = value.trim();
						
						if(DECIMAL.equals(type) || NumberUtils.isNumber(value)){
							value = NumberUtils.createBigDecimal(value).toString();
						}
											
						if(DECIMAL.equals(type) && StringUtils.isNotEmpty(value) && !NumberUtils.isNumber(value)){
							log.info(value + " is not number, line string : " + line);
							flag = false;
							break;
						}
					}else if(DATE_STRING.equals(type)){
						value = value.replaceAll("-", "");
					}else if(DATE_NOW.equals(type)){
						value = DateHelper.getDateFormat(new Date(), dataFileConf.getStyle());
					}
					
					map.put(SqlJavaNameUtil.getVariableName(column, false),value);
				}
				if(flag){
					datas.add(map);
				}
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return datas;
	}
}
