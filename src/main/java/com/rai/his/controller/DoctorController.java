package com.rai.his.controller;

import com.rai.his.domain.DiagnosisRecord;
import com.rai.his.domain.PrescribeRecord;
import com.rai.his.domain.User;
import com.rai.his.mapper.DoctorMapper;
import com.rai.his.util.ResultUtil;
import com.rai.his.vo.PrescribeRecordVO;
import com.rai.his.vo.ResponseEntityUtil;
import com.rai.his.vo.Result;
import com.rai.his.vo.SelectedDrugVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return ResponseEntityUtil.success(doctorMapper.getNeedDiagnose(2));
    }

    @GetMapping("/diagnosed")
    ResponseEntity<Result> getDiagnosed(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        return ResponseEntityUtil.success(doctorMapper.getDiagnosed(2));
    }

    @PostMapping("/diagnose")
    ResponseEntity<Result> doDiagnose(@RequestBody DiagnosisRecord record) {
        return ResponseEntityUtil.success(doctorMapper.diagnosis(record));
    }

    @GetMapping("/drugs")
    ResponseEntity<Result> getDrugs() {
        return ResponseEntityUtil.success(doctorMapper.getDrugs());
    }

    @PostMapping("/prescribe")
    ResponseEntity<Result> doPrescribe(@RequestBody PrescribeRecordVO recordVO){
        PrescribeRecord record = new PrescribeRecord();
        record.setReg_id(recordVO.getReg_id());
        record.setDoctor_id(2);
        record.setPres(recordVO.getPres());
        StringBuilder drugs = new StringBuilder();
        StringBuilder count = new StringBuilder();
        int len = recordVO.getDrugs().size();
        for (int i = 0; i < len; i++) {
            SelectedDrugVO drugVO = recordVO.getDrugs().get(i);
            drugs.append(drugVO.getId());
            count.append(drugVO.getCount());
            if(i != len -1) {
                drugs.append(',');
                count.append(',');
            }
        }
        record.setDrugs(drugs.toString());
        record.setDrugs_count(count.toString());
        record.setAll_drugs_count(recordVO.getAll_drugs_count());
        return ResponseEntityUtil.success(doctorMapper.prescribe(record));
    }
}
