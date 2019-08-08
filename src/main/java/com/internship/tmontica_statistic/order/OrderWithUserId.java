package com.internship.tmontica_statistic.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class OrderWithUserId {

    private int realPrice;
    private String userId;
    private String status;

    public boolean isRealSales(){

        return status.equals(OrderStatusType.PICK_UP.getStatus());
    }
}
