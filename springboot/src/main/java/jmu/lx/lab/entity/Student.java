package jmu.lx.lab.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 学生
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 姓名 */
    private String name;
    /** 电话 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 头像 */
    private String avatar;
    /** 角色标识 */
    private String role;
    /**专业**/
    private String speciality;
    /**班级**/
    private String clazz;

}