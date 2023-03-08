package com.example.inventoryservice.controller;


import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    @Autowired
    private final InventoryService inventoryService;


    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam("skuCode") List<String> skuCode) {
        return ResponseEntity.status(200).body(inventoryService.isInStock(skuCode));
    }
}
