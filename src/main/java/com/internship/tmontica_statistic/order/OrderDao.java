package com.internship.tmontica_statistic.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDao {

    //** 현재로부터 N분전 상태가 변경된 주무**//
    @Select("SELECT real_price, user_id, OSL.status FROM orders O INNER JOIN order_status_logs OSL " +
            "ON O.id = OSL.order_id WHERE OSL.modified_date > NOW() - INTERVAL #{minute} MINUTE")
    List<OrderWithUserId> getOrderByDate(int minute);

    //** 현재로부터 N분전 상태가 변경된 주문 내역 가져오기**//
    @Select("SELECT menu_id, price, quantity, status FROM order_details OD INNER JOIN order_status_logs OSL "+
            "ON OD.order_id = OSL.order_id WHERE OSL.modified_date > NOW() - INTERVAL #{minute} MINUTE")
    List<OrderDetailWithStatus> getOrderDetailWithStatusByIntervalMinute(int minute);

    @Select("SELECT user_agent FROM orders WHERE order_date > NOW() - INTERVAL #{minute} MINUTE")
    List<OrderWithUserAgent> getUserAgentByIntervalMinute(int minute);
}
