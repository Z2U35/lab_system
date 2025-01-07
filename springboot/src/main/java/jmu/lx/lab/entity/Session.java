package jmu.lx.lab.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 节次
*/
@Data
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 节次名称 **/
    private String name;

}