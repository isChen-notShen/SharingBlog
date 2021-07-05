package group.blog.util.token.marker;

import group.blog.util.token.exception.IllegalTokenException;
import group.blog.permission.Role;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public interface UserStatusAware {

    String getUserId(String token) throws IllegalTokenException;

    String getUserName(String token) throws IllegalTokenException;

    String getUserLastLoginHost(String token) throws IllegalTokenException;

    Role getUserRole(String token) throws IllegalTokenException;

}