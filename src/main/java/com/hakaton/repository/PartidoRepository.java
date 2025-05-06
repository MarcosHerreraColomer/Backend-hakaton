package com.hakaton.repository;

import com.hakaton.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    // Obtener partidos municipales por ID de municipio
    List<Partido> findByAmbitoAndCodigoAmbito(String ambito, String codigoAmbito);

    // Obtener partidos autonómicos por ID de comunidad
    List<Partido> findByAmbitoAndCodigoAmbitoOrderByNombreAsc(String ambito, String codigoAmbito);

    // Obtener partidos nacionales (sin códigoAmbito)
    List<Partido> findByAmbito(String ambito);
}
