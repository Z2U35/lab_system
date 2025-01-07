package jmu.lx.lab.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 学期
*/
@Data
public class Semester implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 学期 **/
    private String name;
    /** 周数 **/
    private Integer weeks;
    /** 是否为当前学期 **/
    private Integer isCurSemester;

}