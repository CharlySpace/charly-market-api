package com.charly.market.file.controller;

import com.charly.market.category.model.dto.CategoryResponse;
import com.charly.market.category.model.dto.CreateCategoryRequest;
import com.charly.market.file.model.dto.CreateFileRequest;
import com.charly.market.file.model.dto.FileResponse;
import com.charly.market.file.service.FileService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    //등록
    @PostMapping
    public ResponseEntity<String> createFile(@RequestBody CreateFileRequest request){
        System.out.println(request.toString());
        fileService.createFile(request);
        return ResponseEntity.ok("파일");
    }

    //조회
    @GetMapping
    public ResponseEntity<List<FileResponse>> findFileList() {
        List<FileResponse> fileResponseList= fileService.findAll();
        return ResponseEntity.ok(fileResponseList);
    }

    //검색
    @GetMapping("/{id}")
    public ResponseEntity<FileResponse>  findFileById(@PathVariable @NotNull Long id){
        FileResponse fileResponse = fileService.findById(id);
        return ResponseEntity.ok(fileResponse);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id){
        fileService.delete(id);
        return ResponseEntity.ok("삭제 성공");
    }
}
