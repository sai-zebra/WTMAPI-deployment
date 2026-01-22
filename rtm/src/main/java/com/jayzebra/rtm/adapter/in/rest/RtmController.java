package com.jayzebra.rtm.adapter.in.rest;

import com.jayzebra.rtm.domain.dto.RtmOperationRequestDto;
import com.jayzebra.rtm.domain.port.in.RtmOperationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rtm")
public class RtmController {

    private final RtmOperationUseCase rtmOperationUseCase;

    public RtmController(RtmOperationUseCase rtmOperationUseCase) {
        this.rtmOperationUseCase = rtmOperationUseCase;
    }

    @PostMapping("/operations")
    @ResponseStatus(HttpStatus.ACCEPTED) // Returns a 202 Accepted status code
    public void performRtmOperation(@RequestBody RtmOperationRequestDto requestDto) {
        rtmOperationUseCase.performRtmOperation(requestDto);
    }
}

