package com.rai.his.vo;

import com.rai.his.util.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtil {
    public static ResponseEntity<Result> success(Object data) {
        return new ResponseEntity<>(
                ResultUtil.success(data),
                HttpStatus.OK
        );
    }
}
