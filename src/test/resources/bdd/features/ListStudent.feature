Feature: Listing of students

  In order to be able to see all students that are registered
  As a client
  I want to be able to list all students
  So that I can see who is registered


  Scenario: A client can list all the students registered to the university
    Given I have 4 registered students
    When I retrieve all students
    Then I should have a list of 4 students