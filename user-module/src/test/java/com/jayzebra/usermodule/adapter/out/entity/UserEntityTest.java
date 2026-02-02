package com.jayzebra.usermodule.adapter.out.entity;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserEntityTest {

    @Test
    void testUserEntityGettersAndSetters() {
        // Create an instance of UserEntity
        UserEntity userEntity = new UserEntity();
        assertNotNull(userEntity);

        // Verify initial state (String fields should be null)
        assertNull(userEntity.getId());
        assertNull(userEntity.getUsername());
        assertNull(userEntity.getFirstName());
        assertNull(userEntity.getLastName());
        assertNull(userEntity.getEmail());
        assertNull(userEntity.getProfileImageUrl()); // Though this one is already killed, it's good to include

        // Set values for all properties
        String id = "user123";
        String username = "johndoe";
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String profileImageUrl = "http://example.com/images/johndoe.jpg";

        userEntity.setId(id);
        userEntity.setUsername(username);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmail(email);
        userEntity.setProfileImageUrl(profileImageUrl);

        // Assert that the getters return the correct values
        assertEquals(id, userEntity.getId(), "The ID should match the set value.");
        assertEquals(username, userEntity.getUsername(), "The username should match the set value.");
        assertEquals(firstName, userEntity.getFirstName(), "The first name should match the set value.");
        assertEquals(lastName, userEntity.getLastName(), "The last name should match the set value.");
        assertEquals(email, userEntity.getEmail(), "The email should match the set value.");
        assertEquals(profileImageUrl, userEntity.getProfileImageUrl(), "The profile image URL should match the set value.");
    }
}
