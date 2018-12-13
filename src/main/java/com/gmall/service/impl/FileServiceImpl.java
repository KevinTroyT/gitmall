package com.gmall.service.impl;/**
 * Created by troykevin on 2018/12/6.
 */

import com.gmall.service.IFileService;
import com.gmall.util.FTPUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ program : gmall
 * @ author : troykevin
 * @ create : 2018-12-06 14:22
 **/
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);



    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        // 获取扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf('.')+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，文件名：{}，上传的路径{}，新文件名：{}",fileName,path,uploadFileName);
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件上传成功

            FTPUtil.uploadFile(Lists.<File>newArrayList(targetFile));
            //已经上传到ftp服务器上

            targetFile.delete();
            //删除
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}
