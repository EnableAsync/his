package com.rai.his.vo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CodeEnum {
    SUCCESS(0),
    USERNAME_PASSWORD_WRONG(10001),
    SERVER_ERROR(10002);

    @JsonValue
    private int code;

    private CodeEnum(int code) {
        this.code = code;
    }

    public static CodeEnum valueOf(int value) {
        for (CodeEnum code : values()) {
            if (code.getCode() == value) {
                return code;
            }
        }
        throw new RuntimeException("没有找到对应的状态码");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CodeEnum{" +
                "code=" + code +
                '}';
    }
}
