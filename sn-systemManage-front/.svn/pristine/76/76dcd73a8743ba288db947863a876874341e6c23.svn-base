package com.ratta.spnote.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ratta.spnote.util.StringEscapeEditor;

/**
 * @author page 基础管理，用户封装所有控制层类的公共性 2018-10-31
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}

	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder, @PathVariable String jspName) {
		return "/" + folder + "/" + jspName;
	}

	/**
	 * 将文件file按照fileName 写入path目录下
	 * 
	 * @param path
	 * @param fileName
	 * @param file
	 * @return
	 */
	protected boolean write(String path, String fileName, InputStream is) {
		File directory = new File(path);
		if (!directory.exists()) {
			directory.mkdirs(); // 创建目录
		}
		try {
			OutputStream os = new FileOutputStream(directory.getPath() + "/" + fileName);
			// InputStream is = file.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
			os.close();
			is.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将文件file按照fileName 写入path目录下
	 * 
	 * @param path
	 * @param fileName
	 * @param file
	 * @return
	 */
	protected boolean write(String path, String fileName, MultipartFile file) {
		File directory = new File(path);
		if (!directory.exists()) {
			directory.mkdirs(); // 创建目录
		}
		try {
			OutputStream os = new FileOutputStream(directory.getPath() + "/" + fileName);
			InputStream is = file.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
			os.close();
			is.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除文件用
	 * 
	 * @param path
	 * @param url
	 */
	protected boolean removeFile(String path, String url) {
		File file = new File(path + "/" + url);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 删除文件用
	 * 
	 * @param path
	 * @param url
	 */
	protected boolean removeFile(String url) {
		File file = new File(url);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 删除文件用
	 * 
	 * @param spath
	 */
	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = removeFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除固件文件<b>firmWare</b>专用。<br/>
	 * 删除文件同时删除ReadMe.txt(审核后自动生成)<br/>
	 * 最后删除该path目录<br/>
	 * 
	 * @param path 目录
	 * @param name 文件名
	 */
	protected boolean removeFirmwareFile(String path, String name) {
		File dir = new File(path);
		File file = new File(name);
		File file_readme = new File(path + "/" + "ReadMe.txt");
		if (dir.exists()) {
			if (file.exists()) {
				file.delete();
			}
			if (file_readme.exists()) {
				file_readme.delete();
			}
			return dir.delete();
		}
		return false;
	}

	/**
	 * 对cpath目录下的name文件进行去除_update
	 * 
	 * @param cpath
	 * @param name
	 * @param session
	 * @return 返回改名后的路径
	 */
	protected String reName(String path, String name) {
		if (!StringUtils.isEmpty(name)) {
			String fileName = name.substring(name.lastIndexOf("/") + 1);
			// 更改终端型号的文件名
			int end = fileName.lastIndexOf("_upload");
			if (end < 0) {
				return name;
			}
			String newName = fileName.substring(0, end).concat(fileName.substring(fileName.lastIndexOf(".")));

			File oldFile = new File(path + "/" + fileName);
			File newFile = new File(path + "/" + newName);
			if (newFile.exists()) {
				newFile.delete();
			}
			boolean result = oldFile.renameTo(newFile);

			if (result) {
				return name.substring(0, name.lastIndexOf("/") + 1) + newName;
			}
		}
		return name;
	}

	public boolean write(String path, String fileName, File file) {
		File directory = new File(path);
		if (!directory.exists()) {
			directory.mkdirs(); // 创建目录
		}
		try {
			OutputStream os = new FileOutputStream(directory.getPath() + "/" + fileName);
			InputStream is = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
			os.close();
			is.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected String reFirewareName(String path, String name) {
		if (!StringUtils.isEmpty(name)) {
			String fileName = name.substring(name.lastIndexOf("/") + 1);
			// 更改终端型号的文件名
			int end = fileName.lastIndexOf("_upload");
			if (end < 0) {
				return name;
			}
			String newName = fileName.substring(0, end).concat(fileName.substring(fileName.lastIndexOf(".")));
			String file_no_suffix = fileName.substring(0, end);

			File oldFile = new File(path + "/" + file_no_suffix + "/" + fileName);
			File newFile = new File(path + "/" + file_no_suffix + "/" + newName);
			if (newFile.exists()) {
				newFile.delete();
			}
			boolean result = oldFile.renameTo(newFile);

			if (result) {
				return name.substring(0, name.lastIndexOf("/") + 1) + newName;
			}
		}
		return name;
	}

}
