package group.blog.util.token.marker;

import group.blog.util.token.exception.IllegalTokenException;
import group.blog.permission.Role;

public interface UserStatusAware {

    String getUserId(String token) throws IllegalTokenException;

    String getUserName(String token) throws IllegalTokenException;

    String getUserLastLoginHost(String token) throws IllegalTokenException;

    Role getUserRole(String token) throws IllegalTokenException;

}