package com.jayzebra.feedsmodule.adapter.in.rest;

import com.jayzebra.feedsmodule.domain.dto.FeedOperationRequestDto;
import com.jayzebra.feedsmodule.domain.port.input.FeedOperationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feeds/{feedId}/operations")
@Tag(name = "FeedOperations")
public class FeedOperationController {

    //input port to create feedoperation
    private final FeedOperationUseCase feedOperationUseCase;

    //constructor to inject dependency with input ports
    public FeedOperationController(FeedOperationUseCase feedOperationUseCase) {
        this.feedOperationUseCase = feedOperationUseCase;
    }

    //EndPoint to perform feed operation
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED) // Returns a 202 Accepted status code
    public void performFeedOperation(@RequestBody FeedOperationRequestDto requestDto) {
        feedOperationUseCase.performFeedOperation(requestDto);
    }
}
