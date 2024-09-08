## DAT250: Software Technology Experiment Assignment 2

# Step 0: Get a HTTP client for testing

For this step I decided to try out Bruno, as it was recomended in the assignment description if I didnt have any experience with the other options. The instalation of this went without any issues.



# Step 1: Set up Spring project repository

For this I simply continued with the previous work from Assignment 1 and created a new GitHub repository.


# Step 2: Domain Model

When creating the domain model, I first created the java domain classes with attributes corresponding with the model given in the assignment description. The domain classes: Poll, User, Vote and VoteOption are simple "JavaBeans" with getters and setters. These classes can be found in the domain package.

The DomainManager class contains mainly logic for storing the objects of the domain classes in hashmaps. It also contains methods for adding, retrieving from and updating these maps. 


# Step 3: Implement test scenarios

For this step I used bruno to create a collection of tests that run in a sequence. An example of one of these tests where "Create new user" which had the URL:
POST http://localhost:8080/users
and Body: 
{
  "username": "User2",
  "email": "user2@example.com"
}
The collection also had the rest of the tests listed in the problem description of the assignment. The creation of these tests went mostly without much issue after testing the HTTP client.




# Step 4: Implement controllers

When creating the controllers for Poll, User, Vote and VoteOption, the tests from the previous step showed what each controller should be able to do. For example the poll controller has tests for Post: Creating polls, Get: Listing all polls and Delete: Remove a poll.

The controllers all have notation like this for request handling:
@RestController
@RequestMapping("/polls")
public class PollController {
    ...
}

Then is the inclution of the DomainManager object with:
  @Autowired
    private DomainManager domainManager;
This makes it able to interact with the domain classes through the DomainManager object. Also makes the controller able to access the hashmaps for adding, retrieving or removing objects (Polls, users, votes or voteoptions).

Lastly the controllers have methods for creation and listing (also deletion and updating). These have the notiations corresponding with the request. For creation this is @PostMapping, listing is @Getmapping.

Creating these classes took some debugging of the previous steps in order to sucessfully run the tests. The main problem I faced with this was to match the testinput with the correct format of the creation of the JavaBeans. So the objects were sucessfully sent and retrieved, but the objects were empty because they didnt match the class attributes.

More specificly I had trouble using the Instant type in the classes. I tried matching the testinput with the correct format, but later changed the type to String to simplify the testing and proceed with the assignment. Using string the objects were successfully created and could be seen when listing them.

# Step 5:

To include automatic testing I created a testclass called APITests in the test folder of the project. This class required some dependencies that were added to build.gradle.kts.

The class has the notation @TestMethodOrder(MethodOrderer.OrderAnnotation.class) that ensures the tests are ran in the correct order. Then each test is ran in a sequence with its own test data, and checks the result is as expected. Example of testing createUser:

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

When ran, the test Posts the username and email to "/users" and expects status 201 (created) with the corresponding username equal to the testdata username. If this is accomplished the test runs without failiure.

I continued implementing tests like this until testing of userVotesOnPoll where my test has repeatedly failed after debugging for a while. I can manually check the result at the URL, but there probably issues with either the handling of this or the tests itself. This is also as far as I came with the assignment, the tests after this are commented out because tests related to poll voting will fail. The userVotesonPoll test looks like this:

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

    The logic for voting on a poll should be implemented as only needing the unique ID of the objects it uncludeds as a reference to the object and not the object itself. This should create a vote on poll 1 from user 1 with option 1. Manually checking the result when visiting the URL the vote is created but doesnt have the values equal to the testdata.

The tests can be run with the ./gradlew test command.

Task 6 and 7 are not completed

