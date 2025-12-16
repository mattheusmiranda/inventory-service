package com.miranda.inventory_service.controller;

import com.miranda.inventory_service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/reserve/{productId}")
    public ResponseEntity<Void> reserve(@PathVariable Integer productId) {
        service.reserve(productId);
        return ResponseEntity.ok().build();
    }
}
