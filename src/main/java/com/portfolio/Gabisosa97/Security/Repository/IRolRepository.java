package com.portfolio.Gabisosa97.Security.Repository;

import com.portfolio.Gabisosa97.Security.Entity.Rol;
import com.portfolio.Gabisosa97.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    
    
    
}
