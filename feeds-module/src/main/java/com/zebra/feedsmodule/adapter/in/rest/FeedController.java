package com.zebra.feedsmodule.adapter.in.rest;

import com.zebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.zebra.feedsmodule.domain.dto.FeedResponseDto;
import com.zebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.zebra.feedsmodule.domain.port.input.CreateFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.DeleteFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.GetFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.UpdateFeedUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import java.util.List;
import java.util.UUID;

//Controller to manage all the operations related to feed
@RestController
@RequestMapping("/feeds")
//Here we name our controller
@Tag(name="Feeds")
public class FeedController {
    //input port to create feed
    private final CreateFeedUseCase createFeedUseCase;
    //input port to get the feed
    private final GetFeedUseCase getFeedUseCase;
    //input port to delete the feed
    private final DeleteFeedUseCase deleteFeedUseCase;
    //input port to update the feed
    private final UpdateFeedUseCase updateFeedUseCase;

    //Constructor to inject dependency with input ports
    public FeedController(CreateFeedUseCase createFeedUseCase, GetFeedUseCase getFeedUseCase, ModelMapper modelMapper, DeleteFeedUseCase deleteFeedUseCase, UpdateFeedUseCase updateFeedUseCase) {
        this.createFeedUseCase = createFeedUseCase;
        this.getFeedUseCase = getFeedUseCase;
        this.deleteFeedUseCase = deleteFeedUseCase;
        this.updateFeedUseCase = updateFeedUseCase;
    }

    //Function to get all the feeds
    @GetMapping
    public ResponseEntity<List<FeedResponseDto>> getAllFeeds(){
        //returning the value come from input port
        return ResponseEntity.ok(getFeedUseCase.getAllFeeds());
    }

    //Function get feed by id
    @GetMapping("/{id}")
    public ResponseEntity<FeedResponseDto> getFeedById(@PathVariable UUID id){
        return ResponseEntity.ok(getFeedUseCase.getFeedById(id));
    }

    //Function to create new feed
    @PostMapping
    public ResponseEntity<Void> createFeed(@RequestBody @Valid FeedCreateRequestDto createRequestDto){
        //Here we call the input port function to create feed
        createFeedUseCase.createFeed(createRequestDto);
        return ResponseEntity.noContent().build();
    }

    //Function to delete feed
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeed(@PathVariable UUID id){
        //Here we call the input port function to delete feed
        deleteFeedUseCase.deleteFeed(id);
       return ResponseEntity.noContent().build();
    }

    //Function for Updating feed;
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateFeed(@PathVariable UUID id, @RequestBody @Valid FeedUpdateRequestDto feedUpdateRequestDto){
        //Here we call the input port function to update feed
        updateFeedUseCase.updateFeed(id,feedUpdateRequestDto);
        return ResponseEntity.noContent().build();
    }
}
