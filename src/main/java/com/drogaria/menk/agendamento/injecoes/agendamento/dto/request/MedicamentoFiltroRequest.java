package com.drogaria.menk.agendamento.injecoes.agendamento.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MedicamentoFiltroRequest {

    private String nome;
    private String descricao;
    private Long laboratorio;
    private Long id;


}
