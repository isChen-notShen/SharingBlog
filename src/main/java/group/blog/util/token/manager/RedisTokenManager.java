package group.blog.util.token.manager;

import group.blog.entity.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.UUID;

/**
 * 使用redis缓存token的Token管理器
 *
 * @author Mr.Chen
 **/
public class RedisTokenManager extends TokenManager {

    private JedisPool pool;

    @Override
    public String createToken(User user) {
        UUID uuid = UUID.randomUUID();
        String token = user.getId() + "_" + uuid.toString().replaceAll("-", "");
        String key = user.getId() + "_token";
        Jedis jedis = pool.getResource();
        jedis.set(key, token, "NX", "EX", 180);
        jedis.close();
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        if (token == null || "".equals(token)) {
            return false;
        }

        String[] params = token.split("_");
        if (params.length != 2) {
            return false;
        }

        try (Jedis jedis = pool.getResource()) {
            String key = params[0] + "_token";
            String rightToken = jedis.get(key);
            if (rightToken == null) {
                return false;
            }
            if (!token.equals(rightToken)) {
                return false;
            }
            jedis.del(key);
            jedis.set(key, token, "NX", "EX", 180);
        }

        return true;
    }

    @Override
    public boolean clearToken(String token) {
        if (token == null || "".equals(token)) {
            return false;
        }

        String[] params = token.split("_");
        if (params.length != 2) {
            return false;
        }

        try (Jedis jedis = pool.getResource()) {
            String key = params[0] + "_token";

            if (!jedis.exists(key)) {
                return false;
            }

            jedis.del(key);
        }

        return true;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }
}
