package hou.Application.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hou.Application.entity.Dish;
import hou.Application.mapper.DishMapper;
import hou.Application.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImp extends ServiceImpl<DishMapper, Dish> implements DishService {}
