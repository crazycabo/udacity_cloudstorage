package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.datasource.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.UploadedFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Service
public class FileUploadService {

    private final FileMapper fileMapper;

    public FileUploadService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<UploadedFile> getAllFiles(int userId) {
        return this.fileMapper.getFilesByUserId(userId);
    }

    public UploadedFile getFileByName(String fileName, int userId) {
        return this.fileMapper.getFileByName(fileName, userId);
    }

    public int uploadFile(MultipartFile file, int userId) throws IOException {
        return this.fileMapper.uploadFileById(
                UploadedFile.builder()
                        .fileData(file.getBytes())
                        .fileName(file.getOriginalFilename())
                        .fileSize(file.getSize())
                        .contentType(file.getContentType())
                        .build()
        );
    }

    public int deleteFile(int fileId) {
        return this.fileMapper.deleteFileById(fileId);
    }

    public boolean fileNameExists(String fileName, int userId) {
        return this.fileMapper.getFileByName(fileName, userId) == null;
    }
}
