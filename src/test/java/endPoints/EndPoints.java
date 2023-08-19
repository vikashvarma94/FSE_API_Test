package endPoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Employee;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class EndPoints {

    public static Response getAllUsers() {
        Response response = given().
                when().get(Roots.getAllUsers);
        return response;
    }
    public static Response createUser(Employee employee) {
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(employee).
                when().post(Roots.createUser);
        return response;
    }

    public static Response getUser(String id) {
        Response response = given().
                pathParam("id", id).
                when().get(Roots.getUser);
        return response;
    }

    public static Response updateUser(String id, Employee employee) {
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(employee).
                pathParam("id", id).
                when().put(Roots.getUser);
        return response;
    }

    public static Response deleteUser(String id) {
        Response response = given().
                pathParam("id", id).
                when().delete(Roots.getUser);
        return response;
    }
}
