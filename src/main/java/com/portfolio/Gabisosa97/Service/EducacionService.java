package com.portfolio.Gabisosa97.Service;

import com.portfolio.Gabisosa97.Entity.Educacion;
import com.portfolio.Gabisosa97.Repository.IEducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EducacionService {

    @Autowired
    IEducacionRepository iexperienciaRepository;

    public List<Educacion> list() {
        return iexperienciaRepository.findAll();
    }

    public Optional<Educacion> getOne(int id) {
        return iexperienciaRepository.findById(id);
    }

    public Optional<Educacion> getByTitulo(String titulo) {
        return iexperienciaRepository.findByTitulo(titulo);
    }

    public void save(Educacion edu) {
        iexperienciaRepository.save(edu);
    }

    public void delete(int id) {
        iexperienciaRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return iexperienciaRepository.existsById(id);
    }

    public boolean existsByTitulo(String titulo) {
        return iexperienciaRepository.existsByTitulo(titulo);
    }

}
