package com.food.menu.serviceImpl;

import com.food.menu.entity.MenuItem;
import com.food.menu.exception.MenuItemNotFoundException;
import com.food.menu.qrCodeGenerator.QRCodeService;
import com.food.menu.repository.MenuRepository;
import com.food.menu.service.MenuService;
import com.google.zxing.WriterException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private QRCodeService qrCodeService;

    @PostConstruct
    private void generateQRCodeForMenu() {
        String qrCodePath = "./qrcodes/all_menu_items.png";
        File qrCodeDirectory = new File("./qrcodes");
        if (!qrCodeDirectory.exists()) {
            qrCodeDirectory.mkdir();
        }
        try {
            qrCodeService.generateQRCode("https://458d-2409-40f0-28-ec80-5c5c-f66f-471d-b104.ngrok-free.app/index.html", qrCodePath);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR code: " + e.getMessage());
        }
    }

    @Override
    public MenuItem addMenuItem(MenuItem menuItemRequest) {
        try {
            MenuItem menuItem = new MenuItem();
            menuItem.setItemName(menuItemRequest.getItemName());
            menuItem.setPrice(menuItemRequest.getPrice());
            menuItem.setStatus(menuItemRequest.isStatus());
            return menuRepository.save(menuItem);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save menu item: " + e.getMessage());
        }
    }

    @Override
    public Page<MenuItem> getAllItems(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItemRequest) {
        try {
            MenuItem menuItem = menuRepository.findById(id)
                    .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found with id: " + id));
            menuItem.setItemName(menuItemRequest.getItemName());
            menuItem.setPrice(menuItemRequest.getPrice());
            menuItem.setStatus(menuItemRequest.isStatus());
            return menuRepository.save(menuItem);
        } catch (MenuItemNotFoundException ex) {
            throw ex;  // Re-throw the custom exception to be handled globally
        } catch (Exception e) {
            throw new RuntimeException("Failed to update menu item: " + e.getMessage());
        }
    }

    @Override
    public void deleteMenuItem(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new MenuItemNotFoundException("Menu item not found with id: " + id);
        }
        menuRepository.deleteById(id);
    }
}
