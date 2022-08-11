package com.fsbr.desafio.util;

import com.fsbr.desafio.model.entity.State;
import com.fsbr.desafio.model.response.StateResponse;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {

    public StateResponse mapperToStateResponse(State state) {
        StateResponse response = StateResponse.builder().build();
        response.setId(state.getId());
        response.setState(state.getName());
        return response;
    }

}
