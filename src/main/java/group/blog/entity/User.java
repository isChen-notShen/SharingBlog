package group.blog.entity;

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
}
