package group.blog.service.imp;

import group.blog.dao.UserDAO;
import group.blog.entity.User;
import group.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Chen
 **/
@Service
public class DefaultLoginService implements LoginService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public String login(String userName, String password) {
        User result = userDAO.queryByName(userName);
        return null;
    }
}
