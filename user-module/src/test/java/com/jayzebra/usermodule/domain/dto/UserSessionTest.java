package com.jayzebra.usermodule.domain.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserSessionsTest {

    @Test
    void testUserSessionsCreationAndGetters() {
        // 1. Create an instance of UserSessions
        UserSessions userSessions = new UserSessions();
        assertNotNull(userSessions); // Ensure the object is not null initially

        // Check initial state (should be null for String fields)
        assertNull(userSessions.getUserId());
        assertNull(userSessions.getSessionId());

        // 2. Set values
        String expectedUserId = "testUser123";
        String expectedSessionId = "abcdef123456";

        userSessions.setUserId(expectedUserId);
        userSessions.setSessionId(expectedSessionId);

        // 3. Retrieve and 4. Assert values
        assertEquals(expectedUserId, userSessions.getUserId(), "The userId should match the set value.");
        assertEquals(expectedSessionId, userSessions.getSessionId(), "The sessionId should match the set value.");
    }

    @Test
    void testUserSessionsWithEmptyValues() {
        UserSessions userSessions = new UserSessions();
        userSessions.setUserId("");
        userSessions.setSessionId("");

        assertEquals("", userSessions.getUserId(), "The userId should be an empty string.");
        assertEquals("", userSessions.getSessionId(), "The sessionId should be an empty string.");
    }
}
