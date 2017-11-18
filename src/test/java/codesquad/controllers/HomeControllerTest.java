package codesquad.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    private static final Logger log = LoggerFactory.getLogger(HomeControllerTest.class);
    @Autowired private TestRestTemplate template;

    @Test
    public void home() {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.debug("Response body : {}" , response.getBody());
    }

}