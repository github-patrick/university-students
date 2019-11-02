Feature: As a client
  I want to be able to update students details
  So that I can amend changes

#  A scenario is a possible way to test an application
  Scenario: A client updates a student (via a put method)
    Given I have 1 registered students
    And I retrieve a student to be updated
    And I change the student country to "Brazil"
    When I update the student
    Then I should the student nationality should be "Brazil"

  Scenario: A client attempts to update a student that does not exist
    Given I have 1 registered students
    And I retrieve a student to be updated
    When I update a student that does not exist
    Then I should get the following error message "Student not found with id 12342"

  Scenario: A client attempts to update a student with invalid country
    Given I have 1 registered students
    And I retrieve a student to be updated
    And I change the student country to "France"
    When I update the student
    And I retrieve a student to be updated
    And I change the student country to ""
    When I update the student
    Then I should the student nationality should be "France"
