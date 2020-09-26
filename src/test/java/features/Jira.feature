Feature: Verfiy  Jira API's functionality



Scenario: As a user , in Jira I want to firstly create a session
Given user has the "NikhilArora786" and "Nik@sdlc@123"
When clicks on the post https method
Then session should get created


Scenario: As user has already created the cookie now usign that create a new issue in Jira
Given user has the "NikhilArora786" and "Nik@sdlc@123"
When clicks on the post https method
Then session should get created
Given user has the details
When user clicks on post
Then new issue should get created and status code should be "201"

@Jira
Scenario: As user has already created the cookie now usign that create a new issue in Jira
Given user has the "NikhilArora786" and "Nik@sdlc@123"
When clicks on the post https method
Then session should get created
Given user has the details
When user clicks on post
Then new issue should get created and status code should be "201"
Then user fetch the issue details
Then it should be fetched successfully and status code should be "200"


@JiraSmoke
Scenario: As user has already created the cookie but now grab that using SessionFilter now usign that create a new issue in Jira
Given user has the "NikhilArora786" and "Nik@sdlc@123" using sessionFilter
When clicks on the post https method
Given user has the details with session as well
When user clicks on post
Then new issue should get created and status code should be "201"
Then user fetch the issue details using session
Then it should be fetched successfully and status code should be "200"




@JiraSmoke123
Scenario: As user has already created the cookie but now grab that using SessionFilter now usign that create a new issue in Jira
Given user has the "NikhilArora786" and "Nik@sdlc@123" using sessionFilter
When clicks on the post https method
Given user has the details with session as well
When user clicks on post
Then new issue should get created and status code should be "201"
Then add the attachment as a proof
Then user fetch the issue details using session
Then it should be fetched successfully and status code should be "200"