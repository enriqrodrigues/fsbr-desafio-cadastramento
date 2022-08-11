package com.fsbr.desafio.controller;

import com.fsbr.desafio.model.response.StateResponse;
import com.fsbr.desafio.service.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
@Api(tags = "Estados")
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping(value = "", produces = "application/json")
    @ApiOperation("Consulta Estados")
    public ResponseEntity<List<StateResponse>> findAll() {
        List<StateResponse> stateResponses = stateService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(stateResponses);
    }

}
