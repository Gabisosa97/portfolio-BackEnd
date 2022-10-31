package com.portfolio.Gabisosa97.Controller;

import com.portfolio.Gabisosa97.DTO.ProyectoDTO;
import com.portfolio.Gabisosa97.Entity.Proyecto;
import com.portfolio.Gabisosa97.Security.Controller.Mensaje;
import com.portfolio.Gabisosa97.Service.ProyectoService;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = {"https://proyecto-integrador-2022-8620b.web.app", "http://localhost:4200"})
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyecto eproyectoeriencia = proyectoService.getOne(id).get();
        return new ResponseEntity(eproyectoeriencia, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ProyectoDTO proyectoDTO) {
        if (StringUtils.isBlank(proyectoDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (proyectoService.existsByNombre(proyectoDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = new Proyecto(proyectoDTO.getNombre(), proyectoDTO.getDescripcion(), proyectoDTO.getImg(), proyectoDTO.getLink());
        proyectoService.save(proyecto);

        return new ResponseEntity(new Mensaje("Nombre agregado"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProyectoDTO proyectoDTO) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
//        if (!proyectoService.existsByNombre(proyectoDTO.getNombre()) && proyectoService.getByNombre(proyectoDTO.getNombre()).get().getId() != id) {
//            return new ResponseEntity(new Mensaje("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
//        }
        if (StringUtils.isBlank(proyectoDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = proyectoService.getOne(id).get();

        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setDescripcion(proyectoDTO.getDescripcion());
        proyecto.setImg(proyectoDTO.getImg());
        proyecto.setLink(proyectoDTO.getLink());

        proyectoService.save(proyecto);

        return new ResponseEntity(new Mensaje("Nombre actualizado"), HttpStatus.OK);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        proyectoService.delete(id);

        return new ResponseEntity(new Mensaje("Nombre eliminado"), HttpStatus.OK);
    }
}
