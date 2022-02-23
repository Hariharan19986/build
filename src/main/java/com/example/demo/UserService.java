package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(User user) throws UserExistException{
        User existingUser = userRepository.findUserByUserName(user.getUserName());
        if (existingUser != null){
            throw new UserExistException("USER ALREADY EXIST");
        }
        return userRepository.save(user);
    }

    public Optional<User> getById(Long id) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("USER DOES NOT EXIST IN REPOSITORY");
        }
        return user;
    }

    public User updateById(Long id, User user) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            throw new UserNotFoundException("USER DOES NOT EXIST IN REPOSITORY, PLEASE PROVIDE CORRECT USER ID");
        }
        user.setUserId(id);
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"USER DOES NOT EXIST IN REPOSITORY, PLEASE PROVIDE CORRECT USER ID");
        }
        userRepository.deleteById(id);
    }

    public User getByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }

}
