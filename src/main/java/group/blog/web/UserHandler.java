package group.blog.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.blog.service.UserService;
import group.blog.service.result.BaseResult;
import group.blog.service.result.TokenResult;
import group.blog.service.result.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    public void getPortrait(@RequestParam(value = "user_name", required = false) String userName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        if (userName != null && !userName.isEmpty()) {
            printWriter.print(new String(userService.getPortrait(userName)));
        }
        else {
            String name = (String) request.getAttribute("user_name");
            if (name != null && !name.isEmpty()) {
                printWriter.print(new String(userService.getPortrait(name)));
            }else {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                ObjectMapper objectMapper = new ObjectMapper();
                printWriter.write(objectMapper.writeValueAsString(new BaseResult(ResponseCode.Request_Syntax_Error,"无token携带，无法获得登录信息。或者请求参数缺失")));
            }
        }
        printWriter.flush();
        printWriter.close();
    }
}
