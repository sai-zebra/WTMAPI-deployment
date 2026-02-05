package com.zebra.rtm.adapter.in.rest;

import com.zebra.rtm.domain.dto.RtmOperationRequestDto;
import com.zebra.rtm.domain.port.in.RtmOperationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//function to create rtm
@RestController
@RequestMapping("/rtm")
@Tag(name="RealTimeManagement")
public class RtmController {

    private static final Logger log = LoggerFactory.getLogger(RtmController.class);
    private final RtmOperationUseCase rtmOperationUseCase;

    public RtmController(RtmOperationUseCase rtmOperationUseCase) {
        this.rtmOperationUseCase = rtmOperationUseCase;
    }

    @PostMapping("/operations")
    @ResponseStatus(HttpStatus.ACCEPTED) // Returns a 202 Accepted status code
    public void performRtmOperation(@RequestBody @Valid RtmOperationRequestDto requestDto) {
        log.info("Received RTM operation request: {}", requestDto);
        rtmOperationUseCase.performRtmOperation(requestDto);
        log.info("RTM operation request processed successfully.");
    }
}

