package com.jayzebra.feedsmodule.domain.port.input;

import com.jayzebra.feedsmodule.domain.dto.FeedOperationRequestDto;

public interface FeedOperationUseCase {
    void performFeedOperation(FeedOperationRequestDto requestDto);
}
