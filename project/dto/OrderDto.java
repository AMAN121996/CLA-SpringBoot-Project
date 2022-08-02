package com.shopping.project.dto;

import javax.validation.constraints.NotNull;

import com.shopping.project.model.Order;

public class OrderDto {
    private Integer id;
    private @NotNull String userId;

    public OrderDto(Order order) {
        this.setId(order.getId());
        this.setUserId(order.getUserId());
    }
    //getter and setter are removed for sake of brevity

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
    
}