package group.blog.web;

import group.blog.entity.Portrait;
import group.blog.service.UserService;
import group.blog.service.result.BaseResult;
import group.blog.service.result.TokenResult;
import group.blog.service.result.UserInformationResult;
import group.blog.service.result.code.ResponseCode;
import group.blog.util.converter.ImageMIMEConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Mr.Chen
 **/
@Controller
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public TokenResult login(@RequestParam("user_name") String userName, @RequestParam("password") String password) {
        return userService.login(userName, password);
    }

    @GetMapping("/portrait")
    public void getPortrait(@RequestParam(value = "user_id") int userId, HttpServletResponse response) throws IOException {
        Portrait portrait = userService.getPortraitByUserId(userId);
        response.setContentType(ImageMIMEConverter.NameExtensionToMIME(portrait.getType()));
        OutputStream out = response.getOutputStream();
        out.write(portrait.getData());
        out.flush();
        out.close();
    }

    @PostMapping("/register")
    @ResponseBody
    public BaseResult register(@RequestParam("user_name") String userName, @RequestParam("password") String password,HttpServletResponse response) {
        if (userName == null || userName.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new BaseResult(ResponseCode.Request_Failure, "用户名不能为空");
        } else if (password == null || password.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new BaseResult(ResponseCode.Request_Failure, "密码不能为空");
        } else {
            return userService.register(userName, password);
        }
    }

    @PostMapping("/logout")
    @ResponseBody
    public BaseResult logout(HttpServletRequest request, HttpServletResponse response){
        String tokenHeader = request.getHeader("Authentication");
        if (tokenHeader == null || tokenHeader.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return new BaseResult(ResponseCode.Not_Accessible, "可能未登录，Token不存在");
        }
        if (!tokenHeader.matches("^Bearer ")){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new BaseResult(ResponseCode.Request_Syntax_Error, "请求头Token格式无法识别");
        }
        String token = tokenHeader.substring(tokenHeader.indexOf(" ") + 1);
        return userService.logout(token);
    }

    @GetMapping("/information")
    @ResponseBody
    public UserInformationResult getInformation(@RequestParam("user_id") String userId, HttpServletResponse response){
        if (userId == null || userId.isEmpty()){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new UserInformationResult(ResponseCode.Request_Syntax_Error,"user_id不能未空");
        }
        return userService.getInformation(userId);
    }
}