package jmu.lx.lab.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 借用表
 **/
@Data
public class Borrow  implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID **/
    private Integer id;
    /** 实验室id **/
    private Integer labId;
    /** 申请学期id **/
    private Integer semesterId;
    /** 申请周次 **/
    private Integer week;
    /** 申请星期 **/
    private String day;
    /** 申请节次id **/
    private Integer sessionId;
    /** 申请学生id **/
    private Integer studentId;
    /** 申请原因 **/
    private String reason;
    /** 申请日期 **/
    private String applyDate;
    /** 申请状态 **/
    private String status;

    private Integer labNumber;
    private String semesterName;
    private String studentName;
    private String sessionName;

}
