package group.blog.web;

import group.blog.service.UserService;
import group.blog.service.result.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mr.Chen
 **/
@Controller
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResult login(@RequestParam("user-name") String name, @RequestParam("password") String password) {
        return userService.login(name, password);
    }
}
