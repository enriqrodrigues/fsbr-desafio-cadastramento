package com.fsbr.desafio.util;

import com.fsbr.desafio.model.entity.Registration;
import com.fsbr.desafio.model.request.RegistrationRequest;
import com.fsbr.desafio.model.response.RegistrationResponse;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public Registration mapperToRegistration(RegistrationRequest request) {
        Registration registration = new Registration();
        registration.setCpf(request.getCpf());
        registration.setName(request.getName());
        registration.setState(request.getState());
        registration.setCity(request.getCity());
        return registration;
    }

    public RegistrationResponse mapperToRegistrationResponse(Registration registration) {
        RegistrationResponse response = RegistrationResponse.builder().build();
        response.setId(registration.getId());
        response.setCpf(registration.getCpf());
        response.setName(registration.getName());
        response.setState(registration.getState());
        response.setCity(registration.getCity());
        return response;
    }


}
