package com.fsbr.desafio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
class DesafioApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertTrue(true);
    }

}
