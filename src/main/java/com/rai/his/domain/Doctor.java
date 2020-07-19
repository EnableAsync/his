package com.rai.his.domain;

import lombok.Data;

@Data
public class Doctor {
    private int id;
    private String name;
    private int dept_id;
    private String dept_name;
}
