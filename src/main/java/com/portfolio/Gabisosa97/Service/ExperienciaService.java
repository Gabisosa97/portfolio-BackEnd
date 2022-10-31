package com.portfolio.Gabisosa97.Service;

import com.portfolio.Gabisosa97.Entity.Experiencia;
import com.portfolio.Gabisosa97.Repository.IExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaService {

    @Autowired
    IExperienciaRepository iexperienciaRepository;

    public List<Experiencia> list() {
        return iexperienciaRepository.findAll();
    }

    public Optional<Experiencia> getOne(int id) {
        return iexperienciaRepository.findById(id);
    }

    public Optional<Experiencia> getByTitulo(String titulo) {
        return iexperienciaRepository.findByTitulo(titulo);
    }

    public void save(Experiencia xp) {
        iexperienciaRepository.save(xp);
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
