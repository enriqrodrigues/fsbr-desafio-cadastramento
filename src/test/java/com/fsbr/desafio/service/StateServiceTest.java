package com.fsbr.desafio.service;

import com.fsbr.desafio.model.entity.State;
import com.fsbr.desafio.model.response.StateResponse;
import com.fsbr.desafio.repository.StateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class StateServiceTest {

    @Autowired
    StateService stateService;

    @MockBean
    StateRepository stateRepository;

    State state = State.builder().id(1L).name("Pernambuco").build();

    @Test
    void findAll_returnListOfStateResponse_whenCalledWithSuccess() {
        when(stateRepository.findAll()).thenReturn(List.of(state));

        List<StateResponse> responses = stateService.findAll();

        assertTrue(responses.size() > 0);
        assertEquals(1L, responses.get(0).getId());
        assertEquals("Pernambuco", responses.get(0).getState());
    }
}