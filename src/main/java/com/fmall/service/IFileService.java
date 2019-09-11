package com.fmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZSS
 * @description file service
 */
public interface IFileService {

    /**
     * 上传文件
     *
     * @param file 文件流
     * @param path 路径
     * @return String
     */
    String upload(MultipartFile file, String path);
}
