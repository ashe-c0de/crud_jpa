package com.ashe.database.view;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Page {

    private int pageNumber = 1;
    private int pageSize = 10;

}
