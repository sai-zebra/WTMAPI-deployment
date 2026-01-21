package com.jayzebra.feedsmodule.adapter.in.rest;

import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.jayzebra.feedsmodule.domain.model.Feed;
import com.jayzebra.feedsmodule.domain.port.input.CreateFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.input.DeleteFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.input.GetFeedUseCase;
import com.jayzebra.feedsmodule.domain.port.input.UpdateFeedUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/feeds")
public class FeedController {
    private final CreateFeedUseCase createFeedUseCase;
    private final GetFeedUseCase getFeedUseCase;
    private final ModelMapper modelMapper;
    private final DeleteFeedUseCase deleteFeedUseCase;
    private final UpdateFeedUseCase updateFeedUseCase;
    public FeedController(CreateFeedUseCase createFeedUseCase, GetFeedUseCase getFeedUseCase, ModelMapper modelMapper, DeleteFeedUseCase deleteFeedUseCase, UpdateFeedUseCase updateFeedUseCase) {
        this.createFeedUseCase = createFeedUseCase;
        this.getFeedUseCase = getFeedUseCase;
        this.modelMapper = modelMapper;
        this.deleteFeedUseCase = deleteFeedUseCase;
        this.updateFeedUseCase = updateFeedUseCase;
    }

    //Function to get all the feeds
    @GetMapping
    public ResponseEntity<List<FeedResponseDto>> getAllFeeds(){
        return ResponseEntity.ok(getFeedUseCase.getAllFeeds());
    }

    //Function get feed by id
    @GetMapping("/{id}")
    public ResponseEntity<FeedResponseDto> getFeedById(@PathVariable UUID id){
        return ResponseEntity.ok(getFeedUseCase.getFeedById(id));
    }

    //Function to create new feed
    @PostMapping
    public ResponseEntity<Void> createFeed(@RequestBody FeedCreateRequestDto createRequestDto){
        createFeedUseCase.createFeed(createRequestDto);
        return ResponseEntity.noContent().build();
    }

    //Function to delete feed
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeed(@PathVariable UUID id){
        deleteFeedUseCase.deleteFeed(id);
       return ResponseEntity.noContent().build();
    }

    //Function for Updating feed;
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateFeed(@PathVariable UUID id, @RequestBody FeedUpdateRequestDto feedUpdateRequestDto){
        updateFeedUseCase.updateFeed(id,feedUpdateRequestDto);
        return ResponseEntity.noContent().build();
    }



}
