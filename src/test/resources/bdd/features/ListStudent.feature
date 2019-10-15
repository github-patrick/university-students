Feature: Listing of students

  In order to be able to see all students that are registered
  As a client
  I want to be able to list all students
  So that I can see who is registered


  Scenario: A client can list all the students registered to the university
    Given I have 4 registered students
    When I retrieve all students
    Then I should have a list of 4 students


  Scenario: A client can get a student registered to the university
    Given I have 1 registered students
    When I retrieve a student
    Then I should have the student

  Scenario: A client attempts to retrieve a student that does not exist
    Given I have 1 registered students
    When I attempt to retrieve a student that does not exist
    Then I should not have a student