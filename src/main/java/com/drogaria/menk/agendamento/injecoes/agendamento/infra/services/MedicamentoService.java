package com.drogaria.menk.agendamento.injecoes.agendamento.infra.services;

import com.drogaria.menk.agendamento.injecoes.Util.Utils.UtilPaginacao;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.MedicamentoFiltroRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.MedicamentoRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.infra.repository.MedicamentoRepository;
import com.drogaria.menk.agendamento.injecoes.agendamento.model.Medicamento;
import com.drogaria.menk.agendamento.injecoes.autenticacao.model.RetornoServicoBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private LaboratorioService laboratorioService;

    public Medicamento registrarNovoMedicamento(MedicamentoRequest medicamentoRequest) {
        try {
            var medicamentoSalvo = medicamentoRepository.save(medicamentoRequest.construirMedicamento(this.laboratorioService));
            log.info("[INFO] - Medicamento foi salvo com sucesso.");
            return medicamentoSalvo;
        }catch (Exception e) {
            log.info("[ERRO] - Erro ao salvar o medicamento no banco de dados");
            throw new RuntimeException("Erro ao salvar o medicamento.");
        }
    }

    public PageImpl<?> buscarComFiltro(MedicamentoFiltroRequest medicamentoFiltroRequest,
                                       Pageable page) {
        log.info("[INFO] - Buscando os medicamentos pelo filtro de nome: {}, descricao: {}, laboratorio: {} e id: {}",
                medicamentoFiltroRequest.getNome(),
                medicamentoFiltroRequest.getDescricao(),
                medicamentoFiltroRequest.getLaboratorio(),
                medicamentoFiltroRequest.getId());
        var medicamentos = this.getMedicamentos(medicamentoFiltroRequest);
        return UtilPaginacao.obterPaginacao(medicamentos, page);
    }

    private List<Medicamento> getMedicamentos(MedicamentoFiltroRequest medicamentoFiltroRequest) {
        List<Medicamento> medicamentos = new ArrayList<>();
        if (medicamentoFiltroRequest.getId() != null || medicamentoFiltroRequest.getId() != 0) {
            log.info("[INFO] - Buscando o medicamento pelo o id {}", medicamentoFiltroRequest.getId());
            medicamentos.add(this.buscarMedicamentoPorId(medicamentoFiltroRequest.getId()));
        } else {
            log.info("[INFO] - Buscando medicamentos pelos parametros fornecidos");
            medicamentos.addAll(medicamentoRepository.findAllBynomeAndDescricaoAndLaboratorio(medicamentoFiltroRequest.getNome(),
                    medicamentoFiltroRequest.getDescricao()));

        }

        return medicamentos;
    }

    public Medicamento buscarMedicamentoPorId(Long id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("[INFO] - Medicamento com o id {} não foi encontrado.", id);
                    throw  new RuntimeException("Medicamento com o id "+id+" não foi encontrado");
                });
    }

    public RetornoServicoBase deletarMedicamento(Long id) {
        try {
            medicamentoRepository.delete(this.buscarMedicamentoPorId(id));
            return RetornoServicoBase.positivo("Medicamento id "+id+" deletado com sucesso.");
        }catch (RuntimeException e) {
            log.info("[ERRO] - Erro ao deletar medicamento do id {}", id);
            return RetornoServicoBase.negativo("Não foi possivel deletar este medicamento pois ele não existe");
        }catch (Exception e) {
            log.info("[ERRO] - Erro ao deletar medicamento do id {}", id);
            return RetornoServicoBase.negativo("Não foi possivel deletar este medicamento");
        }
    }
}
