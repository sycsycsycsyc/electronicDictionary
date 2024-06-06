package com.shi.electronicdictionary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyWord {
    private String mail;
    private String english;
    private String chinese;
    private String voice;
    private String description;
}
