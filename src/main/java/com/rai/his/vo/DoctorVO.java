package com.rai.his.vo;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DoctorVO {
    private int dept_id;
    private String dept_name;
    private List<DocVO> doctors;
}

