package apiTests;

import apiTests.models.Project;
import apiTests.models.ProjectResponse;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class SimpleAPITest {

    private static final String TOKEN = "590015ffe839ff4a169beb4b3e8f738d955a81c25ebde48dbc916c7d53ec33a0";
    private static final String BASE_URL = "https://api.qase.io/v1";

    @Test
    public void verifyAboutGetAllProjects() {

        given()
                .header("Token", TOKEN)
        .when()
                .log().all()
                .get(BASE_URL + "/project")
        .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true),
                        "result.entities[0].counts.suites", equalTo(3),
                "result.total", equalTo(2));

    }

    @Test
    public static void verifyAboutCreateNewProject() {

        given()
                .header("Token", TOKEN)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"Super New Java Project\", \"code\": \"JAVA\"}")
        .when()
                .log().all()
                .post(BASE_URL + "/project")
        .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));

    }

    @Test
    public static void verifyAboutGetProjectByCode() {

        Gson gson = new Gson();
        ProjectResponse projectResponse = ProjectResponse.builder()
                .status(true)
                .result(Project.builder()
                        .code("WSCV")
                        .title("www.sharelane.com_VadimTarletskiy")
                        .counts(Project.Counts.builder()
                                .cases(13)
                                .suites(3)
                                .milestones(0)
                                .runs(Project.Counts.Runs.builder()
                                        .total(1)
                                        .active(0)
                                        .build())
                                .defects(Project.Counts.Defects.builder()
                                        .total(6)
                                        .open(5)
                                        .build())
                                .build())
                        .build())
                .build();

        given()
                .pathParam("code", "WSCV")
                .header("Token", TOKEN)
        .when()
                .log().all()
                .get(BASE_URL + "/project/{code}")
        .then()
                .log().all()
                .statusCode(200)
                .body(equalTo(gson.toJson(projectResponse)));

    }

    @Test
    public static void verifyAboutDeleteProjectByCode() {

        given()
                .pathParam("code", "JAVA")
                .header("Token", TOKEN)
        .when()
                .log().all()
                .delete(BASE_URL + "/project/{code}")
        .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));

    }

}
