package com.codspire.mongodbexample.config;

import com.codspire.mongodbexample.model.SpeakerRepository;
import com.codspire.mongodbexample.serializer.ObjectIdSerializerModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.immutables.criteria.mongo.CollectionResolver;
import org.immutables.criteria.mongo.MongoBackend;
import org.immutables.criteria.mongo.MongoSetup;
import org.immutables.criteria.mongo.bson4jackson.BsonModule;
import org.immutables.criteria.mongo.bson4jackson.IdAnnotationModule;
import org.immutables.criteria.mongo.bson4jackson.JacksonCodecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class BeanConfig {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Bean
    public MongoBackend getMongoBackend() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new BsonModule())  // register default codecs like Jsr310, BsonValueCodec, ValueCodecProvider etc.
//                .registerModule(new GuavaModule()) // for Immutable* classes from Guava (eg. ImmutableList)
                .registerModule(new Jdk8Module()) // used for java 8 types like Optional / OptionalDouble etc.
                .registerModule(new IdAnnotationModule());

        CodecRegistry registry = JacksonCodecs.registryFromMapper(mapper);

        MongoDatabase database = getMongoDatabase(registry); // database connection
        CollectionResolver resolver = CollectionResolver.defaultResolver(database, registry);
        return new MongoBackend(MongoSetup.of(resolver));
    }

    private MongoDatabase getMongoDatabase(CodecRegistry registry) {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("test")
                .withCodecRegistry(fromRegistries(MongoClients.getDefaultCodecRegistry(), registry));

        LOG.info("database={}", database);
        return database;
    }

    @Bean
    public SpeakerRepository speakerRepository(MongoBackend mongoBackend) {
        return new SpeakerRepository(mongoBackend);
    }

    //    https://www.baeldung.com/spring-httpmessageconverter-rest
    //    https://stackoverflow.com/questions/14363555/spring-3-2-and-jackson-2-add-custom-object-mapper
    @Bean
    public HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.getObjectMapper().registerModule(new ObjectIdSerializerModule());
        return messageConverter;
    }
}
