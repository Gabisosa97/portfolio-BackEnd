package com.portfolio.Gabisosa97.Controller;

import com.portfolio.Gabisosa97.DTO.ExperienciaDTO;
import com.portfolio.Gabisosa97.Entity.Experiencia;
import com.portfolio.Gabisosa97.Security.Controller.Mensaje;
import com.portfolio.Gabisosa97.Service.ExperienciaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xpLaboral")
@CrossOrigin(origins = {"https://proyecto-integrador-2022-8620b.web.app", "http://localhost:4200"})
public class ExperienciaController {

    @Autowired
    ExperienciaService xpService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = xpService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if (!xpService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = xpService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ExperienciaDTO xpDTO) {
        if (StringUtils.isBlank(xpDTO.getTitulo())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (xpService.existsByTitulo(xpDTO.getTitulo())) {
            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
        }

        Experiencia xp = new Experiencia(xpDTO.getTitulo(), xpDTO.getDescripcion());
        xpService.save(xp);

        return new ResponseEntity(new Mensaje("Titulo agregado"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDTO xpDTO) {
        if (!xpService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
//        if (!xpService.existsByTitulo(xpDTO.getTitulo()) && xpService.getByTitulo(xpDTO.getTitulo()).get().getId() != id) {
//            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
//        }
        if (StringUtils.isBlank(xpDTO.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Experiencia xp = xpService.getOne(id).get();
        
        xp.setTitulo(xpDTO.getTitulo());
        xp.setDescripcion(xpDTO.getDescripcion());

        xpService.save(xp);

        return new ResponseEntity(new Mensaje("Titulo actualizado"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!xpService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        xpService.delete(id);

        return new ResponseEntity(new Mensaje("Titulo eliminado"), HttpStatus.OK);
    }
}
