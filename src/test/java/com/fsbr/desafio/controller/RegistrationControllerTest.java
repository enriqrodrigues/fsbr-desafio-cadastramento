package com.fsbr.desafio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsbr.desafio.model.request.RegistrationRequest;
import com.fsbr.desafio.model.response.RegistrationResponse;
import com.fsbr.desafio.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RegistrationService registrationService;

    RegistrationResponse response = RegistrationResponse.builder()
            .id(1L).name("Fulano de Tal").cpf("11122233300")
            .city("Recife").state("Pernambuco").build();

    RegistrationRequest request = RegistrationRequest.builder()
            .name("Fulano de Tal").cpf("11122233300")
            .city("Recife").state("Pernambuco").build();

    @Test
    void findAll_returnListOfRegistrationResponse_whenCalledWithSuccess() throws Exception {
        when(registrationService.findAll()).thenReturn(List.of(response));

        mockMvc.perform(MockMvcRequestBuilders.get("/cadastramento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(response.getId()))
                .andExpect(jsonPath("$.[0].name").value(response.getName()))
                .andExpect(jsonPath("$.[0].cpf").value(response.getCpf()))
                .andExpect(jsonPath("$.[0].city").value(response.getCity()))
                .andExpect(jsonPath("$.[0].state").value(response.getState()));
    }

    @Test
    void findByCPF_returnRegistrationResponse_whenCalledWithSuccess() throws Exception {
        when(registrationService.findbyCPF("11122233300")).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/cadastramento/11122233300")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.cpf").value(response.getCpf()))
                .andExpect(jsonPath("$.city").value(response.getCity()))
                .andExpect(jsonPath("$.state").value(response.getState()));
    }

    @Test
    void save_returnMapWithConfirmation_whenCalledWithSuccess() throws Exception {
        when(registrationService.save(any()))
                .thenReturn(Map.of("message", "Cadastro Realizado com Sucesso"));

        mockMvc.perform(MockMvcRequestBuilders.post("/cadastramento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message")
                        .value("Cadastro Realizado com Sucesso"));
    }

    @Test
    void update_returnMapWithConfirmation_whenCalledWithSuccess() throws Exception {
        when(registrationService.update(any(Long.class), any(RegistrationRequest.class)))
                .thenReturn(Map.of("message", "Cadastro Atualizado com Sucesso"));

        mockMvc.perform(MockMvcRequestBuilders.put("/cadastramento/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Cadastro Atualizado com Sucesso"));
    }

    @Test
    void delete_returnMapWithConfirmation_whenCalledWithSuccess() throws Exception {
        when(registrationService.delete(any(Long.class)))
                .thenReturn(Map.of("message", "Cadastro Excluído com Sucesso"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/cadastramento/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Cadastro Excluído com Sucesso"));
    }
}