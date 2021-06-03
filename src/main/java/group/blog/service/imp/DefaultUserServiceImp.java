package group.blog.service.imp;

import group.blog.dao.PortraitDao;
import group.blog.dao.UserDAO;
import group.blog.entity.User;
import group.blog.service.UserService;
import group.blog.service.result.TokenResult;
import group.blog.service.result.code.ResponseCode;
import group.blog.util.token.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.Chen
 **/
@Service
public class DefaultUserServiceImp implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PortraitDao portraitDao;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public TokenResult login(String userName, String password) {
        User result = userDAO.queryUserByName(userName);
        if (result != null) {
            if (userName.equals(result.getName()) && password.equals(result.getPassword())) {
                String host = updateHostOnLogin(result.getId());
                if (host != null && !host.isEmpty()) {
                    result.setLastLoginHost(host);
                }
                //System.out.println("登录成功");
                return new TokenResult(ResponseCode.Request_Success, "success", tokenManager.createToken(result));
            }
        }
        //System.out.println("登录失败");
        return new TokenResult(ResponseCode.Resource_Not_Exist, "failure, account or password error", "");
    }

    @Override
    public byte[] getPortrait(String userName) {
        //TODO
        return new byte[0];
    }

    private String updateHostOnLogin(int id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String host = request.getRemoteAddr();
        if (userDAO.updateLastLoginHostById(id, host) == 1) {
            return host;
        }
        return null;
    }
}
