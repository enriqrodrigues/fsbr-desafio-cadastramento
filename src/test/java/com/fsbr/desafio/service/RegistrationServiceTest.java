package com.fsbr.desafio.service;

import com.fsbr.desafio.exception.ResourceNotFoundException;
import com.fsbr.desafio.model.entity.Registration;
import com.fsbr.desafio.model.request.RegistrationRequest;
import com.fsbr.desafio.model.response.RegistrationResponse;
import com.fsbr.desafio.repository.RegistrationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegistrationServiceTest {

    @Autowired
    RegistrationService registrationService;

    @MockBean
    RegistrationRepository registrationRepository;

    Registration registration = Registration.builder()
            .id(1L).name("Fulano de Tal").cpf("11122233300")
            .state("Pernambuco").city("Paulista").build();

    RegistrationRequest request = RegistrationRequest.builder()
            .name("Fulano de Tal").cpf("11122233300")
            .city("Paulista").state("Pernambuco").build();

    @Test
    void findAll_returnListOfRegistrationResponse_whenCalledWithSuccess() {
        when(registrationRepository.findAll()).thenReturn(List.of(registration));

        List<RegistrationResponse> responses = registrationService.findAll();

        assertNotNull(responses);
        assertEquals(responses.get(0).getId(), registration.getId());
        assertEquals(responses.get(0).getName(), registration.getName());
        assertEquals(responses.get(0).getCpf(), registration.getCpf());
        assertEquals(responses.get(0).getCity(), registration.getCity());
        assertEquals(responses.get(0).getState(), registration.getState());
    }

    @Test
    void findbyCPF_returnResgistrationResponse_whenCalledWithSuccess() {
        when(registrationRepository.findByCpf("11122233300")).thenReturn(registration);

        RegistrationResponse response = registrationService.findbyCPF("11122233300");

        assertNotNull(response);
        assertEquals(response.getId(), registration.getId());
        assertEquals(response.getName(), registration.getName());
        assertEquals(response.getCpf(), registration.getCpf());
        assertEquals(response.getCity(), registration.getCity());
        assertEquals(response.getState(), registration.getState());
    }

    @Test
    void findbyCPF_throwResourceNotFoundException_whenCpfNotFound() {
        when(registrationRepository.findByCpf("44455566600")).thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> registrationService.findbyCPF("44455566600"));
    }

    @Test
    void save_saveAndReturnMapWithConfirmation_whenCalledWithSuccess() {
        when(registrationRepository.findByCpf(request.getCpf())).thenReturn(null);
        when(registrationRepository.save(any())).thenReturn(registration);

        Map<String, String> message = registrationService.save(request);

        assertEquals("Cadastro Realizado com Sucesso", message.get("message"));
    }

    @Test
    void save_throwIllegalArgumentException_whenCpfExisting() {
        when(registrationRepository.findByCpf(request.getCpf())).thenReturn(registration);

        assertThrows(IllegalArgumentException.class,
                () -> registrationService.save(request));
    }

    @Test
    void save_returnNullPointerException_whenNotSave() {
        when(registrationRepository.findByCpf(request.getCpf())).thenReturn(null);
        when(registrationRepository.save(any())).thenReturn(null);

        assertThrows(NullPointerException.class,
                () -> registrationService.save(request));
    }

    @Test
    void update_saveAndReturnMapWithConfirmation_whenCalledWithSuccess() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.ofNullable(registration));
        when(registrationRepository.save(any())).thenReturn(registration);

        Map<String, String> message = registrationService.update(1L, request);

        assertEquals("Cadastro Atualizado com Sucesso", message.get("message"));
    }

    @Test
    void update_throwResourceNotFoundException_whenIdNotFound() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> registrationService.update(1L, request));
    }

    @Test
    void update_returnNullPointerException_whenNotSave() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.ofNullable(registration));
        when(registrationRepository.save(any())).thenReturn(null);

        assertThrows(NullPointerException.class,
                () -> registrationService.update(1L, request));
    }

    @Test
    void delete_returnMapWithConfirmation_whenCalledWithSuccess() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.ofNullable(registration));

        Map<String, String> message = registrationService.delete(1L);

        assertEquals("Cadastro Excluído com Sucesso", message.get("message"));
    }

    @Test
    void delete_throwResourceNotFoundException_whenIdNotFound() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> registrationService.delete(1L));
    }

}