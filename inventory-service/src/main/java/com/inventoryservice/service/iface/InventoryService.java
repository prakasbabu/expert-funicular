package com.inventoryservice.service.iface;

import com.inventoryservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InventoryService {
    List <InventoryResponse> isInStock(List<String> skuCode);

}
