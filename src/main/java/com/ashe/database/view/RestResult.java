package com.ashe.database.view;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class RestResult {

    private Integer code;
    private String msg;
    private Object data;

    public static RestResult ok() {
        return new RestResult(HttpStatus.OK.value(), HttpStatus.OK.name(), "");
    }

    public static RestResult ok(Object data) {
        return new RestResult(HttpStatus.OK.value(), HttpStatus.OK.name(), data);
    }

    public static RestResult err(Object data) {
        return new RestResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "err", data);
    }

    public RestResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}