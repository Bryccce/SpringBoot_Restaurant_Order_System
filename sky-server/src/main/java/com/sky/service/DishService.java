package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;


public interface DishService {
    public void saveWithFlavor(DishDTO dto);

    PageResult pageQuery(DishPageQueryDTO dto);

    void deleteBatch(List<Long> ids);

    DishVO getByIdWithFlavor(Long id);

    void updateWIthFlavor(DishDTO dishDTO);

    List<Dish> list(Long categoryId);
    List<DishVO> listWithFlavor(Dish dish);

}