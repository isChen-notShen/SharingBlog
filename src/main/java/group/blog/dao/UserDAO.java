package group.blog.dao;

import group.blog.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {

    User queryUserById(int id);

    User queryUserByName(String name);

    int updateLastLoginHostById(@Param("id") int id,@Param("host") String host);
}