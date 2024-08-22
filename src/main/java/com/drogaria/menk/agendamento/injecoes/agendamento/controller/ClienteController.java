package com.drogaria.menk.agendamento.injecoes.agendamento.controller;

import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.ClienteFiltroRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.ClienteRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.infra.services.ClienteService;
import com.drogaria.menk.agendamento.injecoes.autenticacao.model.RetornoServicoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registrar")
    public ResponseEntity<?> cadastrarNovoCliente (@RequestBody ClienteRequest clienteRequest){
        return ResponseEntity.ok(clienteService.cadastrarNovoCLiente(clienteRequest));
    }

    @PostMapping("/buscar/comFiltro")
    public PageImpl<?> buscarClienteComFiltro(@RequestBody ClienteFiltroRequest clienteFiltroRequest,
                                              @RequestParam(value = "size", defaultValue = "10") int size,
                                              @RequestParam(value = "page", defaultValue = "1") int page){
        return clienteService.buscarComFiltro(clienteFiltroRequest, PageRequest.of(page, size));
    }

    @DeleteMapping("/deletar/{id}")
    public RetornoServicoBase deletarClientePorId(@PathVariable Long id){
     return clienteService.deletarClientePorId(id);
    }

}
