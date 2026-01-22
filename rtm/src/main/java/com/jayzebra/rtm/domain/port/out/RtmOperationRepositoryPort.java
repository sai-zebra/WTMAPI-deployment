package com.jayzebra.rtm.domain.port.out;


import com.jayzebra.rtm.adapter.out.entity.RTMEntity;
import org.springframework.modulith.NamedInterface;

@NamedInterface
public interface RtmOperationRepositoryPort {
    RTMEntity save(RTMEntity rtmOperation);
}
