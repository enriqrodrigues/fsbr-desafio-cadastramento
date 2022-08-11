package com.fsbr.desafio.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationResponse {

    private Long id;

    private String name;

    private String cpf;

    private String state;

    private String city;

}
