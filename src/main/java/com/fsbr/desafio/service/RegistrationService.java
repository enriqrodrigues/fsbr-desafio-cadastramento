package com.fsbr.desafio.service;

import com.fsbr.desafio.exception.ResourceNotFoundException;
import com.fsbr.desafio.model.entity.Registration;
import com.fsbr.desafio.model.request.RegistrationRequest;
import com.fsbr.desafio.model.response.RegistrationResponse;
import com.fsbr.desafio.repository.RegistrationRepository;
import com.fsbr.desafio.util.RegistrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {

    @Autowired
    RegistrationMapper registrationMapper;

    @Autowired
    RegistrationRepository registrationRepository;

    public List<RegistrationResponse> findAll() {
        List<RegistrationResponse> responses = new ArrayList<>();
        List<Registration> registrations = registrationRepository.findAll();
        for (Registration registration : registrations) {
            RegistrationResponse response = registrationMapper.mapperToRegistrationResponse(registration);
            responses.add(response);
        }
        return responses;
    }

    public RegistrationResponse findbyCPF(String cpf) {
        Registration registration = registrationRepository.findByCpf(cpf);
        if (registration == null) {
            throw new ResourceNotFoundException("Dados não localizados, tente novamente.");
        }
        return registrationMapper.mapperToRegistrationResponse(registration);
    }

    public Map<String, String> save(RegistrationRequest request) {
        if (registrationRepository.findByCpf(request.getCpf()) != null) {
            throw new IllegalArgumentException(
                    "CPF já cadastrado. Verifique os dados informados e tente novamente");
        }

        Registration registration = registrationMapper.mapperToRegistration(request);
        registration = registrationRepository.save(registration);

        Map<String, String> message;

        if (registration.getId() != null) {
            message = Map.of("message", "Cadastro Realizado com Sucesso");
        } else {
            throw new NullPointerException("Houve algum problema na inclusão, tentar novamente.");
        }

        return message;
    }

    public Map<String, String> update(Long id, RegistrationRequest request) {
        Registration registrationExisting = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dados não localizados, tente novamente."));

        Registration registration = registrationMapper.mapperToRegistration(request);
        registration.setId(registrationExisting.getId());
        registration = registrationRepository.save(registration);

        Map<String, String> message;
        if (registration.getId() != null) {
            message = Map.of("message", "Cadastro Atualizado com Sucesso");
        } else {
            throw new NullPointerException("Houve algum problema na alteração, tentar novamente.");
        }
        return message;
    }

    public Map<String, String> delete(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dados não localizados, tente novamente."));

        try {
            registrationRepository.delete(registration);
            return Map.of("message", "Cadastro Excluído com Sucesso");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Houve algum problema na exclusão, tentar novamente");
        }

    }

}
