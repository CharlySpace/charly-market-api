package com.charly.market.file.service;

import com.charly.market.file.model.dto.CreateFileRequest;
import com.charly.market.file.model.dto.FileResponse;
import com.charly.market.file.model.entity.File;
import com.charly.market.file.repository.FileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public void createFile(CreateFileRequest request) {
        File file = File.builder()
                .path(request.path())
                .fileName(request.fileName())
                .type(request.type())
                .build();

        fileRepository.save(file);
    }


    @Override
    public List<FileResponse> findAll() {
        List<File> files = fileRepository.findAll();
        List<FileResponse> fileResponseList = new ArrayList<>();
        for (File file : files) {
            FileResponse fileResponse = new FileResponse(
                    file.getPath(),
                    file.getFileName(),
                    file.getType()
            );
            fileResponseList.add(fileResponse);
        }
        return fileResponseList;
    }

    @Override
    public FileResponse findById(Long id) {
        return fileRepository.findById(id)
                .map(file -> new FileResponse(
                        file.getPath(),
                        file.getFileName(),
                        file.getType()))
                .orElseThrow(() -> new EntityNotFoundException("File not found with id: " + id));
    }


    @Override
    @Transactional
    public void delete(Long id) {
        fileRepository.deleteById(id);
    }
}
