package group.blog.web;

import group.blog.entity.Portrait;
import group.blog.service.UserService;
import group.blog.service.result.TokenResult;
import group.blog.util.mime.ImageMIMEConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;

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
    public TokenResult login(@RequestParam("user_name") String userName, @RequestParam("password") String password) {
        return userService.login(userName, password);
    }

    @RequestMapping(value = "/portrait")
    public void getPortrait(@RequestParam(value = "user_id") int userId, HttpServletResponse response) throws IOException {
        Portrait portrait = userService.getPortraitByUserId(userId);
        response.setContentType(ImageMIMEConverter.NameExtensionToMIME(portrait.getType()));
        OutputStream out = response.getOutputStream();
        out.write(portrait.getData());
        out.flush();
        out.close();
    }
}
