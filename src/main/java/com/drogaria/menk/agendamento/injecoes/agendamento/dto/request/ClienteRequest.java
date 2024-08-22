package com.drogaria.menk.agendamento.injecoes.agendamento.dto.request;

import com.drogaria.menk.agendamento.injecoes.agendamento.model.Cliente;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ClienteRequest {

    private String nome;

    private String email;

    private String telefone;


    public Cliente getCliente() {
        return Cliente.builder()
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .build();
    }
}
