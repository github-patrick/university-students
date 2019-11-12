Feature: Creation of Students
  In order to have students registered to the university
  As an student
  I want to be added to the application when I request to be registered


#  A scenario is an example of the system’s behavior from one or more users’ perspectives.
  Scenario: A eligible student is added to the application
    Given I have a student that wants to be registered
    When I register the student
    Then the student should be registered

  Scenario: A student cannot be added if he or she is under 18
    Given I have a student that wants to be registered who is under 18
    When I register the student
    Then the student should not be registered

  Scenario: A student cannot be added if he or she does not specify a nationality
    Given I have a student that wants to be registered that does not have a nationality
    When I register the student
    Then the student should not be registered

  Scenario: A student cannot be added if he or she does not specify a first name
    Given I have a student that wants to be registered that does not first name
    When I register the student
    Then the student should not be registered

  Scenario: A student cannot be added if he or she does not specify a last name
    Given I have a student that wants to be registered that does not last name
    When I register the student
    Then the student should not be registered

  Scenario: A student cannot be added if his or her deposit is less than 0
    Given I have a student that wants to be registered that has a deposit less than 0
    When I register the student
    Then the student should not be registered


#  Acceptance criteria are a set of rules which cover aspects of a system’s behavior, and from which scenarios can be derived.