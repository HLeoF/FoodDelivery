package hou.Application.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hou.Application.entity.Dish;
import hou.Application.mapper.DishMapper;
import hou.Application.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DishServiceImp extends ServiceImpl<DishMapper, Dish> implements DishService {}
