package com.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.domain.DegreeType;
import com.university.dtos.StudentDto;
import com.university.exception.StudentDoesNotExistException;
import com.university.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private StudentDto studentDto;
    private StudentDto studentDtoSaved;

    public StudentControllerTest() {
        studentDto = StudentDto.builder().firstName("Jan").lastName("Melbourne")
                .degreeType(DegreeType.BSc)
                .branch("Kings Cross")
                .country("India").deposit(200.00).age(19).build();

        studentDtoSaved = StudentDto.builder().id(1l).firstName("Jan").lastName("Melbourne")
                .country("India").deposit(200.00).age(29)
                .createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now()).build();
    }

    @Test
    public void shouldCreateStudent() throws Exception {

        given(studentService.addStudent(studentDto)).willReturn(studentDtoSaved);


        mockMvc.perform(post("/students")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(studentDto)))
                .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists());

    }

    @Test
    public void shouldNotCreateStudentUnder18() throws Exception {

        studentDto.setAge(17);
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentDto)))
                .andDo(print())
                .andExpect(jsonPath("$.error").value("Age must be greater than 18"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateStudentOver120() throws Exception {

        studentDto.setAge(121);
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentDto)))
                .andDo(print())
                .andExpect(jsonPath("$.error").value("Age must be greater than 120"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldListAllStudents() throws Exception {
        given(studentService.getAllStudents()).willReturn(Arrays.asList(studentDtoSaved));
        mockMvc.perform(get("/students")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldDeleteAllStudents() throws Exception {
        mockMvc.perform(delete("/students"))
                .andExpect(status().isOk());
    }

    @Test
    public void unauthorizedClientCannotDeleteAllStudents() throws Exception {
        mockMvc.perform(delete("/students/"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void shouldRetrieveStudent() throws Exception {
        given(studentService.getStudent(1L)).willReturn(Optional.of(studentDto));
        mockMvc.perform(get("/students/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotRetrieveStudentThatDoesNotExist() throws Exception {
        given(studentService.getStudent(111L)).willThrow(StudentDoesNotExistException.class);
        mockMvc.perform(get("/students/111")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        mockMvc.perform(put("/students/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(studentDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotUpdateStudentWithDepositLessThanZero() throws Exception {
        studentDto.setAge(100);
        studentDto.setDeposit(-100.00);
        studentDto.setCountry("Mexico");

        mockMvc.perform(put("/students/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(studentDto)))
                .andExpect(jsonPath("$.error").value("The deposit value must be 0 or above"))
                .andExpect(status().isBadRequest());
    }

}