package com.drogaria.menk.agendamento.injecoes.agendamento.dto.request;

import com.drogaria.menk.agendamento.injecoes.agendamento.infra.services.LaboratorioService;
import com.drogaria.menk.agendamento.injecoes.agendamento.model.Medicamento;

public class MedicamentoRequest {
    private String nome;
    private String descricao;
    private Double precoCusto;
    private Double precoVenda;
    private Integer intervaloEntreAsAplicacoes;
    private Boolean isCiclo;
    private Long laboratorioId;

    public Medicamento construirMedicamento(LaboratorioService laboratorioService) {
        return Medicamento.builder()
                .nome(this.nome)
                .descricao(this.descricao)
                .custoMedicamento(this.precoCusto)
                .precoMedicamento(this.precoVenda)
                .intervaloAplicacoes(this.intervaloEntreAsAplicacoes)
                .isCiclo(this.isCiclo)
                .laboratorio(laboratorioService.buscarLaboratorioPorId(this.laboratorioId))
                .build();
    }
}
