package com.zebra.feedsmodule.domain.port.output;

import com.zebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.zebra.feedsmodule.domain.dto.FeedResponseDto;
import com.zebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import org.springframework.modulith.NamedInterface;
import java.util.List;
import java.util.UUID;

 //Output port

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
