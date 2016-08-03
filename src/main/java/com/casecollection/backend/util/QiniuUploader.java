package com.casecollection.backend.util;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuUploader {
    
    private String accessKey;
    
    private String secretKey;
    
    private String bucketName;
    
    private UploadManager uploadManager;
    
    private Auth auth;
    
    private String urlPrefix;
    
    private static final int BYTE_LENGTH = 10 * 1024 * 1024;
    
    public void init() {
      //密钥配置
        auth = Auth.create(accessKey, secretKey);
    }
    
    public Map<String, Object> uploadVideo(MultipartFile file) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取文件名
        String fileName = getFileUniqueName(file);
        
        byte[] bytes = new byte[BYTE_LENGTH];
        
        try {
            //处理字节流
            InputStream inputStream = file.getInputStream();
            
//            int off = 0;
//            int length = BYTE_LENGTH;
//            int read = 0;
            Response res = null;
            //断点记录
//            FileRecorder recorder = new FileRecorder(fileName);     
            Long start = System.currentTimeMillis();
//            while(inputStream.read(bytes) != -1) {
                //上传对象
                UploadManager uploadManager = new UploadManager();
                res = uploadManager.put(bytes, fileName, getToken());
//                recorder.set(fileName, bytes);
//            }
            System.out.println("time>>>" + (System.currentTimeMillis() - start)/1000);
            
            if(res != null && res.isOK()) {
                map.put("state", "SUCCESS");
                if(urlPrefix != null && !urlPrefix.endsWith("/")) {
                    urlPrefix += File.separator;
                }
                map.put("url", urlPrefix + fileName);
                map.put("title", file.getName());
                map.put("original", file.getOriginalFilename());
            }else {
                map.put("state", "ERROR");
            }
        } catch (Exception e) {
            map.put("state", "ERROR");
        }
        return map;
    }
    
    private String getFileUniqueName(MultipartFile file) {
        String fileName = file.getName() + "_" + String.valueOf(System.currentTimeMillis());
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        fileName += CommonUtils.getRandomNumStr(8) + extension;
        return fileName;
    }

    public String getToken() {
        return auth.uploadToken(bucketName);
    }
    
    public Map<String, Object> videoUploadCallback(String fileName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "SUCCESS");
        if(urlPrefix != null && !urlPrefix.endsWith("/")) {
            urlPrefix += File.separator;
        }
        map.put("url", urlPrefix + fileName);
        map.put("title", fileName);
        map.put("original", fileName);
        return map;
    }
    
    public String getTokenWithCallback() {
        return auth.uploadToken(bucketName, 
                null, 
                3600, 
                new StringMap()
                .put("returnUrl", "http://127.0.0.1:8080/common/videoUploadCallback")
                .put("returnBody", "fileName=$(fname)"));
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

}
