package com.hakaton.repository;

import com.hakaton.model.Eleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EleccionRepository extends JpaRepository<Eleccion, Long> {

    @Query("SELECT e FROM Eleccion e WHERE e.fecha_inicio <= :now AND e.fecha_fin >= :now AND e.estado = 'abierta' ORDER BY e.id DESC LIMIT 1")
    Optional<Eleccion> findCurrentElection(LocalDateTime now);

    // ✅ Método necesario para el controlador por tipo y estado
    List<Eleccion> findByTipoAndEstado(String tipo, String estado);
}
