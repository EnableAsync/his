package com.rai.his.controller;

import com.rai.his.domain.Doctor;
import com.rai.his.domain.ProcedureResult;
import com.rai.his.domain.RegisterRecord;
import com.rai.his.mapper.RegistrarMapper;
import com.rai.his.util.ResultUtil;
import com.rai.his.vo.CodeEnum;
import com.rai.his.vo.DocVO;
import com.rai.his.vo.DoctorVO;
import com.rai.his.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RegistrarController {
    @Autowired
    private RegistrarMapper registrarMapper;

    @PostMapping("/register")
    ResponseEntity<Result> register(@RequestBody RegisterRecord record) {
        System.out.println(record);
        ProcedureResult result = registrarMapper.register(record);
        System.out.println(result);
        return new ResponseEntity<>(
                new Result(CodeEnum.valueOf(result.getCode()), result.getMsg(), ""),
                HttpStatus.OK
        );
    }

    @GetMapping("/register")
    ResponseEntity<Result> getRegisterRecords() {
        return new ResponseEntity<>(
                ResultUtil.success(registrarMapper.getRegisterRecords()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/register/{id}")
    ResponseEntity<Result> delRecord(@PathVariable("id") int id) {
        registrarMapper.delRecord(id);
        return new ResponseEntity<>(
                ResultUtil.success(""),
                HttpStatus.OK
        );
    }

    @GetMapping("/doctors")
    ResponseEntity<Result> getDoctors() {
        List<Doctor> doctors = registrarMapper.getDoctors();
        Map<Integer, DoctorVO> doctorVOMap = new HashMap<>();
        for (Doctor d : doctors) {
            if (doctorVOMap.containsKey(d.getDept_id())) {
                doctorVOMap.get(d.getDept_id()).getDoctors().add(DocVO.builder().id(d.getId()).name(d.getName()).build());
            } else {
                List<DocVO> docVOS = new ArrayList<>();
                docVOS.add(DocVO.builder().id(d.getId()).name(d.getName()).build());
                doctorVOMap.put(d.getDept_id(),
                        DoctorVO.builder()
                                .dept_id(d.getDept_id())
                                .dept_name(d.getDept_name())
                                .doctors(docVOS)
                                .build()
                );
            }
        }
        return new ResponseEntity<>(
                ResultUtil.success(doctorVOMap.values()),
                HttpStatus.OK
        );
    }
}
