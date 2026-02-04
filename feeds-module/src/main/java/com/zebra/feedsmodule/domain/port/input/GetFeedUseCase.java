package com.zebra.feedsmodule.domain.port.input;

import com.zebra.feedsmodule.domain.dto.FeedResponseDto;
import java.util.List;
import java.util.UUID;

public interface GetFeedUseCase {
    FeedResponseDto getFeedById(UUID feedId);
    List<FeedResponseDto> getAllFeeds();
}
