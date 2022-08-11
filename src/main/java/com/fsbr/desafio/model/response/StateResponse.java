package com.fsbr.desafio.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StateResponse {

    private Long id;
    private String state;

}
