package group.blog.dao;

import group.blog.entity.User;

public interface UserDAO {

    User queryById(int id);

    User queryByName(String name);

}