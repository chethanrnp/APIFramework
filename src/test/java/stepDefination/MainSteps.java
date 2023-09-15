package stepDefination;

import java.util.ArrayList;

import org.testng.Assert;

import groovyjarjarantlr4.v4.parse.ANTLRParser.element_return;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resuorces.APIResources;
import resuorces.TestDataBuild;
import resuorces.Utils;

public class MainSteps extends Utils {

	public RequestSpecification addPlaceReq;
	public ResponseSpecification resp;
	public Response addPlaceResp;
	TestDataBuild data = new TestDataBuild();
	static String placId;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String addr, String lang) throws Throwable {

		// Add place request
		addPlaceReq = given().spec(requestSpecification()).body(data.addPlacePayload(name, addr, lang));

	}

	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		// building Response SpecBuilder
		resp = new ResponseSpecBuilder().expectStatusCode(200).log(LogDetail.ALL).expectContentType(ContentType.JSON)
				.build();
		if (method.equalsIgnoreCase("post")) {
			// Adding the place using Pojo class
			addPlaceResp = addPlaceReq.when().post(resourceAPI.getResources()).then().spec(resp).extract().response();
		} else if (method.equalsIgnoreCase("get")) {
			addPlaceResp = addPlaceReq.when().get(resourceAPI.getResources()).then().spec(resp).extract().response();
		} else {
			addPlaceResp = addPlaceReq.when().delete(resourceAPI.getResources()).then().spec(resp).extract().response();
		}
	}

	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		Assert.assertEquals(int1, addPlaceResp.getStatusCode());
	}

	@Then("{string} in respose body is {string}")
	public void in_respose_body_is(String key, String expectedValue) {

		Assert.assertEquals(getJsonPath(addPlaceResp, key), expectedValue);
	}

	@Then("verify {string} created maps to {string} using {string}")
	public void verify_created_maps_to_using(String placeId, String name, String getPlaceAPI) throws Throwable {
		// getting the place id
		placId = getJsonPath(addPlaceResp, placeId);
		addPlaceReq = given().spec(requestSpecification()).queryParam(placeId, placId);
		user_call_with_http_request(getPlaceAPI, "Get");
		// verify the name in the response
		Assert.assertEquals(getJsonPath(addPlaceResp, "name"), name);

		System.out.println("name === " + getJsonPath(addPlaceResp, "name"));
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws Throwable {
		addPlaceReq = given().spec(requestSpecification()).body(data.deletePlacePayload(placId));
	}

}
