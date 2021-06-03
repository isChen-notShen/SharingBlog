package group.blog.service.result;

import group.blog.service.result.code.ResponseCode;

import java.util.UUID;

/**
 * @author Mr.Chen
 **/
public class TokenResult extends BaseResult {
    private String token;

    public TokenResult(int code, String info, String token) {
        super(code, info);
        this.token = token;
    }

    public TokenResult(ResponseCode code, String info, String token){
        super(code,info);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
