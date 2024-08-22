package com.drogaria.menk.agendamento.injecoes.agendamento.infra.repository;

import com.drogaria.menk.agendamento.injecoes.agendamento.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    @Query(value = "\n" +
            "SELECT * FROM sc_drogaria_menk.tb_medicamento\n" +
            "WHERE LOWER(tb_medicamento.nome_medicamento) LIKE LOWER(CONCAT('%', :nome, '%'))\n" +
            "AND LOWER(tb_medicamento.descricao_medicamento) LIKE LOWER(CONCAT('%', :descricao, '%'))\n" +
            "ORDER BY tb_medicamento.id_medicamento DESC", nativeQuery = true)
    List<Medicamento> findAllBynomeAndDescricaoAndLaboratorio(
                                                 @Param("nome") String nome,
                                                 @Param("descricao") String descricao);
}
