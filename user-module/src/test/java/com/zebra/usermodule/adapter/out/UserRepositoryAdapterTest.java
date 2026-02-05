package com.zebra.usermodule.adapter.out;

import com.zebra.usermodule.adapter.out.entity.UserEntity;
import com.zebra.usermodule.adapter.out.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

 // Unit tests for UserRepositoryAdapter.
 // This class verifies that the adapter correctly delegates all method calls
 // to the underlying UserRepository, ensuring full mutation coverage.

class UserRepositoryAdapterTest {

    @Mock
    private UserRepository userRepository; // Mock the Spring Data JPA repository

    @InjectMocks
    private UserRepositoryAdapter userRepositoryAdapter; // Inject mock into our adapter

    @BeforeEach
    void setUp() {
        // Initialize mocks and inject them before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should delegate call to UserRepository")
    void findAll_shouldDelegateToRepository() {
        // Given
        UserEntity user = new UserEntity();
        List<UserEntity> expectedUsers = Collections.singletonList(user);
        // Define the behavior of the mock repository
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // When
        List<UserEntity> actualUsers = userRepositoryAdapter.findAll();

        // Then
        // Verify the repository's findAll method was called exactly once
        verify(userRepository, times(1)).findAll();
        // Assert that the adapter returned the same list the repository provided
        assertThat(actualUsers).isEqualTo(expectedUsers);
    }

    @Test
    @DisplayName("findById should delegate call to UserRepository")
    void findById_shouldDelegateToRepository() {
        // Given
        String userId = "user-123";
        UserEntity user = new UserEntity();
        user.setId(userId);
        // Define the behavior of the mock repository
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        Optional<UserEntity> actualUser = userRepositoryAdapter.findById(userId);

        // Then
        // Verify the repository's findById method was called once with the correct ID
        verify(userRepository, times(1)).findById(userId);
        // Assert that the adapter returned the same optional the repository provided
        assertThat(actualUser).isPresent().contains(user);
    }

    @Test
    @DisplayName("save should delegate call to UserRepository")
    void save_shouldDelegateToRepository() {
        // Given
        UserEntity userToSave = new UserEntity();
        userToSave.setUsername("testuser");
        // Define the behavior of the mock repository
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        // When
        UserEntity savedUser = userRepositoryAdapter.save(userToSave);

        // Then
        // Verify the repository's save method was called once with the correct user object
        verify(userRepository, times(1)).save(userToSave);
        // Assert that the adapter returned the same user the repository provided
        assertThat(savedUser).isEqualTo(userToSave);
    }
}

