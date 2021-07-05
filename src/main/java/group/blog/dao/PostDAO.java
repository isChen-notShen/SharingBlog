package group.blog.dao;

import group.blog.entity.Post;

import java.util.List;

public interface PostDAO {

    Post queryPostById(int id);

    List<Post> queryPostsByName(String name);

    int insertPost(Post post);

    int deletePostById(int id);

}
