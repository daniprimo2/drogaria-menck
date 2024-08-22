package com.drogaria.menk.agendamento.injecoes.agendamento.controller;

import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.MedicamentoFiltroRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.MedicamentoRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.infra.services.MedicamentoService;
import com.drogaria.menk.agendamento.injecoes.autenticacao.model.RetornoServicoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarNovoMedicamento (@RequestBody MedicamentoRequest medicamentoRequest) {
        return ResponseEntity.ok(medicamentoService.registrarNovoMedicamento(medicamentoRequest));
    }

    @PostMapping("/buscar/porFiltro")
    public PageImpl<?> buscarMedicamentoComFiltro(@RequestBody MedicamentoFiltroRequest medicamentoFiltroRequest,
                                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                                  @RequestParam(value = "page", defaultValue = "1") int page){
        return medicamentoService.buscarComFiltro(medicamentoFiltroRequest, PageRequest.of(size, page));
    }

    @DeleteMapping("/deletar/{id}")
    public RetornoServicoBase deletarMedicamentoPorId(@PathVariable Long id) {
        return medicamentoService.deletarMedicamento(id);
    }
}
