package jmu.lx.lab.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统管理员
*/
@EqualsAndHashCode(callSuper = true)
/*
自动生成 equals() 和 hashCode() 方法,指定 callSuper = true 时，生成的 equals() 和 hashCode() 方法会包含对父类的 equals() 和 hashCode() 方法的调用。
**/
@Data
public class Admin extends Account implements Serializable {
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

}