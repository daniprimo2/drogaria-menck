package com.drogaria.menk.agendamento.injecoes.autenticacao.dto.request;

import com.drogaria.menk.agendamento.injecoes.autenticacao.model.Usuario;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    private String nome;
    private String email;
    private String cargo;

    public Usuario construirUsuario() {
        return Usuario.builder()
                .nome(nome)
                .email(email)
                .cargo(cargo)
                .status(true)
                .build();
    }
}
