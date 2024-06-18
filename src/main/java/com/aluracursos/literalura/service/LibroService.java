package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LibroService {
    @Autowired
    private LibroRepository repository;

    public Optional<Libro> getLibroByTitulo(String titulo){
        return repository.findByTitulo(titulo);
    }
}
