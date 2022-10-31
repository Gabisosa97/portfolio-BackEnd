package com.portfolio.Gabisosa97.Repository;

import com.portfolio.Gabisosa97.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaRepository extends JpaRepository<Experiencia, Integer> {

    public Optional<Experiencia> findByTitulo(String titulo);

    public boolean existsByTitulo(String titulo);

}
