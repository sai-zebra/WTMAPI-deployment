package com.jayzebra.rtm.adapter.out.repository;

import com.jayzebra.rtm.adapter.out.entity.RTMEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RtmOperationRepository extends JpaRepository<RTMEntity, String> {
}
