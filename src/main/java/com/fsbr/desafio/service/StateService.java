package com.fsbr.desafio.service;

import com.fsbr.desafio.model.entity.State;
import com.fsbr.desafio.model.response.StateResponse;
import com.fsbr.desafio.repository.StateRepository;
import com.fsbr.desafio.util.StateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateService {

    @Autowired
    StateMapper stateMapper;

    @Autowired
    StateRepository stateRepository;

    public List<StateResponse> findAll() {
        List<StateResponse> stateResponses = new ArrayList<>();
        List<State> states = stateRepository.findAll();
        for (State state : states) {
            StateResponse response = stateMapper.mapperToStateResponse(state);
            stateResponses.add(response);
        }
        return stateResponses;
    }
}
