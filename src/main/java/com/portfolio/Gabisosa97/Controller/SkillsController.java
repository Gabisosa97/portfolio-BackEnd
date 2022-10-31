package com.portfolio.Gabisosa97.Controller;

import com.portfolio.Gabisosa97.DTO.SkillsDTO;
import com.portfolio.Gabisosa97.Entity.Skills;
import com.portfolio.Gabisosa97.Security.Controller.Mensaje;
import com.portfolio.Gabisosa97.Security.Service.SkillsService;
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
@RequestMapping("/skills")
@CrossOrigin(origins = {"https://proyecto-integrador-2022-8620b.web.app", "http://localhost:4200"})
public class SkillsController {
    @Autowired
    SkillsService SkillsService;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = SkillsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!SkillsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Skills experiencia = SkillsService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody SkillsDTO SkillsDTO) {
        if (StringUtils.isBlank(SkillsDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (SkillsService.existsByNombre(SkillsDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
        }

        Skills xp = new Skills(SkillsDTO.getNombre(), SkillsDTO.getPorcentaje());
        SkillsService.save(xp);

        return new ResponseEntity(new Mensaje("Titulo agregado"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody SkillsDTO SkillsDTO) {
        if (!SkillsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
//        if (!SkillsService.existsByTitulo(SkillsDTO.getTitulo()) && SkillsService.getByTitulo(SkillsDTO.getTitulo()).get().getId() != id) {
//            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
//        }
        if (StringUtils.isBlank(SkillsDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Skills xp = SkillsService.getOne(id).get();

        xp.setNombre(SkillsDTO.getNombre());
        xp.setPorcentaje(SkillsDTO.getPorcentaje());

        SkillsService.save(xp);

        return new ResponseEntity(new Mensaje("Skills actualizado"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!SkillsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        SkillsService.delete(id);

        return new ResponseEntity(new Mensaje("Titulo eliminado"), HttpStatus.OK);
    }
}
