package group.blog.service.result.code;

/**
 * @author Mr.Chen
 **/
public enum ResponseCode {
    Request_Success(0),
    Not_Accessible(-1),
    Resource_Not_Exist(1),
    Login_Timeout(2),
    Request_Syntax_Error(3);

    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }
}
