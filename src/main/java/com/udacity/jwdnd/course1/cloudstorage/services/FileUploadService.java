package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.datasource.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.UploadedFile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Service
public class FileUploadService {

    private FileMapper fileMapper;

    public FileUploadService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<UploadedFile> getAllFiles(int userId) {
        return this.fileMapper.getFilesByUserId(userId);
    }

    // todo: get file by name

    // todo: upload file

    // todo: delete file

    // todo: check if file name present
}
