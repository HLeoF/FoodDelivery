package hou.Application.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分类
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //菜品类型
    private Integer type;

    //分类名字
    private String name;

    //顺寻
    private Integer sort;

    //create time
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //update time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //create user
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    //update user
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    //detect dishes whether deleted
    //private Integer isDeleted;
}
