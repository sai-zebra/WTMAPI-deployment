package com.zebra.rtm.domain.service;

import com.zebra.rtm.adapter.out.entity.RTMEntity;
import com.zebra.rtm.domain.dto.RtmOperationRequestDto;
import com.zebra.rtm.domain.port.out.RtmOperationRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // 1. Initializes Mockito
class RtmOperationServiceTest {

    @Mock
    private RtmOperationRepositoryPort rtmOperationRepositoryPort; // 2. Mocks the dependency

    @InjectMocks
    private RtmOperationService rtmOperationService; // 3. Injects the mock into the service

    @Test
    void performRtmOperation_shouldCreateAndSaveRtmEntity() {
        // Arrange: Create the input request DTO
        Map<String, Object> payload = Collections.singletonMap("userId", "user-456");
        RtmOperationRequestDto requestDto = new RtmOperationRequestDto(
                RtmOperationRequestDto.RtmOperationType.BROADCAST,
                payload
        );

        // Act: Call the method being tested
        rtmOperationService.performRtmOperation(requestDto);

        // Assert and Verify

        // 4. Create an ArgumentCaptor to capture the entity passed to the save method
        ArgumentCaptor<RTMEntity> entityCaptor = ArgumentCaptor.forClass(RTMEntity.class);

        // 5. Verify that the save method was called exactly once
        verify(rtmOperationRepositoryPort).save(entityCaptor.capture());

        // 6. Get the captured entity
        RTMEntity capturedEntity = entityCaptor.getValue();

        // 7. Assert that the entity's properties are set correctly
        assertNotNull(capturedEntity.getId());
        assertNotNull(capturedEntity.getCreatedAt());
        assertEquals(RTMEntity.OperationStatus.ACCEPTED, capturedEntity.getStatus());
        assertEquals(RTMEntity.RtmOperationType.BROADCAST, capturedEntity.getOperation());
        assertEquals(payload, capturedEntity.getPayload());
    }
}
