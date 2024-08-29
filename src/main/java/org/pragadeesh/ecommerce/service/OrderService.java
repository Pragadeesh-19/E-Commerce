package org.pragadeesh.ecommerce.service;

import jakarta.transaction.Transactional;
import org.pragadeesh.ecommerce.dto.OrderItemDto;
import org.pragadeesh.ecommerce.model.Order;
import org.pragadeesh.ecommerce.model.OrderItem;
import org.pragadeesh.ecommerce.model.Product;
import org.pragadeesh.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Transactional
    public Order createOrder(UUID userId, List<OrderItemDto> orderItemDto) throws Exception {
        Order order = new Order();
        order.setUserId(userId);
        order.setPaymentStatus("PENDING");

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemDto itemDto : orderItemDto) {
            Optional<Product> productOptional = productService.productById(itemDto.getProductId());
            if(productOptional.isPresent()) {
                throw new Exception("Product not found with id " + itemDto.getProductId());
            }

            Product product = productOptional.get();

            if(product.getStock() < itemDto.getQuantity()) {
                throw new Exception("Not enough stock for product with id " + itemDto.getProductId());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(itemDto.getProductId());
            orderItem.setQuantity(itemDto.getQuantity());

            BigDecimal itemPrice = product.getPrice();
            totalPrice = totalPrice.add(itemPrice.multiply(new BigDecimal(itemDto.getQuantity())));

            orderItems.add(orderItem);

            productService.updateProduct(product.getId(), new Product(product.getName(), product.getDescription(), product.getPrice(), product.getStock() - itemDto.getQuantity()));
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        return orderRepository.save(order);

    }

    public Optional<Order> getOrderById(UUID uuid) {
        return orderRepository.findById(uuid);
    }

    public Order updateOrder(UUID uuid, Order order) throws Exception {
        Optional<Order> existingOrderOpt = orderRepository.findById(uuid);

        if(existingOrderOpt.isPresent()) {
            Order existingOrder = existingOrderOpt.get();

            if(order.getTotalPrice() != null) {
                existingOrder.setTotalPrice(order.getTotalPrice());
            }
            if(order.getPaymentStatus() != null) {
                existingOrder.setPaymentStatus(order.getPaymentStatus());
            }
            if(order.getOrderItems() != null) {
                existingOrder.setOrderItems(order.getOrderItems());
            }

            return orderRepository.save(existingOrder);
        } else {
            throw new Exception("Order not found with id " + uuid);
        }
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
