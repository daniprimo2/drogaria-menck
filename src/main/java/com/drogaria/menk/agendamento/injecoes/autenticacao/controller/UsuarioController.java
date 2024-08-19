package com.drogaria.menk.agendamento.injecoes.autenticacao.controller;

import com.drogaria.menk.agendamento.injecoes.autenticacao.dto.RetornoServiceBaseDTO;
import com.drogaria.menk.agendamento.injecoes.autenticacao.dto.request.FiltroUsuariosRquest;
import com.drogaria.menk.agendamento.injecoes.autenticacao.dto.request.UsuarioRequest;
import com.drogaria.menk.agendamento.injecoes.autenticacao.infra.service.UsuarioService;
import com.drogaria.menk.agendamento.injecoes.autenticacao.model.RetornoServicoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public RetornoServiceBaseDTO addUsuario(@RequestBody UsuarioRequest usuario) {
        return usuarioService.adicionarUsuario(usuario);
    }

    @PostMapping("/busca/filtro")
    public PageImpl<?> buscarUsuariosComFiltro(@RequestBody FiltroUsuariosRquest filtroRquest,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioService.buscarUsuarioComFiltro(filtroRquest, pageable);
    }

    @DeleteMapping("/deletar/{id}")
    public RetornoServicoBase deletarUsuariosPorId(@PathVariable Long id) {
        return usuarioService.deletarUsuarioDeId(id);
    }
}
