package com.example.demo;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String helloWorld(){
        return "Hai Hari";
    }

    @GetMapping("/hello-bean")
    public UserDetails helloBean(){
        return new UserDetails("Hari","A","Karur");
    }

}

