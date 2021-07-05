package group.blog.web;

import group.blog.service.result.BlogContentResult;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.Chen
 **/
public class BlogHandler {

    public BlogContentResult getBlogContent(@RequestParam(required = false) String categoryId){
        return null;
    }
}