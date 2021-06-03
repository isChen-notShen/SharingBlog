package group.blog.util.token.manager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import group.blog.entity.User;
import group.blog.util.token.exception.IllegalTokenException;
import group.blog.util.token.marker.UserStatusAware;
import group.blog.permission.Role;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于JWT规范的Token验证机制
 *
 * @author Mr.Chen
 **/
public class JWTManager extends TokenManager implements UserStatusAware {

    private static final String secret = "Web Application's Token secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);

    //默认单位为日
    private int interval = 7;

    private final JWTVerifier verifier = JWT.require(algorithm).withIssuer("SharingBlog").build();

    private final TokenBlacklist blacklist = new TokenBlacklist();

    /**
     * 在使用JWTManager的createToken时，要求已经验证了用户身份，且更改了user对象的最近登录地址
     *
     * @param user 从DAO层获取到的User对象，但该对象的lastLoginHost属性已经更改
     * @return token
     */
    @Override
    public String createToken(User user) {
        return createToken(user, interval);
    }

    public String createToken(User user, int interval) {
        blacklist.remove(user.getName());
        Calendar expiredTime = Calendar.getInstance();
        expiredTime.add(Calendar.DAY_OF_YEAR, interval);
        return JWT.create().withIssuer("SharingBlog")
                .withAudience(user.getLastLoginHost())
                .withExpiresAt(expiredTime.getTime())
                .withSubject(user.getRole().toString())
                .withClaim("user_id", user.getId())
                .withClaim("user_name", user.getName())
                .sign(algorithm);
    }

    /**
     * 验证该token是该token管理器签发的
     *
     * @param token 待检测的token
     * @return 是否合法的token
     */
    @Override
    public boolean checkToken(String token) {
        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return !blacklist.inBlacklist(token);
    }

    @Override
    public boolean clearToken(String token) {
        try{
            blacklist.add(getUserName(token),token);
        }catch (IllegalTokenException e){
          return false;
        }
        return true;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }

    @Override
    public String getUserId(String token) throws IllegalTokenException {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("user_id").asString();
        } catch (JWTVerificationException e) {
            throw new IllegalTokenException("Token验证未通过，可能该Token不是JWTManager签发的。", e);
        }
    }

    @Override
    public String getUserName(String token) throws IllegalTokenException {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("user_name").asString();
        } catch (JWTVerificationException e) {
            throw new IllegalTokenException("Token验证未通过，可能该Token不是JWTManager签发的。", e);
        }
    }

    @Override
    public String getUserLastLoginHost(String token) throws IllegalTokenException {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getAudience().get(0);
        } catch (JWTVerificationException e) {
            throw new IllegalTokenException("Token验证未通过，可能该Token不是JWTManager签发的。", e);
        }
    }

    @Override
    public Role getUserRole(String token) throws IllegalTokenException {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return Role.valueOf(jwt.getSubject());
        } catch (JWTVerificationException e) {
            throw new IllegalTokenException("Token验证未通过，可能该Token不是JWTManager签发的。", e);
        }
    }

    /**
     * Token的黑名单，当Token失效后，Token会被加入该黑名单，直至下次登录
     */
    public static class TokenBlacklist {

        private final Map<String, String> map = new ConcurrentHashMap<>();

        public boolean inBlacklist(String token) {
            return map.containsValue(token);
        }

        public void add(String userName, String token) {
            map.put(userName, token);
        }

        public void remove(String userName) {
            map.remove(userName);
        }

        public void clear() {
            map.clear();
        }
    }
}
