package com.drogaria.menk.agendamento.injecoes.agendamento.infra.services;

import com.drogaria.menk.agendamento.injecoes.Util.Utils.UtilPaginacao;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.ClienteFiltroRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.ClienteRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.infra.repository.ClienteRepository;
import com.drogaria.menk.agendamento.injecoes.agendamento.model.Cliente;
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
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarNovoCLiente(ClienteRequest clienteRequest) {
        var clienteSalvo = clienteRepository.save(clienteRequest.getCliente());
        log.info("[INFO] - Cliente salvo com sucesso.");
        return clienteSalvo;
    }

    public PageImpl<?> buscarComFiltro(ClienteFiltroRequest clienteFiltroRequest, PageRequest page) {
        log.info("[START] - Buscando clientes com filtro.");
        var clientes = this.getClientes(clienteFiltroRequest);
        return UtilPaginacao.obterPaginacao(clientes, page);
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("[ERRO] - Cliente não foi encontrado com id {}", id);
                    return new RuntimeException("Cliente não encontrado");
                });
    }

    private List<Cliente> getClientes(ClienteFiltroRequest clienteFiltroRequest) {
        log.info("[INFO] - Buscando clientes registrado.");
        List<Cliente> clientes =  new ArrayList<>();
        if (clienteFiltroRequest.getId() != null && clienteFiltroRequest.getId() != 0){
            log.info("[INFO] - Buscando clientes registrado pelo ID {}.", clienteFiltroRequest.getId());
            clientes.add(this.buscarClientePorId(clienteFiltroRequest.getId()));
        } else {
            log.info("[INFO] - Buscando cliente registrado pelo filtro NOME {}, EMAIL {} e TELEFONE {}.",
                    clienteFiltroRequest.getNome(),
                    clienteFiltroRequest.getEmail(),
                    clienteFiltroRequest.getTelefone());
            clientes.addAll(clienteRepository.findByAllFilters(clienteFiltroRequest.getNome(),
                    clienteFiltroRequest.getEmail(),
                    clienteFiltroRequest.getTelefone()));
        }

        return clientes;
    }

    public RetornoServicoBase deletarClientePorId(Long id) {
        try {
            clienteRepository.delete(buscarClientePorId(id));
            return RetornoServicoBase.positivo("Cliente do id "+id+" Deletado com sucesso.");
        } catch (RuntimeException r) {
            log.info("[ERRO] - Erro ao deletar este cliente de id {}", id);
            return RetornoServicoBase.negativo("Não foi possivel deletar este cliente pois ele não existe");
        }catch (Exception e) {
            log.info("[ERRO] - Erro ao deletar este cliente de id {}", id);
            return RetornoServicoBase.negativo("Não foi possivel deletar este cliente");
        }
    }
}
