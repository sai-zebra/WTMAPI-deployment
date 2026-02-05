package com.zebra.feedsmodule.adapter.in.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebra.feedsmodule.domain.dto.FeedNoteCreateRequestdto;
import com.zebra.feedsmodule.domain.dto.FeedNoteResponseDto;
import com.zebra.feedsmodule.domain.dto.FeedNoteUpdateDto;
import com.zebra.feedsmodule.domain.model.FeedNote;
import com.zebra.feedsmodule.domain.port.input.CreateFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.DeleteFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.GetFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.UpdateFeedNoteUseCase;
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


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(FeedNoteController.class)
class FeedNoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // --- Mock ALL controller dependencies ---
    @MockBean
    private CreateFeedNoteUseCase createFeedNoteUseCase;
    @MockBean
    private DeleteFeedNoteUseCase deleteFeedNoteUseCase;
    @MockBean
    private GetFeedNoteUseCase getFeedNoteUseCase;
    @MockBean
    private UpdateFeedNoteUseCase updateFeedNoteUseCase;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    void createNote_should_return_201_and_createdNote() throws Exception {
        // --- ARRANGE ---
        UUID feedId = UUID.randomUUID();
        String message = "My new note";
        FeedNoteCreateRequestdto requestDto = new FeedNoteCreateRequestdto(message);

        FeedNote createdDomainNote = new FeedNote(UUID.randomUUID(), feedId, message, OffsetDateTime.now());
        FeedNoteResponseDto responseDto = new FeedNoteResponseDto(createdDomainNote.getId(), createdDomainNote.getMessage(), createdDomainNote.getCreatedAt());

        when(createFeedNoteUseCase.createFeedNote(feedId, message)).thenReturn(createdDomainNote);
        when(modelMapper.map(createdDomainNote, FeedNoteResponseDto.class)).thenReturn(responseDto);

        // --- ACT & ASSERT ---
        mockMvc.perform(post("/feeds/{feedId}/notes", feedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(responseDto.getId().toString()))
                .andExpect(jsonPath("$.message").value(message));

        verify(createFeedNoteUseCase).createFeedNote(feedId, message);
    }

    @Test
    void getNotesForFeed_should_return_200_and_noteList() throws Exception {
        // --- ARRANGE ---
        UUID feedId = UUID.randomUUID();
        FeedNote note1 = new FeedNote(UUID.randomUUID(), feedId, "Note 1", OffsetDateTime.now());
        FeedNote note2 = new FeedNote(UUID.randomUUID(), feedId, "Note 2", OffsetDateTime.now());
        List<FeedNote> domainNotes = List.of(note1, note2);

        FeedNoteResponseDto dto1 = new FeedNoteResponseDto(note1.getId(), note1.getMessage(), note1.getCreatedAt());
        FeedNoteResponseDto dto2 = new FeedNoteResponseDto(note2.getId(), note2.getMessage(), note2.getCreatedAt());

        when(getFeedNoteUseCase.getNotesForFeed(feedId)).thenReturn(domainNotes);
        when(modelMapper.map(note1, FeedNoteResponseDto.class)).thenReturn(dto1);
        when(modelMapper.map(note2, FeedNoteResponseDto.class)).thenReturn(dto2);

        // --- ACT & ASSERT ---
        mockMvc.perform(get("/feeds/{feedId}/notes", feedId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(dto1.getId().toString()));
    }

    @Test
    void updateNote_should_return_204_no_content() throws Exception {
        // --- ARRANGE ---
        UUID feedId = UUID.randomUUID();
        UUID noteId = UUID.randomUUID();
        String updatedMessage = "This is the updated message.";
        FeedNoteUpdateDto updateDto = new FeedNoteUpdateDto(updatedMessage);

        // --- ACT & ASSERT ---
        mockMvc.perform(patch("/feeds/{feedId}/notes/{noteId}", feedId, noteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isNoContent());

        // Verify the controller called the use case with the correct arguments
        verify(updateFeedNoteUseCase).updateFeedNote(feedId, noteId, updatedMessage);
    }

    @Test
    void deleteNote_should_return_204_no_content() throws Exception {
        // --- ARRANGE ---
        UUID feedId = UUID.randomUUID();
        UUID noteId = UUID.randomUUID();

        // --- ACT & ASSERT ---
        mockMvc.perform(delete("/feeds/{feedId}/notes/{noteId}", feedId, noteId))
                .andExpect(status().isNoContent());

        // Verify the controller called the use case
        verify(deleteFeedNoteUseCase).deleteFeedNote(feedId, noteId);
    }
}

