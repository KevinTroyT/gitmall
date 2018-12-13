package com.gmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by troykevin on 2018/12/6.
 */
public interface IFileService {

    /**
     * 上传文件
     * @param file
     * @param path
     * @return
     */
    String upload(MultipartFile file, String path);
}
