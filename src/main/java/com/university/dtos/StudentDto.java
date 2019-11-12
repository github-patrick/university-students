package com.university.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.university.domain.DegreeType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto implements Serializable {

    private Long id;

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotNull(message = "Country cannot be null")
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotNull(message = "Branch cannot be null")
    @NotBlank(message = "Branch cannot be blank")
    private String branch;

    @NotNull(message = "Degree type cannot be null")
    private DegreeType degreeType;

    @Min(value = 18, message = "Age must be greater than 18")
    @Max(value = 120, message = "Age must be greater than 120")
    private int age;

    @Min(value = 0L, message = "The deposit value must be 0 or above")
    private Double deposit;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime modifiedAt;

}
