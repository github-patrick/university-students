package com.university.service;

import com.university.dtos.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    void deleteAllStudents();
}
