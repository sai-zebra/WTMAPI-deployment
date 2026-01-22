package com.jayzebra.rtm.domain.service;

import com.jayzebra.rtm.adapter.out.entity.RTMEntity;
import com.jayzebra.rtm.domain.dto.RtmOperationRequestDto;
import com.jayzebra.rtm.domain.port.in.RtmOperationUseCase;
import com.jayzebra.rtm.domain.port.out.RtmOperationRepositoryPort;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;


public class RtmOperationService implements RtmOperationUseCase {

    private final RtmOperationRepositoryPort rtmOperationRepositoryPort;

    public RtmOperationService(RtmOperationRepositoryPort rtmOperationRepositoryPort) {
        this.rtmOperationRepositoryPort = rtmOperationRepositoryPort;
    }

    @Override
    public void performRtmOperation(RtmOperationRequestDto requestDto) {
        RTMEntity operation = new RTMEntity();
        operation.setId(UUID.randomUUID().toString());
    operation.setOperation(RTMEntity.RtmOperationType.valueOf(requestDto.getOperation().name()));
        operation.setPayload(requestDto.getPayload());
        operation.setStatus(RTMEntity.OperationStatus.ACCEPTED); // Set initial status
        operation.setCreatedAt(Instant.now());

        rtmOperationRepositoryPort.save(operation);
    }
}
