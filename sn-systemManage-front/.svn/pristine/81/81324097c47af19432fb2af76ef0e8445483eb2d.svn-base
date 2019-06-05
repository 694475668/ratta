package com.ratta.spnote.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ratta.spnote.util.Json;

/**
 * @author page 公用方法 2018-10-31
 */
@Controller
@RequestMapping("/commonController")
public class CommonController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(CommonController.class);

	/**
	 * 图片上传
	 * 
	 * @param file
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Json upload(@RequestParam(value = "imageFile") MultipartFile file, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String temppath = "/file";
		Json j = new Json();
		// 构建文件名称multipartFile
		String oldName = file.getOriginalFilename();
		if (!oldName.toLowerCase().endsWith("jpg") && !oldName.toLowerCase().endsWith("png")) {
			System.out.println("格式：" + oldName);
			// 判断是否是图片文件
			j.setMsg(CommonMessage.COMMON_UPLOAD_FILE_FORMAT_JPG_OR_PNG_ERROR.getMessage());
			j.setSuccess(false);
			return j;
		}
		// 获取文件的后缀
		String suffix = oldName.substring(oldName.indexOf('.'));
		String name = String.valueOf(System.currentTimeMillis()).concat(suffix);
		String path = req.getSession().getServletContext().getRealPath(temppath);
		try {
			File filepath = new File(path);
			if (!filepath.exists()) {
				filepath.mkdir(); // 创建目录
			}
			OutputStream os = new FileOutputStream(filepath.getPath() + "/" + name);
			InputStream is = file.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
			os.close();
			is.close();
			j.setSuccess(true);
			j.setMsg(CommonMessage.COMMON_UPLOAD_SUCCESS.getMessage());

		} catch (Exception e) {
			j.setMsg(CommonMessage.COMMON_UPLOAD_ERROR.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		j.setErrorinfo(req.getContextPath() + temppath + "/" + name);
		j.setObject(path + "\\" + name);
		return j;
	}

	@RequestMapping(value = "/upload2", method = RequestMethod.POST)
	@ResponseBody
	public Json upload2(@RequestParam(value = "imageFile2") MultipartFile file, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String temppath = "/file";
		Json j = new Json();
		// 构建文件名称multipartFile
		String oldName = file.getOriginalFilename();
		// 获取文件的后缀
		String suffix = oldName.substring(oldName.indexOf('.'));
		String name = String.valueOf(System.currentTimeMillis()).concat(suffix);
		String path = req.getSession().getServletContext().getRealPath(temppath);
		try {
			File filepath = new File(path);
			if (!filepath.exists()) {
				filepath.mkdir(); // 创建目录
			}
			OutputStream os = new FileOutputStream(filepath.getPath() + "/" + name);
			InputStream is = file.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
			os.close();
			is.close();
			j.setSuccess(true);
			j.setMsg(CommonMessage.COMMON_UPLOAD_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setMsg(CommonMessage.COMMON_UPLOAD_ERROR.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		j.setErrorinfo(req.getContextPath() + temppath + "/" + name);
		j.setObject(path + "\\" + name);
		return j;
	}

	@RequestMapping(value = "/upload3", method = RequestMethod.POST)
	@ResponseBody
	public Json upload3(@RequestParam(value = "imageFile3") MultipartFile file, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String temppath = "/file";
		Json j = new Json();
		// 构建文件名称multipartFile
		String oldName = file.getOriginalFilename();
		// 获取文件的后缀
		String suffix = oldName.substring(oldName.indexOf('.'));
		String name = String.valueOf(System.currentTimeMillis()).concat(suffix);
		String path = req.getSession().getServletContext().getRealPath(temppath);
		try {
			File filepath = new File(path);
			if (!filepath.exists()) {
				filepath.mkdir(); // 创建目录
			}
			OutputStream os = new FileOutputStream(filepath.getPath() + "/" + name);
			InputStream is = file.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
			os.close();
			is.close();
			j.setSuccess(true);
			j.setMsg(CommonMessage.COMMON_UPLOAD_SUCCESS.getMessage());
		} catch (Exception e) {
			j.setMsg(CommonMessage.COMMON_UPLOAD_ERROR.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		j.setErrorinfo(req.getContextPath() + temppath + "/" + name);
		j.setObject(path + "\\" + name);
		return j;
	}

	/**
	 * 文件下载
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/download/{filename}")
	public void download(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

//    	String path = request.getParameter("path") ;
		String path = request.getSession().getServletContext().getRealPath(File.separator) + "excel" + File.separator;
		path = java.net.URLDecoder.decode(path, "UTF-8");

		response.addHeader("Content-disposition",
				"attachment;filename=" + new String(filename.getBytes("utf-8"), "ISO8859-1"));
		response.setContentType("application/x-msdownload");

		java.io.BufferedOutputStream out = null;
		java.io.BufferedInputStream in = null;
		try {
			out = new BufferedOutputStream(response.getOutputStream());
			in = new BufferedInputStream(new java.io.FileInputStream(path + File.separator + filename));
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) > 0) {
				out.write(b, 0, i);
			}
			out.flush();
		} catch (Exception e) {
			logger.error("文件下载失败" + e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
