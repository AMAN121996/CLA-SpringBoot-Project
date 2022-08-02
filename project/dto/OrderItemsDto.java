package com.shopping.project.dto;

import javax.validation.constraints.NotNull;

public class OrderItemsDto {

    private @NotNull double price;
    private @NotNull int quantity;
    private @NotNull int orderId;
    private @NotNull int productId;

    public OrderItemsDto () {}

    public OrderItemsDto(@NotNull double price, @NotNull int quantity, @NotNull int orderId, @NotNull int productId) {
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
    //getter and setter are removed for sake of brevity
    
}