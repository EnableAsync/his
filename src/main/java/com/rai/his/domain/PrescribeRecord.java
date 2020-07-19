package com.rai.his.domain;

import lombok.Data;

@Data
public class PrescribeRecord {
    private int reg_id;

    private int doctor_id;

    private String pres;

    private String drugs;

    private String drugs_count;

    private int all_drugs_count;
}
