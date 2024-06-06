package com.shi.electronicdictionary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String UUID;
    private String name;
    private String password;
    private String mail;
    private String library;
    private LocalTime time;
    private Integer flag;
    private int  count;
    private String flag2;
}
