package com.ashe.database.view;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class OrderReq extends Page {

    private Long customerId;

    private String customerName;

    private LocalDate orderDate;

    private Integer totalAmount;

    private String currency;

}
