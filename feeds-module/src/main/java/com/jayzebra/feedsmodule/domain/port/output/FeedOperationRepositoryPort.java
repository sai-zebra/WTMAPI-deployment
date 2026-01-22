package com.jayzebra.feedsmodule.domain.port.output;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import org.springframework.modulith.NamedInterface;

@NamedInterface
public interface FeedOperationRepositoryPort {
    FeedOperationEntity save(FeedOperationEntity feedOperation);
}
