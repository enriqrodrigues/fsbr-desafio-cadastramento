package com.fsbr.desafio.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity(name = "cadastramento")
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "estado", nullable = false)
    private String state;

    @Column(name = "cidade", nullable = false)
    private String city;

}
