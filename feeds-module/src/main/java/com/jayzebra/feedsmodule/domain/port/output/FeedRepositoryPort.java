package com.jayzebra.feedsmodule.domain.port.output;

import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
import com.jayzebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.jayzebra.feedsmodule.domain.model.Feed;
import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@NamedInterface
public interface FeedRepositoryPort {

    //To save the feed in DB
     void save(FeedCreateRequestDto feedCreateRequestDto);

     // To get all the feeds from DB
    List<FeedResponseDto> getAllFeeds();

    //To delete feed from DB
    void deleteFeed(UUID feedId);

    //To get feed by Id
    FeedResponseDto getFeedById(UUID feedId);

    //To Update feed
    void updateFeed(UUID feedId, FeedUpdateRequestDto feedUpdateRequestDto);



}
