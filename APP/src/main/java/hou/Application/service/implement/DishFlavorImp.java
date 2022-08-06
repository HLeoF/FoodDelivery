package hou.Application.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hou.Application.entity.DishFlavor;
import hou.Application.mapper.DishFlavorMapper;
import hou.Application.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorImp extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {}
