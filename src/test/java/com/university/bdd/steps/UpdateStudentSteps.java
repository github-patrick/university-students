package com.university.bdd.steps;

import com.university.bdd.utils.StudentUtils;
import com.university.domain.Student;
import com.university.dtos.StudentDto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UpdateStudentSteps {

    private Context context;

    public UpdateStudentSteps(Context context) {
        this.context = context;
    }

    @Given("I retrieve a student to be updated")
    public void i_retrieve_a_student_to_be_updated() {
        List<StudentDto> studentDtoList = Arrays.asList(StudentUtils.getAllStudents().as(StudentDto[].class));
        context.setStudentDto(studentDtoList.stream().findFirst().get());
    }

    @Given("I change the student country to {string}")
    public void i_change_the_student_country_to(String county) {
        context.getStudentDto().setCountry(county);
    }

    @When("I update the student")
    public void i_update_the_student() {
        context.setResponse(StudentUtils.updateStudent(context.getStudentDto()));
    }

    @When("I update a student that does not exist")
    public void i_update_a_student_that_does_not_exist() {
        StudentDto studentDto = context.getStudentDto();
        studentDto.setId(12342L);
        context.setResponse(StudentUtils.updateStudent(studentDto));
    }

    @Then("I should get the following error message {string}")
    public void i_should_get_the_following_error_message(String message) {
        Response response = context.getResponse();
        assertEquals(message, response.jsonPath().getString("error"));
    }

    @Then("I should the student nationality should be {string}")
    public void i_should_the_student_nationality_should_be(String expectedCountry) {
        String country = StudentUtils.getStudent(context.getStudentDto()).as(StudentDto.class).getCountry();
        assertEquals(expectedCountry, country);
    }

}
