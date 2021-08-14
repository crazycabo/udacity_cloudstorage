package com.udacity.jwdnd.course1.cloudstorage.datasource;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Brian Smith on 8/12/21.
 * Description:
 */
@Mapper
public interface CredentialMapper {

    @Select("Select * from CREDENTIALS where credentialid = #{credentialId}")
    Credential getCredentialById(int credentialId);

    @Select("Select * from CREDENTIALS where userid = #{userId}")
    List<Credential> getCredentialByUserId(int userId);

    @Insert("Insert into CREDENTIALS (url, username, password, key, userId) " +
    "Values (#{url}, #{username}, #{password}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredentialById(Credential credential);

    @Update("Update CREDENTIALS set url=#{url}, username=#{username}, password=#{password} " +
    "key=#{key}, credentialid=#{credentialId}")
    int updateCredentialById(Credential credential);

    @Delete("Delete from CREDENTIALS where credentialId = #{credentialId} and userid = #{userId}")
    int deleteCredentialById(int credentialId, int userId);
}
