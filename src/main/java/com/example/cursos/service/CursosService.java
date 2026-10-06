package com.example.cursos.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cursos.model.Cursos;
import com.example.cursos.repository.CursosRepository;


@Service
public class CursosService {

    private final CursosRepository repo;

    public CursosService(CursosRepository repo) {
        this.repo = repo;
    }
    
    public List<Cursos> getAll() {
        return repo.findAll();
    }

    public Optional<Cursos> findById(Long id) {
        return repo.findById(id);
    }

    public Cursos save(Cursos cursos) {
        return repo.save(cursos);
    }

    public Optional<Cursos> update(Long id, Cursos cursos) {

        Optional<Cursos> optional = repo.findById(id);

        if (optional.isEmpty()) {
            return Optional.empty();
        }

        Cursos cursosDb = optional.get();

        cursosDb.setNombre(cursos.getNombre());
        cursosDb.setInstructor(cursos.getInstructor());
        cursosDb.setPrecio(cursos.getPrecio());
        cursosDb.setDuracion(cursos.getDuracion());

        return Optional.of(repo.save(cursosDb));
    }

    public boolean delete(Long id) {

        if (!repo.existsById(id)) {
            return false;
        }

        repo.deleteById(id);

        return true;
    }
}
