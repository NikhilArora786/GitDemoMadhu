package StepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.runner.RunWith;
import org.testng.Assert;

import com.gf.sft.PojoClasses.AddPlaceRequest;
import com.gf.sft.PojoClasses.GetPlaceResponse;
import com.gf.sft.PojoClasses.Location;
import com.gft.cs.ReuseableMethods;

@RunWith(Cucumber.class)
public class MyStepDefinitions {

	 RequestSpecification request;
	 RequestSpecification request1;
	 Response response;
	 Response response1;
	 Response response2;
	 String cookie;
	 String putResponse;
	 String expectedAddress="70,Nikhil Arora, USA 09";
	 String expectedMessage="Address successfully updated";
	 String place_id;
	 String jiraIssueId;
	 String JiraIssueKey;
	 String cook;
	 RequestSpecification requestSpec;
	 ResponseSpecification responseSpec;
	 GetPlaceResponse getPlaceResponse=new GetPlaceResponse();
	 SessionFilter session=new SessionFilter();
    @Given("^user has the basic details$")
    public void user_has_the_basic_details() throws Throwable {
    	//given() , when() and then()
    	RestAssured.baseURI="https://rahulshettyacademy.com";
        request= given().log().all().queryParam("key", "qaclick123")
        .header("Content-Type","application/json")
        .body("{\r\n" + 
        		"  \"location\": {\r\n" + 
        		"    \"lat\": -38.383494,\r\n" + 
        		"    \"lng\": 33.427362\r\n" + 
        		"  },\r\n" + 
        		"  \"accuracy\": 50,\r\n" + 
        		"  \"name\": \"Frontline house\",\r\n" + 
        		"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
        		"  \"address\": \"29, side layout, USA 09\",\r\n" + 
        		"  \"types\": [\r\n" + 
        		"    \"shoe park\",\r\n" + 
        		"    \"shop\"\r\n" + 
        		"  ],\r\n" + 
        		"  \"website\": \"http://google.com\",\r\n" + 
        		"  \"language\": \"French-IN\"\r\n" + 
        		"}\r\n" + 
        		"");
      
       
        
    }

    @When("^user hit the add place api$")
    public void user_hit_the_add_place_api() throws Throwable {
    	 response=request.when().post("/maps/api/place/add/json")
    			.then().log().all()
    			.assertThat().statusCode(200).extract().response();
    	
    }

    @Then("^place should get added to the backend$")
    public void place_should_get_added_to_the_backend() throws Throwable {
    	//JsonPath is a class in rest assured which is used to parse the response.
    	JsonPath js=new JsonPath(response.asString());
    	String actualStatus=js.getString("status");   
    	String actualScope=js.get("scope").toString();
    	String expectedStatus="OK";
    	String expectedScope="APP";
    	Assert.assertEquals(actualStatus, expectedStatus);
    	Assert.assertEquals(actualScope, expectedScope);
    	
    }
    
    @Then("^place should get added and scope should be \"([^\"]*)\" and status should be \"([^\"]*)\"$")
    public void place_should_get_added_and_scope_should_be_something_and_status_should_be_something(String scope, String status) throws Throwable {
    	JsonPath js=new JsonPath(response.asString());
    	String actualStatus=js.getString("status");   
    	String actualScope=js.get("scope").toString();
    	Assert.assertEquals(actualStatus, status);
    	Assert.assertEquals(actualScope, scope);
    }
    
    @Then("^hit the get end point$")
    public void hit_the_get_end_point() throws Throwable {
       RestAssured.baseURI="https://rahulshettyacademy.com";
       JsonPath js=new JsonPath(response.asString());
        place_id=js.getString("place_id");
       response1= given().log().all().queryParam("key","qaclick123")
       .queryParam("place_id", place_id)
       .when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response();
       
    }

    @And("^verify the place should be successfully retrived with status code as \"([^\"]*)\"$")
    public void verify_the_place_should_be_successfully_retrived_with_status_code_as_something(String status) throws Throwable {
    	int actualStatusCode= response1.getStatusCode();
    	int expectedStatusCode=Integer.parseInt(status);
    	Assert.assertEquals(actualStatusCode, expectedStatusCode);
   
    }
    
    @Then("^new place should be added successfully$")
    public void new_place_should_be_added_successfully() throws Throwable {
          JsonPath js1=ReuseableMethods.rawToJson(putResponse);
          String actualMsg=js1.getString("msg");
          Assert.assertEquals(actualMsg, expectedMessage);
    }


