package com.university.bdd.steps;

import com.university.bdd.utils.StudentUtils;
import com.university.dtos.StudentDto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ListStudentSteps {

    private Context context;

    public ListStudentSteps(Context context) {
        this.context = context;
    }

    @Given("I have {int} registered students")
    public void i_have_registered_students(Integer count) {
        IntStream.range(0,count).forEach(i ->
                StudentUtils.createStudent(StudentUtils.getStudentDto()));
    }

    @When("I retrieve all students")
    public void i_retrieve_all_students() {
        Response response = StudentUtils.getAllStudents();
        context.setResponse(response);
    }

    @When("I attempt to retrieve a student that does not exist")
    public void i_attempt_to_retrieve_a_student_that_does_not_exist() {
        StudentDto studentDto = StudentDto.builder().id(2l).firstName("Linda").lastName("Bag")
                .build();

        context.setResponse(StudentUtils.getStudent(studentDto));
    }

    @When("I retrieve a student")
    public void i_retrieve_a_student() {
        List<StudentDto> studentDtoList = Arrays.asList(StudentUtils.getAllStudents().as(StudentDto[].class));
        context.setResponse(StudentUtils.getStudent(studentDtoList.stream().findFirst().get()));
    }

    @Then("I should have the student")
    public void i_should_have_the_student() {
        StudentDto studentDto = context.getResponse().as(StudentDto.class);
        assertNotNull(studentDto);
        assertNotNull(studentDto.getId());
    }

    @Then("I should not have a student")
    public void i_should_not_have_a_student() {
        System.out.println(context.getResponse().body().print());
    }

    @Then("I should have a list of {int} students")
    public void i_should_have_a_list_of_students(Integer numberOfStudents) {
        Integer listSize = Arrays.asList(context.getResponse().as(StudentDto[].class)).size();
        assertEquals(numberOfStudents, listSize);
    }


}
