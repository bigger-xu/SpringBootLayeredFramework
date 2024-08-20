package com.efreight.common.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.efreight.common.exception.EfreightBizException;
import lombok.extern.slf4j.Slf4j;

/**
 * ZIP压缩工具类
 *
 * @author 张永伟
 * @since 2023/10/20
 */
@Slf4j
public class ZipUtil {
    
    public static final String DEFAULT_FILE_PATH = "/data/file/temp/";
    
    public static final String DEFAULT_ZIP_PATH = "/data/zip/temp/";
    
    
    /**
     * 压缩文件
     *
     * @param urlList 文件url地址列表
     * @return String
     * @since 2024/8/13
     */
    public static String doCompress(List<String> urlList) throws IOException {
        if (urlList != null && !urlList.isEmpty()) {
            String downloadPath = DEFAULT_FILE_PATH + System.currentTimeMillis() + "/";
            for (String s : urlList) {
                downloadFile(s, downloadPath + s.substring(s.lastIndexOf("/")));
            }
            String outPath = DEFAULT_ZIP_PATH + System.currentTimeMillis() + ".zip";
            File file = new File(DEFAULT_ZIP_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(outPath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File downloadFile = new File(downloadPath);
            doCompress(downloadFile, zipOut, outPath.substring(0, outPath.lastIndexOf("/")));
            zipOut.close();
            fos.close();
            deleteDirectoryLegacyIo(downloadFile);
            return outPath;
        }
        return null;
    }
    
    /**
     * 压缩文件
     *
     * @param urlList     文件url地址列表
     * @param zipFileName 压缩包文件名
     * @return String
     * @since 2024/8/13
     */
    public static String doCompress(List<String> urlList, String zipFileName) throws IOException {
        if (urlList != null && !urlList.isEmpty()) {
            String downloadPath = DEFAULT_FILE_PATH + System.currentTimeMillis() + "/";
            for (String s : urlList) {
                downloadFile(s, downloadPath + s.substring(s.lastIndexOf("/")));
            }
            String outPath = DEFAULT_ZIP_PATH + (StringUtils.isNotEmpty(zipFileName) ? zipFileName : System.currentTimeMillis()) + ".zip";
            File file = new File(DEFAULT_ZIP_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(outPath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File downloadFile = new File(downloadPath);
            doCompress(downloadFile, zipOut, outPath.substring(0, outPath.lastIndexOf("/")));
            zipOut.close();
            fos.close();
            deleteDirectoryLegacyIo(downloadFile);
            return outPath;
        }
        return null;
    }
    
    /**
     * 压缩文件（包文件名可自定义，包内各个文件名可自定义）
     *
     * @param urlList     key:真实文件名，value:文件地址
     * @param zipFileName 压缩包的文件名
     */
    public static String doCompressWithRealName(Map<String, String> urlList, String zipFileName) throws IOException {
        if (urlList != null && !urlList.isEmpty()) {
            String downloadPath = DEFAULT_FILE_PATH + System.currentTimeMillis() + "/";
            for (String fileName : urlList.keySet()) {
                downloadFile(urlList.get(fileName), downloadPath + urlList.get(fileName).substring(urlList.get(fileName).lastIndexOf("/")), fileName);
            }
            String outPath = DEFAULT_ZIP_PATH + (StringUtils.isNotEmpty(zipFileName) ? zipFileName : System.currentTimeMillis()) + ".zip";
            File file = new File(DEFAULT_ZIP_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(outPath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File downloadFile = new File(downloadPath);
            doCompress(downloadFile, zipOut, outPath.substring(0, outPath.lastIndexOf("/")));
            zipOut.close();
            fos.close();
            deleteDirectoryLegacyIo(downloadFile);
            return outPath;
        }
        return null;
    }
    
    /**
     * 下载文件到temp目录   记得用完文件删除
     *
     * @param fileUrl  文件url
     * @param fileName 临时文件地址
     * @since 2023/10/21
     */
    public static void downloadFile(String fileUrl, String fileName) throws IOException {
        DataInputStream in = null;
        DataOutputStream out = null;
        HttpURLConnection connection = null;
        try {
            String path = fileName.substring(0, fileName.lastIndexOf("/"));
            String newFileName = path + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("/") + 1);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            URL url = new URL(fileUrl);
            connection = (HttpURLConnection) url.openConnection();
            in = new DataInputStream(connection.getInputStream());
            out = new DataOutputStream(Files.newOutputStream(Paths.get(newFileName)));
            byte[] buffer = new byte[4096];
            int count;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
        } catch (Exception e) {
            throw new EfreightBizException(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
            
        }
    }
    
    /**
     * 下载文件到temp目录   记得用完文件删除
     *
     * @param fileUrl  文件url
     * @param fileName 临时文件地址
     * @since 2023/10/21
     */
    public static void downloadFile(String fileUrl, String fileName, String newFileName) throws IOException {
        DataInputStream in = null;
        DataOutputStream out = null;
        HttpURLConnection connection = null;
        try {
            String path = fileName.substring(0, fileName.lastIndexOf("/"));
            newFileName = path + newFileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            URL url = new URL(fileUrl);
            connection = (HttpURLConnection) url.openConnection();
            in = new DataInputStream(connection.getInputStream());
            out = new DataOutputStream(Files.newOutputStream(Paths.get(newFileName)));
            byte[] buffer = new byte[4096];
            int count;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
        } catch (Exception e) {
            throw new EfreightBizException(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
            
        }
    }
    
    /**
     * 下载文件为流
     *
     * @param fileUrl
     * @return InputStream
     * @since 2023/10/21
     */
    public static InputStream downloadFileStream(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            return connection.getInputStream();
        } catch (Exception e) {
            return null;
        }
    }
    
    
    /**
     * 删除文件  递归
     *
     * @param file 文件或者文件夹
     * @since 2023/10/21
     */
    public static void deleteDirectoryLegacyIo(File file) {
        
        File[] list = file.listFiles();  //无法做到list多层文件夹数据
        if (list != null) {
            for (File temp : list) {     //先去递归删除子文件夹及子文件
                deleteDirectoryLegacyIo(temp);   //注意这里是递归调用
            }
        }
        
        if (file.delete()) {     //再删除自己本身的文件夹
            log.info("删除成功 --> 方法名:【deleteDirectoryLegacyIO】--> 参数:file = {}", file);
        } else {
            log.info("删除失败 --> 方法名:【deleteDirectoryLegacyIO】--> 参数:file = {}", file);
        }
    }
    
    public static void doCompress(String srcFile, String zipFile) throws IOException {
        doCompress(new File(srcFile), new File(zipFile));
    }
    
    /**
     * 文件压缩
     *
     * @param srcFile 目录或者单个文件
     * @param zipFile 压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File zipFile) throws IOException {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            doCompress(srcFile, out);
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();//记得关闭资源
            }
        }
    }
    
    public static void doCompress(String filelName, ZipOutputStream out) throws IOException {
        doCompress(new File(filelName), out);
    }
    
    public static void doCompress(File file, ZipOutputStream out) throws IOException {
        doCompress(file, out, "");
    }
    
    public static void doCompress(File inFile, ZipOutputStream out, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    String name = inFile.getName();
                    if (!"".equals(dir)) {
                        name = dir + "/" + name;
                    }
                    ZipUtil.doCompress(file, out, name);
                }
            }
        } else {
            ZipUtil.doZip(inFile, out, dir);
        }
    }
    
    public static void doZip(File inFile, ZipOutputStream out, String dir) throws IOException {
        String entryName;
        if (!"".equals(dir)) {
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }
        ZipEntry entry = new ZipEntry(entryName.substring(entryName.lastIndexOf("/") + 1));
        out.putNextEntry(entry);
        
        int len = 0;
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(inFile);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }
}
