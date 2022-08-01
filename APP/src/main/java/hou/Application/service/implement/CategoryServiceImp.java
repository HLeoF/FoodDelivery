package hou.Application.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hou.Application.entity.Category;
import hou.Application.mapper.CategoryMapper;
import hou.Application.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class  CategoryServiceImp extends ServiceImpl<CategoryMapper, Category> implements CategoryService  {}
