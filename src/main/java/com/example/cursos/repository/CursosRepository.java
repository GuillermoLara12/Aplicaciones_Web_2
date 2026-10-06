package com.example.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cursos.model.Cursos;

public interface CursosRepository extends JpaRepository<Cursos, Long> {

}

