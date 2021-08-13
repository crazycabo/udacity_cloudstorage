package com.udacity.jwdnd.course1.cloudstorage.datasource;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Brian Smith on 8/12/21.
 * Description:
 */
@Mapper
public interface CredentialsMapper {

    @Select("Select * from CREDENTIALS where userid = #{userId} and credentialsId = #{credentialsId}")
    Note getCredentialsByName(int credentialsId, int userId);

    @Select("Select * from CREDENTIALS where userid = #{userId}")
    List<Credentials> getCredentialsByUserId(int userId);

    // todo: create new credentials

    @Delete("Delete from CREDENTIALS where credentialId = #{credentialId}")
    int deleteCredentialsById(int credentialId);
}
