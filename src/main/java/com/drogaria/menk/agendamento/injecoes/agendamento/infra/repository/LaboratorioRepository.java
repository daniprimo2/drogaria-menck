package com.drogaria.menk.agendamento.injecoes.agendamento.infra.repository;

import com.drogaria.menk.agendamento.injecoes.agendamento.dto.request.LaboratorioFiltroRequest;
import com.drogaria.menk.agendamento.injecoes.agendamento.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    @Query(value = "SELECT * FROM sc_drogaria_menk.tb_laboratorio\n" +
            "WHERE LOWER(tb_laboratorio.nome_laboratorio) LIKE LOWER(CONCAT('%', :nome, '%'))\n" +
            "AND LOWER(tb_laboratorio.cnpj_laboratorio) LIKE LOWER(CONCAT('%', :cnpj, '%'))\n" +
            "ORDER BY tb_laboratorio.id_laboratorio DESC\n", nativeQuery = true)
    List<Laboratorio> findAllByNomeAndCnpjAndTelefone(@Param("nome") String nome,
                                                      @Param("cnpj") String cnpj);

}
