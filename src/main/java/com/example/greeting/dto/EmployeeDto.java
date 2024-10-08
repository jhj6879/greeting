package com.example.greeting.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private String user_id;
    private String user_pw;
    private int employee_id;
    private String user_name;
    private String r_num;
    private String tel;
    private String address;
    private String email;
    private String gender;
    private String department;
    private String position;
    private String hire_date;
    private String resignation_date;
    private String employment;
    private int permit;

}
