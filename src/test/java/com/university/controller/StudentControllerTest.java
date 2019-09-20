package com.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.config.SecurityConfig;
import com.university.dtos.StudentDto;
import com.university.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
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
    public void shouldNotCreateStudent() throws Exception {

        studentDto.setAge(17);
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentDto)))
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
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void unauthorizedClientCannotDeleteAllStudents() throws Exception {
        mockMvc.perform(delete("/students"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

}