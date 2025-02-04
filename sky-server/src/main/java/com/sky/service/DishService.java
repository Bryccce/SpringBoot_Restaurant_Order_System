package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;


public interface DishService {
    public void saveWithFlavor(DishDTO dto);

    PageResult pageQuery(DishPageQueryDTO dto);
}
