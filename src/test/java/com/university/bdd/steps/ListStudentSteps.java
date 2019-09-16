package com.university.bdd.steps;

import com.university.bdd.utils.StudentUtils;
import com.university.dtos.StudentDto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ListStudentSteps {

    private Context context;

    public ListStudentSteps(Context context) {
        this.context = context;
    }

    @Given("I have {int} registered students")
    public void i_have_registered_students(Integer count) {
        IntStream.range(0,count).forEach(i ->
                StudentUtils.createStudent(StudentUtils.getStudent()));
    }

    @When("I retrieve all students")
    public void i_retrieve_all_students() {
        Response response = StudentUtils.getAllStudents();
        context.setResponse(response);
    }

    @Then("I should have a list of {int} students")
    public void i_should_have_a_list_of_students(Integer numberOfStudents) {
        Integer listSize = Arrays.asList(context.getResponse().as(StudentDto[].class)).size();
        assertEquals(numberOfStudents, listSize);
    }


}
