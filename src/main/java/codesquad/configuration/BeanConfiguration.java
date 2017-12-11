package codesquad.configuration;

import codesquad.security.MaliciousSigninAuhenticationEntryPoint;
import codesquad.utils.CustomSentryExceptionResolver;
import codesquad.utils.LoginAttemptExceedExceptionResolver;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import io.sentry.spring.SentryExceptionResolver;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
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

    @Bean
    public HandlerExceptionResolver loginAttemptExceedExceptionResolver() {
        return new LoginAttemptExceedExceptionResolver();
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
