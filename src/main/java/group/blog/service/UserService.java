package group.blog.service;

import group.blog.service.result.LoginResult;

/**
 * @author Mr.Chen
 **/
public interface UserService {

    LoginResult login(String userName, String password);
}
