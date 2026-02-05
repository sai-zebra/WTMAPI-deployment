package com.zebra.usermodule.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.Assertions.assertThat;

 // Integration tests for the SecurityConfig.
 // This class ensures that the Spring Security configuration loads correctly
 // and that the SecurityFilterChain bean is properly created.
 // This directly addresses the SURVIVED mutant in securityFilterChain.

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("SecurityFilterChain bean should be present in the application context")
    void securityFilterChainBeanExists() {
        // This test explicitly requests the SecurityFilterChain bean from the context.
        // If the @Bean method in SecurityConfig were mutated to return null,
        // this getBean call would fail, or the autowired bean would be null.
        // This assertion directly kills the "replaced return value with null" mutant.
        SecurityFilterChain filterChain = applicationContext.getBean(SecurityFilterChain.class);
        assertThat(filterChain).isNotNull();
    }
}
