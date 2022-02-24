package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping(value = "/modelMapper")
public class UserModelMapperController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/userid/{id}")
    public UserMnDTO getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getById(id);

        if (!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found Mapper");
        }

        User user = optionalUser.get();
        UserMnDTO userMnDTO = modelMapper.map(user,UserMnDTO.class);
        return userMnDTO;

    }


}
