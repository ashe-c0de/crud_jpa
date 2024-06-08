package com.ashe.database.view.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCustomerReq {

    private String customerName;
    private String address;

}
