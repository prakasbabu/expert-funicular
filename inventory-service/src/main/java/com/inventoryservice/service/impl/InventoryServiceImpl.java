package com.inventoryservice.service.impl;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.repository.InventoryRepository;
import com.inventoryservice.service.iface.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    //@Transactional(readOnly = true)
    public List <InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream().map(inventory ->
                     InventoryResponse.builder()
                          .skuCode(inventory.getSkuCode())
                          .isInStock(inventory.getQuantity() > 1000)
                          .build()
                ).collect(Collectors.toList());
    }
}
