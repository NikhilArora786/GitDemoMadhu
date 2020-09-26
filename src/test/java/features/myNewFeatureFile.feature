Feature: Verifying Add place API using pojo classes

@AddPlaceVerification
Scenario: User is adding new place using add place API
Given user has the basic details for entering a place
When user clicks on the post request
Then place should get added and status should be "200"