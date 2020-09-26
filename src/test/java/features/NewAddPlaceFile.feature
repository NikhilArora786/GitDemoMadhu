Feature: Verifying Add place API using pojo classes

@AddPlaceVerification
Scenario: User is adding new place using add place API
Given user has the basic details for entering a place
When user clicks on the post request
Then place should get added and status should be "200"



@GetPlaceVerification
Scenario: User is adding new place using add place API
Given user has the basic details for entering a place
When user clicks on the post request
Then place should get added and status should be "200"
When user clicks on get details
Then details should ge fetched successfully and status code should be "200"


@GetPlaceVerificationRequestSpec
Scenario: User is adding new place using add place API 
Given user has the basic details for entering a place and using request spec builder
When user clicks on the post request
Then place should get added and status should be "200"
When user clicks on get details using request spec builder
Then details should ge fetched successfully and status code should be "200"


@GetPlaceVerificationRequestSpecResponseSpec
Scenario: User is adding new place using add place API 
Given user has the basic details for entering a place and using request spec builder
When user clicks on the post request and use response spec builder
Then place should get added and status should be "200"
When user clicks on get details using request spec builder and response spec builder
Then details should ge fetched successfully and status code should be "200"



