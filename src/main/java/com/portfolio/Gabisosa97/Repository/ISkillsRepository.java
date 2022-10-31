package com.portfolio.Gabisosa97.Repository;

import com.portfolio.Gabisosa97.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISkillsRepository extends JpaRepository<Skills, Integer> {

    public Optional<Skills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
