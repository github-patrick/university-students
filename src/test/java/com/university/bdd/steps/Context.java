package com.university.bdd.steps;

import com.university.dtos.StudentDto;
import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Context {

    private StudentDto studentDto;
    private Response response;
}
