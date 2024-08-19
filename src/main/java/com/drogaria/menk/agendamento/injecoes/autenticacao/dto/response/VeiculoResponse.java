package com.drogaria.menk.agendamento.injecoes.autenticacao.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoResponse {

    private String placa;
    private String modelo;
    private String ano;
    private TipoResponse  tipo;
    private CategoriaResponse categoria;

}
