package com.rai.his.util;

import com.rai.his.vo.CodeEnum;
import com.rai.his.vo.Result;

public class ResultUtil {
    public static Result success(Object data) {
        return new Result(CodeEnum.SUCCESS,"success", data);
    }
}
