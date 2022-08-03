package hou.Application.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hou.Application.entity.Setmeal;
import hou.Application.mapper.SetmealMapper;
import hou.Application.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SetmealImp extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {}
