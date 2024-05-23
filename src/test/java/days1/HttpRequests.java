package days1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

//given()
//when()
//then()

public class HttpRequests {

	@Test(priority = 1)
	void getListOfUser() {
		given().when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();
	}

	int id;

	@Test(priority = 2)
	void createUser() {

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Yash");
		data.put("job", "QA");

		id = given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
		// get the id and store it in the variable
//				.then().statusCode(201).log().all();

	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	void updateUser() {

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Yash");
		data.put("job", "QC");

		given().contentType("application/json").body(data)

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200).log().all();

	}

	@Test(priority = 4, dependsOnMethods = { "createUser" })
	void deletUser() {
		given().when().delete("https://reqres.in/api/users" + id)

				.then().statusCode(204).log().all();
	}
}
