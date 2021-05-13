package com.sansui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 9:32
 * @modified By  西西里_SanSui in 2021/5/12 9:32
 * @description AddDescriptionHere
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private static final long serialVersionUID = 1L;
    private String id ;
    private String name;
    private String birthday;
    private String description;
    private Double avgscore;
}
