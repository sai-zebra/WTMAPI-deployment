package com.zebra.feedsmodule.adapter.in.rest;

import com.zebra.feedsmodule.domain.dto.FeedNoteCreateRequestdto;
import com.zebra.feedsmodule.domain.dto.FeedNoteResponseDto;
import com.zebra.feedsmodule.domain.dto.FeedNoteUpdateDto;
import com.zebra.feedsmodule.domain.model.FeedNote;
import com.zebra.feedsmodule.domain.port.input.CreateFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.DeleteFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.GetFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.UpdateFeedNoteUseCase;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feeds/{feedId}/notes")
@Tag(name = "FeedNotes")
public class FeedNoteController {
    //input port to create the feednote
    private final CreateFeedNoteUseCase createFeedNoteUseCase;
    //input port to delete all the feednotes related to given feed
    private final DeleteFeedNoteUseCase deleteFeedNoteUseCase;
    //input port to get all the feednotes
    private final GetFeedNoteUseCase getFeedNoteUseCase;
    //input port to update the feednote given feedid and noteid
    private final UpdateFeedNoteUseCase updateFeedNoteUseCase;
    private final ModelMapper modelMapper;

    //Constructor to inject dependency with input ports
    public FeedNoteController(CreateFeedNoteUseCase createFeedNoteUseCase, DeleteFeedNoteUseCase deleteFeedNoteUseCase, GetFeedNoteUseCase getFeedNoteUseCase, UpdateFeedNoteUseCase updateFeedNoteUseCase, ModelMapper modelMapper) {
        this.createFeedNoteUseCase = createFeedNoteUseCase;
        this.deleteFeedNoteUseCase = deleteFeedNoteUseCase;
        this.getFeedNoteUseCase = getFeedNoteUseCase;
        this.updateFeedNoteUseCase = updateFeedNoteUseCase;
        this.modelMapper = modelMapper;
    }

    //function to create feednote
    @PostMapping
    public ResponseEntity<FeedNoteResponseDto> createNote(
            @PathVariable UUID feedId,
            @Valid @RequestBody FeedNoteCreateRequestdto requestDto) {

        FeedNote newNote = createFeedNoteUseCase.createFeedNote(feedId, requestDto.getMessage());
        FeedNoteResponseDto responseDto = modelMapper.map(newNote, FeedNoteResponseDto.class);
        return ResponseEntity.status(201).body(responseDto);
    }

    //function to partially update the feednote
    @PatchMapping("/{noteId}")
    public ResponseEntity<Void> updateNote(
            @PathVariable UUID feedId,
            @PathVariable UUID noteId,
            @Valid @RequestBody FeedNoteUpdateDto requestDto) {
        updateFeedNoteUseCase.updateFeedNote(feedId, noteId, requestDto.getMessage());
        return ResponseEntity.noContent().build();
    }

    //function to get list pf feednotes corresponds to particular feedid
    @GetMapping
    public ResponseEntity<List<FeedNoteResponseDto>> getNotesForFeed(@PathVariable UUID feedId) {
        List<FeedNote> notes = getFeedNoteUseCase.getNotesForFeed(feedId);
        List<FeedNoteResponseDto> responseDtos = notes.stream()
                .map(note -> modelMapper.map(note, FeedNoteResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    //function to delete feed note
    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable UUID feedId,
            @PathVariable UUID noteId) {

        deleteFeedNoteUseCase.deleteFeedNote(feedId, noteId);
        return ResponseEntity.noContent().build();
    }
}
