package com.university.bdd.steps;

import com.university.bdd.utils.StudentUtils;
import com.university.dtos.StudentDto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StudentSteps {

    @Autowired
    private Context context;


    @Given("I have a student that wants to be registered")
    public void i_have_a_student_that_wants_to_be_registered() {
        StudentDto studentDto = StudentDto.builder()
                .firstName("Jake").lastName("Break").country("Portugal")
                .deposit(200.00)
                .age(21).build();

        context.setStudentDto(studentDto);
    }

    @Given("I have a student that wants to be registered who is under {int}")
    public void i_have_a_student_that_wants_to_be_registered_who_is_under(Integer int1) {
        StudentDto studentDto = StudentDto.builder()
                .firstName("David").lastName("Goliath").country("England")
                .deposit(30.24)
                .age(17).build();
        context.setStudentDto(studentDto);
    }

    @Given("I have a student that wants to be registered that does not nationality")
    public void i_have_a_student_that_wants_to_be_registered_that_does_not_nationality() {
        StudentDto studentDto = StudentDto.builder()
                .firstName("Jake").lastName("Break").country("")
                .deposit(200.00)
                .age(21).build();

        context.setStudentDto(studentDto);

    }

    @Given("I have a student that wants to be registered that does not first name")
    public void i_have_a_student_that_wants_to_be_registered_that_does_not_first_name() {
        StudentDto studentDto = StudentDto.builder()
                .firstName("").lastName("Break").country("Portugal")
                .deposit(200.00)
                .age(21).build();

        context.setStudentDto(studentDto);
    }

    @Given("I have a student that wants to be registered that does not last name")
    public void i_have_a_student_that_wants_to_be_registered_that_does_not_last_name() {
        StudentDto studentDto = StudentDto.builder()
                .firstName("Jake").lastName("").country("Portugal")
                .deposit(200.00)
                .age(21).build();

        context.setStudentDto(studentDto);
    }

    @Given("I have a student that wants to be registered that has a deposit less than {int}")
    public void i_have_a_student_that_wants_to_be_registered_that_has_a_deposit_less_than(Integer int1) {
        StudentDto studentDto = StudentDto.builder()
                .firstName("Jake").lastName("Break").country("Portugal")
                .deposit(-00.01)
                .age(21).build();

        context.setStudentDto(studentDto);
    }

    @When("I register the student")
    public void i_register_the_student() {
        Response response = StudentUtils.createStudent(context.getStudentDto());
        response.then().log().all();
        context.setResponse(response);

        if (response.statusCode()==201) {
            context.setStudentDto(response.as(StudentDto.class));
        }
    }

    @Then("the student should not be registered")
    public void the_student_should_not_be_registered() {
        assertEquals(400, context.getResponse().statusCode());
    }

    @Then("the student should be registered")
    public void the_student_should_be_registered() {
        assertNotNull(context.getStudentDto().getId());
    }

}