    @When("^user changes its address and we update using put$")
    public void user_changes_its_address_and_we_update_using_put() throws Throwable {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        putResponse= given().log().all().queryParam("key","qaclick123")
        .queryParam("place_id", place_id)
        .body("{\r\n" + 
        		"\"place_id\":\""+place_id+"\",\r\n" + 
        		"\"address\":\""+expectedAddress+"\",\r\n" + 
        		"\"key\":\"qaclick123\"\r\n" + 
        		"}\r\n" + 
        		"")
        .when().put("/maps/api/place/update/json")
        .then().log().all().assertThat().statusCode(200).extract().response().asString();
    }
    
    
    @When("^user hits the delete end point$")
    public void user_hits_the_delete_end_point() throws Throwable {

    }

    @Then("^place should be successfully deleted$")
    public void place_should_be_successfully_deleted() throws Throwable {

    }
    
    
    @Given("^user has the basic details as following$")
    public void user_has_the_basic_details_as_following(DataTable data) throws Throwable {
        List<List<String>> obj=data.raw();
/*        System.out.println(obj.get(0).get(0));
        System.out.println(obj.get(0).get(1));
        System.out.println(obj.get(0).get(2));
        System.out.println(obj.get(0).get(3));
        System.out.println(obj.get(0).get(4));
        System.out.println(obj.get(0).get(5));
        System.out.println(obj.get(0).get(6));
        System.out.println(obj.get(0).get(7));
*/
        String lattitude=obj.get(0).get(0).toString();
        double lat=Double.parseDouble(lattitude);
        String longitude=obj.get(0).get(1).toString();
        double lng=Double.parseDouble(longitude);
        String acc=obj.get(0).get(2).toString();
        int accuracy=Integer.parseInt(acc);
        String name=obj.get(0).get(3).toString();
        String phone_Number=obj.get(0).get(4).toString();
        String address=obj.get(0).get(5).toString();
        String website=obj.get(0).get(6).toString();
        String language=obj.get(0).get(7).toString();
        RestAssured.baseURI="https://rahulshettyacademy.com";
        request=given().log().all()
        .header("Content-Type","application/json")
        .contentType(ContentType.JSON)
        .queryParam("key", "qaclick123")
        .body(ReuseableMethods.getAddPlaceRequest(lat, lng, accuracy, name, phone_Number, address, website, language));
        
      
    }
    
    

    
    //Jira steps
    @Given("^user has the \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_has_the_something_and_something(String username, String password) throws Throwable {
       	RestAssured.baseURI="http://localhost:8080";
       	request1=given().log().all()
    	.header("Content-type","application/json")
    	.body("{ \"username\": \""+username+"\",\r\n" + 
    			" \"password\": \""+password+"\" }");
    }
    
    @When("^clicks on the post https method$")
    public void clicks_on_the_post_https_method() throws Throwable {
    	response2=request1.when().post("/rest/auth/1/session")
    	.then().log().all().assertThat().statusCode(200).extract().response();
    }

    @Then("^session should get created$")
    public void session_should_get_created() throws Throwable {
          JsonPath js2=ReuseableMethods.rawToJson(response2.asString());
          String name=js2.getString("session.name");
          String value=js2.getString("session.value");
          cookie=name+"="+value;
          System.out.println(cookie);
    }

    //scenario 2
    @Given("^user has the details$")
    public void user_has_the_details() throws Throwable {
        JsonPath js2=ReuseableMethods.rawToJson(response2.asString());
        String name=js2.getString("session.name");
        String value=js2.getString("session.value");
        cook=name+"="+value;
    	RestAssured.baseURI="http://localhost:8080";
    	request=given().log().all()
    	.header("Content-Type","application/json")
    	.header("Cookie",cook)
    	.body("{\r\n" + 
    			"    \"fields\": {\r\n" + 
    			"        \"project\":\r\n" + 
    			"         {\r\n" + 
    			"            \"key\":\"API\"\r\n" + 
    			"        },\r\n" + 
    			"        \"summary\": \"REST API defect for debit card\",\r\n" + 
    			"        \"description\": \"Creating an issue using project keys API and issue type names using the REST API\",\r\n" + 
    			"        \"issuetype\": {\r\n" + 
    			"            \"name\":\"Bug\"\r\n" + 
    			"        }\r\n" + 
    			"    }\r\n" + 
    			"}\r\n" + 
    			"");
    	

    }

    @When("^user clicks on post$")
    public void user_clicks_on_post() throws Throwable {
    	response=request.when().post("/rest/api/2/issue")
    	.then().log().all().assertThat().statusCode(201).extract().response();
    }

    @Then("^new issue should get created and status code should be \"([^\"]*)\"$")
    public void new_issue_should_get_created_and_status_code_should_be_something(String status) throws Throwable {
       int expectedStatus=Integer.parseInt(status);
       int actualStatus=response.getStatusCode();
       Assert.assertEquals(actualStatus, expectedStatus);
       JsonPath js=ReuseableMethods.rawToJson(response.asString());
       jiraIssueId=js.getString("id");
       JiraIssueKey=js.getString("key");
       System.out.println(jiraIssueId);
       System.out.println(JiraIssueKey);
    }
    
