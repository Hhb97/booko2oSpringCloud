package com.book.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

public class UploadUtil {
    public static String uploadfile(MultipartFile multipartFile,String path){
        String uploadpath = PropertiesUtil.getProperty("uploadpath");
        String oldName = multipartFile.getOriginalFilename();//获取文件名字
        StringBuffer newName = new StringBuffer(IDUUID.getImageName());//新文件名
        newName.append(oldName.substring(oldName.lastIndexOf(".")));
        String [] strings = DateTimeUtil.dateToStr(new Date()).split(" ");
        String urlpath ="/imgs/"+strings[0]+"/";//服务器路径
        uploadpath = uploadpath+path;
        System.out.println("path="+uploadpath);
        File FileDir = new File(uploadpath);
        if(!FileDir.exists()){
            FileDir.mkdirs();
        }
        try {
            System.out.println(uploadpath+newName);
            multipartFile.transferTo(new File(uploadpath + newName));//上传文件
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return urlpath+newName;
    }

}
