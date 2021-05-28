package group.blog.service.result;

import java.util.UUID;

/**
 * @author Mr.Chen
 **/
public class LoginResult {
    private int code;
    private String token;
    private String info;

    public LoginResult(){}

    public LoginResult(int code, String token, String info){
        this.code = code;
        this.token = token;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