    @Then("^user fetch the issue details$")
    public void user_fetch_the_issue_details() throws Throwable {
       RestAssured.baseURI="http://localhost:8080";
       request=given().log().all()
       .header("Cookie",cook)
       .pathParam("id", jiraIssueId);
    }

    @Then("^it should be fetched successfully and status code should be \"([^\"]*)\"$")
    public void it_should_be_fetched_successfully_and_status_code_should_be_something(String strArg1) throws Throwable {
      response=request.when().get("/rest/api/2/issue/{id}")
        .then().log().all().assertThat().statusCode(200).extract().response();
      JsonPath js=ReuseableMethods.rawToJson(response.asString());
      String id=js.getString("id");
     // Assert.assertEquals(id, jiraIssueId);
    }
    
    

    @Given("^user has the \"([^\"]*)\" and \"([^\"]*)\" using sessionFilter$")
    public void user_has_the_something_and_something_using_sessionfilter(String username, String password) throws Throwable {
       	RestAssured.baseURI="http://localhost:8080";
       	request1=given().log().all()
    	.header("Content-type","application/json")
    	.body("{ \"username\": \""+username+"\",\r\n" + 
    			" \"password\": \""+password+"\" }")
    	.filter(session);
    }
    
    
    @Given("^user has the details with session as well$")
    public void user_has_the_details_with_session_as_well() throws Throwable {
    	RestAssured.baseURI="http://localhost:8080";
    	request=given().log().all()
    	.header("Content-Type","application/json")
    	.filter(session)
    	.body("{\r\n" + 
    			"    \"fields\": {\r\n" + 
    			"        \"project\":\r\n" + 
    			"         {\r\n" + 
    			"            \"key\":\"API\"\r\n" + 
    			"        },\r\n" + 
    			"        \"summary\": \"REST API defect for debit card using session filter\",\r\n" + 
    			"        \"description\": \"Creating an issue using project keys API and issue type names using the REST API and using session filter\",\r\n" + 
    			"        \"issuetype\": {\r\n" + 
    			"            \"name\":\"Bug\"\r\n" + 
    			"        }\r\n" + 
    			"    }\r\n" + 
    			"}\r\n" + 
    			"");
    }
    
    
    @Then("^user fetch the issue details using session$")
    public void user_fetch_the_issue_details_using_session() throws Throwable {
        RestAssured.baseURI="http://localhost:8080";
        request=given().log().all()
        .filter(session)
        .pathParam("id", jiraIssueId);
    }
    

    @Then("^add the attachment as a proof$")
    public void add_the_attachment_as_a_proof() throws Throwable {
         //   /rest/api/2/issue/{issueIdOrKey}/attachments
    	File f=new File("C:\\Users\\NIKHIL ARORA\\Desktop\\Jiratxt.txt");
    	RestAssured.baseURI="http://localhost:8080";
    	given().log().all()
    	.filter(session)
    	.pathParam("id",jiraIssueId)
    	.header("X-Atlassian-Token","no-check")
    	.header("Content-Type","multipart/form-data")
    	.multiPart("file",f)
    	.when().post("/rest/api/2/issue/{id}/attachments")
    	.then().log().all().assertThat().statusCode(200);
    	
    }
    
    
    
    @Given("^user has the basic details for entering a place$")
    public void user_has_the_basic_details_for_entering_a_place() throws Throwable {
    	RestAssured.baseURI="https://rahulshettyacademy.com";
    	AddPlaceRequest addplacerequest=new AddPlaceRequest();
    	addplacerequest.setAccuracy(50);
    	addplacerequest.setAddress("11, Nikhil Aggarwal,USA , Colon street");
    	addplacerequest.setLanguage("Hindi");
    	addplacerequest.setName("Nikhil Aggarwal");
    	addplacerequest.setPhone_number("+651 456 8899");
    	addplacerequest.setWebsite("https://www.google.com");
    	Location l=new Location();
    	l.setLat(38.66);
    	l.setLng(33.42);
    	addplacerequest.setLocation(l);
    	ArrayList<String> al=new ArrayList();
    	al.add("shoe park");
    	al.add("shop");
    	addplacerequest.setTypes(al);
    	request=given().log().all()
    	.queryParam("key", "qaclick123")
    	.contentType(ContentType.JSON)
    	.header("Content-Type","application/json")
    	.body(addplacerequest);

    	
 
    }

