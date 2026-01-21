package com.jayzebra.feedsmodule.domain.service;

import com.jayzebra.feedsmodule.adapter.out.repository.FeedRepository;
import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.jayzebra.feedsmodule.domain.model.Feed;
import com.jayzebra.feedsmodule.domain.port.input.CreateFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.input.DeleteFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.input.GetFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.input.UpdateFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.output.FeedRepositoryPort;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class FeedService implements CreateFeedUseCase, DeleteFeedUseCase, GetFeedUseCase, UpdateFeedUseCase {

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
