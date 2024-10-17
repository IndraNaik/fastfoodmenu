package com.food.menu.repository;

import com.food.menu.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository extends JpaRepository<MenuItem, Long>  {
    @Query(value = "select * from menu_item where status=1", nativeQuery = true)
    Page findAll(Pageable pageable);
}
