package com.example.Assignment.mapper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Data
public class UserDTO {
    private int id;
    @NotNull(message = "u_name is required")
    @NotBlank(message = "u_name is required")
    private String u_name;
    @NotNull(message = "department is required")
    @NotBlank(message = "department is required")
    private String department;
}
