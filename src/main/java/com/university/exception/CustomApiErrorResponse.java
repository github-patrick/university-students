package com.university.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomApiErrorResponse {

    private LocalDateTime timeStamp;
    private int status;
    private String error;
}
