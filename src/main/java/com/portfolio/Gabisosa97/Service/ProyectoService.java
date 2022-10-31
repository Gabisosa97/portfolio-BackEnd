package com.portfolio.Gabisosa97.Service;

import com.portfolio.Gabisosa97.Entity.Proyecto;
import com.portfolio.Gabisosa97.Repository.IProyectoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    IProyectoRepository iproyectoRepository;

    public List<Proyecto> list() {
        return iproyectoRepository.findAll();
    }

    public Optional<Proyecto> getOne(int id) {
        return iproyectoRepository.findById(id);
    }

    public Optional<Proyecto> getByNombre(String nombre) {
        return iproyectoRepository.findByNombre(nombre);
    }

    public void save(Proyecto proyecto) {
        iproyectoRepository.save(proyecto);
    }

    public void delete(int id) {
        iproyectoRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return iproyectoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return iproyectoRepository.existsByNombre(nombre);
    }

}
