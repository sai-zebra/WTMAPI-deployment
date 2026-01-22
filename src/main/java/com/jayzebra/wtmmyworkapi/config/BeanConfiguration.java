package com.jayzebra.wtmmyworkapi.config;

import com.jayzebra.feedsmodule.domain.port.output.FeedNoteRepositoryPort;
import com.jayzebra.feedsmodule.domain.port.output.FeedRepositoryPort;
import com.jayzebra.rtm.domain.port.out.RtmOperationRepositoryPort;
import com.jayzebra.rtm.domain.service.RtmOperationService;
import com.jayzebra.feedsmodule.domain.service.FeedNoteService;
import com.jayzebra.surveys.domain.port.output.SurveyRepositoryPort;
import com.jayzebra.surveys.domain.service.SurveyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jayzebra.feedsmodule.domain.service.FeedService;

@Configuration
public class BeanConfiguration {

    //FeedService Bean
    @Bean
    FeedService feedService(FeedRepositoryPort feedRepositoryPort){
        return new FeedService(feedRepositoryPort);
    }

    @Bean
    SurveyService surveyService(SurveyRepositoryPort surveyRepositoryPort){
        return new SurveyService(surveyRepositoryPort);
    }

    @Bean
    RtmOperationService rtmOperationService(RtmOperationRepositoryPort rtmOperationRepositoryPort){
        return new RtmOperationService(rtmOperationRepositoryPort);
    }
    FeedNoteService feedNoteService(FeedNoteRepositoryPort feedNoteRepositoryPort){
        return new FeedNoteService(feedNoteRepositoryPort);
    }



}
