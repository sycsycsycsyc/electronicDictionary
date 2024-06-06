package com.shi.electronicdictionary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dictionary {
    private String english;
    private String chinese;
    private String voice;
    private String flag;
    private String flag4;
    private String flag6;
    private String flagyan;

}
