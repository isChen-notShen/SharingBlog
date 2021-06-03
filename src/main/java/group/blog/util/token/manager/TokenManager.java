package group.blog.util.token.manager;

import group.blog.entity.User;

public abstract class TokenManager {

    public abstract String createToken(User user);

    public abstract boolean checkToken(String token);

    public abstract boolean clearToken(String token);

}