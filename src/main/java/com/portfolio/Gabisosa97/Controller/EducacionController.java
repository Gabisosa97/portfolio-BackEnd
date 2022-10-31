package com.portfolio.Gabisosa97.Controller;

import com.portfolio.Gabisosa97.DTO.EducacionDTO;
import com.portfolio.Gabisosa97.Entity.Educacion;
import com.portfolio.Gabisosa97.Security.Controller.Mensaje;
import com.portfolio.Gabisosa97.Service.EducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = {"https://proyecto-integrador-2022-8620b.web.app", "http://localhost:4200"})
public class EducacionController {

    @Autowired
    EducacionService eduService;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = eduService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!eduService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion eedueriencia = eduService.getOne(id).get();
        return new ResponseEntity(eedueriencia, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody EducacionDTO eduDTO) {
        if (StringUtils.isBlank(eduDTO.getTitulo())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (eduService.existsByTitulo(eduDTO.getTitulo())) {
            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
        }

        Educacion edu = new Educacion(eduDTO.getTitulo(), eduDTO.getDescripcion());
        eduService.save(edu);

        return new ResponseEntity(new Mensaje("Titulo agregado"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducacionDTO eduDTO) {
        if (!eduService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
//        if (!eduService.existsByTitulo(eduDTO.getTitulo()) && eduService.getByTitulo(eduDTO.getTitulo()).get().getId() != id) {
//            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
//        }
        if (StringUtils.isBlank(eduDTO.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Educacion edu = eduService.getOne(id).get();
        edu.setTitulo(eduDTO.getTitulo());
        edu.setDescripcion(eduDTO.getDescripcion());

        eduService.save(edu);

        return new ResponseEntity(new Mensaje("Titulo actualizado"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!eduService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        eduService.delete(id);

        return new ResponseEntity(new Mensaje("Titulo eliminado"), HttpStatus.OK);
    }
}
