package com.drogaria.menk.agendamento.injecoes.agendamento.infra.repository;

import com.drogaria.menk.agendamento.injecoes.agendamento.model.HistoricoAplicacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoAplicacoesRepository extends JpaRepository<HistoricoAplicacoes, Long> {
}
