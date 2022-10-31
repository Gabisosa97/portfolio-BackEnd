package com.portfolio.Gabisosa97.Repository;

import com.portfolio.Gabisosa97.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacionRepository extends JpaRepository<Educacion, Integer>{
    public Optional<Educacion> findByTitulo(String titulo);
    public boolean existsByTitulo(String titulo);
    
}
