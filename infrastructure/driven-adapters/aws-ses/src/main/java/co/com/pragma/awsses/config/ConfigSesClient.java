package co.com.pragma.awsses.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesAsyncClient;

@Configuration
@RequiredArgsConstructor
public class ConfigSesClient {

    @Bean
    public SesAsyncClient sesAsyncClient() {
        return SesAsyncClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }
}
