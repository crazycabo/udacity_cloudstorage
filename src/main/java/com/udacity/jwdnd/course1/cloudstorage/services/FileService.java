package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.datasource.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getAllFiles(int userId) {
        return this.fileMapper.getFilesByUserId(userId);
    }

    public File getFileByName(String fileName, int userId) {
        return this.fileMapper.getFileByName(fileName, userId);
    }

    public int uploadFile(MultipartFile file, int userId) throws IOException {
        return this.fileMapper.uploadFile(
                File.builder()
                        .fileData(file.getBytes())
                        .fileName(file.getOriginalFilename())
                        .fileSize(file.getSize())
                        .userId(userId)
                        .contentType(file.getContentType())
                        .build()
        );
    }

    public int deleteFile(int fileId) {
        return this.fileMapper.deleteFileById(fileId);
    }

    public boolean fileNameExists(String fileName, int userId) {
        return this.fileMapper.getFileByName(fileName, userId) != null;
    }
}
