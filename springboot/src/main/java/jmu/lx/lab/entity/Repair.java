package jmu.lx.lab.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备报修表
*/
@Data
public class Repair implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 实验室id **/
    private Integer labId;
    /** 设备名称 **/
    private String equipmentName;
    /** 故障说明 **/
    private String description;
    /** 教师id **/
    private Integer teacherId;
    /** 报修日期 **/
    private String applyDate;
    /** 报修状态 **/
    private String status;
    /** 报修完情况说明 **/
    private String endRepair;

    private Integer labNumber;
    private String teacherName;
    private Integer labadminId;
    private String labadminName;

}