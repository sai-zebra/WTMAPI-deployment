package com.zebra.feedsmodule.adapter.out;

import com.zebra.common.exceptions.ResourceNotFoundException;
import com.zebra.feedsmodule.adapter.out.entity.FeedEntity;
import com.zebra.feedsmodule.adapter.out.repository.FeedRepository;
import com.zebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.zebra.feedsmodule.domain.dto.FeedResponseDto;
import com.zebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class FeedRepositoryAdapterTest {

    @Mock
    private FeedRepository feedRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FeedRepositoryAdapter feedRepositoryAdapter;

    private UUID feedId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        feedId = UUID.randomUUID();
    }

    @Test
    @DisplayName("save should map DTO to entity, set status, and save")
    void save_shouldMapAndSave() {
        // Given
        FeedCreateRequestDto createDto = new FeedCreateRequestDto();
        FeedEntity mappedEntity = new FeedEntity();

        when(modelMapper.map(createDto, FeedEntity.class)).thenReturn(mappedEntity);

        // When
        feedRepositoryAdapter.save(createDto);

        // Then
        ArgumentCaptor<FeedEntity> entityCaptor = ArgumentCaptor.forClass(FeedEntity.class);
        verify(feedRepository, times(1)).save(entityCaptor.capture());

        FeedEntity capturedEntity = entityCaptor.getValue();
        assertThat(capturedEntity.getStatus()).isEqualTo("New");
    }

    @Test
    @DisplayName("getAllFeeds should fetch entities and map them to DTOs")
    void getAllFeeds_shouldFetchAndMap() {
        // Given
        FeedEntity entity = new FeedEntity();
        FeedResponseDto responseDto = new FeedResponseDto();

        when(feedRepository.findAll()).thenReturn(List.of(entity));
        when(modelMapper.map(entity, FeedResponseDto.class)).thenReturn(responseDto);

        // When
        List<FeedResponseDto> result = feedRepositoryAdapter.getAllFeeds();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(responseDto);
        verify(feedRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(entity, FeedResponseDto.class);
    }

    @Test
    @DisplayName("deleteFeed should delegate call to repository")
    void deleteFeed_shouldDelegateCall() {
        // When
        feedRepositoryAdapter.deleteFeed(feedId);

        // Then
        verify(feedRepository, times(1)).deleteById(feedId);
    }

    @Test
    @DisplayName("getFeedById should return mapped DTO when found")
    void getFeedById_whenFound_shouldReturnDto() {
        // Given
        FeedEntity entity = new FeedEntity();
        FeedResponseDto responseDto = new FeedResponseDto();

        when(feedRepository.findById(feedId)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity, FeedResponseDto.class)).thenReturn(responseDto);

        // When
        FeedResponseDto result = feedRepositoryAdapter.getFeedById(feedId);

        // Then
        assertThat(result).isEqualTo(responseDto);
    }

    @Test
    @DisplayName("getFeedById should throw ResourceNotFoundException when not found")
    void getFeedById_whenNotFound_shouldThrowException() {
        // Given
        when(feedRepository.findById(feedId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ResourceNotFoundException.class, () -> {
            feedRepositoryAdapter.getFeedById(feedId);
        });
    }

    @Test
    @DisplayName("updateFeed should find entity, update fields, and save")
    void updateFeed_shouldUpdateAndSave() {
        // Given
        FeedUpdateRequestDto updateDto = new FeedUpdateRequestDto();
        updateDto.setTitle("New Title");
        updateDto.setMessage("New Message");

        FeedEntity existingEntity = new FeedEntity();
        when(feedRepository.findById(feedId)).thenReturn(Optional.of(existingEntity));

        // When
        feedRepositoryAdapter.updateFeed(feedId, updateDto);

        // Then
        ArgumentCaptor<FeedEntity> entityCaptor = ArgumentCaptor.forClass(FeedEntity.class);
        verify(feedRepository, times(1)).save(entityCaptor.capture());

        FeedEntity capturedEntity = entityCaptor.getValue();
        assertThat(capturedEntity.getTitle()).isEqualTo("New Title");
        assertThat(capturedEntity.getMessage()).isEqualTo("New Message");
    }

    @Test
    @DisplayName("updateFeed should throw ResourceNotFoundException when not found")
    void updateFeed_whenNotFound_shouldThrowException() {
        // Given
        FeedUpdateRequestDto updateDto = new FeedUpdateRequestDto();
        when(feedRepository.findById(feedId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ResourceNotFoundException.class, () -> {
            feedRepositoryAdapter.updateFeed(feedId, updateDto);
        });
    }
}

