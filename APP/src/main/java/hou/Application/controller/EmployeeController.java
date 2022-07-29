package hou.Application.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import hou.Application.common.R;
import hou.Application.entity.Employee;
import hou.Application.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    //@RequestBody 穿 Jason
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        //1. 将页面提交的密码进行md5进行加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的username去Database中查找
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper); //唯一的用户名，不能重复

        //3.如果没有查找到，返回登录失败的结果
        if(emp == null){
            return R.error("登录失败：Not Found Username");
        }

        //4.密码比对，不一样返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return  R.error("登录失败：密码错误");
        }

        //5.查看员工状态，如果已禁用状态，返回登录失败结果
        if(emp.getStatus() == 0){
            return R.error("登录失败：账号已禁用");
        }
        //6.登录成功，将员工的id存入session并返回登录成功
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }
}
