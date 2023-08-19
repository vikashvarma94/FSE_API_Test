package test;

import com.github.javafaker.Faker;
import endPoints.EndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojos.Employee;

public class RegressionTests {

    Faker faker;
    Employee employeePayload;
    String genId = "";

    @BeforeClass
    public void setUpData() {
        faker = new Faker();
        employeePayload = new Employee();

        employeePayload.setName(faker.name().name());
        employeePayload.setUsername(faker.name().username());
        employeePayload.setEmail(faker.internet().safeEmailAddress());
    }

    @Test(priority = 0)
    public void Verify_ReadAllUsers() {
        Response response = EndPoints.getAllUsers();
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    public void Verify_CreateUser() {
        Response response = EndPoints.createUser(employeePayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonResponse = response.jsonPath();
        genId = Integer.toString(jsonResponse.get("id"));
    }

    @Test(priority = 2)
    public void Verify_ReadUser() {
        Response response = EndPoints.getUser(genId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void Verify_UpdateUser() {
        employeePayload.setName(faker.name().name());
        employeePayload.setUsername(faker.name().username());
        employeePayload.setEmail(faker.internet().safeEmailAddress());

        Response response = EndPoints.updateUser(genId, employeePayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void Verify_DeleteUser() {
        Response response = EndPoints.deleteUser(genId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
