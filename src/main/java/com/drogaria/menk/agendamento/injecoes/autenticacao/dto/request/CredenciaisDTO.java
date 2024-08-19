package com.drogaria.menk.agendamento.injecoes.autenticacao.dto.request;

import lombok.Builder;

@Builder
public record CredenciaisDTO(String login, String password) {

}
