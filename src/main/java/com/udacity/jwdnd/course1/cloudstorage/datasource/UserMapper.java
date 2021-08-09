package com.udacity.jwdnd.course1.cloudstorage.datasource;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Brian Smith on 8/4/21.
 * Description: Map user to SQl table data
 */
@Mapper
public interface UserMapper {

    @Select("Select * from Users where username = #{username}")
    User getUser(String username);

    @Insert("Insert into USERS (username, salt, password, firstname, lastname) " +
            "values(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);
}
