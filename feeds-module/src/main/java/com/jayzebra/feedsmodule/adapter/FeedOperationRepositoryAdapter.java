package com.jayzebra.feedsmodule.adapter;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import com.jayzebra.feedsmodule.adapter.out.repository.FeedOperationRepository;
import com.jayzebra.feedsmodule.domain.port.output.FeedOperationRepositoryPort;
import org.springframework.stereotype.Repository;

@Repository
public class FeedOperationRepositoryAdapter implements FeedOperationRepositoryPort {
    private final FeedOperationRepository feedOperationRepository;

    public FeedOperationRepositoryAdapter(FeedOperationRepository feedOperationRepository) {
        this.feedOperationRepository = feedOperationRepository;
    }

    @Override
    public FeedOperationEntity save(FeedOperationEntity feedOperation) {
        return feedOperationRepository.save(feedOperation);
    }
}
