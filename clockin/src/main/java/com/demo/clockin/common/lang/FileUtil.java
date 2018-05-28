package com.demo.clockin.common.lang;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 * 
 * @author nathan
 * 
 */
public class FileUtil {
	
	private static final String TIME_TYPE_SHORT = "yyyyMMdd";
    private static final String TIME_TYPE_LONG = "yyyyMMddHHmmSS";
    /**
     * 文件上传
     * 
     * @param absoluteFile
     * @param serverFile
     * @return
     */
    public static boolean uploadFile(File absoluteFile, File serverFile) {
        boolean success = false;
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(absoluteFile);
            os = new FileOutputStream(serverFile);
            byte buf[] = new byte[512];
            int size = 0;
            while ((size = is.read(buf)) > 0) {
                os.write(buf, 0, size);
            }
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
        return success;
    }
    /**
     * 写内容到某文件
     * 
     * @param filepath
     * @param content
     * @param charSet
     * @return
     */
    public static boolean writeFile(String filepath, String content,
            String charSet) {
        boolean success = false;
        OutputStream ostream = null;
        BufferedWriter bw = null;
        try {
            ostream = new FileOutputStream(filepath);
            bw = new BufferedWriter(new OutputStreamWriter(ostream, charSet));
            bw.write(content);
            bw.flush();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bw);
            IOUtils.closeQuietly(ostream);
        }
        return success;
    }
    
    public static String getFileString(String fileName){
        StringBuffer includeHtml = new StringBuffer();
        BufferedReader reader = null;
        try {
            File file = new File(fileName);
            if(file.exists()){
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
                String tempString = null;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    includeHtml.append(tempString);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return includeHtml.toString();
    }
    
    
    /** 
     * 功能:压缩多个文件成一个zip文件 
     * @param srcfile：源文件列表 
     * @param zipfile：压缩后的文件 
     */  
    public static void zipFiles(File[] srcfile,File zipfile){  
        byte[] buf=new byte[4096];  
        try {  
            //ZipOutputStream类：完成文件或文件夹的压缩  
            ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipfile));  
            for(int i=0;i<srcfile.length;i++){  
                FileInputStream in=new FileInputStream(srcfile[i]);  
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));  
                int len;  
                while((len=in.read(buf))>0){  
                    out.write(buf,0,len);  
                }  
                out.closeEntry();  
                in.close();  
            }  
            out.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
    
    /**
     * 获取文件扩展名
     * @param filename
     * @return
     */
    public static String getExtension(String filename) { 
        return getExtension(filename, ""); 
    }
    
    /**
     * 获取文件扩展名
     * @param filename
     * @param defExt
     * @return
     */
    public static String getExtension(String filename, String defExt) { 
        if ((filename != null) && (filename.length() > 0)) { 
            int i = filename.lastIndexOf('.'); 
            if ((i >-1) && (i < (filename.length() - 1))) { 
                return filename.substring(i + 1).toLowerCase(); 
            } 
        } 
        return defExt; 
    }
    
    /**
     * 获取目录日期串
     * @return
     */
    public static String genFolderName(){
        Date date = new Date();
        DateFormat SDF = new SimpleDateFormat(TIME_TYPE_SHORT);
        return SDF.format(date);
    }
    
    /**
     * 获取文件名字符串
     * @return
     */
    public static String genFileName() {
    	Date date = new Date();
        DateFormat LDF = new SimpleDateFormat(TIME_TYPE_LONG);
        int rnd = (int)(Math.random()*9000+1000);
        return LDF.format(date)+rnd;
    }

    /** 获取后缀名 **/
    public static String getPrefix(File file) {
        if (file != null) {
            String fileName = file.getName();
            return getPrefix(fileName);
        }
        return null;
    }

    /** 获取后缀名 **/
    public static String getPrefix(String fileName) {
        if (StringUtils.isNotBlank(fileName)) {
            String prefix = StringUtils.substringAfterLast(fileName, ".");
            return prefix;
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(genFolderName());
        System.out.println(genFileName());
        System.out.println(System.currentTimeMillis());
    }

}
