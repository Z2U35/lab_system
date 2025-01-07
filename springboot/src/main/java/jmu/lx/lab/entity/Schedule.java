package jmu.lx.lab.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 排课表
*/
@Data
public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 学期id **/
    private Integer semesterId;
    /** 实验室类型 **/
    private String labType;
    /** 实验室id **/
    private Integer labId;
    /** 开始周 **/
    private Integer startWeek;
    /** 结束周 **/
    private Integer endWeek;
    /** 星期 **/
    private String week;
    /** 节次id **/
    private Integer sessionId;
    /** 课程名 **/
    private String courseName;
    /** 教师id **/
    private Integer teacherId;
    /** 上课班级 **/
    private String clazz;
    /** 上课人数 **/
    private Integer number;
    /** 排课状态 **/
    private String status;

    private String semesterName;
    private String labName;
    private Integer labNumber;
    private String teacherName;
    private String sessionName;

}