package group.blog.util.token.manager;

import group.blog.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于Service层的TokenManager
 */
public abstract class TokenManager {

    public abstract String createToken(User user);

    public abstract boolean checkToken(String token);

    public abstract boolean clearToken(String token);

}