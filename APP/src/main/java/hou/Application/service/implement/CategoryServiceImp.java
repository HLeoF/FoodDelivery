package hou.Application.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hou.Application.common.CustomException;
import hou.Application.common.R;
import hou.Application.entity.Category;
import hou.Application.entity.Dish;
import hou.Application.entity.Setmeal;
import hou.Application.mapper.CategoryMapper;
import hou.Application.service.CategoryService;
import hou.Application.service.DishService;
import hou.Application.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  CategoryServiceImp extends ServiceImpl<CategoryMapper, Category> implements CategoryService  {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;


    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        //当前菜品分类是否关联菜品，如果已经关联，抛出一个异常
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getCategoryId,id);//检查这个分类ID下，一个有多少个关联
        int count = dishService.count(wrapper);

        if(count > 0){
            //已经关联菜品
           throw new CustomException("无法删除，该品类已关联菜品");
        }

        //当前菜品分类是否关联套餐，如果已经关联，抛出一个异常
        LambdaQueryWrapper<Setmeal> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(wrapper1);
        if(count2 > 0){
            //已经关联套餐
            throw new CustomException("无法删除，该品类已经关联套餐");
        }
        //正常删除分类
        super.removeById(id);
    }
}
