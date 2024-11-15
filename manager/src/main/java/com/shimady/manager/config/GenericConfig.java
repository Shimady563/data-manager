package com.shimady.manager.config;

import com.shimady.manager.map.StudentResponseMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class GenericConfig {

    // Setting max attempts to 2 to avoid retries and infinite loop
    @Bean
    public DefaultErrorHandler errorHandler() {
        return new DefaultErrorHandler(
                (record, exception) -> System.out.println("Discarding message due to: " + exception.getCause().getMessage()),
                new FixedBackOff(0L, 1)
        );
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(false);
        mapper.addMappings(new StudentResponseMap());
        return mapper;
    }
}
