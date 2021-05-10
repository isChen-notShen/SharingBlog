package group.blog.entity;


public class Post {

  private int id;

  private int userId;

  private int categoryId;

  private String name;

  private short like;

  private String data;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }


  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public short getLike() {
    return like;
  }

  public void setLike(short like) {
    this.like = like;
  }


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

}
