package group.blog.service;

import group.blog.service.result.TokenResult;

/**
 * @author Mr.Chen
 **/
public interface UserService {

    TokenResult login(String userName, String password);

    byte[] getPortrait(String userName);

}