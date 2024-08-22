package com.drogaria.menk.agendamento.injecoes.agendamento.infra.repository;

import com.drogaria.menk.agendamento.injecoes.agendamento.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM sc_drogaria_menk.tb_cliente\n" +
            "WHERE LOWER(nome_cliente) LIKE LOWER(CONCAT('%', :nome, '%'))\n" +
            "AND LOWER(email_cliente) LIKE LOWER(CONCAT('%', :email, '%'))\n" +
            "AND LOWER(telefone_cliente) LIKE LOWER(CONCAT('%', :telefone, '%'))\n" +
            "ORDER BY id_cliente DESC", nativeQuery = true)
    List<Cliente> findByAllFilters(@Param("nome") String nome,
                                   @Param("email") String email,
                                   @Param("telefone") String telefone);
}
