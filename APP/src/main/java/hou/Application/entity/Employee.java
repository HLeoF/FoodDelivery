package hou.Application.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
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

    //公共字段， 通过 FieldFill枚举制定状态
    @TableField(fill = FieldFill.INSERT)//公共字段的填充，因为时好几个tables里面都有这些元素
    private  LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)//公共字段的填充，因为时好几个tables里面都有这些元素
    private  LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT) //公共字段的填充，因为时好几个tables里面都有这些元素
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)//公共字段的填充，因为时好几个tables里面都有这些元素
    private Long updateUser;
}
