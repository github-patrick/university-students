package com.university.service;

import com.university.domain.Student;
import com.university.dtos.StudentDto;
import com.university.exception.StudentDoesNotExistException;
import com.university.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.rmi.StubNotFoundException;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Spy
    private ModelMapper modelMapper;

//  Injects mocks and creates an instance of the object
    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentDto studentDto;
    private Student student;

    public StudentServiceImplTest() {
        studentDto = StudentDto.builder().id(1l).firstName("Jan").lastName("Melbourne")
                .country("India").deposit(200.00).age(19).build();

        student = Student.builder().id(1l).firstName("Jan").lastName("Melbourne")
                .country("India").deposit(200.00).age(19).build();

    }

    @Test(expected = StudentDoesNotExistException.class)
    public void shouldNotUpdateStudentThatDoesNotExist() throws Exception {
        given(studentRepository.findById(1l)).willReturn(Optional.empty());
        studentService.updateStudent(studentDto, 1l);

    }

    @Test
    public void shouldUpdateStudentIfStudentExists() {
        given(studentRepository.findById(1l)).willReturn(Optional.of(student));
        studentService.updateStudent(studentDto, 1l);

        verify(studentRepository, times(1)).save(student);
    }

}