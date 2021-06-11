package group.blog.service.imp;

import group.blog.dao.PortraitDao;
import group.blog.dao.UserDAO;
import group.blog.entity.Portrait;
import group.blog.entity.User;
import group.blog.service.UserService;
import group.blog.service.result.BaseResult;
import group.blog.service.result.TokenResult;
import group.blog.service.result.UserInformationResult;
import group.blog.service.result.code.ResponseCode;
import group.blog.util.token.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

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
                return new TokenResult(ResponseCode.Request_Success, "success", tokenManager.createToken(result));
            }
        }
        return new TokenResult(ResponseCode.Resource_Not_Exist, "failure, account or password error", "");
    }

    @Override
    public Portrait getPortraitByUserId(int userId) {
        return portraitDao.getPortraitByUserId(userId);
    }

    @Override
    public BaseResult register(String userName, String password) {
        if (userDAO.queryUserByName(userName) == null) {
            User newUser = new User();
            newUser.setName(userName);
            newUser.setPassword(password);
            newUser.setDisplayName(userName);
            if (userDAO.insertCommonUser(newUser) == 1) {
                return new BaseResult(ResponseCode.Request_Success, "注册成功");
            } else {
                return new BaseResult(ResponseCode.Request_Failure, "注册失败，请稍后重试");
            }
        }
        return new BaseResult(ResponseCode.Request_Failure, "用户名重复，请更换用户名");
    }

    @Override
    public BaseResult logout(String token) {
        if (!tokenManager.checkToken(token)) {
            return new BaseResult(ResponseCode.Request_Failure, "无效Token");
        }
        if (tokenManager.clearToken(token)) {
            return new BaseResult(ResponseCode.Request_Success, "注销成功");
        }
        return new BaseResult(ResponseCode.Request_Failure, "注销失败");
    }

    @Override
    public UserInformationResult getInformation(String userId) {
        assert userId != null;
        User user = userDAO.queryUserById(Integer.parseInt(userId));
        if (user == null) {
            return new UserInformationResult(ResponseCode.Resource_Not_Exist, "该用户不存在");
        }
        return new UserInformationResult(ResponseCode.Request_Success, "success", Integer.toString(user.getId()), user.getName(), user.getDisplayName(), user.getDescription());
    }

    private String updateHostOnLogin(int id) {
        HttpServletRequest request = getRequest();
        String host = request.getRemoteAddr();
        if (userDAO.updateLastLoginHostById(id, host) == 1) {
            return host;
        }
        return null;
    }

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    private static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }
}