package com.rai.his.vo;

import lombok.Data;

import java.util.List;

@Data
public class PrescribeRecordVO {
    private int reg_id;
    private String pres;
    private List<SelectedDrugVO> drugs;
    private int all_drugs_count;
}
