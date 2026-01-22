package com.jayzebra.feedsmodule.adapter.out.repository;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedOperationRepository extends JpaRepository<FeedOperationEntity,String> {
}
