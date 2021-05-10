package group.blog.service;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.Chen
 **/
public interface LoginService {

    String login(String userName, String password);
}
