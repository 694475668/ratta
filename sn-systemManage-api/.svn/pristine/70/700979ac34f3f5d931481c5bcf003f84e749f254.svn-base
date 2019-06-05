package com.ratta.suponotes.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author page
 * 文件操作类
 * 2018-10-31
 */
public class FileUtils {

    /**
     * 读取指定文件的修改时间
     * @param file 文件
     * @return 返回文件的最后修改日期
     */
    public static String readModifyTime(File file){
        Calendar cal = Calendar.getInstance();
        long time = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTimeInMillis(time);
        return sdf.format(cal.getTime());
    }


    /**
     * 获取指定路径下的所有文件
     * @param path  指定路径
     * @return file数组
     */
    public static File[] listAllFiles(String path){
        File file = new File(path);
        if(!file.isDirectory()){
            return null;
        }
        File[] files = file.listFiles();
        for(File f:files){
            System.out.println(f.getName());
        }
        return files;
    }


    /**
     * 将指定目录下的文件组装成list
     * @param path  指定路径
     * @return file集合
     */
    public static List<File> listsAllFiles(String path){
        List<File> lists = new ArrayList<File>();
        File file = new File(path);
        if(!file.isDirectory()){
            return null;
        }
        File[] files = file.listFiles();
        for(File f : files){
            lists.add(f);
        }
        return lists;
    }

    /**
     *
     * @param zip     压缩文件名，包括路径
     * @param zipOut  输出流
     * @param srcFile 源文件
     * @param path 路径
     * @throws IOException io异常
     */
    private static void handlerFile(String zip,ZipOutputStream zipOut,File srcFile,String path) throws IOException{
        if(!"".equals(path) && !path.endsWith(File.separator)){
            path += File.separator;
        }
        if(!srcFile.getPath().equals(zip)){
            if(srcFile.isDirectory()){
                File[] files = srcFile.listFiles();
                if(files.length==0){
                    zipOut.putNextEntry(new ZipEntry(path+srcFile.getName()+File.separator));
                    zipOut.closeEntry();
                }else{
                    for(File _f:files){
                        handlerFile(zip,zipOut,_f,path+srcFile.getName());
                    }
                }
            }else{
                InputStream in = new FileInputStream(srcFile);
                zipOut.putNextEntry(new ZipEntry(path+srcFile.getName()));
                int len=0;
                byte[] _byte = new byte[1024];
                while ((len=in.read(_byte))>0){
                    zipOut.write(_byte,0,len);
                }
                in.close();
                zipOut.closeEntry();
            }
        }
    }


    /**
     * 压缩文件或文件夹
     * @param zip       压缩文件名包括路径
     * @param srcFiles  压缩的源文件
     * @throws IOException io异常
     */
    public static void zipFile(String zip,List<File> srcFiles) throws IOException{
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(new File(zip)));
        for(File f:srcFiles){
                handlerFile(zip,zipOut,f,"");
        }
        zipOut.close();
    }


    /**
     * 创建目录
     * @param dir  目录路径
     */
    public static void mkdir(String dir){
        try{
            String dirTemp = dir;
            File dirPath = new File(dirTemp);
            if(!dirPath.exists()){
                dirPath.mkdirs();
            }
        }catch(Exception e){

        }
    }


    /**
     * 删除文件
     * @param fileName 文件名
     */
    public static void deleteFile(String fileName){
        String filePath = fileName;
        File file = new File(filePath);
        file.delete();
    }


    /**
     * 删除文件夹里面的所有文件
     * @param path 文件夹路径
     */
    public static void delAllFile(String path){
        File file = new File(path);
        if(!file.exists()){
            return;
        }
        if(!file.isDirectory()){
            return;
        }
        String[] childFiles = file.list();
        File temp = null;
        for(int i=0;i<childFiles.length;i++){
            if(path.endsWith(File.separator)){
                temp = new File(path+childFiles[i]);
            }else{
                temp = new File(path+File.separator+childFiles[i]);
            }
            if(temp.isFile()){
                temp.delete();
            }

            if(temp.isDirectory()){
                delAllFile(path+"/"+childFiles[i]);
                deleteFolder(path+"/"+childFiles[i]);
            }
        }

    }

    /**
     * 删除文件夹
     * @param folderPath 文件夹路径
     */
    public static void deleteFolder(String folderPath){
        delAllFile(folderPath);
        String filePath = folderPath;
        File path = new File(filePath);
        path.delete();
    }


    /**
     * 复制单个文件
     * @param src    源文件目录
     * @param dest   目标文件目录
     * @throws FileNotFoundException 文件未发现异常
     * @throws IOException io异常
     */
    public static void copyFile(String src,String dest) throws FileNotFoundException,IOException{
        InputStream is = new FileInputStream(src);
        mkdir(dest);
        OutputStream out = new FileOutputStream(dest+"/"+new File(src).getName());
        int len;
        byte[] buffer = new byte[1024];
        while((len=is.read(buffer))!=-1){
            out.write(buffer,0,len);
        }
        out.flush();
        out.close();
        is.close();
    }


    /**
     * 复制文件夹
     * @param oldPath  源文件路径
     * @param newPath  目标文件路径
     * @throws FileNotFoundException 文件未发现
     * @throws IOException IO异常
     */
    public static void copyFoler(String oldPath,String newPath) throws FileNotFoundException,IOException{
        mkdir(newPath);
        File file = new File(oldPath);
        String[] files = file.list();
        File temp = null;
        for(int i=0;i<files.length;i++){
            if(oldPath.endsWith(File.separator)){
                temp = new File(oldPath+files[i]);
            }else{
                temp = new File(oldPath+File.separator+files[i]);
            }
            if(temp.isFile()){
                InputStream  is = new FileInputStream(temp);
                OutputStream out = new FileOutputStream(newPath+"/"+temp.getName());
                byte[] buffer = new byte[1024*2];
                int len;
                while((len=is.read(buffer))!=-1){
                    out.write(buffer,0,len);
                }
                out.flush();
                out.close();
                is.close();
            }
            if(temp.isDirectory()){
                copyFoler(oldPath+"/"+files[i],newPath+"/"+files[i]);
            }
        }
    }


    /**
     * 移动文件到指定目录，不会删除文件夹
     * @param oldPath   源文件目录
     * @param newPath   目标文件目录
     * @throws FileNotFoundException 文件未发现
     * @throws IOException IO异常
     */
    public static void moveFile(String oldPath,String newPath) throws FileNotFoundException,IOException{
        copyFile(oldPath, newPath);
        deleteFile(oldPath);
    }


    /**
     * 移动文件到指定目录，会删除文件夹
     * @param oldPath   源目录
     * @param newPath   目标目录
     * @throws FileNotFoundException 文件未发现
     * @throws IOException IO异常
     */
    public static void moveFolder(String oldPath,String newPath) throws FileNotFoundException,IOException{
        copyFoler(oldPath,newPath);
        deleteFolder(oldPath);
    }
    // 清空已有的文件内容，以便下次重新写入新的内容
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}