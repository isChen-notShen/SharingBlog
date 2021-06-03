package group.blog.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.blog.service.result.BaseResult;
import group.blog.service.result.code.ResponseCode;
import group.blog.util.token.exception.IllegalTokenException;
import group.blog.util.token.manager.RedisTokenManager;
import group.blog.util.token.marker.UserStatusAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 在访问特定资源时，进行登录检查
 * @author Mr.Chen
 **/
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    private RedisTokenManager tokenManager;

    public RedisTokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(RedisTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authentication");
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            try {
                PrintWriter writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(new BaseResult(ResponseCode.Not_Accessible,"需要登录")));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        if (!token.matches("^Bearer ")){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            try {
                PrintWriter writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(new BaseResult(ResponseCode.Request_Syntax_Error,"Authentication字段不符合要求")));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        String realToken = token.substring(token.indexOf(" ") + 1);
        if (tokenManager.checkToken(realToken)){
            if (tokenManager instanceof UserStatusAware){
                try {
                    if (request.getRemoteAddr() != null && request.getRemoteAddr().equals(((UserStatusAware) tokenManager).getUserLastLoginHost(token))){
                        request.setAttribute("user_id",((UserStatusAware) tokenManager).getUserId(token));
                        request.setAttribute("user_name",((UserStatusAware) tokenManager).getUserName(token));
                        request.setAttribute("user_role",((UserStatusAware) tokenManager).getUserRole(token));
                        request.setAttribute("user_last_login_host",((UserStatusAware) tokenManager).getUserLastLoginHost(token));
                        return true;
                    }
                }catch (IllegalTokenException e){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
