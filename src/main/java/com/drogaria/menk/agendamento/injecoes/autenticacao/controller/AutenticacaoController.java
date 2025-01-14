package com.drogaria.menk.agendamento.injecoes.autenticacao.controller;

import com.drogaria.menk.agendamento.injecoes.autenticacao.dto.request.CredenciaisDTO;
import com.drogaria.menk.agendamento.injecoes.autenticacao.dto.request.RequestRefreshToken;
import com.drogaria.menk.agendamento.injecoes.autenticacao.dto.response.LoginResponseDTO;
import com.drogaria.menk.agendamento.injecoes.autenticacao.infra.service.TokenService;
import com.drogaria.menk.agendamento.injecoes.autenticacao.model.RetornoServicoBase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid CredenciaisDTO credenciaisDTO) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(credenciaisDTO.login(), credenciaisDTO.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            System.out.println(auth.toString());
            var token = tokenService.obterToken(credenciaisDTO);

            return ResponseEntity.ok(new LoginResponseDTO(token.token(), token.refreshToken()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(RetornoServicoBase.negativo("Usuario não registrado."));
        }
    }
    @PostMapping("/refreshToken")
    public ResponseEntity login(@RequestBody @Valid RequestRefreshToken refreshToken) {
        try {
            LoginResponseDTO token;
            token = this.tokenService.obterRefresfToken(refreshToken.regreshToken());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            throw new RuntimeException("Erro na autenticação");
        }
    }

}
