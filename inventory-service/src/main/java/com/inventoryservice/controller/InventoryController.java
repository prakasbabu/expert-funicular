package com.inventoryservice.controller;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.service.iface.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List <InventoryResponse> isInStock( @RequestParam(name ="skuCode") List <String> skuCode) throws InterruptedException {
       return inventoryService.isInStock(skuCode);
    }

}
