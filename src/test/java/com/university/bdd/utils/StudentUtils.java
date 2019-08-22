package com.university.bdd.utils;

import com.university.dtos.StudentDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class StudentUtils {

    public static Response createStudent(StudentDto studentDto) {
        return RestAssured.
                given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(studentDto).
                        when()
                .post("students").
                        andReturn();
    }




}
