package hou.Application.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hou.Application.dto.DishDto;
import hou.Application.entity.Dish;
import hou.Application.entity.DishFlavor;
import hou.Application.mapper.DishMapper;
import hou.Application.service.DishFlavorService;
import hou.Application.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DishServiceImp extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     * 新增菜品，同时保存口味数据
     * @param dishDto
     */
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到dish 表中
        this.save(dishDto);

        Long dishId = dishDto.getId();//菜品的ID
        List<DishFlavor> flavors = dishDto.getFlavors();
        //将flavor表里的ID赋值，使用stream 流
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //保存flavor数据到dish_flavor表中
        //saveBatch 是批量保存
        dishFlavorService.saveBatch(flavors);
    }
}
