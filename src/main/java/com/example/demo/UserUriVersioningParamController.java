package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping(value = "/versionParam")
public class UserUriVersioningParamController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}", params = "version=1")
    public UserDTOV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getById(id);

        if (!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found Mapper");
        }

        User user = optionalUser.get();
        UserDTOV1 userDTOV1 = modelMapper.map(user,UserDTOV1.class);
        return userDTOV1;

    }

    @GetMapping(value = "/{id}", params = "version=2")
    public UserDTOV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getById(id);

        if (!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found Mapper");
        }

        User user = optionalUser.get();
        UserDTOV2 userDTOV2 = modelMapper.map(user,UserDTOV2.class);
        return userDTOV2;

    }


}
