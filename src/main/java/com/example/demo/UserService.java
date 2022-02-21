package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public User updateById(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    public User getByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }

}
