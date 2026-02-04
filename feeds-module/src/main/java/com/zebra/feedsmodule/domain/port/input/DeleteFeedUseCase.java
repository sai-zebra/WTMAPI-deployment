package com.zebra.feedsmodule.domain.port.input;

import java.util.UUID;

 // Input Port for the use case of deleting a feed.
@FunctionalInterface
public interface DeleteFeedUseCase {
    void deleteFeed(UUID feedId);
}

