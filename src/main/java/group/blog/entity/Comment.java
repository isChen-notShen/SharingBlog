package group.blog.entity;


public class Comment {

  private int id;

  private int userId;

  private java.sql.Timestamp release;

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


  public java.sql.Timestamp getRelease() {
    return release;
  }

  public void setRelease(java.sql.Timestamp release) {
    this.release = release;
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
