package org.pragadeesh.ecommerce.controller;

import org.pragadeesh.ecommerce.model.Cart;
import org.pragadeesh.ecommerce.model.CartItems;
import org.pragadeesh.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable UUID userId) {
        Cart cart = cartService.createCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItems> addItemsToCart(@PathVariable UUID cartId,
                                                    @RequestParam UUID productId,
                                                    @RequestParam int quantity) {
        CartItems cartItems = cartService.addItemToCart(cartId, productId, quantity);
        return new ResponseEntity<>(cartItems, HttpStatus.CREATED);
    }

    @PutMapping("/items/{cartItemid}")
    public ResponseEntity<CartItems> updateCartItem(@PathVariable UUID cartItemid,
                                                    @RequestParam int quantity) {
        CartItems updatedCartItems = cartService.updateCartItem(cartItemid, quantity);
        return new ResponseEntity<>(updatedCartItems, HttpStatus.OK);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable UUID cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItems>> getCartItems(@PathVariable UUID cartId) {
        List<CartItems> cartItems = cartService.getCartItems(cartId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
}
