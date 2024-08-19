package com.drogaria.menk.agendamento.injecoes.autenticacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltroUsuariosRquest {

    private String nomeUsuario;
    private String emailUsuario;
    private String idUsuario;

}
