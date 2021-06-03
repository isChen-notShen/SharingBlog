package group.blog.service.result;

import group.blog.service.result.code.ResponseCode;

import java.util.Calendar;

/**
 * 响应码的结果
 * @author Mr.Chen
 **/
public class BaseResult {

    private int code;

    private String info;

    public BaseResult(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public BaseResult(ResponseCode code, String info){
        this.code = code.value();
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
