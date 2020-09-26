$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("NewAddPlaceFile.feature");
formatter.feature({
  "line": 1,
  "name": "Verifying Add place API using pojo classes",
  "description": "",
  "id": "verifying-add-place-api-using-pojo-classes",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 30,
  "name": "User is adding new place using add place API",
  "description": "",
  "id": "verifying-add-place-api-using-pojo-classes;user-is-adding-new-place-using-add-place-api",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 29,
      "name": "@GetPlaceVerificationRequestSpecResponseSpec"
    }
  ]
});
formatter.step({
  "line": 31,
  "name": "user has the basic details for entering a place and using request spec builder",
  "keyword": "Given "
});
formatter.step({
  "line": 32,
  "name": "user clicks on the post request and use response spec builder",
  "keyword": "When "
});
formatter.step({
  "line": 33,
  "name": "place should get added and status should be \"200\"",
  "keyword": "Then "
});
formatter.step({
  "line": 34,
  "name": "user clicks on get details using request spec builder and response spec builder",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "details should ge fetched successfully and status code should be \"200\"",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepDefinitions.user_has_the_basic_details_for_entering_a_place_and_using_request_spec_builder()"
});
formatter.result({
  "duration": 1772662600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepDefinitions.user_clicks_on_the_post_request_and_use_response_spec_builder()"
});
formatter.result({
  "duration": 3777103900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 45
    }
  ],
  "location": "MyStepDefinitions.place_should_get_added_and_status_should_be_something(String)"
});
formatter.result({
  "duration": 8915000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepDefinitions.user_clicks_on_get_details_using_request_spec_builder_and_response_spec_builder()"
});
formatter.result({
  "duration": 2629964800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 66
    }
  ],
  "location": "MyStepDefinitions.details_should_ge_fetched_successfully_and_status_code_should_be_something(String)"
});
formatter.result({
  "duration": 294500,
  "status": "passed"
});
});