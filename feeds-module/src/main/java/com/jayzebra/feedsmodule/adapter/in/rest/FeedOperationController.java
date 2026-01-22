package com.jayzebra.feedsmodule.adapter.in.rest;

import com.jayzebra.feedsmodule.domain.dto.FeedOperationRequestDto;
import com.jayzebra.feedsmodule.domain.port.input.FeedOperationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feeds/feedId/operations")
@Tag(name = "FeedOperations")
public class FeedOperationController {

    private final FeedOperationUseCase feedOperationUseCase;

    public FeedOperationController(FeedOperationUseCase feedOperationUseCase) {
        this.feedOperationUseCase = feedOperationUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED) // Returns a 202 Accepted status code
    public void performFeedOperation(@RequestBody FeedOperationRequestDto requestDto) {
        feedOperationUseCase.performFeedOperation(requestDto);
    }
}
