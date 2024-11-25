package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.*;
import com.cybersoft.osahaneat.entity.keys.KeyOrderItem;
import com.cybersoft.osahaneat.payload.OrderRequest;
import com.cybersoft.osahaneat.repository.OrderItemRepository;
import com.cybersoft.osahaneat.repository.OrderRepository;
import com.cybersoft.osahaneat.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try{
            Users user = new Users();
            user.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getResId());

            List<OrderItem> items = new ArrayList<>();


            Orders order = new Orders();
            order.setUsers(user);
            order.setRestaurant(restaurant);
            orderRepository.save(order);
            for(int foodId: orderRequest.getFoodIds()){
                Food food = new Food();
                food.setId(foodId);

                OrderItem orderItem = new OrderItem();
                KeyOrderItem keyOrderItem = new KeyOrderItem(order.getId(), foodId);
//                orderItem.setFood(food);
//                orderItem.setOrders(order);
                orderItem.setKeyOrderItem(keyOrderItem);
                items.add(orderItem);
            }

            orderItemRepository.saveAll(items);
            return true;
        }catch (Exception e){
            System.out.println("Khong dat duoc hang: " + e);
            return false;
        }


    }
}
