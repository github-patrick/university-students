package com.university.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be blank")
    private String firstName;

    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be blank")
    private String lastName;

    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be blank")
    private String country;

    @Min(value = 18, message = "Age must be greater than 18")
    @Max(value = 120, message = "Age must be greater than 120")
    private int age;

    @Min(value = 0L, message = "The value must be positive")
    private Double deposit;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime modifiedAt;

}
