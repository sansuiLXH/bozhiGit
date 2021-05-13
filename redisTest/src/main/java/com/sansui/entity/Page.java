package com.sansui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 12:57
 * @modified By  西西里_SanSui in 2021/5/12 12:57
 * @description AddDescriptionHere
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Page  implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Student> list;

    //总数量
    private int count;
    //当前页
    private int showpage;
    //一页多少条
    private int pagesize ;
    //总页数
    private int pagenum;


}
