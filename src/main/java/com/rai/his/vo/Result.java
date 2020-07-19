package com.rai.his.vo;

public class Result {
    private CodeEnum code;

    private String msg;

    private Object data;

    public Result(CodeEnum code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        if (this.msg == null) {
            this.msg = "";
        }
        this.data = data;
    }

    public CodeEnum getCode() {
        return code;
    }

    public void setCode(CodeEnum code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
