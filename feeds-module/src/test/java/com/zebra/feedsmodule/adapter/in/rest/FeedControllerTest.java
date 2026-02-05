package com.zebra.feedsmodule.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.zebra.feedsmodule.domain.dto.FeedResponseDto;
import com.zebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.zebra.feedsmodule.domain.port.input.CreateFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.DeleteFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.GetFeedUseCase;
import com.zebra.feedsmodule.domain.port.input.UpdateFeedUseCase;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedController.class)
class FeedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // MOCK ALL DEPENDENCIES OF THE CONTROLLER

    @MockBean
    private CreateFeedUseCase createFeedUseCase;

    @MockBean
    private GetFeedUseCase getFeedUseCase;

    @MockBean
    private DeleteFeedUseCase deleteFeedUseCase;

    @MockBean
    private UpdateFeedUseCase updateFeedUseCase;


    // Add a @MockBean for ModelMapper as well
    @MockBean
    private ModelMapper modelMapper;


    @Test
    void getAllFeeds_should_return_200_and_feed_list() throws Exception {
        List<FeedResponseDto> feeds = List.of(
                new FeedResponseDto(UUID.randomUUID(), "Feed 1", "Msg 1", "NEW", OffsetDateTime.now())
        );
        when(getFeedUseCase.getAllFeeds()).thenReturn(feeds);

        mockMvc.perform(get("/feeds"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Feed 1"));
    }

    @Test
    void getFeedById_should_return_200_and_single_feed() throws Exception {
        UUID feedId = UUID.randomUUID();
        FeedResponseDto feed = new FeedResponseDto(feedId, "Specific Feed", "Msg", "NEW", OffsetDateTime.now());
        when(getFeedUseCase.getFeedById(feedId)).thenReturn(feed);

        mockMvc.perform(get("/feeds/{id}", feedId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(feedId.toString()))
                .andExpect(jsonPath("$.title").value("Specific Feed"));
    }

    @Test
    void createFeed_should_return_204_no_content() throws Exception {
        FeedCreateRequestDto createDto = new FeedCreateRequestDto("New Feed", "New Message");

        mockMvc.perform(post("/feeds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isNoContent());

        verify(createFeedUseCase, times(1)).createFeed(any(FeedCreateRequestDto.class));
    }

    @Test
    void deleteFeed_should_return_204_no_content() throws Exception {
        UUID feedId = UUID.randomUUID();

        mockMvc.perform(delete("/feeds/{id}", feedId))
                .andExpect(status().isNoContent());

        verify(deleteFeedUseCase, times(1)).deleteFeed(feedId);
    }

    @Test
    void updateFeed_should_return_204_no_content() throws Exception {
        UUID feedId = UUID.randomUUID();
        FeedUpdateRequestDto updateDto = new FeedUpdateRequestDto("Updated Title", "Updated Message");

        mockMvc.perform(patch("/feeds/{id}", feedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isNoContent());

        verify(updateFeedUseCase, times(1)).updateFeed(eq(feedId), any(FeedUpdateRequestDto.class));
    }
}
