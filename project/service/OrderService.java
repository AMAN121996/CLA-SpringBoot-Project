package com.shopping.project.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.project.dto.PlaceOrderDto;
import com.shopping.project.model.CartItem;
import com.shopping.project.model.Order;
import com.shopping.project.model.OrderItem;
import com.shopping.project.model.ShoppingCart;
import com.shopping.project.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    OrderItemsService orderItemsService;
    
        public void placeOrder(String userId, String sessionId) 
      {
       
        	ShoppingCart cart= shoppingCartService. getShoppingCartBySessionToken(sessionId);

             System.out.println(cart.getItems().toString());
       
       
         PlaceOrderDto placeOrderDto = new PlaceOrderDto();
          placeOrderDto.setUserId(userId);
          placeOrderDto.setTotalPrice(cart.getTotalPrice());

        int orderId = saveOrder(placeOrderDto, userId, sessionId);
       
        Set<CartItem>cartItem = cart.getItems();
        for (CartItem Item: cartItem) {
            OrderItem orderItem = new OrderItem(
                    orderId,
                    Item.getProduct().getId(),            
                    Item.getQuantity(),
                    Item.getProduct().getPrice());
            orderItemsService.addOrderedProducts(orderItem);
        }
        shoppingCartService.clearShoppingCart(sessionId);
        
        System.out.println("Success");
        
    }

    public int saveOrder(PlaceOrderDto orderDto, String userId, String sessionID){
        Order order = getOrderFromDto(orderDto,userId,sessionID);
        return orderRepository.save(order).getId();
    }

    private Order getOrderFromDto(PlaceOrderDto orderDto, String userId,String sessionID) {
        Order order = new Order(orderDto,userId,sessionID);
        return order;
    }

   

	public List<Order> listOrders(String user_id) {
        List<Order> orderList = orderRepository.findAllByUserIdOrderByCreatedDateDesc(user_id);
        return orderList;
    }

}