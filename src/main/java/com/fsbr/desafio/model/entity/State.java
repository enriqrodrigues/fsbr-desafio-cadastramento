package com.fsbr.desafio.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder()
@Entity(name = "estados")
@NoArgsConstructor
@AllArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado", nullable = false)
    private String name;

}
