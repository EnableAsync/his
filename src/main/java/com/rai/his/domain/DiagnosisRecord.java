package com.rai.his.domain;

import lombok.Data;

@Data
public class DiagnosisRecord {
    private int reg_id;

    private String pres;

    private String history_disease;

    private String allergy_history;

    private int disease_id;
}
