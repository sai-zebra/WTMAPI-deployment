package com.zebra.usermodule.domain.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserSessionsTest {

    @Test
    void testUserSessionsCreationAndGetters() {
        //  Create an instance of UserSessions
        UserSessions userSessions = new UserSessions();
        assertNotNull(userSessions); // Ensure the object is not null initially

        // Check initial state (should be null for String fields)
        assertNull(userSessions.getUserId());
        assertNull(userSessions.getSessionId());

        //  Set values
        String expectedUserId = "testUser123";
        String expectedSessionId = "abcdef123456";

        userSessions.setUserId(expectedUserId);
        userSessions.setSessionId(expectedSessionId);

        //  Retrieve and  Assert values
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
