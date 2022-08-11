package com.fsbr.desafio.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotBlank
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 11, max = 11)
    //@CPF
    private String cpf;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50)
    private String state;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50)
    private String city;

}
