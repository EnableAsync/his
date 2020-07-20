package com.rai.his.mapper;

import com.rai.his.domain.ProcedureResult;
import com.rai.his.vo.DrugRoomVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRoomMapper {
    @Select("select distinct medical_id from prescription_details left join prescriptions on prescriptions.id = prescription_id where pay = 1 and send = 0 and prescription_details.removed = 0 order by prescription_details.id desc")
    List<DrugRoomVO> getNeedSend();

    @Select("{call send_drugs(#{medical_id,mode=IN})}")
    ProcedureResult sendDrugs(DrugRoomVO drugRoomVO);
}
