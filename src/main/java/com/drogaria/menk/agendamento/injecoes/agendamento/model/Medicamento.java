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
@Table(name = "TB_MEDICAMENTO", schema = "sc_drogaria_menk")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Long id;

    @Column(name = "nome_medicamento", nullable = false)
    private String nome;

    @Column(name = "descricao_medicamento")
    private String descricao;

    @Column(name = "is_medic_ciclo", nullable = false)
    private Boolean isCiclo;

    @Column(name = "intervalo_de_aplicacoes")
    private int intervaloAplicacoes;

    @ManyToOne
    private Laboratorio laboratorio;

    @OneToMany(mappedBy = "medicamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoricoAplicacoes> historicos;

    private Double custoMedicamento;

    private Double precoMedicamento;

}
