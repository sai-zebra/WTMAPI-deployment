package com.jayzebra.feedsmodule.adapter;


import com.jayzebra.common.exceptions.ResourceNotFoundException;
import com.jayzebra.feedsmodule.adapter.out.entity.FeedEntity;
import com.jayzebra.feedsmodule.adapter.out.repository.FeedRepository;
import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.jayzebra.feedsmodule.domain.port.output.FeedRepositoryPort;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Output adapter
 * implementation of all methods in output port and connected with DB
 **/
@AllArgsConstructor
@Repository
public class FeedRepositoryAdapter implements FeedRepositoryPort {
    private final FeedRepository feedRepository;
    private final ModelMapper modelMapper;


    //Fucntion to save the feed in DB
    @Override
    public void save(FeedCreateRequestDto feedCreateRequestDto) {
        // Here we map the Dto to entity as we can interact with DB
         FeedEntity feed= modelMapper.map(feedCreateRequestDto,FeedEntity.class);
         //setting status of feed
         feed.setStatus("New");
         //save the feed into DB
          feedRepository.save(feed);
    }

    //Function to get all feeds
    @Override
    public List<FeedResponseDto> getAllFeeds() {
        // fetching all feeds from DB
        List<FeedEntity> feeds=feedRepository.findAll();
        //map it into dto and return
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
        // here we fetch the feed by id which is a entity throw exception id feed nt found
        FeedEntity feed = feedRepository.findById(feedId).orElseThrow(()-> new ResourceNotFoundException("Feed not found"));
        //map this entity to dto and return
        return modelMapper.map(feed,FeedResponseDto.class);

    }

    //Function to Update the Feed
    @Override
    public void updateFeed(UUID feedId, FeedUpdateRequestDto feedUpdateRequestDto) {
        //fetch the feed by its id throw exception if feed not found
        FeedEntity feed = feedRepository.findById(feedId).orElseThrow(()-> new ResourceNotFoundException("Feed not found"));
        //update the title of feed we just fetched
        feed.setTitle(feedUpdateRequestDto.getTitle());
        //update message
        feed.setMessage(feedUpdateRequestDto.getMessage());
        //after updating feed save the feed into DB
        feedRepository.save(feed);
    }
}
