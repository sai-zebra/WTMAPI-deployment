package com.jayzebra.application.config;

import com.jayzebra.feedsmodule.domain.port.output.FeedNoteRepositoryPort;
import com.jayzebra.feedsmodule.domain.port.output.FeedOperationRepositoryPort;
import com.jayzebra.feedsmodule.domain.port.output.FeedRepositoryPort;
import com.jayzebra.feedsmodule.domain.service.FeedOperationService;
import com.jayzebra.rtm.domain.port.out.RtmOperationRepositoryPort;
import com.jayzebra.rtm.domain.service.RtmOperationService;
import com.jayzebra.feedsmodule.domain.service.FeedNoteService;
import com.jayzebra.surveys.domain.port.output.SurveyRepositoryPort;
import com.jayzebra.surveys.domain.service.SurveyService;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import com.jayzebra.usermodule.domain.service.UserService;
import org.modelmapper.ModelMapper;
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
    @Bean
    FeedNoteService feedNoteService(FeedNoteRepositoryPort feedNoteRepositoryPort){
        return new FeedNoteService(feedNoteRepositoryPort);
    }
    @Bean
    FeedOperationService feedOperationService(FeedOperationRepositoryPort feedOperationRepositoryPort){
        return new FeedOperationService(feedOperationRepositoryPort);
    }

    @Bean
    UserService userService(UserRepositoryPort userRepositoryPort, ModelMapper modelMapper){
        return new UserService(userRepositoryPort ,modelMapper);
    }
}
