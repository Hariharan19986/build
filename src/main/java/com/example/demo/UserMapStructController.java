package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/mapstruct")
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public List<UserMsDTO> getAllUserDTOs(){
        return userMapper.usersToUserMsDTOList(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMsDTO getUserById(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return userMapper.userToUserMsDTO(user);
    }

}
