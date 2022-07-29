package hou.Application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hou.Application.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//增删改查都已经再BaseMapper里
public interface EmployeeMapper extends BaseMapper<Employee> {}
