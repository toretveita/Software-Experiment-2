package no.hvl.experiment1;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APITests {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    @Order(1)
    public void testCreateUser() {
        given()
                .contentType("application/json")
                .body("{\"username\": \"testuser\", \"email\": \"test@example.com\"}")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("username", equalTo("testuser"));
    }

    @Test
    @Order(2)
    public void testListUsers() {
        Response response = given()
                .when()
                .get("/users");

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(3)
    public void testCreateAnotherUser() {
        given()
                .contentType("application/json")
                .body("{\"username\": \"user2\", \"email\": \"user2@example.com\"}")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("username", equalTo("user2"));
    }

    @Test
    @Order(4)
    public void testListUsersAgain() {
        Response response = given()
                .when()
                .get("/users");

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(1));
    }

    @Test
    @Order(5)
    public void testUserCreatesPoll() {
        given()
                .contentType("application/json")
                .body("{\"question\": \"What?\", \"publishedAt\": \"2024\", \"validUntil\": \"2025\"}")
                .when()
                .post("/polls")
                .then()
                .statusCode(201)
                .body("question", equalTo("What?"));
    }

    @Test
    @Order(6)
    public void testListPolls() {
        Response response = given()
                .when()
                .get("/polls");

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(7)
    public void testCreatePollAndVoteOptions() {
        // Create a poll
        given()
                .contentType("application/json")
                .body("{\"question\": \"What is your favorite color?\", \"publishedAt\": \"2024-09-05T00:00:00Z\", \"validUntil\": \"2024-09-10T00:00:00Z\"}")
                .when()
                .post("/polls")
                .then()
                .statusCode(201);

        // Create vote options for the poll
        given()
                .contentType("application/json")
                .body("{\"caption\": \"Option A\", \"presentationOrder\": 1}")
                .when()
                .post("/polls/1/voteOptions")
                .then()
                .statusCode(201);

        given()
                .contentType("application/json")
                .body("{\"caption\": \"Option B\", \"presentationOrder\": 2}")
                .when()
                .post("/polls/1/voteOptions")
                .then()
                .statusCode(201);
    }


    @Test
    @Order(8)
    public void testUserVotesOnPoll() {
        given()
                .contentType("application/json")
                .body("{\"userId\": 1, \"pollId\": 1, \"voteOptionId\": 1}")
                .when()
                .post("/polls/1/votes")
                .then()
                .statusCode(200);
    }

/*
    @Test
    @Order(9)
    public void testUserChangesVote() {
        String voteId = "1"; // replace with actual vote ID
        given()
                .contentType("application/json")
                .body("{\"voteOptionId\": \"3\"}")
                .when()
                .put("/votes/" + voteId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(10)
    public void testListVotes() {
        Response response = given()
                .when()
                .get("/votes");

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
/*
    @Test
    @Order(11)
    public void testDeletePoll() {
        String pollId = "1"; // replace with actual poll ID
        given()
                .when()
                .delete("/polls/" + pollId)
                .then()
                .statusCode(204);
    }

    @Test
    @Order(11)
    public void testListVotesAfterPollDeletion() {
        Response response = given()
                .when()
                .get("/votes");

        response.then()
                .statusCode(200)
                .body("size()", equalTo(0));
                }
*/

}
