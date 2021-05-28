package group.blog.util.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @author Mr.Chen
 **/
@Component
public class TokenManager {

    @Autowired
    private JedisPool pool;


    public String createToken(int userId) {
        UUID uuid = UUID.randomUUID();
        String token = userId + "_" + uuid.toString().replaceAll("-", "");
        String key = userId + "_token";
        Jedis jedis = pool.getResource();
        jedis.set(key, token, "NX", "EX", 180);
        jedis.close();
        return token;
    }

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

}
