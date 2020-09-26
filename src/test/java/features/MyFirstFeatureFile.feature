Feature: Verifying Add place API

Scenario: verify whether the user is able to add the place in google or not
Given user has the basic details
When user hit the add place api
Then place should get added to the backend

Scenario: verify whether the user is able to add the place in google or not and then validations
Given user has the basic details
When user hit the add place api
Then place should get added and scope should be "APP" and status should be "OK"


Scenario: verify whether the user is able to add the place in google or not and then validations
Given user has the basic details
When user hit the add place api
Then place should get added and scope should be "APP" and status should be "OK"
Then hit the get end point
And verify the place should be successfully retrived with status code as "200"


Scenario: verify whether the user is able to update the place in google or not and then validations
Given user has the basic details
When user hit the add place api
Then place should get added and scope should be "APP" and status should be "OK"
Then hit the get end point
And verify the place should be successfully retrived with status code as "200"
When user changes its address and we update using put
Then new place should be added successfully
Then hit the get end point
And verify the place should be successfully retrived with status code as "200"

@Test
Scenario: verify whether the user is able to update the place in google or not and then validations
Given user has the basic details
When user hit the add place api
Then place should get added and scope should be "APP" and status should be "OK"
Then hit the get end point
And verify the place should be successfully retrived with status code as "200"
When user changes its address and we update using put
Then new place should be added successfully
Then hit the get end point
And verify the place should be successfully retrived with status code as "200"
When user hits the delete end point
Then place should be successfully deleted

@smoke
Scenario: verify whether the user is able to update the place in google or not and then validations by sending details from feature file
Given user has the basic details as following
|38.38     |33.42    |80      |Front house |(+91) 988 893 9999|24, side layout, USA 09|http://google.com  |PUNJABI-IN|
When user hit the add place api
Then place should get added and scope should be "APP" and status should be "OK" 

@smoke
Scenario: verify whether the user is able to update the place in google or not and then validations by sending details from feature file
Given user has the basic details as following
|38.383 |33.427|50      |Nikhil house |(+91) 883 893 3937|18, side layout, USA 09|http://tttt.com    |ENGLISH-IN|
When user hit the add place api
Then place should get added and scope should be "APP" and status should be "OK"

 