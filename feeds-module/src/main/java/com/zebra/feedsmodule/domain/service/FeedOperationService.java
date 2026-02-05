package com.zebra.feedsmodule.domain.service;

import com.zebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import com.zebra.feedsmodule.domain.dto.FeedOperationRequestDto;
import com.zebra.feedsmodule.domain.port.input.FeedOperationUseCase;
import com.zebra.feedsmodule.domain.port.output.FeedOperationRepositoryPort;
import org.springframework.stereotype.Service;


import java.time.OffsetDateTime;
import java.util.UUID;


//  This service class implements the use case for handling feed operations.
//  It's responsible for processing requests to perform operations on a feed,
//  creating a record of that operation.

@Service
public class FeedOperationService implements FeedOperationUseCase {

    // The output port for the feed operation repository.
    private final FeedOperationRepositoryPort feedOperationRepositoryPort;

    //It uses dependency injection to receive an implementation of the repository port
    public FeedOperationService(FeedOperationRepositoryPort feedOperationRepositoryPort) {
        this.feedOperationRepositoryPort = feedOperationRepositoryPort;
    }

//    Processes a request to perform a feed operation --> It creates a new FeedOperationEntity, populates it with data from the request, sets its initial status, and saves it.
    @Override
    public void performFeedOperation(FeedOperationRequestDto requestDto) {
        FeedOperationEntity operation = new FeedOperationEntity();
        operation.setId(UUID.randomUUID().toString());
        // The operation type is mapped from the DTO's enum to the entity's enum.
        operation.setOperation(FeedOperationEntity.FeedOperationType.valueOf(requestDto.getOperation().name()));
        // The payload is copied from the DTO.
        operation.setPayload(requestDto.getPayload());
        // The initial status of the operation is set to ACCEPTED
        operation.setStatus(FeedOperationEntity.FeedOperationStatus.ACCEPTED);
        operation.setCreatedAt(OffsetDateTime.now());

        feedOperationRepositoryPort.save(operation);
    }
}
