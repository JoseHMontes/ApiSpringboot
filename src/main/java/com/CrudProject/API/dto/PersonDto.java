package com.CrudProject.API.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {

    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Email(message = "email invalid")
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "phone cannot be empty")
    private String phone;
    @NotEmpty(message = "password cannot be empty")
    private String password;

}