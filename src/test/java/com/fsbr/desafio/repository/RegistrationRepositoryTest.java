package com.fsbr.desafio.repository;

import com.fsbr.desafio.model.entity.Registration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RegistrationRepositoryTest {

    @Autowired
    RegistrationRepository registrationRepository;

    Registration registration = Registration.builder()
            .name("Fulano de Tal").cpf("11122233300")
            .state("Pernambuco").city("Paulista")
            .build();

    @Test
    void findAll_returnListOfRegistration_whenCalledWithSuccess() {
        registration = registrationRepository.save(registration);
        List<Registration> registrations = registrationRepository.findAll();

        assertTrue(registrations.size() > 0);
    }

    @Test
    void findByCpf_returnRegistration_whenCalledWithSuccess() {
        registration = registrationRepository.save(registration);
        Registration registration = registrationRepository.findByCpf("11122233300");

        assertEquals("Fulano de Tal", registration.getName());
        assertEquals("Paulista", registration.getCity());
        assertEquals("Pernambuco", registration.getState());
    }

    @Test
    void findById_returnRegistration_whenCalledWithSuccess() {
        registration = registrationRepository.save(registration);
        Registration registrationExisting = registrationRepository
                .findById(registration.getId()).orElseThrow(null);

        assertEquals("11122233300", registrationExisting.getCpf());
        assertEquals("Fulano de Tal", registrationExisting.getName());
        assertEquals("Paulista", registrationExisting.getCity());
        assertEquals("Pernambuco", registrationExisting.getState());
    }

    @Test
    void save_persistAndReturnRegistration_whenCalledWithSuccess() {
        Registration registration = Registration.builder()
                .name("Beltrano de Tal").cpf("11122233311")
                .state("Paraíba").city("João Pessoa")
                .build();
        Registration registrationSaved = registrationRepository.save(registration);

        assertNotNull(registrationSaved.getId());
    }

    @Test
    void delete_deleteRegistration_whenCalledWithSuccess() {
        registration = registrationRepository.save(registration);

        registrationRepository.delete(registration);

        assertEquals(registrationRepository.findById(registration.getId()), Optional.empty());
    }

}