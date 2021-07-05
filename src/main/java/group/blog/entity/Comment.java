package group.blog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述的是评论的信息
 *
 * @TableName comment
 */
public class Comment implements Serializable {
    /**
     * 评论id
     */
    private Integer id;

    /**
     * 评论者id
     */
    private Integer userId;

    /**
     * 评论发布时间
     */
    private Date release;

    /**
     * 点赞数
     */
    private Short like;

    /**
     * 评论内容
     */
    private String data;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public Short getLike() {
        return like;
    }

    public void setLike(Short like) {
        this.like = like;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getRelease() == null ? other.getRelease() == null : this.getRelease().equals(other.getRelease()))
                && (this.getLike() == null ? other.getLike() == null : this.getLike().equals(other.getLike()))
                && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRelease() == null) ? 0 : getRelease().hashCode());
        result = prime * result + ((getLike() == null) ? 0 : getLike().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", userId=" + userId +
                ", release=" + release +
                ", like=" + like +
                ", data=" + data +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}