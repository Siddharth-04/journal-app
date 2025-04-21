package com.sid.journal.service;

import com.sid.journal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        assertEquals(4,2+2);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
    })
    public void test1(String name) {
        assertNotNull(userRepository.findByUserName(name),"falied for user" + name);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "5,5,10"
    })
    public void test2(int a,int b,int expected) {
        assertEquals(expected,a+b);
    }
}
