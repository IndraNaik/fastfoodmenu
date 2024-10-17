package com.food.menu.controller;

import com.food.menu.entity.MenuItem;
import com.food.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItemRequest) {
        MenuItem newItem = menuService.addMenuItem(menuItemRequest);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @GetMapping
    public Page<MenuItem> getMenuItems(Pageable pageable) {
        return menuService.getAllItems(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItemRequest) {
        MenuItem updatedItem = menuService.updateMenuItem(id, menuItemRequest);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuService.deleteMenuItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
