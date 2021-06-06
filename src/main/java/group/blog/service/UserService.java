package group.blog.service;

import group.blog.entity.Portrait;
import group.blog.service.result.TokenResult;

/**
 * @author Mr.Chen
 **/
public interface UserService {

    TokenResult login(String userName, String password);

    Portrait getPortraitByUserId(int userId);

}