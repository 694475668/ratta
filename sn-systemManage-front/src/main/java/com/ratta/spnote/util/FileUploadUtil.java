package com.ratta.spnote.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author page 文件上传帮助类2 2018-10-31
 */
public class FileUploadUtil {

	/**
	 * 上传文件到path路径下
	 * 
	 * @param request
	 * @param path
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static List<File> doUpload(HttpServletRequest request, String path)
			throws IllegalStateException, IOException {
		List<File> files = new ArrayList<File>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file == null) {
					continue;
				}
				// 取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (myFileName.trim() != "") {
					System.out.println(myFileName);
					// 重命名上传后的文件名
					String fileName = file.getOriginalFilename();
					File localFile = new File(path + fileName);
					file.transferTo(localFile);
					files.add(localFile);
				}
			}
		}
		return files;
	}

	/**
	 * 上传文件到默认路径
	 * 
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static List<File> doUpload(HttpServletRequest request) throws IllegalStateException, IOException {
		String filePath = request.getSession().getServletContext().getRealPath(File.separator) + "file"
				+ File.separator;
		return doUpload(request, filePath);
	}
}
