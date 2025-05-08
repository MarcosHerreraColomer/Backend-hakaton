package com.hakaton.repository;

import com.hakaton.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    // Municipales por ID de municipio
    List<Partido> findByAmbitoAndCodigoAmbito(String ambito, String codigoAmbito);

    // Autonómicos por ID de comunidad (ordenados opcionalmente)
    List<Partido> findByAmbitoAndCodigoAmbitoOrderByNombreAsc(String ambito, String codigoAmbito);

    // Nacionales
    List<Partido> findByAmbito(String ambito);
}
