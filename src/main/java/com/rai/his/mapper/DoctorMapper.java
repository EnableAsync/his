package com.rai.his.mapper;

import com.rai.his.domain.DiagnosisRecord;
import com.rai.his.vo.DiseaseVO;
import com.rai.his.domain.PrescribeRecord;
import com.rai.his.domain.ProcedureResult;
import com.rai.his.vo.DrugVO;
import com.rai.his.vo.RegisterRecordVO;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorMapper {
    @Select("select id, medical_id, name, genders, birthday from registers where removed = 0 and status = 0 and doctor_id = #{doctor_id} order by id desc")
    List<RegisterRecordVO> getNeedDiagnose(int doctor_id);

    @Select("select id, medical_id, name, genders, birthday from registers where removed = 0 and status = 1 and doctor_id = #{doctor_id} order by id desc")
    List<RegisterRecordVO> getDiagnosed(int doctor_id);

    @Select("select id, disease_name from diseases")
    List<DiseaseVO> getDisease();

    @Select("select id, drug_name from drugs")
    List<DrugVO> getDrugs();

    @Select(
            "{call diagnosis(#{reg_id,mode=IN}, #{pres,mode=IN}," +
                    "#{history_disease,mode=IN}, #{allergy_history,mode=IN}," +
                    "#{disease_id,mode=IN})}"
    )
    @Options(statementType = StatementType.CALLABLE)
    ProcedureResult diagnosis(DiagnosisRecord record);

    @Select(
            "{call prescribe(#{reg_id,mode=IN}, #{doctor_id,mode=IN}," +
                    "#{pres,mode=IN}, #{drugs,mode=IN}," +
                    "#{drugs_count,mode=IN}, #{all_drugs_count,mode=IN})}"
    )
    @Options(statementType = StatementType.CALLABLE)
    ProcedureResult prescribe(PrescribeRecord record);
}


