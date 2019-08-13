package com.internship.tmontica_statistic.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("SELECT id, name, email, birth_date, password, role, created_date, point, is_active, activate_code FROM users")
    List<User> getAllUser();
}
