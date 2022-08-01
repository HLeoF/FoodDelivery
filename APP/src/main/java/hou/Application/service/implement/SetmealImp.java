package hou.Application.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hou.Application.entity.Setmeal;
import hou.Application.mapper.SetmealMapper;
import hou.Application.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealImp extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {}
