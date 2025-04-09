package ar.edu.unrn.couchbaseblogs.config;

import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseSerializationConfig {

    private final ObjectMapper objectMapper;

    public CouchbaseSerializationConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean("couchbaseEnvironment")
    public ClusterEnvironment couchbaseEnvironment() {
        return ClusterEnvironment.builder()
                .jsonSerializer(JacksonJsonSerializer.create(objectMapper))
                .build();
    }
}
