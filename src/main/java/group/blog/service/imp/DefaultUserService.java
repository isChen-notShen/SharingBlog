package group.blog.service.imp;

import group.blog.dao.UserDAO;
import group.blog.entity.User;
import group.blog.service.UserService;
import group.blog.service.result.LoginResult;
import group.blog.service.result.code.ResponseCode;
import group.blog.util.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Chen
 **/
@Service
public class DefaultUserService implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public LoginResult login(String userName, String password) {
        User result = userDAO.queryByName(userName);
        if (result != null) {
            if (userName.equals(result.getName()) && password.equals(result.getPassword())) {
                //System.out.println("登录成功");
                return new LoginResult(ResponseCode.Request_Success, tokenManager.createToken(result.getId()), "success");
            }
        }
        //System.out.println("登录失败");
        return new LoginResult(ResponseCode.Resources_Not_EXIST, "", "failure, account or password error");
    }
}
