package com.jayzebra.feedsmodule.domain.port.input;


import com.jayzebra.feedsmodule.domain.dto.FeedUpdateRequestDto;

import java.util.Map;
import java.util.UUID;

/**
 * Input Port for the use case of updating an existing feed.
 */
@FunctionalInterface
public interface UpdateFeedUseCase {
    void updateFeed(UUID feedId, FeedUpdateRequestDto feedUpdateRequestDto);
}

