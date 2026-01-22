package com.jayzebra.rtm.domain.port.in;

import com.jayzebra.rtm.domain.dto.RtmOperationRequestDto;

// RtmOperationUseCase.java
public interface RtmOperationUseCase {
    void performRtmOperation(RtmOperationRequestDto requestDto);
}
