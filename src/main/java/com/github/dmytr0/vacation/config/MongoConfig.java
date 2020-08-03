package com.github.dmytr0.vacation.config;

import com.github.dmytr0.vacation.converter.DateToLocalDateConverter;
import com.github.dmytr0.vacation.converter.LocalDateToStringConverter;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@EnableMongoRepositories(basePackages = "com.github.dmytr0.vacation.repository")
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${mongodb.uri}")
    private String mongodbUri;


    @Bean("mongo_client")
    @Override
    public MongoClient mongoClient() {
        log.debug("MONGO URI: " + mongodbUri);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongodbUri))
                .retryWrites(false)
                .build();

        return new MongoClientImpl(settings, null);
    }

    @Override
    protected String getDatabaseName() {
        return new MongoClientURI(mongodbUri).getDatabase();
    }

    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new LocalDateToStringConverter());
        converters.add(new DateToLocalDateConverter());
        return new CustomConversions(CustomConversions.StoreConversions.NONE, converters);
    }


}
