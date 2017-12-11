package codesquad.configuration;

import codesquad.security.*;
import codesquad.security.Exceptions.AuthenticationFailException;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.List;


@Configuration
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String DEFAULT_USER_ROLE = "ROLE_USER";
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Qualifier("oauth2ClientContext")
    @Autowired
    OAuth2ClientContext oAuth2ClientContext;

    @Autowired
    private MaliciousSigninAuhenticationEntryPoint maliciousSigninAuhenticationEntryPoint;

    private SigninRedirectionHandler signinRedirectionHandler = new SigninRedirectionHandler("/");

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/lib/**", "/fonts/**", "/image/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.customUserDetailsService).passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable();

        httpSecurity
                .headers().frameOptions().disable();

        httpSecurity
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/login/**", "/sign**", "/h2-console/**", "/captcha**").permitAll()
                .antMatchers("/board**", "/api/**").hasAuthority("ROLE_USER")
                .anyRequest()
                .authenticated()
                .and()

                .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/signin")
                    .successHandler(this.signinRedirectionHandler)
                    .permitAll()
                .and()
                    .addFilterBefore(generateFilterSet(), BasicAuthenticationFilter.class);

        httpSecurity
                .logout()
                .logoutUrl("/logout");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(-100);
        return registrationBean;
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResources githubResourcesNew() {
        return new ClientResources();
    }

    private Filter generateFilterSet() {
        CompositeFilter filter = new CompositeFilter();

        List<Filter> filters = Lists.newArrayList();
        filters.add(ssoFilter(githubResourcesNew(), "/login/github", SocialSigninProviders.GITHUB));
        filter.setFilters(filters);

        return filter;
    }

    private Filter ssoFilter(ClientResources resources, String path, SocialSigninProviders providers) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(resources.getClient(), oAuth2ClientContext);

        String redirectUrl = "/";

        filter.setRestTemplate(template);
        filter.setTokenServices(new OauthUserTokenService(resources, providers));
        filter.setAuthenticationSuccessHandler(this.signinRedirectionHandler);
        filter.setAuthenticationFailureHandler((req, res, auth) -> new AuthenticationFailException());
        filter.setApplicationEventPublisher(this.applicationEventPublisher);

        return filter;

    }
}
