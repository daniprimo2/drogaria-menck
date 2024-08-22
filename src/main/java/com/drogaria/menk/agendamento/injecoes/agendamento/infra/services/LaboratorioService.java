package com.drogaria.menk.agendamento.injecoes.agendamento.infra.services;

import com.drogaria.menk.agendamento.injecoes.Util.Utils.UtilPaginacao;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.LaboratorioFiltroRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.LaboratorioRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.infra.repository.LaboratorioRepository;
import com.drogaria.menk.agendamento.injecoes.agendamento.model.Laboratorio;
import com.drogaria.menk.agendamento.injecoes.autenticacao.model.RetornoServicoBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;


    public Laboratorio cadastrarNovoLaboratorio(LaboratorioRequest laboratorioRequest) {
        try {
            var laboratorioSalvo = laboratorioRepository.save(laboratorioRequest.construirLaboratorio());
            log.info("[INFO] - Laboratorio {} salvo com sucesso.", laboratorioSalvo.getNome());
            return laboratorioSalvo;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("[ERRO] - Erro ao salvar o laboratorio {}", laboratorioRequest.getNome());
            throw new RuntimeException("Erro ao salvar um novo laboratorio.");
        }
    }

    public PageImpl<?> buscarComFiltro(LaboratorioFiltroRequest laboratorioFiltroRequest,
                                       PageRequest page) {
        log.info("[START] - Buscando os laboratorio com os filtros nome: {}, cnpj: {}, id: {}.",
                laboratorioFiltroRequest.getNome(),
                laboratorioFiltroRequest.getCnpj(),
                laboratorioFiltroRequest.getId());
        var laboratorios = this.getLaboratorios(laboratorioFiltroRequest);
        return UtilPaginacao.obterPaginacao(laboratorios, page);
    }

    public Laboratorio buscarLaboratorioPorId(Long id) {
        return laboratorioRepository.findById(id)
                .orElseThrow(() -> {
                   log.info("[INFO] - Laboratorio com o id {} não foi encontrado.", id);
                   return new RuntimeException("Laboratorio com o id "+id+" não foi encontrado.");
                });
    }

    private List<Laboratorio> getLaboratorios(LaboratorioFiltroRequest laboratorioFiltroRequest) {
        List<Laboratorio> laboratorios = new ArrayList<>();
        if (laboratorioFiltroRequest.getId() != null || laboratorioFiltroRequest.getId() != 0) {
            log.info("[INFO] - Buscando o laboratorio pelo o id {}", laboratorioFiltroRequest.getId());
            laboratorios.add(this.buscarLaboratorioPorId(laboratorioFiltroRequest.getId()));
        } else {
            log.info("[INFO] - Buscando laboratorios pelos parametros fornecidos");
            laboratorios.addAll(laboratorioRepository.findAllByNomeAndCnpjAndTelefone(laboratorioFiltroRequest.getNome(),
                    laboratorioFiltroRequest.getCnpj()));
        }

        return laboratorios;
    }

    public RetornoServicoBase deletarLaboratorioPeloId(Long id) {
        try {
            laboratorioRepository.delete(this.buscarLaboratorioPorId(id));
            return RetornoServicoBase.positivo("Laboratorio id "+id+" deletado com sucesso.");
        }catch (RuntimeException e) {
            log.info("[ERRO] - Erro ao deletar laboratorio do id {}", id);
            return RetornoServicoBase.negativo("Não foi possivel deletar este laboratorio pois ele não existe");
        }catch (Exception e) {
            log.info("[ERRO] - Erro ao deletar laboratorio do id {}", id);
            return RetornoServicoBase.negativo("Não foi possivel deletar este laboratorio");
        }
    }
}
