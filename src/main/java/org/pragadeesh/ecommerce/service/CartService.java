package org.pragadeesh.ecommerce.service;

import org.pragadeesh.ecommerce.exception.ResourceNotFoundException;
import org.pragadeesh.ecommerce.model.Cart;
import org.pragadeesh.ecommerce.model.CartItems;
import org.pragadeesh.ecommerce.repository.CartItemRepository;
import org.pragadeesh.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    // creating a new cart
    public Cart createCart(UUID userId) {
        Cart cart = new Cart(userId, "ACTIVE");
        return cartRepository.save(cart);
    }

    // Add an item to the Cart
    public CartItems addItemToCart(UUID cartId, UUID productId, int quantity) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            CartItems cartItem = new CartItems(cart, productId, quantity);
            return cartItemRepository.save(cartItem);
        } else {
            throw new ResourceNotFoundException("Cart not found with id " + cartId);
        }
    }

    // update the quantity of cart
    public CartItems updateCartItem(UUID cartItemId, int quantity) {
        Optional<CartItems> cartItemsOptional = cartItemRepository.findById(cartItemId);
        if(cartItemsOptional.isPresent()) {
            CartItems cartItems = cartItemsOptional.get();
            cartItems.setQuantity(quantity);
            return cartItemRepository.save(cartItems);
        } else {
            throw new ResourceNotFoundException("Cart item not found with id " + cartItemId);
        }
    }

    // remove an item from cart
    public void removeItemFromCart(UUID cartItemId) {
        Optional<CartItems> cartItemsOptional = cartItemRepository.findById(cartItemId);
        if(cartItemsOptional.isPresent()) {
            cartItemRepository.deleteById(cartItemId);
        } else {
            throw new ResourceNotFoundException("Cart item not found with id " + cartItemId);
        }
    }

    // Get all the items in a cart
    public List<CartItems> getCartItems(UUID cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}
