package com.jayzebra.feedsmodule.adapter;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedEntity;
import com.jayzebra.feedsmodule.adapter.out.repository.FeedRepository;
import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.jayzebra.feedsmodule.domain.model.Feed;
import com.jayzebra.feedsmodule.domain.port.output.FeedRepositoryPort;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

//Output Adaptor
@AllArgsConstructor
@Repository
public class FeedRepositoryAdapter implements FeedRepositoryPort {
    private final FeedRepository feedRepository;
    private final ModelMapper modelMapper;


    //Fucntion to save the feed in DB
    @Override
    public void save(FeedCreateRequestDto feedCreateRequestDto) {
         FeedEntity feed= modelMapper.map(feedCreateRequestDto,FeedEntity.class);
         feed.setStatus("New");
          feedRepository.save(feed);
    }

    //Function to get all feeds
    @Override
    public List<FeedResponseDto> getAllFeeds() {
        List<FeedEntity> feeds=feedRepository.findAll();
       return feeds.stream().map(feed -> modelMapper.map(feed, FeedResponseDto.class)).toList();

    }

    // Function to delete the feed
    @Override
    public void deleteFeed(UUID feedId) {
        feedRepository.deleteById(feedId);
    }

    //Function to get feed by id
    @Override
    public FeedResponseDto getFeedById(UUID feedId) {
        FeedEntity feed = feedRepository.findById(feedId).orElseThrow(()-> new IllegalArgumentException("Feed not found"));
        return modelMapper.map(feed,FeedResponseDto.class);

    }

    //Function to Update the Feed
    @Override
    public void updateFeed(UUID feedId, FeedUpdateRequestDto feedUpdateRequestDto) {
        FeedEntity feed = feedRepository.findById(feedId).orElseThrow(()-> new IllegalArgumentException("Feed not found"));

        feed.setTitle(feedUpdateRequestDto.getTitle());
        feed.setMessage(feedUpdateRequestDto.getMessage());

        feedRepository.save(feed);
    }
}
