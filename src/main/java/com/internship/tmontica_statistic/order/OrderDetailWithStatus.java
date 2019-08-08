package com.internship.tmontica_statistic.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class OrderDetailWithStatus {

     private int menuId;
     private int price;
     private int quantity;
     private String status;

    public boolean isRealSales(){

        return status.equals(OrderStatusType.PICK_UP.getStatus());
    }

    public int getTotalPrice(){

        return price * quantity;
    }
}
