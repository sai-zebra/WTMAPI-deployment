package com.zebra.feedsmodule.adapter.out;

import com.zebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import com.zebra.feedsmodule.adapter.out.repository.FeedOperationRepository;
import com.zebra.feedsmodule.domain.port.output.FeedOperationRepositoryPort;
import org.springframework.stereotype.Repository;

 // Output adapter
@Repository
public class FeedOperationRepositoryAdapter implements FeedOperationRepositoryPort {
    private final FeedOperationRepository feedOperationRepository;

    public FeedOperationRepositoryAdapter(FeedOperationRepository feedOperationRepository) {
        this.feedOperationRepository = feedOperationRepository;
    }

    //function to save feedoperation in DB
    @Override
    public FeedOperationEntity save(FeedOperationEntity feedOperation) {
        return feedOperationRepository.save(feedOperation);
    }
}
