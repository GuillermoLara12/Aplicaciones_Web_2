package com.example.cursos.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursos.model.Cursos;
import com.example.cursos.service.CursosService;



@RestController
@RequestMapping("/api/cursos")
public class CursosController {

    private final CursosService service;

    public CursosController(CursosService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cursos>> findAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cursos> getById(@PathVariable Long id) {

        Optional<Cursos> cursos = service.findById(id);

        if (cursos.isPresent()) {
            return ResponseEntity.ok(cursos.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cursos> save(@RequestBody Cursos cursos) {

        Cursos nuevoCurso = service.save(cursos);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursos> update(
            @PathVariable Long id,
            @RequestBody Cursos cursos) {

        Optional<Cursos> cursosActualizado = service.update(id, cursos);

        if (cursosActualizado.isPresent()) {
            return ResponseEntity.ok(cursosActualizado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean eliminado = service.delete(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}