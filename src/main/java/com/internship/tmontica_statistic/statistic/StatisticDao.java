package com.internship.tmontica_statistic.statistic;

import com.internship.tmontica_statistic.statistic.vo.OrderWithUserAgentData;
import com.internship.tmontica_statistic.statistic.vo.SalesWithAgeGroupData;
import com.internship.tmontica_statistic.statistic.vo.SalesWithMenuData;
import org.apache.ibatis.annotations.Insert;

public interface StatisticDao {

    @Insert("INSERT INTO sales_agegroup_data (age_group , total_price) " +
            "VALUES(#{ageGroup}, #{totalPrice})")
    int saveSalesAgeGroupData(SalesWithAgeGroupData salesWithAgeGroupData);
    @Insert("INSERT INTO sales_menu_data (menu_id , total_price, menu_name) " +
            "VALUES(#{menuId}, #{totalPrice}, #{menuName})")
    int saveSalesMenuData(SalesWithMenuData salesWithMenuData);
    @Insert("INSERT INTO order_useragent_data (user_agent, count) " +
            "VALUES(#{userAgent}, #{count})")
    int saveOrderUserAgnetData(OrderWithUserAgentData orderWithUserAgentData);


}
