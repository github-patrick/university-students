package com.university.bdd.utils;

import com.github.javafaker.Faker;
import com.university.domain.DegreeType;
import com.university.dtos.StudentDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.LocalDateTime;


public class StudentUtils {

    private static Faker faker = new Faker();

    public static Response createStudent(StudentDto studentDto) {
        return RestAssured.
                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(studentDto).
                        when()
                .post("students").
                        andReturn();
    }

    public static Response getAllStudents() {
        return RestAssured.given()
                .accept(ContentType.JSON)
                .get("students")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public static StudentDto getStudentDto() {
        return StudentDto.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .country(faker.address().country())
                .degreeType(DegreeType.Masters)
                .branch(faker.address().city())
                .deposit(faker.number().randomDouble(2, 1,140))
                .age(faker.number().numberBetween(18,55))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public static Response getStudent(StudentDto studentDto) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(studentDto)
                .pathParam("id", studentDto.getId())
                .when()
                    .get("students/{id}")
                .andReturn();
    }

    public static Response updateStudent(StudentDto studentDto) {

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(studentDto)
                .pathParam("id", studentDto.getId())
                .log().all()
                .when()
                    .put("students/{id}")

                .then().log().all().extract().response()
                .andReturn();
    }
}
