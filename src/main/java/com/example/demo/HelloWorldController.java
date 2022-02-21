package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String helloWorld(){
        return "Hai Hari";
    }

    @GetMapping("/hello-bean")
    public UserDetails helloBean(){
        return new UserDetails("Hari","A","Karur");
    }

    @GetMapping("/user")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/newUser")
    public User createUser(@RequestBody User user){
        return userService.createNewUser(user);
    }

    @GetMapping("/userid/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id){
        return userService.getById(id);
    }

    @PutMapping("/userUpdate/{id}")
    public User updateUseById(@PathVariable("id") Long id,@RequestBody User user){
        return userService.updateById(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
    }

    @GetMapping("/username/{username}")
    public User findByName(@PathVariable("username") String userName){
        return userService.getByUserName(userName);
    }

}

