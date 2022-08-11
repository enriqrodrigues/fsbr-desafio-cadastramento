package com.fsbr.desafio.controller;

import com.fsbr.desafio.model.response.StateResponse;
import com.fsbr.desafio.service.StateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StateService stateService;

    StateResponse response = StateResponse.builder()
            .id(1L).state("Pernambuco").build();

    @Test
    void findAll_returnListOfStateResponse_whenCalledWithSuccess() throws Exception {
        when(stateService.findAll()).thenReturn(List.of(response));

        mockMvc.perform(MockMvcRequestBuilders.get("/estados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(response.getId()))
                .andExpect(jsonPath("$.[0].state").value(response.getState()));
    }

}