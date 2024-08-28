package org.pragadeesh.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(name = "product_id", nullable = false)
    private UUID product_id;

    @Column(nullable = false)
    private Integer quantity;

    public CartItems(Cart cart, UUID productId, int quantity) {
        this.cart = cart;
        this.product_id = productId;
        this.quantity = quantity;
    }
}
