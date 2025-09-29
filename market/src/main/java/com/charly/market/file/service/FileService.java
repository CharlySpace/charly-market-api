package com.charly.market.file.service;


import com.charly.market.file.model.dto.CreateFileRequest;
import com.charly.market.file.model.dto.FileResponse;

import java.util.List;

public interface FileService {
    //카테고리 등록
    void createFile(CreateFileRequest request);


    //카테고리 조회
    List<FileResponse> findAll();


    //카테고리 검색
    FileResponse findById(Long id);


    //카테고리 삭제
    void delete(Long id);
}
