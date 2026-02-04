package com.zebra.feedsmodule.domain.service;

import com.zebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.zebra.feedsmodule.domain.dto.FeedResponseDto;
import com.zebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.zebra.feedsmodule.domain.port.input.CreateFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.DeleteFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.GetFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.UpdateFeedUseCase;
import com.zebra.feedsmodule.domain.port.output.FeedRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class FeedService implements CreateFeedUseCase, DeleteFeedUseCase, GetFeedUseCase, UpdateFeedUseCase {

    //Output port object
    private final FeedRepositoryPort feedRepositoryPort;
    public FeedService(FeedRepositoryPort feedRepositoryPort) {
        this.feedRepositoryPort = feedRepositoryPort;
    }

    //Function to create feed
    @Override
    public void createFeed(FeedCreateRequestDto feedCreateRequestDto) {
      feedRepositoryPort.save(feedCreateRequestDto);
    }

    //Function to delete feed
    @Override
    public void deleteFeed(UUID feedId) {
        feedRepositoryPort.deleteFeed(feedId);
    }

    //Function to get feed by id
    @Override
    public FeedResponseDto getFeedById(UUID feedId) {
        return feedRepositoryPort.getFeedById(feedId);
    }

    //Function to get all the feeds
    @Override
    public List<FeedResponseDto> getAllFeeds() {
        return feedRepositoryPort.getAllFeeds();
    }

    //Function to update feed
    @Override
    public void updateFeed(UUID feedId, FeedUpdateRequestDto feedUpdateRequestDto) {
      feedRepositoryPort.updateFeed(feedId,feedUpdateRequestDto);
    }
}
