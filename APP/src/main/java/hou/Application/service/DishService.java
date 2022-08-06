package hou.Application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import hou.Application.dto.DishDto;
import hou.Application.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入口味数据，需要插入两张表， dish & dishFlavor
    public void saveWithFlavor(DishDto dishDto);
}
