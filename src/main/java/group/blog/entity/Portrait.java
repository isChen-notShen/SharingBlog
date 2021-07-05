package group.blog.entity;

import java.io.Serializable;

/**
 * @author Mr.Chen
 **/
public class Portrait implements Serializable {

    private static final long serialVersionUID = -5573969212525841744L;

    private String name;

    private byte[] data;

    private String type;

    private long size;

    public Portrait() {
    }

    public Portrait(String name, byte[] data, String type) {
        this.name = name;
        this.data = data;
        this.type = type;
        this.size = data.length;
    }

    public Portrait(String name, byte[] data, String type, long size) {
        this.name = name;
        this.data = data;
        this.type = type;
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
