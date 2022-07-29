package hou.Application.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import sun.util.resources.LocaleData;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工的实体类
 */

@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String name;
    private String password;
    private String phone;
    private String sex;
    private String idNumber; //使用驼峰命名法，驼峰命名法已在application.yml中开启
    private Integer status;
    private LocalDateTime createTime; //驼峰命名法
    private LocalDateTime updateTime; //驼峰命名法

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT)
    private Long updateUser;
}