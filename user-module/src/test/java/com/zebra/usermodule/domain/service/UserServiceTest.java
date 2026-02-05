package com.zebra.usermodule.domain.service;

import com.zebra.usermodule.adapter.out.entity.UserEntity;
import com.zebra.usermodule.domain.dto.UserDto;
import com.zebra.usermodule.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort; // Mock the repository dependency

    @Mock
    private ModelMapper modelMapper; // Mock the mapper dependency

    @InjectMocks
    private UserService userService; // Inject mocks into our service under test

    @Test
    void listUsers_shouldReturnMappedDtoList() {
        // Arrange
        UserEntity userEntity = new UserEntity(); // Entity from the database
        userEntity.setId("user-1");

        UserDto userDto = new UserDto(); // DTO that the entity should map to
        userDto.setId("user-1");

        when(userRepositoryPort.findAll()).thenReturn(List.of(userEntity));
        when(modelMapper.map(userEntity, UserDto.class)).thenReturn(userDto);

        // Act
        List<UserDto> result = userService.listUsers();

        // Assert
        assertEquals(1, result.size());
        assertEquals("user-1", result.get(0).getId());
        verify(userRepositoryPort).findAll(); // Verify repository was called
        verify(modelMapper).map(userEntity, UserDto.class); // Verify mapper was called
    }

    @Test
    void getUserById_whenUserExists_shouldReturnOptionalOfDto() {
        // Arrange
        String userId = "user-123";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        UserDto userDto = new UserDto();
        userDto.setId(userId);

        when(userRepositoryPort.findById(userId)).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, UserDto.class)).thenReturn(userDto);

        // Act
        Optional<UserDto> result = userService.getUserById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
    }

    @Test
    void getUserById_whenUserDoesNotExist_shouldReturnEmptyOptional() {
        // Arrange
        String userId = "non-existent";
        when(userRepositoryPort.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<UserDto> result = userService.getUserById(userId);

        // Assert
        assertTrue(result.isEmpty());
        verify(modelMapper, never()).map(any(), any()); // Ensure mapper is never called
    }

    @Test
    void uploadUserImage_whenUserExists_shouldUpdateAndSaveUser() {
        // Arrange
        String userId = "user-abc";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        MockMultipartFile mockFile = new MockMultipartFile("file", "test.png", "image/png", "bytes".getBytes());

        when(userRepositoryPort.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        userService.uploadUserImage(userId, mockFile);

        // Assert
        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepositoryPort).save(userCaptor.capture()); // Capture the saved entity

        UserEntity savedUser = userCaptor.getValue();
        assertNotNull(savedUser.getProfileImageUrl());
        assertTrue(savedUser.getProfileImageUrl().startsWith("/images/users/"));
        assertTrue(savedUser.getProfileImageUrl().endsWith(".png"));
    }

    @Test
    void uploadUserImage_whenUserNotFound_shouldThrowException() {
        // Arrange
        String userId = "user-xyz";
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.png", "image/png", "bytes".getBytes());

        when(userRepositoryPort.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.uploadUserImage(userId, mockFile);
        });
        assertEquals("User not found with id: " + userId, exception.getMessage());
        verify(userRepositoryPort, never()).save(any()); // Verify save was never called
    }
}
