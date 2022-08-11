package com.fsbr.desafio.repository;

import com.fsbr.desafio.model.entity.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class StateRepositoryTest {

    @Autowired
    StateRepository stateRepository;

    State state = State.builder().name("Pernambuco").build();

    @Test
    void findAll_returnListOfRegistration_whenCalledWithSuccess() {
        state = stateRepository.save(state);
        List<State> states = stateRepository.findAll();

        assertTrue(states.size() > 0);
    }

}