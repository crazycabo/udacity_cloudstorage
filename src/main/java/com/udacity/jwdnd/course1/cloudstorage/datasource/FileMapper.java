package com.udacity.jwdnd.course1.cloudstorage.datasource;

import com.udacity.jwdnd.course1.cloudstorage.models.UploadedFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Brian Smith on 8/8/21.
 * Description:
 */
@Mapper
public interface FileMapper {

    @Select("Select * from FILES where userId = #{userId} and filename = #{fileName}")
    UploadedFile getFileByName(String fileName, int userId);

    @Select("Select * from FILES where userid = #{username}")
    List<UploadedFile> getFilesByUserId(int userid);

    @Insert("Insert into FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int uploadFileById(UploadedFile file);

    @Delete("Delete from FILES where fileId = #{fileId}")
    int deleteFileById(int fileId);
}
