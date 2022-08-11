package com.fsbr.desafio.repository;

import com.fsbr.desafio.model.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Registration findByCpf(String cpf);
}
