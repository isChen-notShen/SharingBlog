package group.blog.dao;

import group.blog.entity.User;

public interface PortraitDao {

    byte[] queryPortrait(User user);

    int insertPortrait(User user, byte[] image, String type);

    int updatePortrait(User user, byte[] image, String type);

    int deletePortrait(User user);

    String getType(User user);
}