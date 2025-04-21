package com.sid.journal.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.sid.journal.entity.User;
import com.sid.journal.repository.UserRepository;

@Component
@Slf4j
public class UserEntryService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


     public void saveNewUser(User user){
         try{
             user.setPassword(passwordEncoder.encode(user.getPassword()));
             user.setRoles(Arrays.asList("USER"));
             userRepository.save(user);
         }catch(Exception e){
             System.out.println("Error saving new user");
             log.error(e.getMessage());
         }
     }

     public void saveUser(User user){
         userRepository.save(user);
     }

     public void saveAdmin(User user){
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(Arrays.asList("ADMIN","USER"));
         userRepository.save(user);
     }



    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
