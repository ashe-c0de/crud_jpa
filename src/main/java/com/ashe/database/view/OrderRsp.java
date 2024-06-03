package com.ashe.database.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class OrderRsp {
    private Long orderId;
    private LocalDate orderDate;
    private Integer totalAmount;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
