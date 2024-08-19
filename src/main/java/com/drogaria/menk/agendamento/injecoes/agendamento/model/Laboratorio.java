package com.drogaria.menk.agendamento.injecoes.agendamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_LABORATORIO", schema = "sc_drogaria_menk")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laboratorio")
    private Long id;

    @Column(name = "nome_laboratorio")
    private String nome;

    @Column(name = "cnpj_laboratorio")
    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "laboratorio")
    private List<Medicamento> medicamentos;

}
