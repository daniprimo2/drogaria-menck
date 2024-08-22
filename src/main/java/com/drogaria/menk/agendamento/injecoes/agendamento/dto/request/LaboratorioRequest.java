package com.drogaria.menk.agendamento.injecoes.agendamento.dto.request;

import com.drogaria.menk.agendamento.injecoes.agendamento.model.Laboratorio;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LaboratorioRequest {

    private String nome;
    private String cnpj;

    public Laboratorio construirLaboratorio() {
        return Laboratorio.builder()
                .nome(this.nome)
                .cnpj(this.cnpj)
                .build();
    }
}
