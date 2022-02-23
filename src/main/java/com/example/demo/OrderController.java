package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(userid);
        if (!user.isPresent()){
            throw new UserNotFoundException("User Not Found @OrderControl");
        }
        return user.get().getOrders();
    }

    @PostMapping("/{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent()){
            throw new UserNotFoundException("User Not Found @OrderControl Create");
        }
        User user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);
    }
}
