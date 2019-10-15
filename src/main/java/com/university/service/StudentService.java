package com.university.service;

import com.university.domain.Student;
import com.university.dtos.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    void deleteAllStudents();

    Optional<StudentDto> getStudent(Long id);
}
