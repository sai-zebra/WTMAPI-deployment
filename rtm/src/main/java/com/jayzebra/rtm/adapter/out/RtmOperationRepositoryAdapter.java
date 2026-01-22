package com.jayzebra.rtm.adapter.out;


import com.jayzebra.rtm.adapter.out.entity.RTMEntity;
import com.jayzebra.rtm.adapter.out.repository.RtmOperationRepository;
import com.jayzebra.rtm.domain.port.out.RtmOperationRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class RtmOperationRepositoryAdapter implements RtmOperationRepositoryPort {

    private final RtmOperationRepository rtmOperationRepository;

    public RtmOperationRepositoryAdapter(RtmOperationRepository rtmOperationRepository) {
        this.rtmOperationRepository = rtmOperationRepository;
    }

    @Override
    public RTMEntity save(RTMEntity rtmOperation) {
        return rtmOperationRepository.save(rtmOperation);
    }
}


