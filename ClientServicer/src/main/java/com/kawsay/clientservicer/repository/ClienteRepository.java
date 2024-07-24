package com.kawsay.clientservicer.repository;

import com.kawsay.clientservicer.entity.Cliente;
import com.kawsay.clientservicer.entity.Persona;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByIdentificacion(String identificacion);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cliente c WHERE c.identificacion = :cedula")
    void deleteByIdentificacion(String cedula);
}
