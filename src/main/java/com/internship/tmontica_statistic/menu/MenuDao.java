package com.internship.tmontica_statistic.menu;

import com.internship.tmontica_statistic.menu.vo.MenuIdName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuDao {

    @Select("SELECT id, name_ko FROM menus")
    List<MenuIdName> getAllMenuIdAndName();
}
