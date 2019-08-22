package com.university.controller;

import com.university.dtos.StudentDto;
import com.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(StudentController.STUDENT_REQUEST_MAPPING)
public class StudentController {

    public final static String STUDENT_REQUEST_MAPPING = "/students";
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid StudentDto studentDto) {
        StudentDto studentDtoSaved = studentService.addStudent(studentDto);
        return new ResponseEntity(studentDtoSaved, HttpStatus.CREATED);
    }


}
