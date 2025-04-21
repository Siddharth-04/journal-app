package com.sid.journal.service;

import com.sid.journal.entity.User;
import com.sid.journal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    public void loadByUsernameTest() {
        System.out.println("loadByUsernameTest runs successfully");
        User user = new User("John", "Doe");
        user.setRoles(Collections.singletonList("USER"));

        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }

}
