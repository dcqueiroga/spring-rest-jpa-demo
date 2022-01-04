package com.example.springrestjpa.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.*;

@Data
public class EmployeeDTO {

    private Long id;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String name;

    @JsonProperty(required = true)
    @NotNull
    @Positive
    private Integer salary;

    @Size(min = 3, max = 3)
    private String department;
}
