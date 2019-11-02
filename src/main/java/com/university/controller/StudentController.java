package com.university.controller;

import com.university.dtos.StudentDto;
import com.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return new ResponseEntity(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {
        return new ResponseEntity(studentService.getStudent(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAllStudents() {
        studentService.deleteAllStudents();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody @Valid StudentDto studentDto, @PathVariable Long id) {
        studentService.updateStudent(studentDto, id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
