package com.jayzebra.feedsmodule.domain.service;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import com.jayzebra.feedsmodule.domain.dto.FeedOperationRequestDto;
import com.jayzebra.feedsmodule.domain.port.input.FeedOperationUseCase;
import com.jayzebra.feedsmodule.domain.port.output.FeedOperationRepositoryPort;


import java.time.OffsetDateTime;
import java.util.UUID;

public class FeedOperationService implements FeedOperationUseCase {

    private final FeedOperationRepositoryPort feedOperationRepositoryPort;

    public FeedOperationService(FeedOperationRepositoryPort feedOperationRepositoryPort) {
        this.feedOperationRepositoryPort = feedOperationRepositoryPort;
    }

    @Override
    public void performFeedOperation(FeedOperationRequestDto requestDto) {
        FeedOperationEntity operation = new FeedOperationEntity();
        operation.setId(UUID.randomUUID().toString());
        operation.setOperation(FeedOperationEntity.FeedOperationType.valueOf(requestDto.getOperation().name()));
        operation.setPayload(requestDto.getPayload());
        operation.setStatus(FeedOperationEntity.FeedOperationStatus.ACCEPTED);
        operation.setCreatedAt(OffsetDateTime.now());

        feedOperationRepositoryPort.save(operation);
    }
}