    @When("^user clicks on the post request$")
    public void user_clicks_on_the_post_request() throws Throwable {

    	response=request.when().post("/maps/api/place/add/json")
    	.then().log().all().assertThat().statusCode(200).extract().response();
    }

    @Then("^place should get added and status should be \"([^\"]*)\"$")
    public void place_should_get_added_and_status_should_be_something(String statusCode) throws Throwable {
             JsonPath js=ReuseableMethods.rawToJson(response.asString());
             int actualStatusCode=response.getStatusCode();
             int expectedStatusCode=Integer.parseInt(statusCode);
             Assert.assertEquals(actualStatusCode, expectedStatusCode);
    	
    }
    
    @When("^user clicks on get details$")
    public void user_clicks_on_get_details() throws Throwable {
    	RestAssured.baseURI="https://rahulshettyacademy.com";
    	JsonPath js=ReuseableMethods.rawToJson(response.asString());
    	String place_id=js.getString("place_id");
    	getPlaceResponse=given().log().all()
    	.queryParam("key","qaclick123")
    	.queryParam("place_id", place_id)
    	.when().get("/maps/api/place/get/json")
    	.then().log().all().extract().response().as(GetPlaceResponse.class);

    }

    @Then("^details should ge fetched successfully and status code should be \"([^\"]*)\"$")
    public void details_should_ge_fetched_successfully_and_status_code_should_be_something(String strArg1) throws Throwable {
    			System.out.println(getPlaceResponse.getLocation().getLatitude());
    			System.out.println(getPlaceResponse.getLocation().getLongitude());
    			System.out.println(getPlaceResponse.getAccuracy());
    			System.out.println(getPlaceResponse.getName());
    			System.out.println(getPlaceResponse.getPhone_number());
    			System.out.println(getPlaceResponse.getAddress());
    			System.out.println(getPlaceResponse.getTypes());
    			System.out.println(getPlaceResponse.getWebsite());
    			System.out.println(getPlaceResponse.getLanguage());
    	
    }
    
    
    
    
    @Given("^user has the basic details for entering a place and using request spec builder$")
    public void user_has_the_basic_details_for_entering_a_place_and_using_request_spec_builder() throws Throwable {
    	
    	requestSpec=new RequestSpecBuilder().setContentType(ContentType.JSON)
    			.setBaseUri("https://rahulshettyacademy.com")
    			 .addQueryParam("key","qaclick123")
    			 .build();
    	AddPlaceRequest addplacerequest=new AddPlaceRequest();
    	addplacerequest.setAccuracy(50);
    	addplacerequest.setAddress("11, Nikhil Aggarwal,USA , Colon street");
    	addplacerequest.setLanguage("Hindi");
    	addplacerequest.setName("Nikhil Aggarwal");
    	addplacerequest.setPhone_number("+651 456 8899");
    	addplacerequest.setWebsite("https://www.google.com");
    	Location l=new Location();
    	l.setLat(38.66);
    	l.setLng(33.42);
    	addplacerequest.setLocation(l);
    	ArrayList<String> al=new ArrayList();
    	al.add("shoe park");
    	al.add("shop");
    	addplacerequest.setTypes(al);
    	request=given().log().all().spec(requestSpec)
    	.body(addplacerequest);
    }

    @When("^user clicks on get details using request spec builder$")
    public void user_clicks_on_get_details_using_request_spec_builder() throws Throwable {

    	JsonPath js=ReuseableMethods.rawToJson(response.asString());
    	String place_id=js.getString("place_id");
    	getPlaceResponse=given().log().all()
    	.spec(requestSpec)
    	.queryParam("place_id", place_id)
    	.when().get("/maps/api/place/get/json")
    	.then().log().all().extract().response().as(GetPlaceResponse.class);
    }
    
    
    @When("^user clicks on the post request and use response spec builder$")
    public void user_clicks_on_the_post_request_and_use_response_spec_builder() throws Throwable {
    	 responseSpec=new ResponseSpecBuilder().expectContentType(ContentType.JSON)
    	.expectStatusCode(200).build();
    	response=request.when().post("/maps/api/place/add/json")
    	.then().log().all().spec(responseSpec).extract().response();

    }

    @When("^user clicks on get details using request spec builder and response spec builder$")
    public void user_clicks_on_get_details_using_request_spec_builder_and_response_spec_builder() throws Throwable {
    	JsonPath js=ReuseableMethods.rawToJson(response.asString());
    	String place_id=js.getString("place_id");
    	getPlaceResponse=given().log().all()
    	.spec(requestSpec)
    	.queryParam("place_id", place_id)
    	.when().get("/maps/api/place/get/json")
    	.then().log().all().spec(responseSpec).extract().response().as(GetPlaceResponse.class);
    }

}

