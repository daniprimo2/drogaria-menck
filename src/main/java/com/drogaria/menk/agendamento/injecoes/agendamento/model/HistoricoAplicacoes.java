package com.drogaria.menk.agendamento.injecoes.agendamento.model;


import com.drogaria.menk.agendamento.injecoes.autenticacao.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_HISTORICO_DE_APLICACOES", schema = "sc_drogaria_menk")
public class HistoricoAplicacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hist_aplicacoes")
    private Long id;

    @Column(name = "data_de_aplicacoes", nullable = false)
    private String data;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Medicamento medicamento;

    @ManyToOne
    private Cliente cliente;

    @Column(name = "observacoes", nullable = false)
    private String observacoes;

}
