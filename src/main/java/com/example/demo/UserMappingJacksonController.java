package com.example.demo;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/jackson")
@Validated
public class UserMappingJacksonController {

    @Autowired
    private UserService userService;

    @GetMapping("/userid/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            Optional<User> optionalUser =  userService.getById(id);
            User user = optionalUser.get();

            Set<String> fields = new HashSet<String>();
            fields.add("userId");
            fields.add("userName");
            fields.add("ssn");
            fields.add("orders");

            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);
            return mapper;

        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @GetMapping("/params/{id}")
    public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields){
        try {
            Optional<User> optionalUser =  userService.getById(id);
            User user = optionalUser.get();

            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);
            return mapper;

        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }
}
