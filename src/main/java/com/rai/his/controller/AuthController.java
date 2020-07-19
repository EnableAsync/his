package com.rai.his.controller;

import com.rai.his.domain.User;
import com.rai.his.mapper.UserMapper;
import com.rai.his.util.ResultUtil;
import com.rai.his.vo.CodeEnum;
import com.rai.his.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

@RestController
public class AuthController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    ResponseEntity<Result> login(@NotEmpty(message = "用户名不能为空") @RequestParam String username,
                                 @NotEmpty(message = "密码不能为空") @RequestParam String password,
                                 HttpServletRequest request
    ) {
        User u = userMapper.findByUsername(username);
        if (u == null) {
            return new ResponseEntity<>(
                    new Result(CodeEnum.USERNAME_PASSWORD_WRONG, "用户名或密码错误", ""),
                    HttpStatus.OK
            );
        }

        if (!password.equalsIgnoreCase(u.getPassword())) {
            return new ResponseEntity<>(
                    new Result(CodeEnum.USERNAME_PASSWORD_WRONG, "用户名或密码错误", ""),
                    HttpStatus.OK
            );
        }

        request.getSession().setAttribute("user", u);
        return new ResponseEntity<>(
                ResultUtil.success(""),
                HttpStatus.OK
        );
    }
}
