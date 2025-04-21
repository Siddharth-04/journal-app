package com.sid.journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;


import com.sid.journal.entity.User;
import com.sid.journal.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if(user != null){
            System.out.println("Authenticated with password: " + user.getPassword());
            return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())

                .roles(user.getRoles().toArray(new String[0]))
                .build();
        }
        throw new UsernameNotFoundException(username);
    }

}
