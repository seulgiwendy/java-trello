package codesquad.configuration;

import codesquad.utils.CustomSentryExceptionResolver;
import io.sentry.spring.SentryExceptionResolver;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class BeanConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new CustomSentryExceptionResolver();
    }
}
