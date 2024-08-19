package com.drogaria.menk.agendamento.injecoes.agendamento.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_PROG_AGEND_APLICACOES", schema = "sc_drogaria_menk")
public class ProgramacaoAgendaAplicacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long id;

    @Column(name = "data_aplicacoes")
    private String data;

    @ManyToOne
    private Medicamento medicamento;

    @ManyToOne
    private Cliente cliente;

    @Column(name = "status_aplicacoes")
    private EStatus status;

}
