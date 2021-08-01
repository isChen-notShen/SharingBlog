package group.blog.dao;

import group.blog.entity.Portrait;

@SuppressWarnings("MybatisXMapperMethodInspection")
public interface PortraitDAO {

    Portrait getPortraitByUserId(int userId);

    int insertPortrait(Portrait portrait);

    int updatePortraitByUserId(int userId, byte[] image, String type);

    int deletePortraitByUserId(int userId);

}