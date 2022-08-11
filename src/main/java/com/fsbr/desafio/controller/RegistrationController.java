package com.fsbr.desafio.controller;

import com.fsbr.desafio.model.request.RegistrationRequest;
import com.fsbr.desafio.model.response.RegistrationResponse;
import com.fsbr.desafio.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cadastramento")
@Api(tags = "Cadastramento")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping(value = "", produces = "application/json")
    @ApiOperation("Consultar Registros")
    public ResponseEntity<List<RegistrationResponse>> findAll() {
        List<RegistrationResponse> responses = registrationService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping(value = "/{cpf}", produces = "application/json")
    @ApiOperation("Consultar Registro Por CPF")
    public ResponseEntity<RegistrationResponse> findByCPF(@PathVariable String cpf) {
        RegistrationResponse response = registrationService.findbyCPF(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    @ApiOperation("Incluir Registro")
    public ResponseEntity<Map<String, String>> save(@Valid @RequestBody RegistrationRequest request) {
        Map<String, String> message = registrationService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    @ApiOperation("Alterar Registro")
    public ResponseEntity<Map<String, String>> update(@PathVariable Long id,
                                                      @Valid @RequestBody RegistrationRequest request) {
        Map<String, String> message = registrationService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Excluir Registro")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        Map<String, String> message = registrationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
