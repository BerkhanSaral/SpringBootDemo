package com.tpe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStudentDTO {

    @NotBlank(message = "name can not be blank!")//request anında doğrulama
    @Size(min = 2, max = 50, message = "name must be between 2 and 50")//request anında doğrulama
    private String name;

    @NotBlank(message = "lastname can not be blank!")//request anında doğrulama
    @Size(min = 2, max = 50, message = "lastname must be between 2 and 50")//request anında doğrulama
    private String lastName;

    @Column(unique = true)
    @Email(message = "please provide valid email!")//aaa@bbb.ccc email formatında olmasını doğrular
    //@Pattern()
    private String email;
}
