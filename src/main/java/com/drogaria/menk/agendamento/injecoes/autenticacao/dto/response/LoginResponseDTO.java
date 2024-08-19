package com.drogaria.menk.agendamento.injecoes.autenticacao.dto.response;

import lombok.Builder;

@Builder
public record LoginResponseDTO(String token, String refreshToken) {
}
