package com.university.bdd.steps;

import com.university.dtos.StudentDto;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Context {

    private StudentDto studentDto;
    private Response response;
}
