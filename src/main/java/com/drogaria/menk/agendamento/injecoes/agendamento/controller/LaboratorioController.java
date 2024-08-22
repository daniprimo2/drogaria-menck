package com.drogaria.menk.agendamento.injecoes.agendamento.controller;

import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.LaboratorioFiltroRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.LaboratorioRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.infra.services.LaboratorioService;
import com.drogaria.menk.agendamento.injecoes.autenticacao.model.RetornoServicoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {
    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarNovoLaboratorio(@RequestBody LaboratorioRequest laboratorioRequest) {
        return ResponseEntity.ok(laboratorioService.cadastrarNovoLaboratorio(laboratorioRequest));
    }

    @PostMapping("/buscar/comFiltro")
    public PageImpl<?> buscarComFiltro(@RequestBody LaboratorioFiltroRequest laboratorioFiltroRequest,
                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                       @RequestParam(value = "page", defaultValue = "1") int page){
        return laboratorioService.buscarComFiltro(laboratorioFiltroRequest, PageRequest.of(page, size));
    }

    @DeleteMapping("/deletar/{id}")
    public RetornoServicoBase deletarLaboratorioPorId(@PathVariable Long id){
        return laboratorioService.deletarLaboratorioPeloId(id);
    }
}
