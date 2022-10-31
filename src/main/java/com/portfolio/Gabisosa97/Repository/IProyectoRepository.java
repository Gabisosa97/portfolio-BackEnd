package com.portfolio.Gabisosa97.Repository;

import com.portfolio.Gabisosa97.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProyectoRepository extends JpaRepository<Proyecto, Integer> {

    public Optional<Proyecto> findByNombre(String nombre);

    public boolean existsByNombre(String nombre);
}
