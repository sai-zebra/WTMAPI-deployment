package com.jayzebra.feedsmodule.adapter.in.rest;

import com.jayzebra.feedsmodule.domain.dto.FeedNoteCreateRequestdto;
import com.jayzebra.feedsmodule.domain.dto.FeedNoteResponseDto;
import com.jayzebra.feedsmodule.domain.model.FeedNote;
import com.jayzebra.feedsmodule.domain.port.input.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feeds/{feedId}/notes")
public class FeedNoteController {
    private final CreateFeedNoteUseCase createFeedNoteUseCase;
    private final DeleteFeedNoteUseCase deleteFeedNoteUseCase;
    private final GetFeedNoteUseCase getFeedNoteUseCase;
    private final UpdateFeedNoteUseCase updateFeedNoteUseCase;
    private final ModelMapper modelMapper;

    public FeedNoteController(CreateFeedNoteUseCase createFeedNoteUseCase, DeleteFeedNoteUseCase deleteFeedNoteUseCase, GetFeedNoteUseCase getFeedNoteUseCase, UpdateFeedNoteUseCase updateFeedNoteUseCase, ModelMapper modelMapper) {
        this.createFeedNoteUseCase = createFeedNoteUseCase;
        this.deleteFeedNoteUseCase = deleteFeedNoteUseCase;
        this.getFeedNoteUseCase = getFeedNoteUseCase;
        this.updateFeedNoteUseCase = updateFeedNoteUseCase;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<FeedNoteResponseDto> createNote(
            @PathVariable UUID feedId,
            @Valid @RequestBody FeedNoteCreateRequestdto requestDto) {

        FeedNote newNote = createFeedNoteUseCase.createFeedNote(feedId, requestDto.getMessage());
        FeedNoteResponseDto responseDto = modelMapper.map(newNote, FeedNoteResponseDto.class);
        return ResponseEntity.status(201).body(responseDto);
    }

    @PatchMapping("/{noteId}")
    public ResponseEntity<Void> updateNote(
            @PathVariable UUID feedId,
            @PathVariable UUID noteId,
            @Valid @RequestBody FeedNoteCreateRequestdto requestDto) {
        updateFeedNoteUseCase.updateFeedNote(feedId, noteId, requestDto.getMessage());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FeedNoteResponseDto>> getNotesForFeed(@PathVariable UUID feedId) {
        List<FeedNote> notes = getFeedNoteUseCase.getNotesForFeed(feedId);
        List<FeedNoteResponseDto> responseDtos = notes.stream()
                .map(note -> modelMapper.map(note, FeedNoteResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable UUID feedId,
            @PathVariable UUID noteId) {

        deleteFeedNoteUseCase.deleteFeedNote(feedId, noteId);
        return ResponseEntity.noContent().build();
    }
}
