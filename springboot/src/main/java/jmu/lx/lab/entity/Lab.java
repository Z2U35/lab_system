package jmu.lx.lab.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 实验室
*/
@Data
public class Lab implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 实验室编号 **/
    private Integer number;
    /** 实验室名称 **/
    private String name;
    /** 实验室类型 **/
    private String type;
    /** 实验室设备树 **/
    private Integer equipmentCount;
    /** 实验员id **/
    private Integer labadminId;

    private String labadminName;

}