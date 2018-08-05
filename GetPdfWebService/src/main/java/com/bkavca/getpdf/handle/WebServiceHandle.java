package com.bkavca.getpdf.handle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import com.bkavca.getpdf.model.WebServiceResponse;

/**
 * 
 * @author HungDMc
 *
 */
public class WebServiceHandle {

	public static final Logger _LOG = Logger.getLogger(WebServiceHandle.class);
	public static String key = "";
	public static String initVector = "";
	
	static {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		InputStream is = null;
		
		try {
			is = loader.getResourceAsStream("config.properties");
			props.load(is);
			
			key = props.getProperty("crypto.key");
			initVector = props.getProperty("crypto.initVector");
		} catch (Exception e) {
			_LOG.error("Không thể đọc file config.properties\n" + e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					//ignore
				}
			}
		}
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * @author HungDMc
	 */
	public static WebServiceResponse getPdfFile(String path) {
		
		path = EncryptorUtil.decrypt(key, initVector, path);
		
		File filePath = new File(path);
		if (!filePath.exists()) {
			_LOG.error("#getPdfFile(String path): đường dẫn " + path + " không tồn tại!");
			return new WebServiceResponse(false, "Đường dẫn " + path + " không tồn tại!", null);
		}
		
		if (!filePath.isFile()) {
			_LOG.error("#getPdfFile(String path): đường dẫn " + path + " không phải là một file!");
			return new WebServiceResponse(false, "Đường dẫn " + path + " không phải là một file!", null);
		}
		
		if (!path.substring(path.length() - 3, path.length()).equalsIgnoreCase("pdf")) {
			_LOG.error("#getPdfFile(String path): đường dẫn " + path + " không phải là một file định dạng PDF!");
			return new WebServiceResponse(false, "Đường dẫn " + path + " không phải là một file định dạng PDF!", null);
		}
		
		try {
			byte[] fileContent = Files.readAllBytes(filePath.toPath());
			if (fileContent != null)
				return new WebServiceResponse(true, "Lấy file thành công", Base64.encodeBase64String(fileContent));
			else {
				_LOG.error("#getPdfFile(String path): nội dung file " + path + " null!");
				return new WebServiceResponse(false, "Nội dung file " + path + " null!", null);
			}
		} catch (IOException e) {
			_LOG.error("#getPdfFile(String path): " + e);
			return new WebServiceResponse(false, "Exception: " + e, null);
		}
	}
	
}
