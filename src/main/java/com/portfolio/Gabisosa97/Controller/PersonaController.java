package com.portfolio.Gabisosa97.Controller;

import com.portfolio.Gabisosa97.DTO.PersonaDTO;
import com.portfolio.Gabisosa97.Entity.Persona;
import com.portfolio.Gabisosa97.Security.Controller.Mensaje;
import com.portfolio.Gabisosa97.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://proyecto-integrador-2022-8620b.web.app", "http://localhost:4200"})

public class PersonaController {

    @Autowired
    ImpPersonaService ImpPersonaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = ImpPersonaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!ImpPersonaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Persona epersonaeriencia = ImpPersonaService.getOne(id).get();
        return new ResponseEntity(epersonaeriencia, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PersonaDTO PersonaDTO) {
        if (!ImpPersonaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
//        if (!ImpPersonaService.Nombre(PersonaDTO.Nombre()) && ImpPersonaService.getByNombre(PersonaDTO.Nombre()).get().getId() != id) {
//            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
//        }
        if (StringUtils.isBlank(PersonaDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = ImpPersonaService.getOne(id).get();

        persona.setNombre(PersonaDTO.getNombre());
        persona.setApellido(PersonaDTO.getApellido());
        persona.setDescripcion(PersonaDTO.getDescripcion());
        persona.setImg(PersonaDTO.getImg());

        ImpPersonaService.save(persona);

        return new ResponseEntity(new Mensaje("Persona actualizado"), HttpStatus.OK);

    }
}
