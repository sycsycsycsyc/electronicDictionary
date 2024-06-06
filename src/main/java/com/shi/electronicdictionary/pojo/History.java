package com.shi.electronicdictionary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class History {
    private String mail;
    private LocalDate date;
    private LocalTime time;
    private String option;
    private String project;

}
