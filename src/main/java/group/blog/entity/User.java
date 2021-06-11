package group.blog.entity;

import group.blog.permission.Role;

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

    String displayName;

    String description;

    Role role;

    String lastLoginHost;

    List<User> watch;


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

    public List<User> getWatch() {
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

    public void setWatch(List<User> watch) {
        this.watch = watch;
    }

    public String getLastLoginHost() {
        return lastLoginHost;
    }

    public void setLastLoginHost(String lastLoginHost) {
        this.lastLoginHost = lastLoginHost;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
