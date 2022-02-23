package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hateoas")
@Validated
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUsers() throws UserNotFoundException{
        List<User> userList = userService.getAllUsers();
        for (User user : userList){
            Long userId = user.getUserId();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash("/userid").slash(userId).withSelfRel();
            user.add(selfLink);
            List<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
            Link orderLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
            user.add(orderLink);
        }
//        User tryUser = new User();
//        Link selfLinkForGelAllUsers =  WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
//        tryUser.add(selfLinkForGelAllUsers);
//        userList.add(tryUser);
        return userList;
    }

    @GetMapping("/userid/{id}")
    public User getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            Optional<User> optionalUser = userService.getById(id);
            User user = optionalUser.get();
            Long userId = user.getUserId();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash("/userid").slash(userId).withSelfRel();
            user.add(selfLink);
            return user;
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }


}
