package group.blog.service.result;

import group.blog.service.result.code.ResponseCode;

/**
 * @author Mr.Chen
 **/
public class UserInformationResult extends BaseResult {

    private String userId;

    private String userName;

    private String displayName;

    private String description;

    public UserInformationResult(int code, String info) {
        super(code, info);
    }

    public UserInformationResult(ResponseCode code, String info) {
        super(code, info);
    }

    public UserInformationResult(int code, String info, String userId, String userName, String displayName, String description) {
        super(code, info);
        this.userId = userId;
        this.userName = userName;
        this.displayName = displayName;
        this.description = description;
    }

    public UserInformationResult(ResponseCode code, String info, String userId, String userName, String displayName, String description) {
        super(code, info);
        this.userId = userId;
        this.userName = userName;
        this.displayName = displayName;
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
