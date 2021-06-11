package group.blog.service;

import group.blog.entity.Portrait;
import group.blog.service.result.BaseResult;
import group.blog.service.result.TokenResult;
import group.blog.service.result.UserInformationResult;

/**
 * @author Mr.Chen
 **/
public interface UserService {

    TokenResult login(String userName, String password);

    Portrait getPortraitByUserId(int userId);

    BaseResult register(String userName, String password);

    BaseResult logout(String token);

    UserInformationResult getInformation(String userId);

}