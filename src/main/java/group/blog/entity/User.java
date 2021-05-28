package group.blog.entity;

import java.util.List;

/**
 * 用户实体，对应数据库的User表
 *
 * @author Mr.Chen
 **/
public class User {

    int id;

    String name;

    String password;

    String description;

    List<String> watch;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getWatch() {
        return watch;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWatch(List<String> watch) {
        this.watch = watch;
    }
}
