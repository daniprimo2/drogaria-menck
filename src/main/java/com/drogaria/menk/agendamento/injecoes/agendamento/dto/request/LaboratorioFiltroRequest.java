package com.drogaria.menk.agendamento.injecoes.agendamento.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LaboratorioFiltroRequest {

    private String nome;
    private String cnpj;
    private Long id;

}
