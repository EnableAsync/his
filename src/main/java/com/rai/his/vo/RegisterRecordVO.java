package com.rai.his.vo;

import lombok.Data;

@Data
public class RegisterRecordVO {
    private int id;
    private int medical_id;
    private String name;
    private short genders;
    private String birthday;
}
