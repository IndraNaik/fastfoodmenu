package com.food.menu.service;

import com.food.menu.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MenuService {
    MenuItem addMenuItem(MenuItem menuItemRequest);

    Page<MenuItem> getAllItems(Pageable pageable);

    MenuItem updateMenuItem(Long id, MenuItem menuItemRequest);

    void deleteMenuItem(Long id);
}
