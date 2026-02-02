package com.jayzebra.usermodule;

import com.jayzebra.usermodule.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(SecurityConfig.class)
class UserEntityModuleApplicationTests {

    @Test
    void contextLoads() {
    }

}
