package com.drogaria.menk.agendamento.injecoes.agendamento.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CLIENTE", schema = "sc_drogaria_menk")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome_cliente", nullable = false)
    private String nome;

    @Email
    @Column(name = "email_cliente", nullable = false)
    private String email;

    @Column(name = "telefone_cliente", nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistoricoAplicacoes> historicoAplicacoes;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProgramacaoAgendaAplicacoes> programacaoAgendaAplicacoes;

}
