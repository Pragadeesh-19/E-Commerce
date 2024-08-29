package org.pragadeesh.ecommerce.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDto {

    private UUID productId;
    private Integer quantity;
}
