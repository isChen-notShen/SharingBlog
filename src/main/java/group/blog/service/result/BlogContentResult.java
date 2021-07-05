package group.blog.service.result;

import group.blog.service.result.code.ResponseCode;

/**
 * @author Mr.Chen
 **/
public class BlogContentResult extends BaseResult {

    public BlogContentResult(int code, String info) {
        super(code, info);
    }

    public BlogContentResult(ResponseCode code, String info) {
        super(code, info);
    }
}
