package codesquad.controllers;

import codesquad.model.Account;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    private static final Logger log = LoggerFactory.getLogger(AccountControllerTest.class);

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void testNewUser() {
        Account newAccount = new Account("lemon", "1234", "양희정");
        given()
                .contentType(ContentType.JSON)
                .body(newAccount)
                .when()
                .post("/api/user")
                .then()
                .statusCode(HttpStatus.OK.value());

    }
}