package com.example.orderservice4.service;

import com.example.orderservice4.dto.InventoryResponse;
import com.example.orderservice4.dto.OrderItemRequest;
import com.example.orderservice4.dto.OrderRequest;
import com.example.orderservice4.entity.Order;
import com.example.orderservice4.entity.OrderItems;
import com.example.orderservice4.repository.OrderItemRepository;
import com.example.orderservice4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
public class OrderPaymentServiceImpl implements OrderPayment {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final WebClient.Builder webClient;

    @Autowired
    public OrderPaymentServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, WebClient.Builder webClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.webClient = webClient;
    }

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCode = order.getOrderLineItemsList().stream().map(OrderItems::getSkuCode).toList();

        InventoryResponse[] result = webClient.build().get().
                uri("http://inventory-service/api/v1/inventory", uriBuilder -> uriBuilder.
                        queryParam("skuCode", skuCode).build()).
                retrieve().
                bodyToMono(InventoryResponse[].class).
                block();

        Boolean allProductsIsInStock = Arrays.stream(result).allMatch(inventoryResponse -> inventoryResponse.isInStock());

        if(allProductsIsInStock) {
            orderRepository.save(order);
            return "Order Place Successfully";
        }else {
            throw new IllegalArgumentException("Product is not in stock");
        }

    }

    @Override
    public OrderItems mapToDto(OrderItemRequest orderItemRequest) {

        OrderItems orderItems = new OrderItems();

        orderItems.setSkuCode(orderItemRequest.getSkuCode());
        orderItems.setPrice(orderItemRequest.getPrice());
        orderItems.setQuantiry(orderItemRequest.getQuantiry());

        orderItemRepository.save(orderItems);

        return orderItems;
    }
}
