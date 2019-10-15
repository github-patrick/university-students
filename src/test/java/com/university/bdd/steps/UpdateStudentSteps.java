package com.university.bdd.steps;

import com.university.bdd.utils.StudentUtils;
import com.university.domain.Student;
import com.university.dtos.StudentDto;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class UpdateStudentSteps {

    private Context context;

    public UpdateStudentSteps(Context context) {
        this.context = context;
    }

}
