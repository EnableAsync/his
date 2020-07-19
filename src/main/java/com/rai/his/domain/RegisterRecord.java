package com.rai.his.domain;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRecord {
    private int doc;

    private String name;

    private int genders;

    private String birth;

    private String id_number;

    private String address;

    private int register_type;

    private int dept;
}
