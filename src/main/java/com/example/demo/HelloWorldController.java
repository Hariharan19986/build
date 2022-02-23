package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
//Global
@RestController
@Validated
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceBundleMessageSource messageSource;

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
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){
        try {
            userService.createNewUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/userid/{id}").buildAndExpand(user.getUserId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (UserExistException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/userid/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            return userService.getById(id);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @PutMapping("/userUpdate/{id}")
    public User updateUseById(@PathVariable("id") Long id,@RequestBody User user){
        try {
            return userService.updateById(id, user);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
    }

    @GetMapping("/username/{username}")
    public User findByName(@PathVariable("username") String userName) throws UserNameNotFoundException{
        User user =  userService.getByUserName(userName);
        if (user == null){
            throw new UserNameNotFoundException("UserName " + userName + " Not found in repository");
        }
        return user;
    }

    @GetMapping("/hello-int")
    public String getMessageInI18NFormat(@RequestHeader(name = "Accept-Language",required = false) String locale){
        return messageSource.getMessage("label.hello",null,new Locale(locale));
    }

    @GetMapping("/hello-int2")
    public String getMessageInI18NFormat2(@RequestHeader(name = "Accept-Language",required = false) String locale){
        return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
    }

}

