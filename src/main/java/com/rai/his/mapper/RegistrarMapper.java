package com.rai.his.mapper;

import com.rai.his.domain.Doctor;
import com.rai.his.domain.ProcedureResult;
import com.rai.his.domain.RegisterRecord;
import com.rai.his.vo.RegisterRecordVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrarMapper {
    @Select(
            "{call register(#{doc,mode=IN}, #{name,mode=IN}," +
                    "#{genders,mode=IN}, #{birth,mode=IN}," +
                    "#{id_number,mode=IN}, #{address,mode=IN},"+
                    "#{register_type,mode=IN}, #{dept,mode=IN})}"
    )
    @Options(statementType= StatementType.CALLABLE)
    ProcedureResult register(RegisterRecord record);

    @Select("select id, medical_id, name, genders, birthday from registers where removed = 0 and status = 0 order by id desc")
    List<RegisterRecordVO> getRegisterRecords();

    @Update("update registers set removed = 1 where id = #{id}")
    void delRecord(int id);

    @Select("select doctors.id,name,dept_id,dept_name from doctors left join departments as d on " +
            "doctors.dept_id = d.id where doctors.removed = 0")
    List<Doctor> getDoctors();
}
