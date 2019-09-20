package com.university.service;

import com.university.domain.Student;
import com.university.dtos.StudentDto;
import com.university.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        log.info("Persisting student to database ");
        Student student = modelMapper.map(studentDto, Student.class);
        StudentDto studentSaved = modelMapper.map(studentRepository.save(student), StudentDto.class);
        log.info("Student saved");
        return studentSaved;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> studentDtos = new ArrayList<>();

        studentRepository.findAll().forEach(
                student -> studentDtos.add(modelMapper.map(student, StudentDto.class)));
        return studentDtos;
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
