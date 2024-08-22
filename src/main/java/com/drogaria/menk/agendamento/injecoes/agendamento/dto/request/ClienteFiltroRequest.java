package com.drogaria.menk.agendamento.injecoes.agendamento.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltroRequest {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

}
