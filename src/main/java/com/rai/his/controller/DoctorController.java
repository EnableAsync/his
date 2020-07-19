package com.rai.his.controller;

import com.rai.his.domain.User;
import com.rai.his.mapper.DoctorMapper;
import com.rai.his.util.ResultUtil;
import com.rai.his.vo.ResponseEntityUtil;
import com.rai.his.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DoctorController {
    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping("/disease")
    ResponseEntity<Result> getDisease() {
        return new ResponseEntity<>(
                ResultUtil.success(doctorMapper.getDisease()),
                HttpStatus.OK
        );
    }

    @GetMapping("/diagnose")
    ResponseEntity<Result> getNeedDiagnose(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        return ResponseEntityUtil.success(doctorMapper.getNeedDiagnose(u.getId()));
    }

    @GetMapping("/diagnosed")
    ResponseEntity<Result> getDiagnosed(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        return ResponseEntityUtil.success(doctorMapper.getDiagnosed(u.getId()));
    }


}